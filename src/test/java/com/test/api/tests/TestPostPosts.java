package com.test.api.tests;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;

import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.test.api.helpers.PostServiceHelpers;
import com.test.api.models.Post;

public class TestPostPosts {

private PostServiceHelpers postServiceHelper;
	
	@BeforeClass
	public void init(){
		postServiceHelper = new PostServiceHelpers();
	}
	
	@Test
	public void testGetPosts() {
		//String id = postServiceHelper.createPosts().jsonPath().getString("id");
		String id = postServiceHelper.createPostsUsingFile().jsonPath().getString("id");
		System.out.println(id);
		assertNotNull(id,"Post id is not empty");
		assertFalse(id.isEmpty(),"Post id is not true");
	}
	
	
}
