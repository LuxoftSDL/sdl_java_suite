/*
 * Copyright (c) 2017 - 2019, SmartDeviceLink Consortium, Inc.
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
 * Neither the name of the SmartDeviceLink Consortium, Inc. nor the names of its
 * contributors may be used to endorse or promote products derived from this
 * software without specific prior written permission.
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
package com.smartdevicelink.proxy.rpc;

import com.smartdevicelink.protocol.enums.FunctionID;
import com.smartdevicelink.proxy.RPCRequest;
import com.smartdevicelink.util.SdlDataTypeConverter;

import java.util.Hashtable;
import java.util.List;

/**
 * <p>This RPC is used to update the user with navigation information for the constantly shown screen (base screen), but
 * also for the alert type screen</p>
 *
 * <p>Function Group: Navigation</p>
 *
 * <p><b>HMILevel needs to be FULL, LIMITED or BACKGROUND</b></p>
 *
 * @see AlertManeuver
 * @see UpdateTurnList
 * @since SmartDeviceLink 2.0
 */
public class ShowConstantTBT extends RPCRequest {

    public static final String KEY_TEXT1 = "navigationText1";
    public static final String KEY_TEXT2 = "navigationText2";
    public static final String KEY_ETA = "eta";
    public static final String KEY_TOTAL_DISTANCE = "totalDistance";
    public static final String KEY_DISTANCE_TO_MANEUVER = "distanceToManeuver";
    public static final String KEY_DISTANCE_TO_MANEUVER_SCALE = "distanceToManeuverScale";
    public static final String KEY_MANEUVER_IMAGE = "turnIcon";
    public static final String KEY_NEXT_MANEUVER_IMAGE = "nextTurnIcon";
    public static final String KEY_MANEUVER_COMPLETE = "maneuverComplete";
    public static final String KEY_SOFT_BUTTONS = "softButtons";
    public static final String KEY_TIME_TO_DESTINATION = "timeToDestination";

    /**
     * Constructs a new ShowConstantTBT object
     */
    public ShowConstantTBT() {
        super(FunctionID.SHOW_CONSTANT_TBT.toString());
    }

    /**
     * Constructs a new ShowConstantTBT object indicated by the Hashtable parameter
     *
     * @param hash The Hashtable to use
     */
    public ShowConstantTBT(Hashtable<String, Object> hash) {
        super(hash);
    }

    /**
     * Sets a text for navigation text field 1
     *
     * @param navigationText1 a String value representing a text for navigation text field 1
     *                        <p></p>
     *                        <b>Notes: </b>Maxlength=500
     */
    public ShowConstantTBT setNavigationText1(String navigationText1) {
        setParameters(KEY_TEXT1, navigationText1);
        return this;
    }

    /**
     * Gets a text for navigation text field 1
     *
     * @return String -a String value representing a text for navigation text field 1
     */
    public String getNavigationText1() {
        return getString(KEY_TEXT1);
    }

    /**
     * Sets a text for navigation text field 2
     *
     * @param navigationText2 a String value representing a text for navigation text field 2
     *                        <p></p>
     *                        <b>Notes: </b>Maxlength=500
     */
    public ShowConstantTBT setNavigationText2(String navigationText2) {
        setParameters(KEY_TEXT2, navigationText2);
        return this;
    }

    /**
     * Gets a text for navigation text field 2
     *
     * @return String -a String value representing a text for navigation text field 2
     */
    public String getNavigationText2() {
        return getString(KEY_TEXT2);
    }

    /**
     * Sets a text field for estimated time of arrival
     *
     * @param eta a String value representing a text field for estimated time of arrival
     *            <p></p>
     *            <b>Notes: </b>Maxlength=500
     */
    public ShowConstantTBT setEta(String eta) {
        setParameters(KEY_ETA, eta);
        return this;
    }

    /**
     * Gets a text field for estimated time of arrival
     *
     * @return String -a String value representing a text field for estimated time of arrival
     */
    public String getEta() {
        return getString(KEY_ETA);
    }

    /**
     * Sets a text field for total distance
     *
     * @param totalDistance a String value representing a text field for total distance
     *                      <p></p>
     *                      <b>Notes: </b>Maxlength=500
     */
    public ShowConstantTBT setTotalDistance(String totalDistance) {
        setParameters(KEY_TOTAL_DISTANCE, totalDistance);
        return this;
    }

    /**
     * Gets a text field for total distance
     *
     * @return String -a String value representing a text field for total distance
     */
    public String getTotalDistance() {
        return getString(KEY_TOTAL_DISTANCE);
    }

    /**
     * Sets an Image for turn icon
     *
     * @param turnIcon an Image value
     */
    public ShowConstantTBT setTurnIcon(Image turnIcon) {
        setParameters(KEY_MANEUVER_IMAGE, turnIcon);
        return this;
    }

    /**
     * Gets an Image for turn icon
     *
     * @return Image -an Image value representing an Image for turnicon
     */
    public Image getTurnIcon() {
        return (Image) getObject(Image.class, KEY_MANEUVER_IMAGE);
    }

    /**
     * Sets an Image for nextTurnIcon
     *
     * @param nextTurnIcon an Image value
     */
    public ShowConstantTBT setNextTurnIcon(Image nextTurnIcon) {
        setParameters(KEY_NEXT_MANEUVER_IMAGE, nextTurnIcon);
        return this;
    }

    /**
     * Gets an Image for nextTurnIcon
     *
     * @return Image -an Image value representing an Image for nextTurnIcon
     */
    public Image getNextTurnIcon() {
        return (Image) getObject(Image.class, KEY_NEXT_MANEUVER_IMAGE);
    }

    /**
     * Sets the distanceToManeuver.
     *
     * @param distanceToManeuver Distance (in meters) until next maneuver. May be used to calculate progress bar.
     * {"num_min_value": 0.0, "num_max_value": 1000000000.0}
     * @since SmartDeviceLink 2.0.0
     */
    public ShowConstantTBT setDistanceToManeuver(Double distanceToManeuver) {
        setParameters(KEY_DISTANCE_TO_MANEUVER, distanceToManeuver);
        return this;
    }

    /**
     * Gets the distanceToManeuver.
     *
     * @return Float Distance (in meters) until next maneuver. May be used to calculate progress bar.
     * {"num_min_value": 0.0, "num_max_value": 1000000000.0}
     * @since SmartDeviceLink 2.0.0
     */
    public Double getDistanceToManeuver() {
        Object object = getParameters(KEY_DISTANCE_TO_MANEUVER);
        return SdlDataTypeConverter.objectToDouble(object);
    }

    /**
     * Sets the distanceToManeuverScale.
     *
     * @param distanceToManeuverScale Distance (in meters) from previous maneuver to next maneuver. May be used to calculate
     * progress bar.
     * {"num_min_value": 0.0, "num_max_value": 1000000000.0}
     * @since SmartDeviceLink 2.0.0
     */
    public ShowConstantTBT setDistanceToManeuverScale(Double distanceToManeuverScale) {
        setParameters(KEY_DISTANCE_TO_MANEUVER_SCALE, distanceToManeuverScale);
        return this;
    }

    /**
     * Gets the distanceToManeuverScale.
     *
     * @return Float Distance (in meters) from previous maneuver to next maneuver. May be used to calculate
     * progress bar.
     * {"num_min_value": 0.0, "num_max_value": 1000000000.0}
     * @since SmartDeviceLink 2.0.0
     */
    public Double getDistanceToManeuverScale() {
        Object object = getParameters(KEY_DISTANCE_TO_MANEUVER_SCALE);
        return SdlDataTypeConverter.objectToDouble(object);
    }

    /**
     * <p>Sets a maneuver complete flag. If and when a maneuver has completed while an AlertManeuver is active, the app
     * must send this value set to TRUE in order to clear the AlertManeuver overlay
     * If omitted the value will be assumed as FALSE</p>
     *
     * @param maneuverComplete a Boolean value
     */
    public ShowConstantTBT setManeuverComplete(Boolean maneuverComplete) {
        setParameters(KEY_MANEUVER_COMPLETE, maneuverComplete);
        return this;
    }

    /**
     * Gets a maneuver complete flag
     *
     * @return Boolean -a Boolean value
     */
    public Boolean getManeuverComplete() {
        return getBoolean(KEY_MANEUVER_COMPLETE);
    }

    /**
     * <p>Sets Three dynamic SoftButtons available (first SoftButton is fixed to "Turns"). If omitted on supported
     * displays, the currently displayed SoftButton values will not change</p>
     *
     * <p><b>Notes: </b>Minsize=0; Maxsize=3</p>
     *
     * @param softButtons a List<SoftButton> value
     */
    public ShowConstantTBT setSoftButtons(List<SoftButton> softButtons) {
        setParameters(KEY_SOFT_BUTTONS, softButtons);
        return this;
    }

    /**
     * Gets Three dynamic SoftButtons available (first SoftButton is fixed to "Turns"). If omitted on supported
     * displays, the currently displayed SoftButton values will not change
     *
     * @return Vector<SoftButton> -a Vector<SoftButton> value
     */
    @SuppressWarnings("unchecked")
    public List<SoftButton> getSoftButtons() {
        return (List<SoftButton>) getObject(SoftButton.class, KEY_SOFT_BUTTONS);
    }

    public ShowConstantTBT setTimeToDestination(String timeToDestination) {
        setParameters(KEY_TIME_TO_DESTINATION, timeToDestination);
        return this;
    }

    public String getTimeToDestination() {
        return getString(KEY_TIME_TO_DESTINATION);
    }
}
