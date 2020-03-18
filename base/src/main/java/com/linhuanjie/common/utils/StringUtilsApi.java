package com.linhuanjie.common.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * String工具类
 * 
 * @author niexiaolong
 * 
 */
public class StringUtilsApi extends StringUtils{

	private static final Logger logger = LoggerFactory.getLogger(StringUtilsApi.class);

	protected static char[] chars = new char[] {'1', '2', '3', '4', '5',
        '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',  
        'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',  
        'W', 'X', 'Y', 'Z' };

	public static List<Long> string2List(String value, String split) {
		if (isBlank(value)) {
			return Collections.emptyList();
		}
		String[] splitValue = value.split(split);
		List<Long> rst = new ArrayList<Long>();
		if (splitValue != null && splitValue.length > 0) {
			for (String v : splitValue) {
				rst.add(Long.valueOf(v + ""));
			}
		}
		return rst;
	}

	public static String encodeFileName(HttpServletRequest request, String fileNames) {  
        String codedfilename = null;  
        try {  
            String agent = request.getHeader("USER-AGENT");  
            if (null != agent && -1 != agent.indexOf("MSIE") || null != agent  
                    && -1 != agent.indexOf("Trident")) {// ie  
  
                String name = java.net.URLEncoder.encode(fileNames, "UTF8");  
                codedfilename = name;  
            } else if (null != agent && -1 != agent.indexOf("Mozilla")) {// 火狐,chrome等  
                codedfilename = new String(fileNames.getBytes("UTF-8"), "iso-8859-1");  
            }  
        } catch (Exception e) {
        	logger.warn("[encodeFileName]出现异常", e);
        }
        return codedfilename;  
    }  
	
	/**
	 * 通过UUID来生成，去掉-和变成大写
	 */
	public static String generateUUIDCode() {
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}
	
	
	public static void main(String[] args) {
		for (int i = 0; i < 10000000; i++) {
			logger.info(generateUniqueCode(8, true));
		}
	}

	/**
	 * 生成length位数的字符串,
	 * @param length
	 * @param isAllNumber 是否全部都是数字
	 */
	public static String generateUniqueCode(int length, boolean isAllNumber) {
		if (length <= 0) {
			return EMPTY;
		}
		if (isAllNumber) {
			//return RandomStringUtils.random(length, 10000000, 99999999, false, true);
			String number = RandomStringUtils.randomNumeric(length);
			while (number.charAt(0) == '0') {
				number = RandomStringUtils.randomNumeric(length);
			}
			return number;
		} else {
			return RandomStringUtils.random(length, chars).toUpperCase();
		}
	}

	/**
	 * 下划线转驼峰
	 */
	public static String underlineToCamel(String param) {
		if (StringUtils.isBlank(param)) {
			return null;
		}
		int len = param.length();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			char c = param.charAt(i);
			if (c == '_') {
				if (++i < len) {
					sb.append(Character.toUpperCase(param.charAt(i)));
				}
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}
	
	/**
	 * 驼峰转下划线
	 */
	public static String camelToUnderline(String param) {
		if (param == null || "".equals(param.trim())) {
			return "";
		}
		if (param.indexOf("_") != -1) {
			return param;
		}
		int len = param.length();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			char c = param.charAt(i);
			if (Character.isUpperCase(c)) {
				sb.append('_');
				sb.append(Character.toLowerCase(c));
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}
	
	/**
	 * 字符串反转移
	 */
	public static String stripslashes(String str){
		if (StringUtils.isBlank(str)) {
			return str;
		}
		char[] sc = str.toCharArray();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < sc.length; i++) {
			if (sc[i]=='\\') {
				if (i+1 <= sc.length-1 && sc[i+1]=='\\') {
					sb.append(sc[i]);
					i++;
				}
			}else{
				sb.append(sc[i]);
			}
		}
		return sb.toString();
	}
	
	public static String joinList(List pieces,String glue){
		StringBuilder resual = new StringBuilder();
		int size = pieces.size();
		
		for(int i=0;i<size;i++){
			resual.append(pieces.get(i)); 
			if(i != size-1){
				resual.append(glue); 
			}
		}
		return resual.toString();
	}
}
