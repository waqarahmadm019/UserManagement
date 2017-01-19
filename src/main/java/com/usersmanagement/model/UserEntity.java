package com.usersmanagement.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
//import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

@Entity(name = "tbl_users")
public class UserEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int usersId;
	private String email;
	private String password;
	private int managerId;
	
	
	private List<RoleEntity> roles;
	
	public UserEntity(){
		
	}
	
	public UserEntity(int usersId){
		this.usersId = usersId;
	}
	
	public UserEntity(int usersId, String email, String password, int managerId, List<RoleEntity> roles){
		this.usersId = usersId;
		this.email = email;
		this.password = password;
		this.managerId = managerId;
		this.roles = roles;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="users_seq")
	@SequenceGenerator(
		name="users_seq",
		sequenceName="seq_users_id",
		allocationSize=20
	)
	@Column(name = "users_id", unique = true, nullable = false)
	public int getUsersId() {
		return usersId;
	}
	
	public void setUsersId(int usersId) {
		this.usersId = usersId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name = "manager_id", nullable = false)
	public int getManagerId() {
		return managerId;
	}
	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="tbl_usersroles", 
	joinColumns=@JoinColumn(name="users_id"), 
	inverseJoinColumns=@JoinColumn(name="role_id"))
	public List<RoleEntity> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleEntity> roles) {
		this.roles = roles;
	}

}
