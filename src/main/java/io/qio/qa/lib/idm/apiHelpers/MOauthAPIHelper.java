/**
 * © Qio Technologies Ltd. 2016. All rights reserved.
 * CONFIDENTIAL AND PROPRIETARY INFORMATION OF QIO TECHNOLOGIES LTD.
 */
package io.qio.qa.lib.idm.apiHelpers;

import io.qio.qa.lib.apiHelpers.APIRequestHelper;
import io.qio.qa.lib.common.MBaseAPIHelper;

public class MOauthAPIHelper extends MBaseAPIHelper {
	private final String oauthEndpoint = "/oauth2/token";

	public void authenticateUsingOauth(String microservice, String environment, APIRequestHelper apiRequestHelper) {
		super.authenticateUsingOauth(microservice, environment, oauthEndpoint, apiRequestHelper);
	}
}
