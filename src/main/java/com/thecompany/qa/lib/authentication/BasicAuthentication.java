/**
 * © TheCompany QA 2019. All rights reserved.
 * CONFIDENTIAL AND PROPRIETARY INFORMATION OF TheCompany.
 */
package com.thecompany.qa.lib.authentication;

import com.thecompany.qa.lib.apiHelpers.APIHeaderRequestHelper;

import java.util.Base64;

public class BasicAuthentication {
    String basicAuthString = null;

    public String initBasicAuthString(APIHeaderRequestHelper apiHeaderRequestHelper) {
        basicAuthString = "Basic " + Base64.getEncoder().encodeToString((apiHeaderRequestHelper.getUserName()
                + ":" + apiHeaderRequestHelper.getPassword()).getBytes());
        return basicAuthString;
    }
}
