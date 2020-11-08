package com;

public class UserInfoSort extends Sort1<UserInfo> {

	@Override
	public int compare(UserInfo o1, UserInfo o2) {
		return o1.getUserId() - o2.getUserId();
	}

}

