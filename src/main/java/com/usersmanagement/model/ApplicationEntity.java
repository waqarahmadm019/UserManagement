package com.usersmanagement.model;

//Generated Dec 21, 2016 5:48:01 PM by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
* TblApplications generated by hbm2java
*/
@Entity(name = "tbl_applications")
public class ApplicationEntity implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int applicationId;
	private String applicationname;
	
	public ApplicationEntity() {
	}

	public ApplicationEntity(int applicationId) {
		this.applicationId = applicationId;
	}

	public ApplicationEntity(int applicationId, String applicationname) {
		this.applicationId = applicationId;
		this.applicationname = applicationname;
	}

	@Id
	@Column(name = "application_id", unique = true, nullable = false)
	public int getApplicationId() {
		return this.applicationId;
	}

	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}

	@Column(name = "applicationname")
	public String getApplicationname() {
		return this.applicationname;
	}

	public void setApplicationname(String applicationname) {
		this.applicationname = applicationname;
	}


}

