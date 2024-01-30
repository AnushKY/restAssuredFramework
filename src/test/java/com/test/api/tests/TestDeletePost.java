package com.test.api.tests;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.test.api.helpers.PostServiceHelpers;

public class TestDeletePost {
	
private PostServiceHelpers postServiceHelper;
	
	@BeforeClass
	public void init(){
		postServiceHelper = new PostServiceHelpers();
	}
	
	@Test
	public void testDeletePosts() {
		postServiceHelper.deletePost("3");
	}
}
