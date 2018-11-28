package com.shufan.resource;

import java.sql.Connection;

import javax.ws.rs.core.Response;

import com.shufan.dao.impl.StepCountDAOImpl;
import com.shufan.model.StepCount;



public class StepCountResource {
	
    public static Response postStepCount(Connection conn, Integer userID, Integer day, Integer timeInterval, Integer stepCount) {
        long dummyId = -1;
        StepCount entity = new StepCount(dummyId, userID, day, timeInterval, stepCount);
        
        
        return StepCountDAOImpl.postStepCount(conn, entity);
    }
    
    public static Response getCurrent(Connection conn, Integer userID) {
    	

		return StepCountDAOImpl.getCurrent(conn, userID);
    }
    
    public static Response getSingle(Connection conn,  Integer userID,  Integer day) {
    	
		return StepCountDAOImpl.getSingle(conn, userID, day);
    }
    
    public static Response getRange(Connection conn, Integer userID,  Integer startDay, Integer numDays) {
    			
		return StepCountDAOImpl.getRange(conn, userID, startDay, numDays);
    }
    
    public static Response deleteAll(Connection conn) {
		return StepCountDAOImpl.deleteAll(conn);
    }
    
}
