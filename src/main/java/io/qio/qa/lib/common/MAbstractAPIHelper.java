/**
 * © Qio Technologies Ltd. 2016. All rights reserved.
 * CONFIDENTIAL AND PROPRIETARY INFORMATION OF QIO TECHNOLOGIES LTD.
 */
package io.qio.qa.lib.common;

import io.qio.qa.lib.apiHelpers.APIRequestHelper;
import io.qio.qa.lib.common.BaseHelper;
import io.qio.qa.lib.common.Links;
import io.qio.qa.lib.common.model.CollectionListResponseStyleB;
import io.qio.qa.lib.common.model.Page;
import io.qio.qa.lib.connection.ConnectionResponse;
import io.qio.qa.lib.idm.apiHelpers.MOauthAPIHelper;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;


public class MAbstractAPIHelper {
	public static int responseCodeForInputRequest;
	public static Page pageForInputRequest = null;
	public static Links linksForInputRequest = null;
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

	public static <T> T getResponseObjForCreate(Object requestObject, String microservice, String environment, String collectionId, APIRequestHelper apiRequestHelper, Object apiHelperObj, Class<T> classType) {

		try {
			initOauthAuthentication(environment, apiRequestHelper);

			Class[] methodArgs = new Class[5];
			methodArgs[0] = methodArgs[1] = methodArgs[2] = methodArgs[3] = String.class;
			methodArgs[4] = APIRequestHelper.class;

			Method createMethod = apiHelperObj.getClass().getMethod("create", methodArgs);

			String payload = BaseHelper.toJSONString(requestObject);
			ConnectionResponse conRespPost = (ConnectionResponse) createMethod.invoke(apiHelperObj, microservice, environment, collectionId, payload, apiRequestHelper);
			responseCodeForInputRequest = conRespPost.getRespCode();
			return (T) BaseHelper.toClassObject(conRespPost.getRespBody(), classType);
		} catch (RuntimeException | IllegalAccessException | NoSuchMethodException | InvocationTargetException | IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static <T> T getResponseObjForUpdate(Object requestObject, String microservice, String environment, String elementId, APIRequestHelper apiRequestHelper, Object apiHelperObj, Class<T> classType) {
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

	public static <T> T getResponseObjForUpdate(Object requestObject, String microservice, String environment, String collectionId, String elementId, APIRequestHelper apiRequestHelper, Object apiHelperObj, Class<T> classType) {
		try {
			initOauthAuthentication(environment, apiRequestHelper);

			Class[] methodArgs = new Class[6];
			methodArgs[0] = methodArgs[1] = methodArgs[2] = methodArgs[3] = methodArgs[4] = String.class;
			methodArgs[5] = APIRequestHelper.class;
			Method updateMethod = apiHelperObj.getClass().getMethod("update", methodArgs);

			String payload = BaseHelper.toJSONString(requestObject);
			ConnectionResponse conRespPut = (ConnectionResponse) updateMethod.invoke(apiHelperObj, microservice, environment, payload, collectionId, elementId, apiRequestHelper);
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

			// TODO
			// do not know how to capture the response code
			//responseCodeForInputRequest = conRespDelete.getRespCode();
		} catch (RuntimeException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	public static <T> void deleteRequestObj(String microservice, String environment, String collectionId, String elementId, APIRequestHelper apiRequestHelper, Object apiHelperObj) {
		try {
			initOauthAuthentication(environment, apiRequestHelper);
			Class[] methodArgs = new Class[5];
			methodArgs[0] = methodArgs[1] = methodArgs[2] = methodArgs[3] = String.class;
			methodArgs[4] = APIRequestHelper.class;
			Method deleteMethod = apiHelperObj.getClass().getMethod("delete", methodArgs);
			ConnectionResponse conRespDelete = (ConnectionResponse) deleteMethod.invoke(apiHelperObj, microservice, environment, collectionId, elementId, apiRequestHelper);

			// TODO
			// do not know how to capture the response code
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

	public static <T> T getResponseObjForRetrieve(String microservice, String environment, String elementId, String firstArg, String secondArg, APIRequestHelper apiRequestHelper, Object apiHelperObj,
                                                  Class<T> classType) {
		try {
			initOauthAuthentication(environment, apiRequestHelper);

			Class[] methodArgs = new Class[6];
			methodArgs[0] = methodArgs[1] = methodArgs[2] = methodArgs[3] = methodArgs[4] = String.class;
			methodArgs[5] = APIRequestHelper.class;
			Method retrieveMethod = apiHelperObj.getClass().getMethod("retrieve", methodArgs);

			ConnectionResponse conRespGet = (ConnectionResponse) retrieveMethod.invoke(apiHelperObj, microservice, environment, elementId, firstArg, secondArg, apiRequestHelper);
			responseCodeForInputRequest = conRespGet.getRespCode();
			return (T) BaseHelper.toClassObject(conRespGet.getRespBody(), classType);
		} catch (RuntimeException | IllegalAccessException | NoSuchMethodException | InvocationTargetException | IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static <T> List<T> getListResponseObjForRetrieve(String microservice, String environment, String elementId, APIRequestHelper apiRequestHelper, Object apiHelperObj, Class<T> classType) {
		logger.info("getListResponseObjForRetrieve 1");
		try {
			initOauthAuthentication(environment, apiRequestHelper);

			Class[] methodArgs = new Class[4];
			methodArgs[0] = methodArgs[1] = methodArgs[2] = String.class;
			methodArgs[3] = APIRequestHelper.class;
			Method retrieveMethod = apiHelperObj.getClass().getMethod("retrieve", methodArgs);

			ConnectionResponse conRespGet = (ConnectionResponse) retrieveMethod.invoke(apiHelperObj, microservice, environment, elementId, apiRequestHelper);

			return formListResponseBasedOnResponseFormat(conRespGet, classType);
		} catch (RuntimeException | IllegalAccessException | NoSuchMethodException | InvocationTargetException | IOException | ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static <T> List<T> getListResponseObjForRetrieve(String microservice, String environment, String elementId, String firstArg, APIRequestHelper apiRequestHelper, Object apiHelperObj, Class<T> classType) {
		logger.info("getListResponseObjForRetrieve 2");
		try {
			initOauthAuthentication(environment, apiRequestHelper);

			Class[] methodArgs = new Class[5];
			methodArgs[0] = methodArgs[1] = methodArgs[2] = methodArgs[3] = String.class;
			methodArgs[4] = APIRequestHelper.class;
			Method retrieveMethod = apiHelperObj.getClass().getMethod("retrieve", methodArgs);

			ConnectionResponse conRespGet = (ConnectionResponse) retrieveMethod.invoke(apiHelperObj, microservice, environment, elementId, firstArg, apiRequestHelper);

			return formListResponseBasedOnResponseFormat(conRespGet, classType);
		} catch (RuntimeException | IllegalAccessException | NoSuchMethodException | InvocationTargetException | IOException | ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static <T> List<T> getListResponseObjForRetrieve(String microservice, String environment, String elementId, String firstArg, String secondArg, APIRequestHelper apiRequestHelper, Object apiHelperObj, Class<T> classType) {
		logger.info("getListResponseObjForRetrieve 3");
		try {
			initOauthAuthentication(environment, apiRequestHelper);

			Class[] methodArgs = new Class[6];
			methodArgs[0] = methodArgs[1] = methodArgs[2] = methodArgs[3] = methodArgs[4] = String.class;
			methodArgs[5] = APIRequestHelper.class;
			Method retrieveMethod = apiHelperObj.getClass().getMethod("retrieve", methodArgs);

			ConnectionResponse conRespGet = (ConnectionResponse) retrieveMethod.invoke(apiHelperObj, microservice, environment, elementId, firstArg, secondArg, apiRequestHelper);

			return formListResponseBasedOnResponseFormat(conRespGet, classType);
		} catch (RuntimeException | IllegalAccessException | NoSuchMethodException | InvocationTargetException | IOException | ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static <T> List<T> getListResponseObjForRetrieve(String microservice, String environment, String elementId, String firstArg, String secondArg, APIRequestHelper apiRequestHelper, Object apiHelperObj, Class<T> classType, String page, String pageSize) {
		logger.info("getListResponseObjForRetrieve 3 with page/pageSize args");
		try {
			initOauthAuthentication(environment, apiRequestHelper);

			Class[] methodArgs = new Class[8];
			methodArgs[0] = methodArgs[1] = methodArgs[2] = methodArgs[3] = methodArgs[4] = String.class;
			methodArgs[5] = APIRequestHelper.class;
			methodArgs[6] = methodArgs[7] = String.class;
			Method retrieveMethod = apiHelperObj.getClass().getMethod("retrieve", methodArgs);

			ConnectionResponse conRespGet = (ConnectionResponse) retrieveMethod.invoke(apiHelperObj, microservice, environment, elementId, firstArg, secondArg, apiRequestHelper, page, pageSize);

			return formListResponseBasedOnResponseFormat(conRespGet, classType);
		} catch (RuntimeException | IllegalAccessException | NoSuchMethodException | InvocationTargetException | IOException | ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static <T> List<T> getListResponseObjForRetrieveBySearch(String microservice, String environment, String searchBy, String searchValue, APIRequestHelper apiRequestHelper, Object apiHelperObj, Class<T> classType) {
		logger.info("getListResponseObjForRetrieveBySearch 1");
		try {
			initOauthAuthentication(environment, apiRequestHelper);

			Class[] methodArgs = new Class[5];
			methodArgs[0] = methodArgs[1] = methodArgs[2] = methodArgs[3] = String.class;
			methodArgs[4] = APIRequestHelper.class;

			Method retrieveMethod = apiHelperObj.getClass().getMethod("retrieve", methodArgs);

			ConnectionResponse conRespGet = (ConnectionResponse) retrieveMethod.invoke(apiHelperObj, microservice, environment, searchBy, searchValue, apiRequestHelper);

			return formListResponseBasedOnResponseFormat(conRespGet, classType);
		} catch (RuntimeException | IllegalAccessException | NoSuchMethodException | InvocationTargetException | IOException | ParseException e) {
			e.printStackTrace();
			return null;
		}
	}


	public static <T> List<T> getResponseObjForRetrieveAll(String microservice, String environment, APIRequestHelper apiRequestHelper, Object apiHelperObj, Class<T> classType) {
		logger.info("getListResponseObjForRetrieveAll 1");
		try {
			initOauthAuthentication(environment, apiRequestHelper);

			Class[] methodArgs = new Class[3];
			methodArgs[0] = methodArgs[1] = String.class;
			methodArgs[2] = APIRequestHelper.class;
			Method retrieveAllMethod = apiHelperObj.getClass().getMethod("retrieve", methodArgs);

			ConnectionResponse conRespGet = (ConnectionResponse) retrieveAllMethod.invoke(apiHelperObj, microservice, environment, apiRequestHelper);

			return formListResponseBasedOnResponseFormat(conRespGet, classType);
		} catch (RuntimeException | IllegalAccessException | NoSuchMethodException | InvocationTargetException | IOException | ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static <T> List<T> formListResponseBasedOnResponseFormat(ConnectionResponse conRespGet, Class<T> classType) throws IOException, ParseException {
		responseCodeForInputRequest = conRespGet.getRespCode();
		String responseBody = conRespGet.getRespBody();

		//Note that the response depends on the API implementation. In some cases it only contains the list
		//of collection items, in others the list is a json key:value pair under an "_embedded" element
        if (responseBody.contains("_embedded")) {
            logger.info("getListResponseObjForRetrieve: embedded");
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(responseBody);

            JSONObject emb = new JSONObject();
            JSONArray embArr = new JSONArray();

            try {
                emb = (JSONObject) json.get("_embedded");
            } catch (ClassCastException e) {
                //e.printStackTrace();
            }

            try {
                embArr = (JSONArray) json.get("_embedded");
            } catch (ClassCastException e) {
                //e.printStackTrace();
            }

            if (!embArr.isEmpty()) {
                logger.info("It is an array");
                String collectionItemList = embArr.toJSONString();

				Links pageLinks = new Links();
				JSONObject linksObj = null;
				linksObj = (JSONObject) json.get("_links");

				if (linksObj != null) {
					//try {
					JSONObject lastPageLinkObj = null;
					JSONObject firstPageLinkObj = null;
					JSONObject previousPageLinkObj = null;
					JSONObject nextPageLinkObj = null;

					lastPageLinkObj = (JSONObject) linksObj.get("lastPage");
					firstPageLinkObj = (JSONObject) linksObj.get("firstPage");
					previousPageLinkObj = (JSONObject) linksObj.get("previousPage");
					nextPageLinkObj = (JSONObject) linksObj.get("nextPage");

					if (lastPageLinkObj != null) {
						Links.LastPageLink lastPage = pageLinks.new LastPageLink();
						String lastPageLinkHref = ((String) lastPageLinkObj.get("href"));
						lastPage.setHref(lastPageLinkHref);
						pageLinks.setLastPage(lastPage);

						logger.info(pageLinks.getLastPage().getHref());
					}

					if (firstPageLinkObj != null) {
						Links.FirstPageLink firstPage = pageLinks.new FirstPageLink();
						String firstPageLinkHref = ((String) firstPageLinkObj.get("href"));
						firstPage.setHref(firstPageLinkHref);
						pageLinks.setFirstPage(firstPage);

						logger.info(pageLinks.getFirstPage().getHref());
					}

					if (nextPageLinkObj != null) {
						Links.NextPageLink nextPage = pageLinks.new NextPageLink();
						String nextPageLinkHref = ((String) nextPageLinkObj.get("href"));
						nextPage.setHref(nextPageLinkHref);
						pageLinks.setNextPage(nextPage);

						logger.info(pageLinks.getNextPage().getHref());
					}

					if (previousPageLinkObj != null) {
						Links.PreviousPageLink previousPage = pageLinks.new PreviousPageLink();
						String previousPageLinkHref = ((String) previousPageLinkObj.get("href"));
						previousPage.setHref(previousPageLinkHref);
						pageLinks.setPreviousPage(previousPage);

						logger.info(pageLinks.getPreviousPage().getHref());
					}
				}

				linksForInputRequest = pageLinks;

				Page pg = new Page((Long) json.get("size"), (Long) json.get("totalElements"), (Long) json.get("totalPages"), (Long) json.get("number"), (Long) json.get("numberOfElements"), (Boolean) json.get("last"), (Boolean) json.get("first"));
				pageForInputRequest = pg;

				/*} catch (RuntimeException e) {
					e.printStackTrace();
				}*/
                return (List<T>) BaseHelper.toClassObjectList(collectionItemList, classType);
            } else {
                logger.info("It is NOT an array");
                CollectionListResponseStyleB collectionListResponseStyleB = BaseHelper.toClassObject(responseBody, CollectionListResponseStyleB.class);

                logger.info("getListResponseObjForRetrieve: get page and links");
				linksForInputRequest = collectionListResponseStyleB.get_links();

				pageForInputRequest = collectionListResponseStyleB.getPage();


                //Expirimenting to see if we could use something like this instead of getCollectionItemListFromEmbeddedElement
//					Set<String> keys = emb.keySet();
//					for (String key : keys) {
//						Object obj = json.get(key);
//						logger.info("The key is "+obj.toString());
//						logger.info("Its value is " + emb.get(obj));
//					}

				if (collectionListResponseStyleB == null) logger.info("BBBB");
                String collectionItemList = BaseHelper.getCollectionItemListFromEmbeddedElement(collectionListResponseStyleB);
				logger.info("AAAAAAAA");
                return (List<T>) BaseHelper.toClassObjectList(collectionItemList, classType);
            }
        } else {
            return (List<T>) BaseHelper.toClassObjectList(conRespGet.getRespBody(), classType);
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
