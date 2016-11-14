/**
 * © Qio Technologies Ltd. 2016. All rights reserved.
 * CONFIDENTIAL AND PROPRIETARY INFORMATION OF QIO TECHNOLOGIES LTD.
 */
package io.qio.qa.lib.common;

import io.qio.qa.lib.apiHelpers.APIRequestHelper;
import io.qio.qa.lib.connection.ConnectionResponse;
import io.qio.qa.lib.idm.apiHelpers.MOauthAPIHelper;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class MAbstractAPIHelper {

	public static int responseCodeForInputRequest;
	private static MOauthAPIHelper oauthAPIHelper = null;

	final static Logger logger = Logger.getRootLogger();

	public static <T> T getResponseObjForCreate(Object requestObject, String microservice, String environment, APIRequestHelper apiRequestHelper, Object apiHelperObj, Class<T> classType) {

		try {
			initOauthAuthentication(environment, apiRequestHelper);

			Class[] methodArgs = new Class[4];
			methodArgs[0] = methodArgs[1] = methodArgs[2] = String.class;
			methodArgs[3] = APIRequestHelper.class;
			Method createMethod = apiHelperObj.getClass().getMethod("create", methodArgs);

			String payload = BaseHelper.toJSONString(requestObject);
			ConnectionResponse conRespPost = (ConnectionResponse) createMethod.invoke(apiHelperObj, microservice, environment, payload, apiRequestHelper);
			responseCodeForInputRequest = conRespPost.getRespCode();
			return (T) BaseHelper.toClassObject(conRespPost.getRespBody(), classType);
		} catch (RuntimeException | IllegalAccessException | NoSuchMethodException | InvocationTargetException | IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static <T> T getResponseObjForUpdate(Object requestObject, String microservice, String environment, String elementId, APIRequestHelper apiRequestHelper, Object apiHelperObj,
                                                Class<T> classType) {
		try {
			initOauthAuthentication(environment, apiRequestHelper);

			Class[] methodArgs = new Class[5];
			methodArgs[0] = methodArgs[1] = methodArgs[2] = methodArgs[3] = String.class;
			methodArgs[4] = APIRequestHelper.class;
			Method updateMethod = apiHelperObj.getClass().getMethod("update", methodArgs);

			String payload = BaseHelper.toJSONString(requestObject);
			ConnectionResponse conRespPut = (ConnectionResponse) updateMethod.invoke(apiHelperObj, microservice, environment, payload, elementId, apiRequestHelper);
			responseCodeForInputRequest = conRespPut.getRespCode();
			return (T) BaseHelper.toClassObject(conRespPut.getRespBody(), classType);
		} catch (RuntimeException | IllegalAccessException | NoSuchMethodException | InvocationTargetException | IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static <T> void deleteRequestObj(String microservice, String environment, String elementId, APIRequestHelper apiRequestHelper, Object apiHelperObj) {
		try {
			initOauthAuthentication(environment, apiRequestHelper);
			Class[] methodArgs = new Class[4];
			methodArgs[0] = methodArgs[1] = methodArgs[2] = String.class;
			methodArgs[3] = APIRequestHelper.class;
			Method deleteMethod = apiHelperObj.getClass().getMethod("delete", methodArgs);
			ConnectionResponse conRespDelete = (ConnectionResponse) deleteMethod.invoke(apiHelperObj, microservice, environment, elementId, apiRequestHelper);

			logger.info("AAAAAAAAAAAAAA "+conRespDelete.toString());

			//responseCodeForInputRequest = conRespDelete.getRespCode();
		} catch (RuntimeException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	/**
	 * elementId - refers the unique GUID identifier for item collection.
	 */
	public static <T> T getResponseObjForRetrieve(String microservice, String environment, String elementId, APIRequestHelper apiRequestHelper, Object apiHelperObj, Class<T> classType) {

		try {
			initOauthAuthentication(environment, apiRequestHelper);

			Class[] methodArgs = new Class[4];
			methodArgs[0] = methodArgs[1] = methodArgs[2] = String.class;
			methodArgs[3] = APIRequestHelper.class;
			Method retrieveMethod = apiHelperObj.getClass().getMethod("retrieve", methodArgs);

			ConnectionResponse conRespGet = (ConnectionResponse) retrieveMethod.invoke(apiHelperObj, microservice, environment, elementId, apiRequestHelper);
			responseCodeForInputRequest = conRespGet.getRespCode();
			return (T) BaseHelper.toClassObject(conRespGet.getRespBody(), classType);
		} catch (RuntimeException | IllegalAccessException | NoSuchMethodException | InvocationTargetException | IOException e) {
			e.printStackTrace();
			return null;
		}

	}

	public static <T> T getResponseObjForRetrieve(String microservice, String environment, String elementId, String subElementId, APIRequestHelper apiRequestHelper, Object apiHelperObj,
                                                  Class<T> classType) {
		try {
			initOauthAuthentication(environment, apiRequestHelper);

			Class[] methodArgs = new Class[5];
			methodArgs[0] = methodArgs[1] = methodArgs[2] = methodArgs[3] = String.class;
			methodArgs[4] = APIRequestHelper.class;
			Method retrieveMethod = apiHelperObj.getClass().getMethod("retrieve", methodArgs);

			ConnectionResponse conRespGet = (ConnectionResponse) retrieveMethod.invoke(apiHelperObj, microservice, environment, elementId, subElementId, apiRequestHelper);
			responseCodeForInputRequest = conRespGet.getRespCode();
			return (T) BaseHelper.toClassObject(conRespGet.getRespBody(), classType);
		} catch (RuntimeException | IllegalAccessException | NoSuchMethodException | InvocationTargetException | IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static <T> List<T> getListResponseObjForRetrieve(String microservice, String environment, String elementId, APIRequestHelper apiRequestHelper, Object apiHelperObj, Class<T> classType) {
		try {
			initOauthAuthentication(environment, apiRequestHelper);

			Class[] methodArgs = new Class[4];
			methodArgs[0] = methodArgs[1] = methodArgs[2] = String.class;
			methodArgs[3] = APIRequestHelper.class;
			Method retrieveMethod = apiHelperObj.getClass().getMethod("retrieve", methodArgs);

			ConnectionResponse conRespGet = (ConnectionResponse) retrieveMethod.invoke(apiHelperObj, microservice, environment, elementId, apiRequestHelper);
			responseCodeForInputRequest = conRespGet.getRespCode();
			return (List<T>) BaseHelper.toClassObjectList(conRespGet.getRespBody(), classType);
		} catch (RuntimeException | IllegalAccessException | NoSuchMethodException | InvocationTargetException | IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static <T> List<T> getListResponseObjForRetrieveBySearch(String microservice, String environment, String searchBy, String searchValue, APIRequestHelper apiRequestHelper, Object apiHelperObj, Class<T> classType) {
		try {
			initOauthAuthentication(environment, apiRequestHelper);

			Class[] methodArgs = new Class[5];
			methodArgs[0] = methodArgs[1] = methodArgs[2] = methodArgs[3] = String.class;
			methodArgs[4] = APIRequestHelper.class;
			
			Method retrieveMethod = apiHelperObj.getClass().getMethod("retrieve", methodArgs);
			logger.info(retrieveMethod.toString());

			ConnectionResponse conRespGet = (ConnectionResponse) retrieveMethod.invoke(apiHelperObj, microservice, environment, searchBy, searchValue, apiRequestHelper);
			responseCodeForInputRequest = conRespGet.getRespCode();
			
			//REVIEW REQUIRED: This is probably not the best way for extracting the list out of the response
			//Note that the response depends on the API implementation. In some cases it only contains the list 
			//of collection items, in others is list is a json key:value pair under an "_embedded" element
			String jsonString = conRespGet.getRespBody();
			int startIndex=jsonString.indexOf("[");
			int endIndex=jsonString.indexOf("]")+1;
			String collectionItemList=jsonString.substring(startIndex, endIndex);
			
		    return (List<T>) BaseHelper.toClassObjectList(collectionItemList, classType);
			//return (List<T>) BaseHelper.toClassObjectList(conRespGet.getRespBody(), classType);
		} catch (RuntimeException | IllegalAccessException | NoSuchMethodException | InvocationTargetException | IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	

	public static <T> T getResponseObjForRetrieveAll(String microservice, String environment, APIRequestHelper apiRequestHelper, Object apiHelperObj, Class<T> classType) {
		try {
			initOauthAuthentication(environment, apiRequestHelper);

			Class[] methodArgs = new Class[3];
			methodArgs[0] = methodArgs[1] = String.class;
			methodArgs[2] = APIRequestHelper.class;
			Method retrieveAllMethod = apiHelperObj.getClass().getMethod("retrieve", methodArgs);

			ConnectionResponse conRespGet = (ConnectionResponse) retrieveAllMethod.invoke(apiHelperObj, microservice, environment, apiRequestHelper);
			responseCodeForInputRequest = conRespGet.getRespCode();
			return (T) BaseHelper.toClassObject(conRespGet.getRespBody(), classType);
		} catch (RuntimeException | IllegalAccessException | NoSuchMethodException | InvocationTargetException | IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * Although this method is called before making any of the get, delete, put and post requests, but the oauth tokens are fetched only once, based on the logic abstracted inside the Connection
	 * Manager class.
	 */
	private static void initOauthAuthentication(String environment, APIRequestHelper apiRequestHelper) {
		oauthAPIHelper = oauthAPIHelper == null ? new MOauthAPIHelper() : oauthAPIHelper;
		oauthAPIHelper.authenticateUsingOauth(apiRequestHelper.getOauthMicroservice(), environment, apiRequestHelper);
	}
}
