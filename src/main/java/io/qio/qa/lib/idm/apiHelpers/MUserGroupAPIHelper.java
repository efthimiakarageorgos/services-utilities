/**
 * © Qio Technologies Ltd. 2016. All rights reserved.
 * CONFIDENTIAL AND PROPRIETARY INFORMATION OF QIO TECHNOLOGIES LTD.
 */
package io.qio.qa.lib.idm.apiHelpers;


import io.qio.qa.lib.apiHelpers.APIRequestHelper;
import io.qio.qa.lib.common.MBaseAPIHelper;
import io.qio.qa.lib.connection.ConnectionResponse;

public class MUserGroupAPIHelper extends MBaseAPIHelper {
	private final String createOrUpdateUserGroupEndpoint = "/groups";
	private final String getOrDeleteSingleUserGroupEndpointAbstract = "/groups/{userGroupId}";
	private final String getUserGroupsEndpointsByNameAbstract = "/groups/search/findByNameLike?name={userGroupName}";
	private final String getAllUserGroupsEndpoint = "/groups";

	public String getUserGroupsEndpointsByNameAbstract () {
		return getUserGroupsEndpointsByNameAbstract;
	}
	
	public String getGetOrDeleteSingleUserGroupEndpointAbstract() {
		return getOrDeleteSingleUserGroupEndpointAbstract;
	}

	public ConnectionResponse create(String microservice, String environment, String payload, APIRequestHelper apiRequestHeaders) {
		return super.create(microservice, environment, createOrUpdateUserGroupEndpoint, payload, apiRequestHeaders);
	}

	public void delete(String microservice, String environment, String userGroupId, APIRequestHelper apiRequestHeaders) {
		super.delete(microservice, environment, replaceUserGroupIdInSingleUserGroupEndpoint(userGroupId), apiRequestHeaders);
	}

	public ConnectionResponse update(String microservice, String environment, String payload, String userGroupId, APIRequestHelper apiRequestHeaders) {
		return super.update(microservice, environment, replaceUserGroupIdInSingleUserGroupEndpoint(userGroupId), payload, apiRequestHeaders);
	}

	public ConnectionResponse retrieve(String microservice, String environment, APIRequestHelper apiRequestHeaders) {
		return super.retrieve(microservice, environment, getAllUserGroupsEndpoint, apiRequestHeaders);
	}

//	public ConnectionResponse retrieve(String microservice, String environment, String userGroupId, APIRequestHelper apiRequestHeaders) {
//		return super.retrieve(microservice, environment, replaceUserGroupIdInSingleUserGroupEndpoint(userGroupId), apiRequestHeaders);
//	}
	
	public ConnectionResponse retrieve(String microservice, String environment, String userGroupName, APIRequestHelper apiRequestHeaders) {
		return super.retrieve(microservice, environment, replaceUserGroupNameInUserGroupByNameEndpoint(userGroupName), apiRequestHeaders);
	}

	protected String replaceUserGroupIdInSingleUserGroupEndpoint(String userGroupId) {
		String singleUserGroupEndpoint = getOrDeleteSingleUserGroupEndpointAbstract.replace("{userGroupId}", userGroupId);
		return singleUserGroupEndpoint;
	}
	
	protected String replaceUserGroupNameInUserGroupByNameEndpoint(String userGroupId) {
		String userGroupsEndpoint = getUserGroupsEndpointsByNameAbstract.replace("{userGroupName}", userGroupId);
		return userGroupsEndpoint;
	}
}
