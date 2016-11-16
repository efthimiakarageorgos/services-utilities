/**
 * © Qio Technologies Ltd. 2016. All rights reserved.
 * CONFIDENTIAL AND PROPRIETARY INFORMATION OF QIO TECHNOLOGIES LTD.
 */
package io.qio.qa.lib.idm.model.organizations.helper;


import io.qio.qa.lib.idm.model.organizations.Organization;
import static io.qio.qa.lib.common.BaseHelper.getCurrentTimeStamp;

public class OrganizationHelper {
	Organization organization = null;

	/*
	 * This method is invoked from each of the following methods to make sure every time a new organization is created with a unique timestamp.
	 */
	private void initDefaultOrganization() {
		organization = new Organization(getCurrentTimeStamp());
	}

	public Organization getOrganization() {
		initDefaultOrganization();
		return organization;
	}
}