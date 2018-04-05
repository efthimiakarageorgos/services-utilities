/**
 * © Qio Technologies Ltd. 2016. All rights reserved.
 * CONFIDENTIAL AND PROPRIETARY INFORMATION OF QIO TECHNOLOGIES LTD.
 */
package com.hbc.qa.lib.idm.model.userGroup;

public class UserGroupRequest extends UserGroup {
	private String userGroup;

	public UserGroupRequest() {
	}

	@SuppressWarnings("serial")
	public UserGroupRequest(String timeStamp, String scope) {
		super(timeStamp, scope);
		this.userGroup = userGroup;
	}

	public UserGroupRequest(String name, String scope, String AAA) {
		super(name, scope);
		this.userGroup = userGroup;
	}

	public String getUserGroup() {
		return userGroup;
	}

	public void setUserGroup(String userGroup) {
		this.userGroup = userGroup;
	}
}