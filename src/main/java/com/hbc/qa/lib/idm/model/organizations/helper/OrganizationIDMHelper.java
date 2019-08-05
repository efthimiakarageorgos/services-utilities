/**
 * © TheCompany QA 2019. All rights reserved.
 * CONFIDENTIAL AND PROPRIETARY INFORMATION OF TheCompany.
 */
package com.hbc.qa.lib.idm.model.organizations.helper;

import com.hbc.qa.lib.common.BaseHelper;
import com.hbc.qa.lib.idm.model.organizations.OrganizationIDM;

public class OrganizationIDMHelper {
	OrganizationIDM organizationIDM = null;

	/*
	 * This method is invoked from each of the following methods to make sure every time a new organizationIDM is created with a unique timestamp.
	 */
	private void initDefaultOrganization() {
		organizationIDM = new OrganizationIDM(BaseHelper.getCurrentTimeStamp());
	}

	public OrganizationIDM getOrganizationIDM() {
		initDefaultOrganization();
		return organizationIDM;
	}
}