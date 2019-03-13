package org.blackboa.utils.json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {

	private static ObjectMapper mapper = new StringFormatToEmptyObjectMapper();

	/**
	 * 将java对象转换成json字符串
	 * 
	 * @param obj
	 *            准备转换的对象
	 * @return json字符串
	 * @throws Exception
	 */
	public static String toJson(Object obj) throws Exception {
		if (null == obj) {
			return "{}";
		}
		return mapper.writeValueAsString(obj);

	}

	public static ObjectMapper getMapper() {
		return mapper;
	}

	/**
	 * 
	 * 将json字符串转换成java对象
	 * 
	 * @param json
	 *            准备转换的json字符串
	 * 
	 * @param cls
	 *            准备转换的类
	 * 
	 * @return
	 * 
	 * @throws Exception
	 * 
	 */
	public static <T> T toBean(String json, Class<T> cls) {

		try {
			if (StringUtils.isEmpty(json)) {
				return null;
			}
			T t = mapper.readValue(json, cls);
			return t;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}

	public static <T> T toBean(String json, TypeReference<T> t) {

		try {
			if (StringUtils.isEmpty(json)) {
				return null;
			}
			return mapper.readValue(json, t);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * 转换成json数组
	 * 
	 * @param jsonData
	 * @param clazz
	 * @return
	 */
	public static <T> List<T> jsonToList(String jsonData, Class<T> clazz) throws Exception {
		List<T> list = mapper.readValue(jsonData, getCollectionType(mapper, clazz));
		return list;
	}

	public static JavaType getCollectionType(ObjectMapper mapper, Class<?> elementClasses) {
		return mapper.getTypeFactory().constructParametrizedType(ArrayList.class, List.class, elementClasses);
	}
}