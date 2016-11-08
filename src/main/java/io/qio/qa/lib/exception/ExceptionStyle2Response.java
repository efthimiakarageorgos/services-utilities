/**
 * © Qio Technologies Ltd. 2016. All rights reserved.
 * CONFIDENTIAL AND PROPRIETARY INFORMATION OF QIO TECHNOLOGIES LTD.
 */
package io.qio.qa.lib.exception;

import org.codehaus.jackson.annotate.JsonProperty;
import java.util.List;

public class ExceptionStyle2Response {
	@JsonProperty("errors")
	private List<ExceptionStyle2ResponseError> errors;

	public ExceptionStyle2Response(List<ExceptionStyle2ResponseError> errors) {
		this.errors = errors;
	}

	public List<ExceptionStyle2ResponseError> getErrors() {
		return errors;
	}
}
