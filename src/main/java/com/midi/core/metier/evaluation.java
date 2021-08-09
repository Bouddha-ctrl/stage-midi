package com.midi.core.metier;

import java.util.ArrayList;
import java.util.List;

public class evaluation {

	private String name;
	private List<role> roles;
	
	public evaluation() {
	}
	public evaluation(String name) {
		this.name = name;
		roles = new ArrayList<role>();
	}
	
	public void addObjectif(role obj) {
		if (roles == null) roles = new ArrayList<role>();
		roles.add(obj);
		obj.setEval(this);
	}
	@Override
	public String toString() {
		return "evaluation [name=" + name + ", objs=" + roles + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<role> getRoles() {
		return roles;
	}
	public void setRoles(List<role> objs) {
		this.roles = objs;
	}
	
	
}
