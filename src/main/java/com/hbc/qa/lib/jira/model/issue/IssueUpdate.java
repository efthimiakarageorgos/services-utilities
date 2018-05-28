/**
 * © HBC Shared Services QA 2018. All rights reserved.
 * CONFIDENTIAL AND PROPRIETARY INFORMATION OF HBC.
 */
package com.hbc.qa.lib.jira.model.issue;

import com.hbc.qa.lib.common.Links;
import org.apache.log4j.Logger;
import org.codehaus.jackson.annotate.JsonProperty;

import java.lang.reflect.Field;

public class IssueUpdate {
	private Update update;

	public Update getUpdate ()
	{
		return update;
	}

	public void setUpdate (Update update)
	{
		this.update = update;
	}
}