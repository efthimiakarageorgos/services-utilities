/**
 * © TheCompany QA 2019. All rights reserved.
 * CONFIDENTIAL AND PROPRIETARY INFORMATION OF TheCompany.
 */
package com.thecompany.qa.lib.jira.apiHelpers;

import com.thecompany.qa.lib.apiHelpers.APIHeaderRequestHelper;
import com.thecompany.qa.lib.common.MBaseAPIHelper;
import com.thecompany.qa.lib.connection.ConnectionResponse;

public class MIssueAPIHelper extends MBaseAPIHelper {
    private final String createIssueEndpoint = "/issue";
    private final String getOrDeleteOrUpdateSingleIssueEndpoint = "/issue/{issueId}";

    public ConnectionResponse create(String microservice, String environment, String payload, APIHeaderRequestHelper apiRequestHeaders) {
        return super.create(microservice, environment, createIssueEndpoint, payload, apiRequestHeaders);
    }

    public void delete(String microservice, String environment, String issueId, APIHeaderRequestHelper apiRequestHeaders) {
        super.delete(microservice, environment, replaceIssueIdInSingleIssueEndpoint(issueId), apiRequestHeaders);
    }

    public ConnectionResponse update(String microservice, String environment, String payload, String issueId, APIHeaderRequestHelper apiRequestHeaders) {
        return super.update(microservice, environment, replaceIssueIdInSingleIssueEndpoint(issueId), payload, apiRequestHeaders);
    }

    public ConnectionResponse retrieve(String microservice, String environment, String issueId, APIHeaderRequestHelper apiRequestHeaders) {
        return super.retrieve(microservice, environment, replaceIssueIdInSingleIssueEndpoint(issueId), apiRequestHeaders);
    }

    protected String replaceIssueIdInSingleIssueEndpoint(String issueId) {
        String singleIssueEndpoint = getOrDeleteOrUpdateSingleIssueEndpoint.replace("{issueId}", issueId);
        return singleIssueEndpoint;
    }
}