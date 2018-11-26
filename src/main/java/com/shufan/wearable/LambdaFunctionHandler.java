package com.shufan.wearable;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.Writer;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Response;

import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.shufan.resource.StepCountResource;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;



public class LambdaFunctionHandler implements RequestStreamHandler {
	JSONParser parser = new JSONParser();

	@Override
	public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
		LambdaLogger logger = context.getLogger();
		logger.log("Loading Java Lambda handler of ProxyWithStream");

		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		
		JSONObject responseJson = new JSONObject();
		
		Response output = null;
		
		try {
			String httpMethod = null;
			String proxy = null;
			JSONObject event = (JSONObject) parser.parse(reader);
			
			logger.log("reader: " + event.toJSONString());
			
			if(event.get("httpMethod") != null) {
				httpMethod = (String) event.get("httpMethod");
			}
			
			if (event.get("pathParameters") != null) {
				JSONObject pps = (JSONObject) event.get("pathParameters");
				if (pps.get("proxy") != null) {
					proxy = (String) pps.get("proxy");
				}
			}
			
			String[] params = null;
			
			switch(httpMethod) {
			case "POST":
				if (event.get("pathParameters") != null) {
					JSONObject pps = (JSONObject) event.get("pathParameters");
					if (pps.get("proxy") != null) {
						proxy = (String) pps.get("proxy");
					}else {
						throw new Exception("empty POST proxy");
					}
				}
				
				params = proxy.split("\\/");
				if(params.length==4) {
//					{userID}/{day}/{timeInterval}/{stepCount}
					int userID = Integer.valueOf(params[0]);
					int day = Integer.valueOf(params[1]);
					int timeInterval = Integer.valueOf(params[2]);
					int stepCount = Integer.valueOf(params[3]);
					
					output = StepCountResource.postStepCount(userID, day, timeInterval, stepCount);
					
				}else {
					throw new Exception("wrong POST pathParameters: " + proxy);
				}
				
				break;
			case "GET":
				if (event.get("pathParameters") != null) {
					JSONObject pps = (JSONObject) event.get("pathParameters");
					if (pps.get("proxy") != null) {
						proxy = (String) pps.get("proxy");
					}else {
						throw new Exception("empty POST proxy");
					}
				}
				
				params = proxy.split("\\/");
				
				switch(params[0]) {
				case "current":
					//"current/{userID}"
					
					break;
				case "single":
					//("single/{userID}/{day}")
					break;
				case "range":
					//"range/{userID}/{startDay}/{numDays}"
					break;
				case "deleteAll":
					break;
				default:
					throw new Exception("unsupport GET pathParameter: " + proxy);
				}
				
				break;
			default:
				throw new Exception("unsupported http method: " + httpMethod);
			}
			
		} catch (Exception e) {
			responseJson.put("statusCode", "400");
			responseJson.put("exception", e);
		}
		
		JSONObject responseBody = new JSONObject();
		responseBody.put("message",  output);

		responseJson.put("isBase64Encoded", false);
		responseJson.put("statusCode", "200");
		responseJson.put("body", responseBody.toString());
		
		
			
		OutputStreamWriter writer = new OutputStreamWriter(outputStream, "UTF-8");
		writer.write(responseJson.toJSONString());
		writer.close();	
			
			
			
	}			
			
			
			
//			if (event.get("queryStringParameters") != null) {
//				JSONObject qps = (JSONObject) event.get("queryStringParameters");
//				if (qps.get("param1") != null) {
//					param1 = (String) qps.get("param1");
//				}
//			}
//			if (event.get("queryStringParameters") != null) {
//				JSONObject qps = (JSONObject) event.get("queryStringParameters");
//				if (qps.get("param2") != null) {
//					param2 = (String) qps.get("param2");
//				}
//			}
//		} catch (Exception pex) {
//			responseJson.put("statusCode", "400");
//			responseJson.put("exception", pex);
//		}
//		// Implement your logic here
//		int output = 0;
//		if (proxy.equals("sum")) {
//			output = sum(Integer.parseInt(param1), Integer.parseInt(param2));
//		} else if (proxy.equals("subtract")) {
//			output = subtract(Integer.parseInt(param1), Integer.parseInt(param2));
//		}
	
//		JSONObject responseBody = new JSONObject();
//		responseBody.put("input", event.toJSONString());
//		responseBody.put("message", "Output is" + output);
//		JSONObject headerJson = new JSONObject();
//		headerJson.put("x-custom-header", "my custom header value");
//		headerJson.put("Access-Control-Allow-Origin", "*");
//		responseJson.put("isBase64Encoded", false);
//		responseJson.put("statusCode", responseCode);
//		responseJson.put("headers", headerJson);
//		responseJson.put("body", responseBody.toString());
	
//		OutputStreamWriter writer = new OutputStreamWriter(outputStream, "UTF-8");
//		writer.write(responseJson.toJSONString());
//		writer.close();
//	}
//
//	public int sum(int a, int b) {
//		return a + b;
//	}
//
//	public int subtract(int a, int b) {
//		return a - b;
//	}
}