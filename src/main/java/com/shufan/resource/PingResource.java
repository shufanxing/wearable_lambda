package com.shufan.resource;


import java.util.Map;
import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


@Path("/ping")
public class PingResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.WILDCARD)
    public Response createPet() {
        Map<String, String> pong = new HashMap<>();
        
        
		try {
			
			Class.forName("org.postgresql.Driver");
			
			Connection con = DriverManager.getConnection(
					"jdbc:postgresql://wdpostgredb.cm4pdwx2jgw0.us-west-2.rds.amazonaws.com:5432/root", 
					"root", 
					"12345678");
			

			String query = "select * from step_count limit 1";
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			
			if(resultSet.next()) {
				String output = "hello output: " + resultSet.getInt(1) + " " + resultSet.getInt(2) + " " + resultSet.getInt(3) + " " + resultSet.getInt(4) + " " + resultSet.getInt(5) ;
				
		        pong.put("pong", output);
		        return Response.status(200).entity(pong).build();
			    
			}
			


		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return Response.status(505).build();

	}

}