/**
 * © Qio Technologies Ltd. 2016. All rights reserved.
 * CONFIDENTIAL AND PROPRIETARY INFORMATION OF QIO TECHNOLOGIES LTD.
 */
package io.qio.qa.lib.common;

import io.qio.qa.lib.apiHelpers.APIRequestHelper;
import io.qio.qa.lib.common.model.CollectionListResponseStyleB;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.codehaus.jackson.map.type.TypeFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

public class BaseHelper {
	private static ObjectMapper mapper;
	final static Logger logger = Logger.getRootLogger();

	public static String toJSONString(Object classObject) throws JsonGenerationException, JsonMappingException, IOException {
		configureMapperObject();
		return mapper.writeValueAsString(classObject);
	}

	public static <T> T toClassObject(String jsonString, Class<T> classType) throws JsonGenerationException, JsonMappingException, IOException {
		configureMapperObject();
		return (T) mapper.readValue(jsonString, classType);
	}

	@SuppressWarnings("unchecked")
	public static <T> List<T> toClassObjectList(String jsonString, Class<T> classType) throws JsonGenerationException, JsonMappingException,
			IOException {
		configureMapperObject();
		return (List<T>) mapper.readValue(jsonString, TypeFactory.defaultInstance().constructCollectionType(List.class, classType));
	}

	private static void configureMapperObject() {
		mapper = new ObjectMapper();
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.setSerializationInclusion(Inclusion.NON_NULL);
	}

	/**
	 * This method parses out the id from the input href link, which as observed is the last part in the links.
	 *
	 * @param hrefLink
	 * @return
	 */
	public static String getElementId(String hrefLink) {
		String[] HrefLinkSplitArray = hrefLink.split("/");
		return HrefLinkSplitArray[HrefLinkSplitArray.length - 1];
	}

	public static String getCurrentTimeStamp() {
		java.util.Date date = new java.util.Date();
		return Long.toString(date.getTime());
	}

	public static String getCollectionItemListFromEmbeddedElement(CollectionListResponseStyleB collectionListResponseStyleB) {
		//REVIEW REQUIRED: This is probably not the best way for extracting the list out of the response
		//The following commented code does not work as the string has '=' instead of ":" and no quotes around the key-value pairs
//		JSONObject embedded = collectionListResponseStyleB.get_embedded();
//
//		String className = classType.getSimpleName();
//		String key = className.substring(0,1).toLowerCase()+ className.substring(1)+ "s";
//
//		String collectionItemList = embedded.get(key).toString();
//		logger.info("collectionItemList: " + collectionItemList);

		logger.info("I am here");
		String embedded = collectionListResponseStyleB.get_embedded().toString();
		int startIndex=embedded.indexOf("[");
		int endIndex=embedded.indexOf("]")+1;
		String collectionItemList=embedded.substring(startIndex, endIndex);
		logger.info("collectionItemList: " + collectionItemList);
		return collectionItemList;
	}

	public static boolean isDateCorrectlyFormattedForISO8601NoMS(String inputDate, String fieldName) {
		Logger logger = Logger.getRootLogger();
		logger.info("Checking validity of date format for field named "+fieldName + " and value: "+inputDate);
		Boolean dateFormatCheckerFlag = true;
		String dateFormatCheckerRegex = "^\\d\\d\\d\\d-(0?[1-9]|1[0-2])-(0?[1-9]|[12][0-9]|3[01])T(00|0[0-9]|1[0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])Z$";
		if (!inputDate.matches(dateFormatCheckerRegex)) {
			dateFormatCheckerFlag = false;
			logger.error("Incorrectly formatted date for " + fieldName + ": " + inputDate);
			logger.error("Expected date format: " + dateFormatCheckerRegex);
		}
		return dateFormatCheckerFlag;
	}

	public static boolean isDateCorrectlyFormattedForISO8601WithMS(String inputDate, String fieldName) {
		Logger logger = Logger.getRootLogger();
		Boolean dateFormatCheckerFlag = true;
		String dateFormatCheckerRegex = "^\\d\\d\\d\\d-(0?[1-9]|1[0-2])-(0?[1-9]|[12][0-9]|3[01])T(00|0[0-9]|1[0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9]).([0-9][0-9][0-9])Z$";
		if (!inputDate.matches(dateFormatCheckerRegex)) {
			dateFormatCheckerFlag = false;
			logger.error("Incorrectly formatted Date for " + fieldName + ": " + inputDate);
			logger.error("Expected Date format: " + dateFormatCheckerRegex);
		}
		return dateFormatCheckerFlag;
	}


	public static void deleteListOfCollectionItems(String microservice, String environment, APIRequestHelper apiRequestHeaders, Object apiHelperObj, ArrayList<String> idListForDeletion) {
		for (String elementId : idListForDeletion) {
			String[] elementIdArray = elementId.split(":");

			if (elementIdArray.length == 1) {
				MAbstractAPIHelper.deleteRequestObj(microservice, environment, elementId, apiRequestHeaders, apiHelperObj);
			} else {
				MAbstractAPIHelper.deleteRequestObj(microservice, environment, elementIdArray[0], elementIdArray[1], apiRequestHeaders, apiHelperObj);
			}
		}
	}
}
