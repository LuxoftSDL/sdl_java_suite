package com.smartdevicelink.proxy.rpc;

import java.util.Hashtable;

import com.smartdevicelink.proxy.RPCRequest;
import com.smartdevicelink.proxy.constants.Names;

/**
 * Used to set existing local file on SDL as the app's icon. Not supported on
 * first generation SDL vehicles
 * <p>
 * 
 * @since SmartDeviceLink 2.0
 */
public class SetAppIcon extends RPCRequest {

	/**
	 * Constructs a new SetAppIcon object
	 */
    public SetAppIcon() {
        super("SetAppIcon");
    }

	/**
	 * Constructs a new SetAppIcon object indicated by the Hashtable parameter
	 * <p>
	 * 
	 * @param hash
	 *            The Hashtable to use
	 */
    public SetAppIcon(Hashtable hash) {
        super(hash);
    }

	/**
	 * Sets a file reference name
	 * 
	 * @param sdlFileName
	 *            a String value representing a file reference name
	 *            <p>
	 *            <b>Notes: </b>Maxlength=500
	 */
    public void setSdlFileName(String sdlFileName) {
        if (sdlFileName != null) {
            parameters.put(Names.smartDeviceLinkFileName, sdlFileName);
        } else {
        	parameters.remove(Names.smartDeviceLinkFileName);
        }
    }

	/**
	 * Gets a file reference name
	 * @return String -a String value
	 */
    public String getSdlFileName() {
        return (String) parameters.get(Names.smartDeviceLinkFileName);
    }
}
