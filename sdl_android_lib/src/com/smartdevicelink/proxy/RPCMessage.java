package com.smartdevicelink.proxy;

import java.util.Hashtable;
import java.util.Iterator;

import com.smartdevicelink.proxy.constants.Names;

public class RPCMessage extends RPCStruct  {

	public RPCMessage(String functionName) {
		this(functionName, "request");
	}
	
	protected RPCMessage(RPCMessage rpcm) {
		this(rpcm.store);
	}
	
	protected RPCMessage(RPCStruct rpcs) {
		this("", "");
		this.parameters = rpcs.store;
	}
	
	public RPCMessage(String functionName, String messageType) {
		function = new Hashtable();
		this.messageType = messageType;
		store.put(messageType, function);
		parameters = new Hashtable();
		function.put(Names.parameters, parameters);
		function.put(Names.function_name, functionName);
	}

	public RPCMessage(Hashtable hash) {
        store = hash;
        messageType = getMessageTypeName(hash.keySet());
        function = (Hashtable) hash.get(messageType);
        parameters = (Hashtable) function.get(Names.parameters);
        if (hasKey(hash.keySet(), Names.bulkData)) {
            setBulkData((byte[]) hash.get(Names.bulkData));
        }
	}

	protected String messageType;
	protected Hashtable parameters;
	protected Hashtable function;
	
	public String getFunctionName() {
		return (String)function.get(Names.function_name);
	}
	
	protected void setFunctionName(String functionName) {
		function.put(Names.function_name, functionName);
	}

	public String getMessageType() {
		return messageType;
	}
	
	public void setParameters(String functionName, Object value) {
		if (value != null) {
			parameters.put(functionName, value);
		} else {
			parameters.remove(functionName);
		}
	}
	
	public Object getParameters(String functionName) {
		return parameters.get(functionName);
	}
}
