/**
 * © HBC Shared Services QA 2018. All rights reserved.
 * CONFIDENTIAL AND PROPRIETARY INFORMATION OF HBC.
 */
package com.hbc.qa.lib.exception;

import org.codehaus.jackson.annotate.JsonProperty;
import java.util.List;

public class ExceptionStyle2Response {
	@JsonProperty("errors")
	private List<ExceptionStyle2ResponseError> errors;

	public ExceptionStyle2Response() {
		this.errors = errors;
	}

	public List<ExceptionStyle2ResponseError> getErrors() {
		return errors;
	}
}