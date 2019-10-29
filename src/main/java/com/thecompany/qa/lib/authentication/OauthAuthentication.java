/**
 * © TheCompany QA 2019. All rights reserved.
 * CONFIDENTIAL AND PROPRIETARY INFORMATION OF TheCompany.
 */
package com.thecompany.qa.lib.authentication;

import com.thecompany.qa.lib.apiHelpers.APIHeaderRequestHelper;
import com.thecompany.qa.lib.common.BaseHelper;
import com.thecompany.qa.lib.connection.ConnectionResponse;
import com.thecompany.qa.lib.connection.OauthValidationResponse;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class OauthAuthentication {
    private OauthValidationResponse oauthValidationResponse = null;
    final static Logger logger = Logger.getRootLogger();

    public void initOauthAccessToken(String URI, APIHeaderRequestHelper apiHeaderRequestHelper) {
		/*
		 * Fetching new access token under the following scenarios: 1. if this is the first time we are going to fetch the access token. 2. if a new
		 * object for APIHeaders has been instantiated, or its user/password has been changed(upon which the flag fetchNewAccessToken will be set to
		 * True), or we explicitly set the flag fetchNewAccessToken to true. 3. if the access token has expired
		 *
		 * TODO: Include the condition for expiry of the access token, although at present its set to 130min, but just in case its reduced and our
		 * tests take longer to run then there can be a problem.
		 */
        try {
            if (oauthValidationResponse == null || apiHeaderRequestHelper.getFetchNewAccessToken()) {
                Config applicationUserConfig = ConfigFactory.load("application_user_creds.conf");
                String basicAuthStr = "Basic " + Base64.getEncoder().encodeToString((applicationUserConfig.getString("application_user.username")
                        + ":" + applicationUserConfig.getString("application_user.password")).getBytes());

                ConnectionResponse conResp = getOauthValidationResponse(URI, basicAuthStr, apiHeaderRequestHelper);

                oauthValidationResponse = BaseHelper.toClassObject(conResp.getRespBody(), OauthValidationResponse.class);
                apiHeaderRequestHelper.setFetchNewAccessToken(false);
            }
        } catch (JsonGenerationException e) {
            // TODO Auto-generated catch block
            logger.error(e.getMessage());
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            logger.error(e.getMessage());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            logger.error(e.getMessage());
        }
    }

    private ConnectionResponse getOauthValidationResponse(String URI, String basicAuthStr, APIHeaderRequestHelper apiHeaderRequestHelper) {
        ConnectionResponse conResp = new ConnectionResponse();
        URL url;
        try {
            url = new URL(URI);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");

            StringBuilder postParams = new StringBuilder();
            postParams.append("username=" + apiHeaderRequestHelper.getUserName());
            postParams.append("&password=" + apiHeaderRequestHelper.getPassword());
            postParams.append("&grant_type=" + apiHeaderRequestHelper.getGrant_type());
            postParams.append("&scope=" + apiHeaderRequestHelper.getScope());
            postParams.append("&response_type=token");

            byte[] postData = postParams.toString().getBytes(StandardCharsets.UTF_8);

            // add request header
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            con.setRequestProperty("charset", "utf-8");
            con.setRequestProperty("Content-Length", Integer.toString(postData.length));
            con.setRequestProperty("Authorization", basicAuthStr);

            // Send post request
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            // wr.writeBytes(postParams);
            wr.write(postData);
            wr.flush();
            wr.close();

            logger.debug("Sending 'POST' request to URL : " + URI);

            int responseCode = con.getResponseCode();
            conResp.setRespCode(responseCode);

            BufferedReader in;
            if (responseCode != 200)
                in = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            else
                in = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            conResp.setRespBody(response.toString());

            // print result
            logger.debug("Response Code and Body: " + conResp.toString());

        } catch (IOException e) {
            // TODO Auto-generated catch block
            logger.error(e.getMessage());
        }
        return conResp;
    }
}
