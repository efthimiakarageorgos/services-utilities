/**
 * © TheCompany QA 2019. All rights reserved.
 * CONFIDENTIAL AND PROPRIETARY INFORMATION OF TheCompany.
 */
package com.thecompany.qa.lib.idm.apiHelpers;

import com.thecompany.qa.lib.apiHelpers.APIHeaderRequestHelper;
import com.thecompany.qa.lib.common.MBaseAPIHelper;

public class MOauthAPIHelper extends MBaseAPIHelper {
	private final String oauthEndpoint = "/oauth2/token";

	public void authenticateUsingOauth(String microservice, String environment, APIHeaderRequestHelper apiHeaderRequestHelper) {
		super.authenticateUsingOauth(microservice, environment, oauthEndpoint, apiHeaderRequestHelper);
	}
}
