/**
 * © HBC Shared Services QA 2018. All rights reserved.
 * CONFIDENTIAL AND PROPRIETARY INFORMATION OF HBC.
 */
package com.hbc.qa.lib.common;

import com.hbc.qa.lib.apiHelpers.APIRequestHelper;
import com.hbc.qa.lib.connection.ConnectionManager;
import com.hbc.qa.lib.connection.ConnectionResponse;
import org.apache.log4j.Logger;

public class MBaseAPIHelper {
	ConnectionManager conManager = null;
	final static Logger logger = Logger.getRootLogger();

	public ConnectionResponse create(String microservice, String environment, String endpoint, String payload, APIRequestHelper apiRequestHeaders){
		initConManager();
		logger.info(getURI(microservice, environment, endpoint) + "    xxxx");
		return conManager.post(getURI(microservice, environment, endpoint), payload, apiRequestHeaders);
	}

	public void delete(String microservice, String environment, String endpoint, APIRequestHelper apiRequestHeaders){
		initConManager();
		conManager.delete(getURI(microservice, environment, endpoint), apiRequestHeaders);
	}
	
	public ConnectionResponse update(String microservice, String environment, String endpoint, String payload,
			APIRequestHelper apiRequestHeaders) {
		initConManager();
		return conManager.put(getURI(microservice, environment, endpoint), payload, apiRequestHeaders);
	}

	public ConnectionResponse patch(String microservice, String environment, String endpoint, String payload,
									 APIRequestHelper apiRequestHeaders) {
		initConManager();
		return conManager.patch(getURI(microservice, environment, endpoint), payload, apiRequestHeaders);
	}

	public ConnectionResponse retrieve(String microservice, String environment, String endpoint, APIRequestHelper apiRequestHeaders){
		initConManager();
		return conManager.get(getURI(microservice, environment, endpoint), apiRequestHeaders);
	}

	public ConnectionResponse retrieve(String microservice, String environment, String endpoint, String payload, APIRequestHelper apiRequestHeaders){
		initConManager();
		return conManager.get(getURI(microservice, environment, endpoint), payload, apiRequestHeaders);
	}

	public void authenticateUsingOauth(String microservice, String environment, String endpoint, APIRequestHelper apiRequestHeaders){
		initConManager();
		conManager.initOauthAccessToken(getURI(microservice, environment, endpoint), apiRequestHeaders);
	}

	public void authenticateUsingBasicAuth(String microservice, String environment, String endpoint, APIRequestHelper apiRequestHeaders){
		initConManager();
		conManager.initOauthAccessToken(getURI(microservice, environment, endpoint), apiRequestHeaders);
	}

	private void initConManager(){
		conManager = conManager == null ? ConnectionManager.getInstance() : conManager;
	}

	private String getURI(String microservice, String environment, String endpoint) {
		String URI = "https://" + microservice + environment + endpoint;
		return URI;
	}
}