/*
 * Copyright (c) 2018 Livio, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following
 * disclaimer in the documentation and/or other materials provided with the
 * distribution.
 *
 * Neither the name of the Livio Inc. nor the names of its contributors
 * may be used to endorse or promote products derived from this software
 * without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */

package com.smartdevicelink.util;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.ResolveInfo;
import android.content.res.XmlResourceParser;
import android.os.Bundle;

import com.smartdevicelink.proxy.rpc.VehicleType;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Joey Grover on 2/2/18.
 */

public class SdlAppInfo {
    private static final String TAG = "SdlAppInfo";

    //FIXME we shouldn't be duplicating constants, but this currently keeps us from needing a context instance.
    private static final String SDL_ROUTER_VERSION_METADATA = "sdl_router_version";
    private static final String SDL_CUSTOM_ROUTER_METADATA = "sdl_custom_router";
    private static final String SDL_OEM_VEHICLE_TYPE_METADATA = "sdl_oem_vehicle_type";


    String packageName;
    ComponentName routerServiceComponentName;
    int routerServiceVersion = 4; //We use this as a default and assume if the number doesn't exist in meta data it is because the app hasn't updated.
    boolean isCustomRouterService = false;
    List<VehicleType> vehicleMakesList = new ArrayList<>();
    long lastUpdateTime;


    public SdlAppInfo(ResolveInfo resolveInfo, PackageInfo packageInfo, Context context) {
        if (resolveInfo.serviceInfo != null) {

            this.packageName = resolveInfo.serviceInfo.packageName;
            this.routerServiceComponentName = new ComponentName(resolveInfo.serviceInfo.packageName, resolveInfo.serviceInfo.name);

            Bundle metadata = resolveInfo.serviceInfo.metaData;
            if (metadata != null) {

                if (metadata.containsKey(SDL_ROUTER_VERSION_METADATA)) {
                    this.routerServiceVersion = metadata.getInt(SDL_ROUTER_VERSION_METADATA);
                }

                if (metadata.containsKey(SDL_CUSTOM_ROUTER_METADATA)) {
                    this.isCustomRouterService = metadata.getBoolean(SDL_CUSTOM_ROUTER_METADATA);
                }

                if (metadata.containsKey(SDL_OEM_VEHICLE_TYPE_METADATA)) {
                    XmlResourceParser parser = context.getResources().getXml(metadata.getInt(SDL_OEM_VEHICLE_TYPE_METADATA));
                    this.vehicleMakesList = deserializeVehicleMake(parser);
                }
            } else {
                DebugTool.logWarning(TAG, packageName + " has not supplied metadata with their router service!");
            }
        }

        if (packageInfo != null) {
            this.lastUpdateTime = packageInfo.lastUpdateTime;
            if (this.lastUpdateTime <= 0) {
                this.lastUpdateTime = packageInfo.firstInstallTime;
            }
        } else {
            this.lastUpdateTime = 0;
        }
    }

    public int getRouterServiceVersion() {
        return routerServiceVersion;
    }

    public boolean isCustomRouterService() {
        return isCustomRouterService;
    }


    public ComponentName getRouterServiceComponentName() {
        return routerServiceComponentName;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("-------- Sdl App Info ------\n");

        builder.append("Package Name: ");
        builder.append(packageName);

        builder.append("\nRouter Service: ");
        builder.append(this.routerServiceComponentName.getClassName());

        builder.append("\nRouter Service Version: ");
        builder.append(this.routerServiceVersion);

        builder.append("\nCustom Router Service?: ");
        builder.append(this.isCustomRouterService);

        builder.append("\nLast updated: ");
        builder.append(this.lastUpdateTime);

        builder.append("\nVehicle make list: ");
        builder.append(this.vehicleMakesList.toString());

        builder.append("\n-------- Sdl App Info End------");

        return builder.toString();
    }

    public static List<VehicleType> deserializeVehicleMake(XmlResourceParser parser) {
        List<VehicleType> vehicleMakesList = new ArrayList<VehicleType>();

        try {
            int eventType = parser.getEventType();
            VehicleType vehicleMake = null;
            String data = null;
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagname = parser.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (tagname.equalsIgnoreCase("vehicle-type")) {
                            // create a new instance of employee
                            vehicleMake = new VehicleType();
                        }
                        break;

                    case XmlPullParser.TEXT:
                        data = parser.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        if (vehicleMake == null) {
                            break;
                        }
                        if (tagname.equalsIgnoreCase("vehicle-type")) {
                            // add employee object to list
                            vehicleMakesList.add(vehicleMake);
                        } else if (tagname.equalsIgnoreCase("make")) {
                            vehicleMake.setMake(data == null ? "" : data);
                        } else if (tagname.equalsIgnoreCase("model")) {
                            vehicleMake.setModel(data);
                        } else if (tagname.equalsIgnoreCase("modelYear")) {
                            vehicleMake.setModelYear(data);
                        } else if (tagname.equalsIgnoreCase("trim")){
                            vehicleMake.setTrim(data);
                        }
                        break;
                    default:
                        break;
                }
                eventType = parser.next();
            }

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return vehicleMakesList;
    }
    /**
     * This comparator will sort a list to find the best router service to start out of the known SDL enabled apps
     */
    public static class BestRouterComparator implements Comparator<SdlAppInfo> {

        @Override
        public int compare(SdlAppInfo one, SdlAppInfo two) {
            if (one != null) {
                if (two != null) {
                    if (one.isCustomRouterService) {
                        if (two.isCustomRouterService) {
                            return 0;
                        } else {
                            return 1;
                        }
                    } else if (two.isCustomRouterService) {
                        return -1;

                    }//else, do nothing. Move to version check

                    int versionCompare = two.routerServiceVersion - one.routerServiceVersion;

                    if (versionCompare == 0) { //Versions are equal so lets use the one that has been updated most recently
                        long updateTime = two.lastUpdateTime - one.lastUpdateTime;
                        if (updateTime == 0) {
                            //This is arbitrary, but we want to ensure all lists are sorted in the same order
                            return one.routerServiceComponentName.getPackageName().compareTo(two.routerServiceComponentName.getPackageName());
                        } else {
                            return (updateTime < 0 ? -1 : 1);
                        }
                    } else {
                        return (versionCompare < 0 ? -1 : 1);
                    }

                } else {
                    return -1;
                }
            } else {
                if (two != null) {
                    return 1;
                }
            }
            return 0;
        }


    }
}
