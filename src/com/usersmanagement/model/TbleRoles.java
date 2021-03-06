package com.usersmanagement.model;

// Generated Dec 19, 2016 1:27:44 PM by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;

/**
 * TbleRoles generated by hbm2java
 */
public class TbleRoles implements java.io.Serializable {

	private int roleId;
	private String rolenames;
	private Set tblPermissionses = new HashSet(0);
	private Set tblUserses = new HashSet(0);

	public TbleRoles() {
	}

	public TbleRoles(int roleId) {
		this.roleId = roleId;
	}

	public TbleRoles(int roleId, String rolenames, Set tblPermissionses,
			Set tblUserses) {
		this.roleId = roleId;
		this.rolenames = rolenames;
		this.tblPermissionses = tblPermissionses;
		this.tblUserses = tblUserses;
	}

	public int getRoleId() {
		return this.roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRolenames() {
		return this.rolenames;
	}

	public void setRolenames(String rolenames) {
		this.rolenames = rolenames;
	}

	public Set getTblPermissionses() {
		return this.tblPermissionses;
	}

	public void setTblPermissionses(Set tblPermissionses) {
		this.tblPermissionses = tblPermissionses;
	}

	public Set getTblUserses() {
		return this.tblUserses;
	}

	public void setTblUserses(Set tblUserses) {
		this.tblUserses = tblUserses;
	}

}
