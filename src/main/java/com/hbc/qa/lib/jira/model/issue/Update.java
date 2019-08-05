/**
 * © TheCompany QA 2019. All rights reserved.
 * CONFIDENTIAL AND PROPRIETARY INFORMATION OF TheCompany.
 */
package com.hbc.qa.lib.jira.model.issue;

public class Update {
    private Summary[] summary;

    private FieldX[] FieldX;

    public Summary[] getSummary ()
    {
        return summary;
    }

    public void setSummary (Summary[] summary)
    {
        this.summary = summary;
    }

    public FieldX[] getFieldX()
    {
        return FieldX;
    }

    public void setFieldX(FieldX[] fieldX)
    {
        this.FieldX = fieldX;
    }
}
