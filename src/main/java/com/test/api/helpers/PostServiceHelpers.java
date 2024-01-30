package com.test.api.helpers;


import static org.testng.Assert.*;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.apache.http.HttpStatus;

import com.fasterxml.jackson.core.type.TypeReference;
import com.test.api.constants.EndPoints;
import com.test.api.models.Post;
import com.test.api.utils.ConfigManager;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PostServiceHelpers {

	//fetch data from end points
	// GET ,POST, GET Single, PATCH
	
	private static final String BASE_URL = ConfigManager.getInstance().getString("baseUrl");
	private static final String PORT = ConfigManager.getInstance().getString("port");
	
	
	public PostServiceHelpers() {
		RestAssured.baseURI = BASE_URL;
		RestAssured.port = Integer.parseInt(PORT);
		RestAssured.useRelaxedHTTPSValidation();
	}
	
	public List<Post> getAllpost(){
		
		Response res = RestAssured.given().log().all()
				.contentType(ContentType.JSON)
				.get(EndPoints.GET_ALL_POSTS)
				.andReturn();
		
		Type type  = new TypeReference<List<Post>>() {}.getType();
		assertEquals(res.getStatusCode(), HttpStatus.SC_OK,"OK");
		List<Post> postList = res.as(type);
		return postList;	
	}
	
	public Response createPosts() {		
		Post ps1 = new Post();
		ps1.setId("3");
		ps1.setTitle("Title 3");
		
		Response res = RestAssured.given()
						.contentType(ContentType.JSON)
						.when()
						.body(ps1)
						.post(EndPoints.CREATE_POST)
						.andReturn();
		assertEquals(res.getStatusCode(), HttpStatus.SC_CREATED,"created");
		return res;
	}
	
	public Response createPostsUsingFile() {
		
		String jsonBody =null;
		try {
			jsonBody = generateStringFromResource("C:\\Users\\Admin\\examplePost.json");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Response res = RestAssured.given()
						.contentType(ContentType.JSON)
						.when()
						.body(jsonBody)
						.post(EndPoints.CREATE_POST)
						.andReturn();
		assertEquals(res.getStatusCode(), HttpStatus.SC_CREATED,"created");
		return res;
	}
	
	public Response patchPosts(String id) {
		
		Post ps1 = new Post();
		ps1.setTitle("Title changed 3");
		
		Response res = RestAssured.given()
						.contentType(ContentType.JSON)
						.pathParam("id", id)
						.when()
						.body(ps1)
						.patch(EndPoints.UPDATE_POST)
						.andReturn();
		assertTrue(res.getStatusCode()==HttpStatus.SC_OK);
		return res;
	}
	
	
	public Response deletePost(String id) {
		
		Response res = RestAssured.given()
						.contentType(ContentType.JSON)
						.pathParam("id", id)
						.when()
						.delete(EndPoints.DELETE_POST)
						.andReturn();
		assertTrue(res.getStatusCode()==HttpStatus.SC_OK);
		return res;
	}
	
	
	public String generateStringFromResource(String path) throws IOException {

	    return new String(Files.readAllBytes(Paths.get(path)));

	}
	
}
