/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;

import java.util.Objects;

/**
 *
 * @author Development-21
 */
public class ResultBean {

    String dpt, post, name;
    Integer vote;

    public String getDpt() {
        return dpt;
    }

    public void setDpt(String dpt) {
        this.dpt = dpt;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getVote() {
        return vote;
    }

    public void setVote(Integer vote) {
        this.vote = vote;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + Objects.hashCode(this.dpt);
        hash = 19 * hash + Objects.hashCode(this.post);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ResultBean other = (ResultBean) obj;
        if (!Objects.equals(this.dpt, other.dpt)) {
            return false;
        }
        if (!Objects.equals(this.post, other.post)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ResultBean{" + "dpt=" + dpt + ", post=" + post + ", name=" + name + ", vote=" + vote + '}';
    }

}
