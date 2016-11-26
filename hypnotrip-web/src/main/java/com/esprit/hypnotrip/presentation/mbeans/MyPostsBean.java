package com.esprit.hypnotrip.presentation.mbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.esprit.hypnotrip.persistence.Posts;
import com.esprit.hypnotrip.persistence.User;
import com.esprit.hypnotrip.services.interfaces.PostServicesLocal;

@ManagedBean
@ViewScoped
public class MyPostsBean {
	@EJB
	private PostServicesLocal postServicesLocal;

	@ManagedProperty(value = "#{loginBean}")
	private LoginBean loginBean;

	private User user;

	private List<Posts> myPosts = new ArrayList<Posts>();

	@PostConstruct
	public void init() {
		user = loginBean.getUser();
		myPosts = postServicesLocal.listMyPost(user.getId());
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

}
