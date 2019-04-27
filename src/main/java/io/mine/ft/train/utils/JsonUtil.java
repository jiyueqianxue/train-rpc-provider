package io.mine.ft.train.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * Json转换工具类
 * 
 * Created by machao on 2016-04-05
 */
public final class JsonUtil {
	private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);

	private static final ObjectMapper OBJECT_MAPPER;

	static {
		OBJECT_MAPPER = new ObjectMapper();
		OBJECT_MAPPER.getSerializationConfig().withSerializationInclusion(JsonInclude.Include.NON_NULL);
		OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		// 空值处理为空串
		OBJECT_MAPPER.getSerializerProvider().setNullKeySerializer(new JsonSerializer<Object>() {
			@Override
			public void serialize(Object paramT, JsonGenerator paramJsonGenerator,
					SerializerProvider paramSerializerProvider) throws IOException, JsonProcessingException {
				paramJsonGenerator.writeString("");
			}
		});
		// 默认格式化时间格式
		OBJECT_MAPPER.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		// allow the private and package private fields to be detected
		OBJECT_MAPPER.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
	}

	private JsonUtil() {

	}

	public static ObjectMapper getObjectMapper() {
		return OBJECT_MAPPER;
	}

	/**
	 * JSON串转换为Java泛型对象，可以是各种类型
	 * 
	 * @param <T>
	 *            return type
	 * @param jsonString
	 *            JSON
	 * @param tr
	 *            TypeReference,例如: new TypeReference< List<FamousUser> >(){}
	 * @return List对象列表
	 */
	@SuppressWarnings("unchecked")
	public static <T> T json2GenericObject(String jsonString, TypeReference<T> tr) {
		if (!StringUtils.isBlank(jsonString)) {
			try {
				return (T) OBJECT_MAPPER.readValue(jsonString, tr);
			} catch (Exception e) {
				logger.warn("json error:" + e.getMessage());
			}
		}
		return null;
	}

	/**
	 * Java对象转JSON字符串
	 * 
	 * @param object
	 *            Java对象，可以是对象，数组，List,Map等
	 * @return JSON 字符串
	 */
	public static String toJson(Object object) {
		try {
			if (null == object) {
				return "";
			} else {
				return OBJECT_MAPPER.writeValueAsString(object);
			}
		} catch (Exception e) {
			logger.warn("json error:" + e.getMessage());
		}
		return "";
	}

	/**
	 * JSON字符串转Java对象
	 * 
	 * @param jsonString
	 *            JSON
	 * @param c
	 *            class
	 * @return
	 */
	public static Object json2Object(String jsonString, Class<?> c) {
		if (!StringUtils.isBlank(jsonString)) {
			try {
				return OBJECT_MAPPER.readValue(jsonString, c);
			} catch (Exception e) {
				logger.warn("json error:" + e.getMessage());
			}
		}
		return null;
	}

	/**
	 * 验证字符串是否为json格式
	 * 
	 * @param json
	 * @return
	 */
	public static boolean isValidJson(final String json) {
		boolean valid = false;
		try {
			final JsonParser parser = new ObjectMapper().getFactory().createParser(json);
			while (parser.nextToken() != null) {
			}
			valid = true;
		} catch (JsonParseException jpe) {
			return valid;
		} catch (IOException ioe) {
			return valid;
		}
		return valid;
	}
}
