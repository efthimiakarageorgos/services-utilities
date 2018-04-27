/**
 * © HBC Shared Services QA 2018. All rights reserved.
 * CONFIDENTIAL AND PROPRIETARY INFORMATION OF HBC.
 */
package com.hbc.qa.lib.idm.apiHelpers;

import com.hbc.qa.lib.apiHelpers.APIRequestHelper;
import com.hbc.qa.lib.common.MBaseAPIHelper;

public class MOauthAPIHelper extends MBaseAPIHelper {
	private final String oauthEndpoint = "/oauth2/token";

	public void authenticateUsingOauth(String microservice, String environment, APIRequestHelper apiRequestHelper) {
		super.authenticateUsingOauth(microservice, environment, oauthEndpoint, apiRequestHelper);
	}
}
