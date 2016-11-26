package com.esprit.hypnotrip.presentation.mbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.esprit.hypnotrip.persistence.Posts;
import com.esprit.hypnotrip.services.interfaces.PostServicesLocal;

@ManagedBean
@ViewScoped
public class MyPostsBean {
	@EJB
	private PostServicesLocal postServicesLocal;

	private String idOwner;
	private List<Posts> myPosts = new ArrayList<Posts>();

	@PostConstruct
	public void init() {
		idOwner = "b38f3299-6949-42c7-9a6c-f998c66f4852";
		myPosts = postServicesLocal.listMyPost(idOwner);
		for (Posts posts : myPosts) {
			System.out.println("hné mel bean " + posts.getPostId());
		}
	}

	public List<Posts> getMyPosts() {
		return myPosts;
	}

	public void setMyPosts(List<Posts> myPosts) {
		this.myPosts = myPosts;
	}

}
