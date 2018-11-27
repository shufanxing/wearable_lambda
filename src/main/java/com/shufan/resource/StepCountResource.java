package com.shufan.resource;

import javax.ws.rs.core.Response;

import com.shufan.dao.impl.StepCountDAOImpl;
import com.shufan.model.StepCount;



public class StepCountResource {
	
    public static Response postStepCount( Integer userID, Integer day, Integer timeInterval, Integer stepCount) {
        long dummyId = -1;
        StepCount entity = new StepCount(dummyId, userID, day, timeInterval, stepCount);
        
        
        return StepCountDAOImpl.postStepCount(entity);
    }
    
    public static Response getCurrent(Integer userID) {
    	

		return StepCountDAOImpl.getCurrent(userID);
    }
    
    public static Response getSingle( Integer userID,  Integer day) {
    	
		return StepCountDAOImpl.getSingle(userID, day);
    }
    
    public static Response getRange(Integer userID,  Integer startDay, Integer numDays) {
    			
		return StepCountDAOImpl.getRange(userID, startDay, numDays);
    }
    
    public static Response deleteAll() {
		return StepCountDAOImpl.deleteAll();
    }
    
}
