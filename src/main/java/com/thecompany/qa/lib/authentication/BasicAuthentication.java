/**
 * © TheCompany QA 2019. All rights reserved.
 * CONFIDENTIAL AND PROPRIETARY INFORMATION OF TheCompany.
 */
package com.thecompany.qa.lib.authentication;

import com.thecompany.qa.lib.apiHelpers.APIRequestHelper;
import java.util.Base64;

public class BasicAuthentication {
    String basicAuthString = null;

    public String initBasicAuthString(APIRequestHelper apiRequestHelper) {
        basicAuthString = "Basic " + Base64.getEncoder().encodeToString((apiRequestHelper.getUserName()
                + ":" + apiRequestHelper.getPassword()).getBytes());
        return basicAuthString;
    }
}

