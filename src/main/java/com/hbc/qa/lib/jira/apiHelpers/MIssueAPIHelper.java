/**
 * © HBC Shared Services QA 2018. All rights reserved.
 * CONFIDENTIAL AND PROPRIETARY INFORMATION OF HBC.
 */
package com.hbc.qa.lib.jira.apiHelpers;

import com.hbc.qa.lib.apiHelpers.APIRequestHelper;
import com.hbc.qa.lib.common.MBaseAPIHelper;
import com.hbc.qa.lib.connection.ConnectionResponse;

public class MIssueAPIHelper extends MBaseAPIHelper {
    private final String createIssueEndpoint = "/issue";
    private final String getOrDeleteOrUpdateSingleIssueEndpoint = "/issue/{issueId}";

    public ConnectionResponse create(String microservice, String environment, String payload, APIRequestHelper apiRequestHeaders) {
        return super.create(microservice, environment, createIssueEndpoint, payload, apiRequestHeaders);
    }

    public void delete(String microservice, String environment, String issueId, APIRequestHelper apiRequestHeaders) {
        super.delete(microservice, environment, replaceIssueIdInSingleIssueEndpoint(issueId), apiRequestHeaders);
    }

    public ConnectionResponse update(String microservice, String environment, String payload, String issueId, APIRequestHelper apiRequestHeaders) {
        return super.update(microservice, environment, replaceIssueIdInSingleIssueEndpoint(issueId), payload, apiRequestHeaders);
    }

    public ConnectionResponse retrieve(String microservice, String environment, String issueId, APIRequestHelper apiRequestHeaders) {
        return super.retrieve(microservice, environment, replaceIssueIdInSingleIssueEndpoint(issueId), apiRequestHeaders);
    }

    protected String replaceIssueIdInSingleIssueEndpoint(String issueId) {
        String singleIssueEndpoint = getOrDeleteOrUpdateSingleIssueEndpoint.replace("{issueId}", issueId);
        return singleIssueEndpoint;
    }
}