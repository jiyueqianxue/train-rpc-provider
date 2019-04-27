package io.mine.ft.train.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by Marco 加签/验签工具类
 */
public class SignUtil {

	private static final Logger logger = LoggerFactory.getLogger(SignUtil.class);

	/**
	 * 加签方法(map对象)
	 * 
	 * @param map
	 *            请求map
	 * @param key
	 *            加签盐值
	 * @return
	 */
	public static String sign(Map<String, Object> map, String key) {
		String genSign = "";
		String orgStr = "";
		try {
			StringBuilder stringBuilder = new StringBuilder();
			// 将map放入treeMap进行升序排序
			TreeMap<?, ?> treeMap = sortMap(map);
			String treeMapStr = JSONObject.toJSONString(treeMap);
			stringBuilder.append(treeMapStr);
			// 将私钥加在排好序的map字符串后
			orgStr = stringBuilder.append(key).toString();

			System.out.println(treeMapStr);
			System.out.println(orgStr);
			System.out.println(orgStr.getBytes("UTF-8"));

			// logger.info("加签原文：" + orgStr);
			genSign = DigestUtils.md5Hex(orgStr.getBytes("UTF-8"));// 指定编码格式
			// logger.info("加签密文：" + genSign);
		} catch (Exception e) {
			logger.error("加签失败,加签原文：" + orgStr, e);
		}
		return genSign;
	}

	public static String sign(Map<String, Object> map, String timestamp, String salt)
			throws UnsupportedEncodingException {
		map.put("timestamp", timestamp);

		return sign(map, salt);
	}

	/**
	 * 加签方法(bean对象)
	 * 
	 * @param bean
	 *            请求bean
	 * @param key
	 *            加签盐值
	 * @return
	 */
	public static String sign(Object bean, String key) {
		Map<String, Object> map = beanToMap(bean);
		return sign(map, key);
	}

	/**
	 * 验签方法
	 * 
	 * @param map
	 *            map对象
	 * @param key
	 *            验签盐值
	 * @param sign
	 *            签名密文
	 * @return
	 */
	public static boolean verify(Map<String, Object> map, String key, String sign) {
		boolean flag = false;
		try {
			StringBuilder stringBuilder = new StringBuilder();
			// 将map放入treeMap进行升序排序
			TreeMap<String, Object> treeMap = sortMap(map);
			String treeMapStr = JSONObject.toJSONString(treeMap);

			stringBuilder.append(treeMapStr);
			// 将私钥加在排好序的map字符串后
			String orgStr = stringBuilder.append(key).toString();

			// logger.info("验签原文：" + orgStr);
			String genSign = DigestUtils.md5Hex(orgStr.getBytes("UTF-8"));// 指定编码格式
			// logger.info("验签密文：" + genSign);
			if (genSign.equals(sign)) {
				flag = true;
			} else {
				logger.info("验签密文：" + genSign + " 原签名：" + sign + " 验签原文：" + orgStr);
			}
		} catch (Exception e) {
			logger.info("验签失败", e);
		}
		return flag;
	}

	/**
	 * 将JavaBean对象转化为Map
	 * 
	 * @param bean
	 * @return
	 */
	public static Map<String, Object> beanToMap(Object bean) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		try {
			Class<? extends Object> type = bean.getClass();
			BeanInfo beanInfo = Introspector.getBeanInfo(type);
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			// 循环获取bean属性和值放入map中
			for (int i = 0; i < propertyDescriptors.length; i++) {
				PropertyDescriptor descriptor = propertyDescriptors[i];
				String propertyName = descriptor.getName();
				// 忽略class和signValue属性
				if (!"class".equals(propertyName) && !"signValue".equals(propertyName)) {
					Method readMethod = descriptor.getReadMethod();
					Object result = readMethod.invoke(bean, new Object[0]);
					returnMap.put(propertyName, result);
				}
			}
		} catch (Exception e) {
			logger.info("beanToMap失败", e);
		}
		return returnMap;
	}

	/**
	 * map排序
	 * 
	 * @param map
	 * @return
	 */

	@SuppressWarnings("unchecked")
	private static TreeMap<String, Object> sortMap(Map<String, Object> map) {
		TreeMap<String, Object> treeMap = new TreeMap<String, Object>();

		for (Entry<String, Object> entry : map.entrySet()) {
			if (entry.getValue() instanceof Map<?, ?>) {

				Map<String, Object> map2 = (Map<String, Object>) entry.getValue();
				TreeMap<String, Object> subTreeMap = sortMap(map2);
				treeMap.put(entry.getKey(), subTreeMap);
			} else {
				treeMap.put(entry.getKey(), entry.getValue());
				System.out.println(treeMap);
			}
		}
		return treeMap;
	}

}
