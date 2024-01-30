package com.test.api.tests;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;

import java.util.Iterator;
import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.test.api.helpers.PostServiceHelpers;
import com.test.api.models.Post;

public class TestGetPosts {
	
	private PostServiceHelpers postServiceHelper;
	
	@BeforeClass
	public void init(){
		postServiceHelper = new PostServiceHelpers();
	}
	
	@Test
	public void testGetPosts() {
		List<Post> postList = postServiceHelper.getAllpost();
		System.out.println(postList.get(0).getId());
		System.out.println(postList.get(0).getTitle());
		assertNotNull(postList,"Post list is not empty");
		assertFalse(postList.isEmpty(),"Post list is not true");
		
		String createdid = postServiceHelper.createPosts().jsonPath().getString("id");
		System.out.println("Newly created ID : "+createdid);
		
		Iterator<Post> itr = postList.iterator();
		String id= "";
		while(itr.hasNext()) {
			Post p1 = itr.next();
			String title = p1.getTitle();
			if(title.equalsIgnoreCase("another title")) {
				id = p1.getId();
				System.out.println("ID : "+id);
			}
		}
		
		postServiceHelper.deletePost(id);
		
	}
	
}
