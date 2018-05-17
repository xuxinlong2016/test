/*
 * @(#) Util.java  2010-4-29 ����05:38:33
 *
 * Copyright 2010 by Sparta 
 */

package avatar.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

/**
 *�����ࡣ
 */
public class Util {

	private static long pkId; // ΨһLong������

	/**
	 * ����ҵ����Ҫ���������� char��20�����͵��ֶ�ӳ��Ϊid��id��assigned����
	 * (����ID�����������Ƽ�)������loadһ����¼��ʱ����Key����20λ��
	 * ������key����20λ���󲹿գ�������load��getʱ���Ҳ�����Ӧ�ļ�¼�� �������Oracle,�������ݿ�δ�������ԣ�
	 * 
	 * @author sparta 10/5/20
	 * @param key
	 * @param dbSize
	 * @return
	 */
	public static String decorateKey(String key, int dbSize) {
		String keyWithoutBlank = key.trim();
		String keyOf20Length = null;
		if (keyWithoutBlank.length() < dbSize) { // char(20) �������ո񣬷����޷��ҵ�����
			// assigned not recommended
			keyOf20Length = keyWithoutBlank
					+ getBlanks(dbSize - keyWithoutBlank.length());
		} else {
			keyOf20Length = keyWithoutBlank;
		}
		return keyOf20Length;
	}

	public static String getBlanks(int size) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < size; i++) {
			sb.append(" ");
		}
		return sb.toString();
	}

	/**
	 * �õ������½ڵ��id(�����ڽڵ��Ϸź������Ӻ���½ڵ�ID����)
	 * 
	 * ���磬 0001_010_003�����ص���ֵΪ 0001_010_004�� ���磬 0001_010_009,���ص���ֵΪ
	 * 0001_010_010�� (��id���ɲ��Խ��ܹ�������߸��ڵ����ֵΪ9999�����ӽڵ����ֵΪ999;
	 * Ŀǰ�ݲ���ʵ���϶��ӽڵ��Ϊ��߸��ڵ�)
	 * 
	 * @author sparta 2010/5
	 */
	public static String getMaxNode(String id) {
		String oldId = id;
		id = id.substring(id.lastIndexOf("_") + 1, id.length());

		id = oldId.substring(0, oldId.length() - id.length()) + getIdNumber(id);

		return id;
	}

	/**
	 * �ڵ�ֵ��1 ����001������002
	 * 
	 * @author sparta 2010/5
	 */
	public static String getIdNumber(String id) {
		// ȡ����ֵ��1
		String str = (Integer.parseInt(id) + 1) + "";
		// ����3λ�� ��������λ��0����(δ����idΪ999���������Ϊ����Ŀǰ������
		// �ӽڵ�id�ĸ����ǲ��ᳬ��999��)
		switch (str.length()) {
		case 1:
			str = "00" + str;
			break;
		case 2:
			str = "0" + str;
			break;
		}

		return str;

	}

	/**
	 * ������0001������0002
	 * 
	 * @param id
	 * @author sparta 2010/5
	 */
	public static String getIncrementStr(String id) {
		// ȡ����ֵ��1
		String str = (Integer.parseInt(id) + 1) + "";
		// ǰ�߲���3λ�� ��������λ��0����(δ����idΪ999���������Ϊ����Ŀǰ������
		// �ӽڵ�id�ĸ����ǲ��ᳬ��999��)
		switch (str.length()) {
		case 1:
			str = "000" + str;
			break;
		case 2:
			str = "00" + str;
			break;
		case 3:
			str = "0" + str;
			break;
		}

		return str;
	}

	/**
	 * �ж�����������Ƿ�����ż��
	 * 
	 * @param int
	 * @return boolean trueΪż����falseΪ����
	 * @author sparta 2010/5
	 */
	public static boolean isEven(int num) {
		if (num % 2 == 0)
			return true;
		else
			return false;
	}

	/**
	 * �����ӽڵ㴮�ĸ��ڵ㴮�� ���ӽڵ㴮Ϊ0000_001���򷵻ظ��ڵ㴮Ϊ0000�� ϵͳĬ�ϳ������ڵ�Ϊ1��Ĭ�ϳ����ӽڵ�Ϊ0����ˣ�
	 * ���ӽڵ㴮Ϊ0000���򷵻ظ��ڵ㴮Ϊ0�����ӽڵ㴮Ϊ0���򷵻ظ��ڵ㴮Ϊ1
	 * 
	 * @see #avatar
	 * @param �ӽڵ㴮
	 * @author sparta 2010/5
	 */
	public static String getParentId(String childId) {
		if (childId.length() == 4)
			return "0";
		if (childId.indexOf("000") == 0)
			return childId.substring(0, childId.length() - 4);
		else if (childId.equals("0"))
			return "1";

		return "0";

	}

	/**
	 * ������id����ȡ�õ�λ���� �����ڵ�λidΪ"10011001.."����ʽ(Ŀǰ��ϵͳ�����б��ʹ�õĸ���id��ʽ,4λΪһ������)��
	 * 
	 * @author sparta 2010/06
	 */
	public static int getDwjb(int len) {
		if (len < 4)
			return 1;
		return len / 4;
	}

	/**
	 * �õ���ֵ�͵������ա� ���� 20100705
	 * 
	 * @author sparta 10/07/05
	 */
	public static int getYYMMDD() {
		Calendar cl = Calendar.getInstance();
		String y = cl.get(Calendar.YEAR) + "";
		String m = ((cl.get(Calendar.MONTH) + 1) < 10) ? "0"
				+ (cl.get(Calendar.MONTH) + 1) : (cl.get(Calendar.MONTH) + 1)
				+ "";
		String d = (cl.get(Calendar.DAY_OF_MONTH) < 10) ? "0"
				+ cl.get(Calendar.DAY_OF_MONTH) : cl.get(Calendar.DAY_OF_MONTH)
				+ "";
		return Integer.parseInt(y + m + d);
	}

	/**
	 * ���ؽڵ�+1��ֵ ���磺 ����10011001������10011002��
	 * ���룺100110011001100110011001100110011001100110011001
	 * ���أ�100110011001100110011001100110011001100110011002
	 * 
	 * @author sparta 2010/6
	 */
	public static String getNewNodeId(String id) {
		BigInteger bi = new BigInteger(id);
		bi = bi.add(new BigInteger("1"));
		return bi + "";
	}

	/**
	 * ����java���似��ʵ�ּ�¼��������صĲ���
	 * 
	 * @author sparta 10/07/22 9:25
	 * @param obj
	 *            , logger, optType: ����ʱ����, ��־�������, ��������(��ɾ�Ĳ�)
	 * @return void
	 */
	public static void log4Drgs(Object obj, Logger logger, String optType)
			throws SecurityException, NoSuchMethodException,
			IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {

		// �õ�����ʱ����
		Class clazz = obj.getClass();

		// �õ�����ʱ��������з���
		Method[] methods = clazz.getMethods();
		String methodName = "";

		// ����get��ͷ�ķ����õ��Ĳ������м�¼
		for (Method method : methods) {
			methodName = ((Member) method).getName();

			// ���˵�����get��ͷ�ķ��������ҹ��˵�getClass������
			if (methodName.indexOf("get", 0) < 0
					|| methodName.indexOf("getCla", 0) >= 0)
				continue;

			// ��ȡ�����Ա�ִ��
			Method getMethod = clazz.getMethod(methodName, null);

			// ��־��¼(��ִ�к󷽷���ֵ��¼����)
			logger.info("::::::::::" + methodName + "="
					+ getMethod.invoke(obj, null) + "::::::::::");
		}

	}

	/**
	 *���Զ��ŷָ����ִ�������Ϊ���� �磺"Str1,Str2,Str3"�ִ��� ����Ϊ arrStr[0]= str1,arrStr[1]=
	 * str2,arrStr[2]= str3��
	 * 
	 * @author sparta 2010-7-23 ����08:16:20
	 *@param str
	 *@return String[]
	 */
	public static String[] getArr(String str) {
		String[] strArray = { "" };

		// ���ִ�δ��������,��ֱ�ӷ��ر��ִ�
		if (str.indexOf(",") < 0) {
			strArray[0] = str;
			return strArray;
		}

		// ����
		StringTokenizer st = new StringTokenizer(str, ",");
		strArray = new String[st.countTokens()];
		int strLeng = st.countTokens();

		for (int i = 0; i < strLeng; i++) {
			strArray[i] = st.nextToken();
		}

		// ����
		return strArray;

	}

	/**
	 * ��δ�����HashMapת��Ϊ�����ListMap ����
	 * 
	 * @param inMap
	 * @return
	 */
	public static ListMap HashMapToListMap(HashMap inMap) {
		ListMap outMap = new ListMap();
		if (inMap == null)
			return outMap;

		Iterator iterator = inMap.keySet().iterator();
		String[] key = new String[inMap.size()];
		int p = 0;
		while (iterator.hasNext()) {
			String keys = (String) iterator.next();
			key[p] = keys;
			p++;
		}
		for (int i = 0; i < key.length; i++) {
			for (int j = i + 1; j < key.length; j++) {
				if (stringToLong(key[i]) > stringToLong(key[j])) {
					String t = key[i];
					key[i] = key[j];
					key[j] = t;
				}
			}
		}
		for (int k = 0; k < key.length; k++) {
			if (stringToLong(key[k]) > 0) {
				outMap.put(key[k] + "", (String) inMap.get(key[k] + ""));
			}
		}
		return outMap;
	}

	/**
	 * ��Stringת��Long�͡�
	 * 
	 * @author sparta 2011-4-9 ����05:05:05
	 *@param longStr
	 *@return
	 */
	public static long stringToLong(String longStr) {
		if (longStr == null)
			longStr = "";
		long mm = 0;
		try {
			mm = Long.parseLong(longStr);
		} catch (Exception ex) {
		}
		return mm;
	}

	public static synchronized long getPkId() {
		long lTmp = System.currentTimeMillis();
		if (pkId < lTmp) {
			pkId = lTmp;
		} else {
			pkId++;
		}
		return pkId;
	}

	/*
	 * ������ڣ�����һ�±���ĸ��������Ƿ�ɹ�
	 */
	public static void main(String[] args) {

		System.out.println("@@@@@@2  getPkId() = " + getPkId());
	}

}
