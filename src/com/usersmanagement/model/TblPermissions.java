package com.usersmanagement.model;

// Generated Dec 19, 2016 1:27:44 PM by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;

/**
 * TblPermissions generated by hbm2java
 */
public class TblPermissions implements java.io.Serializable {

	private int permissionId;
	private String permissionname;
	private Set tbleRoleses = new HashSet(0);
	private Set tblApplicationses = new HashSet(0);

	public TblPermissions() {
	}

	public TblPermissions(int permissionId) {
		this.permissionId = permissionId;
	}

	public TblPermissions(int permissionId, String permissionname,
			Set tbleRoleses, Set tblApplicationses) {
		this.permissionId = permissionId;
		this.permissionname = permissionname;
		this.tbleRoleses = tbleRoleses;
		this.tblApplicationses = tblApplicationses;
	}

	public int getPermissionId() {
		return this.permissionId;
	}

	public void setPermissionId(int permissionId) {
		this.permissionId = permissionId;
	}

	public String getPermissionname() {
		return this.permissionname;
	}

	public void setPermissionname(String permissionname) {
		this.permissionname = permissionname;
	}

	public Set getTbleRoleses() {
		return this.tbleRoleses;
	}

	public void setTbleRoleses(Set tbleRoleses) {
		this.tbleRoleses = tbleRoleses;
	}

	public Set getTblApplicationses() {
		return this.tblApplicationses;
	}

	public void setTblApplicationses(Set tblApplicationses) {
		this.tblApplicationses = tblApplicationses;
	}

}
