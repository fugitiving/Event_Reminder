package com.gaoke.util;
/**
 * �ַ���������
 * @author gaoke
 *
 */
public class StringUtil {
	/**
	 * �ж��Ƿ�Ϊ��
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if(str == null || "".equals(str.trim()))
			return true;
		else
			return false;
	}
	
	public static boolean isNotEmpty(String str) {
		if(str != null && !"".equals(str.trim()))
			return true;
		else
			return false;
	}
	
	public static boolean isMoreTwentyFiveChar(String str) {
		if(str.length() > 50)
			return true;
		else
			return false;
	}
}
