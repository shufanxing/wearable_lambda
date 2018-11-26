package com.shufan.resource;

//import javax.ws.rs.GET;
//import javax.ws.rs.POST;
//import javax.ws.rs.Path;
//import javax.ws.rs.PathParam;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import com.shufan.dao.impl.StepCountDAOImpl;
import com.shufan.model.StepCount;

//@Path("stepcount")
//public class StepCountResource {
//	
//	@Path("status")
//	@GET
//	@Produces(MediaType.TEXT_HTML)
//	public String getStatus() {
//		return "Server Status is OK...";
//	}
//	
//    @POST
//    @Path("{userID}/{day}/{timeInterval}/{stepCount}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response postStepCount(@PathParam("userID") Integer userID, @PathParam("day") Integer day, @PathParam("timeInterval") Integer timeInterval, @PathParam("stepCount") Integer stepCount) {
//        long dummyId = -1;
//        StepCount entity = new StepCount(dummyId, userID, day, timeInterval, stepCount);
//        
//        
//        return StepCountDAOImpl.postStepCount(entity);
//    }
//    
//    @GET
//    @Path("current/{userID}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getCurrent(@PathParam("userID") Integer userID) {
//    	
//
//		return StepCountDAOImpl.getCurrent(userID);
//    }
//    
//    @GET
//    @Path("single/{userID}/{day}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getSingle(@PathParam("userID") Integer userID, @PathParam("day") Integer day) {
//    	
//		return StepCountDAOImpl.getSingle(userID, day);
//    }
//    
//    @GET
//    @Path("range/{userID}/{startDay}/{numDays}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getRange(@PathParam("userID") Integer userID, @PathParam("startDay") Integer startDay, @PathParam("numDays") Integer numDays) {
//    			
//		return StepCountDAOImpl.getRange(userID, startDay, numDays);
//    }
//    
//    @POST
//    @Path("deleteAll")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response deleteAll() {
//		return StepCountDAOImpl.deleteAll();
//    }
//    
//}


public class StepCountResource {
	
//	@Path("status")
//	@GET
//	@Produces(MediaType.TEXT_HTML)
//	public String getStatus() {
//		return "Server Status is OK...";
//	}
	
//    @POST
//    @Path("{userID}/{day}/{timeInterval}/{stepCount}")
//    @Produces(MediaType.APPLICATION_JSON)
    public static Response postStepCount( Integer userID, Integer day, Integer timeInterval, Integer stepCount) {
        long dummyId = -1;
        StepCount entity = new StepCount(dummyId, userID, day, timeInterval, stepCount);
        
        
        return StepCountDAOImpl.postStepCount(entity);
    }
    
//    @GET
//    @Path("current/{userID}")
//    @Produces(MediaType.APPLICATION_JSON)
    public static Response getCurrent(Integer userID) {
    	

		return StepCountDAOImpl.getCurrent(userID);
    }
    
//    @GET
//    @Path("single/{userID}/{day}")
//    @Produces(MediaType.APPLICATION_JSON)
    public static Response getSingle( Integer userID,  Integer day) {
    	
		return StepCountDAOImpl.getSingle(userID, day);
    }
    
//    @GET
//    @Path("range/{userID}/{startDay}/{numDays}")
//    @Produces(MediaType.APPLICATION_JSON)
    public static Response getRange(Integer userID,  Integer startDay, Integer numDays) {
    			
		return StepCountDAOImpl.getRange(userID, startDay, numDays);
    }
    
//    @POST
//    @Path("deleteAll")
//    @Produces(MediaType.APPLICATION_JSON)
    public static Response deleteAll() {
		return StepCountDAOImpl.deleteAll();
    }
    
}
