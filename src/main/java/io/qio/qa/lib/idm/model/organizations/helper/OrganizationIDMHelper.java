/**
 * © Qio Technologies Ltd. 2016. All rights reserved.
 * CONFIDENTIAL AND PROPRIETARY INFORMATION OF QIO TECHNOLOGIES LTD.
 */
package io.qio.qa.lib.idm.model.organizations.helper;

import io.qio.qa.lib.idm.model.organizations.OrganizationIDM;
import static io.qio.qa.lib.common.BaseHelper.getCurrentTimeStamp;

public class OrganizationIDMHelper {
	OrganizationIDM organizationIDM = null;

	/*
	 * This method is invoked from each of the following methods to make sure every time a new organizationIDM is created with a unique timestamp.
	 */
	private void initDefaultOrganization() {
		organizationIDM = new OrganizationIDM(getCurrentTimeStamp());
	}

	public OrganizationIDM getOrganizationIDM() {
		initDefaultOrganization();
		return organizationIDM;
	}
}