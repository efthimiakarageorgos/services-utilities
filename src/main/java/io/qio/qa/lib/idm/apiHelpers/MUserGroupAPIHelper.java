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
	private final String getOrDeleteSingleUserGroupEndpoint = "/groups/{userGroupId}";
	private final String getUserGroupsEndpointsByNameLike = "/groups/search/findByNameLike?name={userGroupName}";
	private final String getUserGroupsEndpointsByScopeLike = "/groups/search/findByScopeLike?name={userGroupScope}";
	private final String getAllUserGroupsEndpoint = "/groups";

	//REVIEW FEEDBACK: Cannot tell what the purpose of the following methods is
//	public String getUserGroupsEndpointsByNameLikeAbstract () {
//		return getUserGroupsEndpointsByNameLike;
//	}
//	
//	public String getGetOrDeleteSingleUserGroupEndpointAbstract() {
//		return getOrDeleteSingleUserGroupEndpoint;
//	}

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

	public ConnectionResponse retrieve(String microservice, String environment, String userGroupId, APIRequestHelper apiRequestHeaders) {
		return super.retrieve(microservice, environment, replaceUserGroupIdInSingleUserGroupEndpoint(userGroupId), apiRequestHeaders);
	}
	
	public ConnectionResponse retrieve(String microservice, String environment, String searchBy, String searchValue, APIRequestHelper apiRequestHeaders) {
		switch(searchBy) {
			case "byNameLike":
				return super.retrieve(microservice, environment, replaceUserGroupNameInUserGroupByNameLikeEndpoint(searchValue), apiRequestHeaders);
			case "byScopeLike":
				return super.retrieve(microservice, environment, replaceUserGroupNameInUserGroupByScopeLikeEndpoint(searchValue), apiRequestHeaders);
			default:
				return super.retrieve(microservice, environment, replaceUserGroupNameInUserGroupByNameLikeEndpoint(searchValue), apiRequestHeaders);
		}
	}

	protected String replaceUserGroupIdInSingleUserGroupEndpoint(String userGroupId) {
		String singleUserGroupEndpoint = getOrDeleteSingleUserGroupEndpoint.replace("{userGroupId}", userGroupId);
		return singleUserGroupEndpoint;
	}
	
	protected String replaceUserGroupNameInUserGroupByNameLikeEndpoint(String userGroupName) {
		String userGroupsEndpoint = getUserGroupsEndpointsByNameLike.replace("{userGroupName}", userGroupName);
		return userGroupsEndpoint;
	}
	
	protected String replaceUserGroupNameInUserGroupByScopeLikeEndpoint(String userGroupScope) {
		String userGroupsEndpoint = getUserGroupsEndpointsByScopeLike.replace("{userGroupName}", userGroupScope);
		return userGroupsEndpoint;
	}
}
