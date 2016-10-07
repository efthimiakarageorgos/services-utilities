/**
 * © Qio Technologies Ltd. 2016. All rights reserved.
 * CONFIDENTIAL AND PROPRIETARY INFORMATION OF QIO TECHNOLOGIES LTD.
 */
package io.qio.qa.lib.common;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.codehaus.jackson.map.type.TypeFactory;

import java.io.IOException;
import java.util.List;

public class BaseHelper {
	private static ObjectMapper mapper;

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
}
