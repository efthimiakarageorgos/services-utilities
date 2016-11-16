/**
 * © Qio Technologies Ltd. 2016. All rights reserved.
 * CONFIDENTIAL AND PROPRIETARY INFORMATION OF QIO TECHNOLOGIES LTD.
 */
package io.qio.qa.lib.idm.apiHelpers;

import io.qio.qa.lib.apiHelpers.APIRequestHelper;
import io.qio.qa.lib.common.MBaseAPIHelper;
import io.qio.qa.lib.connection.ConnectionResponse;

public class MOrganizationIDMAPIHelper extends MBaseAPIHelper {
	private final String createOrganization = "/organizations";
	private final String getOrDeleteorUpdateSingleOrganization = "/organizations/{organizationId}";
	private final String getByNameLike = "/organizations/search/findByNameLike?name={organizationName}";
	private final String getAllOrganizations = "/organizations";

	public ConnectionResponse create(String microservice, String environment, String payload, APIRequestHelper apiRequestHeaders) {
		return super.create(microservice, environment, createOrganization, payload, apiRequestHeaders);
	}

	public void delete(String microservice, String environment, String organizationId, APIRequestHelper apiRequestHeaders) {
		super.delete(microservice, environment, replaceOrganizationIdInSingleOrganization(organizationId), apiRequestHeaders);
	}

	public ConnectionResponse update(String microservice, String environment, String payload, String organizationId, APIRequestHelper apiRequestHeaders) {
		return super.update(microservice, environment, replaceOrganizationIdInSingleOrganization(organizationId), payload, apiRequestHeaders);
	}

	public ConnectionResponse retrieve(String microservice, String environment, APIRequestHelper apiRequestHeaders) {
		return super.retrieve(microservice, environment, getAllOrganizations, apiRequestHeaders);
	}

	public ConnectionResponse retrieve(String microservice, String environment, String organizationId, APIRequestHelper apiRequestHeaders) {
		return super.retrieve(microservice, environment, replaceOrganizationIdInSingleOrganization(organizationId), apiRequestHeaders);
	}
	
	public ConnectionResponse retrieve(String microservice, String environment, String searchBy, String searchValue, APIRequestHelper apiRequestHeaders) {
		switch(searchBy) {
			case "byNameLike":
				return super.retrieve(microservice, environment, replaceOrgNameInOrgByNameLikeEndpoint(searchValue), apiRequestHeaders);
//			case "byScopeLike":
//				return super.retrieve(microservice, environment, replaceOrgNameInOrgByXXXLikeEndpoint(searchValue), apiRequestHeaders);
			default:
				return super.retrieve(microservice, environment, replaceOrgNameInOrgByNameLikeEndpoint(searchValue), apiRequestHeaders);
		}
	}

	protected String replaceOrganizationIdInSingleOrganization(String organizationId) {
		String singleOrganization = getOrDeleteorUpdateSingleOrganization.replace("{organizationId}", organizationId);
		return singleOrganization;
	}
	
	protected String replaceOrgNameInOrgByNameLikeEndpoint(String orgName) {
		String orgsEndpoint = getByNameLike.replace("{organizationName}", orgName);
		return orgsEndpoint;
	}

}
