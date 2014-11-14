package com.martinsweft.domain.user;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;


public enum Status {
	
	REQUESTED("requested"),
	ACCEPTED("accepted"),
	PENDING_RESPONSE("awaiting response"), 
	SUGGESTED("suggested");
	
	private Status(String status) {
		this.status = status;
    }	
	/**
	 * value variable
	 */
	private final String status;
	/**
	 * @return the value
	 */
	public String getValue() {
		return status;
	}
	
	private static Map<String, Status> statuss = new HashMap<String, Status>();
    static {
        for (Status r : EnumSet.allOf(Status.class)) {
        	statuss.put(r.toString(), r);
        }
    }	
    
    public static Status getStatus(String status) {
        return statuss.get(status);
    }
    @Override
    public String toString() {
        return status;
    }

} 
