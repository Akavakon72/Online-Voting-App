package com.bt.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DepartmentBean implements Serializable {
	private String departmentName;
	private List<PostBean> posts;

	public DepartmentBean() {
		this.posts = new ArrayList<PostBean>();
	}

	public DepartmentBean(String departmentName, List<PostBean> posts) {
		this.departmentName = departmentName;
		this.posts = posts;
	}

	public List<PostBean> getPosts() {
		return posts;
	}

	public void setPosts(List<PostBean> posts) {
		this.posts = posts;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public void add(PostBean post) {
		this.posts.add(post);
	}

	public PostBean get(int index) {
		return this.posts.get(index);
	}

	public PostBean get(String post) {
		PostBean postBean = null;
		for (PostBean bean : posts) {
			if (bean.getPostName().equals(post)) {
				postBean = bean;
				break;
			}
		}
		return postBean;
	}

	@Override
	public String toString() {
		return "DepartmentBean{" + "departmentName=" + departmentName
				+ ", posts=" + posts + '}';
	}
}
