package com;

import java.util.Date;

public class UserInfo {
	
	private Integer userId;
	private String userName;
	private String userSex;
	private Date birthday;
	
	public UserInfo(Integer userId, String userName, String userSex, Date birthday)
	{
		this.userId= userId;
		this.userName = userName;
		this.userSex = userSex;
		this.birthday = birthday;
	}
	
	public Integer getUserId() {
		return this.userId;
	}

	@Override
	public String toString() {
		return "UserInfo [userId=" + userId + ", userName=" + userName + ", userSex=" + userSex + ", birthday="
				+ birthday + "]";
	}
}
