/**
 * © TheCompany QA 2019. All rights reserved.
 * CONFIDENTIAL AND PROPRIETARY INFORMATION OF TheCompany.
 */
package com.hbc.qa.lib.jira.model.issue.helper;

import com.hbc.qa.lib.jira.model.issue.IssueUpdate;
import com.hbc.qa.lib.jira.model.issue.Summary;
import com.hbc.qa.lib.jira.model.issue.Update;

public class IssueUpdateHelper {
    IssueUpdate issueUpdate=null;
    Update update=null;
    Summary[] summary = null;
    Summary summarySingle = null;

    private void updateIssueSummary(String newSummaryValue) {
        summarySingle = new Summary();
        summarySingle.setSet(newSummaryValue);

        summary[1]=summarySingle;

        update = new Update();
        update.setSummary(summary);

        issueUpdate = new IssueUpdate();
        issueUpdate.setUpdate(update);
    }
}
