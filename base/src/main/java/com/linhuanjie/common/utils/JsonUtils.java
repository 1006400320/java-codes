package com.linhuanjie.common.utils;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.NameFilter;
import com.alibaba.fastjson.serializer.SerializeFilter;

/**
 * Json操作帮助类
 * 
 * @author niexiaolong
 * 
 */
public class JsonUtils {

	private static final Logger logger = LoggerFactory
			.getLogger(JsonUtils.class);

	// 自动将驼峰转下划线 orderId -> order_id
	private static SerializeFilter f = new MySerializeFilter();

	public static Object parse(String json) {
		return JSON.parse(json);
	}

	public static <T> T date2Bean(String data, Class<T> bean) {
		return JSON.parseObject(data, bean);
	}

	public static JSONArray toJsonArray(List<?> objs) {
		return (JSONArray) JSON.toJSON(objs);
	}

	public static <T> List<T> toBeanList(String data, Class<T> bean) {
		return JSON.parseArray(data, bean);
	}

	public static <T> T toBean(String json, Class<T> bean) {
		T t = null;
		try {
			t = JSON.toJavaObject(toJSON(json), bean);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return t;
	}

	public static <T> T toBean(JSONObject json, Class<T> bean) {
		return JSON.toJavaObject(json, bean);
	}

	public static JSONObject toJSON(String text) {
		JSONObject json = null;
		try {
			json = JSON.parseObject(text);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return json;
	}

	public static JSONObject toJSON(Object javaObject) {
		return (JSONObject) JSON.toJSON(javaObject);
	}

	/**
	 * Java对象转Json字符串 自动将Java对象驼峰属性名，改成Json下划线属性名
	 * 
	 * @param javaObject
	 *            - 可以是POJO对象，也可以是List列表
	 * @return
	 */
	public static String toJSONString(Object javaObject) {
		return JSON.toJSONString(javaObject, f);
	}

	public static String toString(Object javaObject) {
		return JSON.toJSONString(javaObject);
	}

	/**
	 * 自定义序列化-下划线转驼峰
	 * 
	 */
	static class MySerializeFilter implements NameFilter {

		@Override
		public String process(Object object, String name, Object value) {
			return StringUtilsApi.camelToUnderline(name);
		}

	}
	
	public static boolean isJson(Object text){
		boolean result = true;
		try{
			JSON.parse((String)text);
		}catch(Exception e){
			result = false;
		}
		return result;
	}
}
