/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bt.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author AXIOM
 */
public class PostBean implements Serializable {

    private String postName;
    private List<String> candidates;

    public PostBean() {
        this.candidates = new ArrayList<String>();
    }

    public PostBean(String postName, List<String> candidates) {
        this.postName = postName;
        this.candidates = candidates;
    }

    public List<String> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<String> candidates) {
        this.candidates = candidates;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public void add(String candidate) {
        this.candidates.add(candidate);
    }

    public String get(int index) {
        return this.candidates.get(index);
    }

    @Override
    public String toString() {
        return "PostBean{" + "postName=" + postName + ", candidates=" + candidates + '}';
    }

}
