package com.linhuanjie.common.utils;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.lang.reflect.Field;
import java.util.*;

final public class DataUtils extends NumberUtils {
	
	public static List<Long> int2Long(List<Integer> list) {
		 List<Long> rst = new ArrayList<>();
		 if (list == null || list.isEmpty()) {
			 return rst;
		 }
		 for (Integer i : list) {
			 rst.add(Long.valueOf(i + ""));
		 }
		 return rst;
	}
	
	public static List<Long> short2Long(List<Short> list) {
		 List<Long> rst = new ArrayList<>();
		 if (list == null || list.isEmpty()) {
			 return rst;
		 }
		 for (Short i : list) {
			 rst.add(Long.valueOf(i + ""));
		 }
		 return rst;
	}
	
	public static boolean listNotEmpty(Collection<?> c) {
		if (c == null || c.isEmpty()) {
			return false;
		}
		return true;
	}

	/**
	 * 如果传入的字符串为空，则返回空字符串【“”】
	 * @return
	 */
	public static String nullToBlank(String s){
		if (StringUtils.isEmpty(s)) {
			s = "";
		}
		return s;
	}

	/**
	 * 将null转成空集合
	 * @param list 要转换的集合
	 * @param <T> 集合类型
	 * @return 转换之后的集合
	 */
	public static <T> List<T> null2Empty(List<T> list) {
		if (CollectionUtils.isEmpty(list)) {
			list = new ArrayList<>();
		}
		return list;
	}

	/**
	 * 集合根据元素的指定字段进行分组
	 * @param list 集合
	 * @param fieldName 指定字段名
	 * @param <T> 指定字段类型
	 * @param <E> 集合的类型
	 * @return 分组后的map
	 */
	public static <T,E> Map<T, List<E>> getGroupMapByList(List<E> list, String fieldName){
		list = DataUtils.null2Empty(list);
		fieldName = StringUtils.isBlank(fieldName) ? "" : fieldName;
		Map<T,List<E>> map = new HashMap<>();
		try {
			for (E e : list) {
				Field field = e.getClass().getDeclaredField(fieldName);
				field.setAccessible(true);
				T t = (T) field.get(e);
				List<E> objects = map.get(t);
				if (CollectionUtils.isEmpty(objects)) {
					objects = new ArrayList<>();
					map.put(t,objects);
				}
				objects.add(e);
			}
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
			map = new HashMap<>();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			map = new HashMap<>();
		}
		return map;
	}

	/**
	 * 集合根据元素的指定字段进行分组
	 * @param list 集合
	 * @param fieldName 指定字段名
	 * @param <T> 指定字段类型
	 * @param <E> 集合的类型
	 * @return 分组后的map
	 */
	public static <T,E> Map<T, E> getSingleMapByList(List<E> list, String fieldName){
		list = DataUtils.null2Empty(list);
		fieldName = StringUtils.isBlank(fieldName) ? "" : fieldName;
		Map<T,E> map = new HashMap<>();
		try {
			for (E e : list) {
				Field field = e.getClass().getDeclaredField(fieldName);
				field.setAccessible(true);
				T t = (T) field.get(e);
				map.put(t,e);
			}
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
			map = new HashMap<>();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			map = new HashMap<>();
		}
		return map;
	}

	public static <E> boolean lillegeField(Class<E> clazz, String fieldName){
		boolean result = false;
		try {
			Field field = clazz.getDeclaredField(fieldName);
			result = true;
		} catch (Exception e) {
			result = false;
		}
		return result;
	}


	/**
	 * 判断对象是否为正数
	 * @param val
	 * @param <T> 泛型
	 * @return
	 */
	public static<T> boolean isPositiveNumber(T val) {
		if (val == null) return false;
		String str = val.toString();
		return isPositiveNumber(str);
	}

	public static boolean isPositiveNumber(String str) {
		if (StringUtils.isEmpty(str)) {
			return false;
		}
		final char[] chars = str.toCharArray();
		int sz = chars.length;
		boolean hasExp = false;
		boolean hasDecPoint = false;
		boolean allowSigns = false;
		boolean foundDigit = false;
		// deal with any possible sign up front
		final int start = (chars[0] == '-') ? 1 : 0;
		if (start == 1) return false;
		if (sz > start + 1 && chars[start] == '0' && chars[start + 1] == 'x') {
			int i = start + 2;
			if (i == sz) {
				return false; // str == "0x"
			}
			// checking hex (it can't be anything else)
			for (; i < chars.length; i++) {
				if ((chars[i] < '0' || chars[i] > '9')
						&& (chars[i] < 'a' || chars[i] > 'f')
						&& (chars[i] < 'A' || chars[i] > 'F')) {
					return false;
				}
			}
			return true;
		}
		sz--; // don't want to loop to the last char, check it afterwords
		// for type qualifiers
		int i = start;
		// loop to the next to last char or to the last char if we need another digit to
		// make a valid number (e.g. chars[0..5] = "1234E")
		while (i < sz || (i < sz + 1 && allowSigns && !foundDigit)) {
			if (chars[i] >= '0' && chars[i] <= '9') {
				foundDigit = true;
				allowSigns = false;

			} else if (chars[i] == '.') {
				if (hasDecPoint || hasExp) {
					// two decimal points or dec in exponent
					return false;
				}
				hasDecPoint = true;
			} else if (chars[i] == 'e' || chars[i] == 'E') {
				// we've already taken care of hex.
				if (hasExp) {
					// two E's
					return false;
				}
				if (!foundDigit) {
					return false;
				}
				hasExp = true;
				allowSigns = true;
			} else if (chars[i] == '+' || chars[i] == '-') {
				if (!allowSigns) {
					return false;
				}
				allowSigns = false;
				foundDigit = false; // we need a digit after the E
			} else {
				return false;
			}
			i++;
		}
		if (i < chars.length) {
			if (chars[i] >= '0' && chars[i] <= '9') {
				// no type qualifier, OK
				return true;
			}
			if (chars[i] == 'e' || chars[i] == 'E') {
				// can't have an E at the last byte
				return false;
			}
			if (chars[i] == '.') {
				if (hasDecPoint || hasExp) {
					// two decimal points or dec in exponent
					return false;
				}
				// single trailing decimal point after non-exponent is ok
				return foundDigit;
			}
			if (!allowSigns
					&& (chars[i] == 'd'
					|| chars[i] == 'D'
					|| chars[i] == 'f'
					|| chars[i] == 'F')) {
				return foundDigit;
			}
			if (chars[i] == 'l'
					|| chars[i] == 'L') {
				// not allowing L with an exponent or decimal point
				return foundDigit && !hasExp && !hasDecPoint;
			}
			// last character is illegal
			return false;
		}
		// allowSigns is true iff the val ends in 'E'
		// found digit it to make sure weird stuff like '.' and '1E-' doesn't pass
		return !allowSigns && foundDigit;
	}

	/**
	 *
	 * 方法描述 判断一个对象是否是一个数组
	 *
	 * @param obj
	 * @return
	 *
	 * @author yaomy
	 * @date 2018年2月5日 下午5:03:00
	 */
	public static boolean isArray(Object obj) {
		if(obj == null) {
			return false;
		}
		return obj.getClass().isArray();
	}

}
