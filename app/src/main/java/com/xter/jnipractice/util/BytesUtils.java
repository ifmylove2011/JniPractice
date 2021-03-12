/**
 * All rights Reserved, Designed By www.beonelot.com
 *
 * @Package: com.beonelot.bcca.common.bcca.DTO.util
 * @Filename: BytesUtils.java
 * @Description: 字节数组工具类
 * @author: 金鑫智慧
 * 创建时间:: 2018年8月21日 上午11:27:02
 * @version: V1.0
 * @Copyright: 2018 www.beonelot.com Inc. All rights reserved.
 * 注意：本内容仅限于重庆金鑫智慧科技有限公司内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.xter.jnipractice.util;

import java.math.BigInteger;
import java.util.List;
import java.util.Locale;

/**
 * 字节数组工具类
 *
 * 创建时间:: 2018年8月21日
 */
public class BytesUtils {

	public static byte[] intToBytes(int n, int len) {
		byte[] b = new byte[len];

		for (int i = 0; i < len; i++) {
			b[i] = (byte) (n >> (((len - 1 - i) * 8)) & 0xFF);
		}
		return b;
	}

	/**
	 * 整型转1字节
	 * @param num 整型数
	 * @return byte[1]
	 */
	public static byte[] intTo1Bytes(int num) {
		return intToBytes(num, 1);
	}

	/**
	 * 整型转2字节
	 * @param num 整型数
	 * @return byte[2]
	 */
	public static byte[] intTo2Bytes(int num) {
		return intToBytes(num, 2);
	}

	/**
	 * 整型转3字节
	 * @param num 整型数
	 * @return byte[3]
	 */
	public static byte[] intTo3Bytes(int num) {
		return intToBytes(num, 3);
	}

	/**
	 * 整型转4字节
	 * @param num 整型数
	 * @return byte[4]
	 */
	public static byte[] intTo4Bytes(int num) {
		return intToBytes(num, 4);
	}

	public static int bytesToInt(byte[] bytes) {
		int temp = 0;
		for (int i = 0; i < bytes.length; i++) {
			temp += (bytes[i] & 0xFF) << ((bytes.length - 1 - i) * 8);
		}
		return temp;
	}

	/**
	 * 长整型转字节数组
	 * @param num 长整型
	 * @param len 长度
	 * @return byte[len]
	 */
	public static byte[] longToBytes(long num, int len) {
		byte[] longLenBytes = new byte[len];
		for (int i = 0, temp; i < len; i++) {
			temp = (len - 1 - i) * 8;
			if (temp == 0) {
				longLenBytes[i] += (num & 0x0ff);
			} else {
				longLenBytes[i] += (num >>> temp) & 0x0ff;
			}
		}
		return longLenBytes;
	}

	/**
	 * 1字节转整型数
	 * @param oneBytes 字节数组
	 * @return 整型数
	 */
	public static int oneBytesToInt(byte[] oneBytes) {
		return bytesToInt(oneBytes);
	}

	/**
	 * 2字节转整型数
	 * @param twoBytes 字节数组
	 * @return 整型数
	 */
	public static int twoBytesToInt(byte[] twoBytes) {
		return bytesToInt(twoBytes);
	}

	/**
	 * 3字节转整型数
	 * @param threeBytes 字节数组
	 * @return 整型数
	 */
	public static int threeBytesToInt(byte[] threeBytes) {
		return bytesToInt(threeBytes);
	}

	/**
	 * 4字节转整型数
	 * @param fourBytes 字节数组
	 * @return 整型数
	 */
	public static int fourBytesToInt(byte[] fourBytes) {
		return bytesToInt(fourBytes);
	}

	/**
	 * 字节转t长整型数
	 * @param bytes 字节数组
	 * @return 长整型数
	 */
	public static long bytesToLong(byte[] bytes) {
		long longNum = 0;
		int len = bytes.length;
		for (int i = 0, temp; i < len; i++) {
			temp = (len - 1 - i) * 8;
			if (temp == 0) {
				longNum += (bytes[i] & 0x0ff);
			} else {
				longNum += (bytes[i] & 0x0ff) << temp;
			}
		}
		return longNum;
	}

	/**
	 * 整型数转十六进制字符串，等同于Integer.hexString
	 * @param hexNum 整数
	 * @return 字符
	 */
	public static String hexInt2String(int hexNum){
		return bytesToHexString(intTo4Bytes(hexNum));
	}

	/**
	 * 字符串转字节数组
	 * @param hexNum 字符串
	 * @return 字节数组
	 */
	public static byte[] toHexBytes(String hexNum) {
		hexNum = hexNum.replaceAll("\\s", "").trim();
		// 奇数,前补零
		hexNum = (hexNum.length() & 0x1) == 1 ? "0" + hexNum : hexNum;
		byte[] numBytes = new byte[hexNum.length() / 2];
		for (int i = 0; i < numBytes.length; i++) {
			byte high = toHexByte((byte) hexNum.charAt(i * 2));
			byte low = toHexByte((byte) hexNum.charAt(i * 2 + 1));
			// 遮罩低四位
			numBytes[i] = (byte) ((high << 4) | low);
		}
		return numBytes;
	}

	/**
	 * 字符串转固定长度字节数组
	 * @param hexNum 字符串
	 * @param num 长度
	 * @return 字节数组
	 */
	public static byte[] toHexBytes(String hexNum, int num) {
		byte[] numBytes = toHexBytes(hexNum);
		byte[] reBytes = new byte[num];

		if (numBytes.length == num) {
			return numBytes;
		} else if (numBytes.length > num) {
			for (int i = 0; i < num; i++) {
				reBytes[i] = numBytes[i];
			}
		} else {
			int cha = num - numBytes.length;
			for (int i = 0; i < cha; i++) {
				reBytes[i] = 0;
			}
			for (int j = 0; j < numBytes.length; j++) {
				reBytes[cha + j] = numBytes[j];
			}
		}

		return reBytes;
	}

	/**
	 * ascii码转byte
	 * @param charNum ascii码
	 * @return byte
	 */
	public static byte toHexByte(byte charNum) {
		if ((charNum >= '0') && (charNum <= '9')) {
			return (byte) (charNum - '0');
		} else if ((charNum >= 'A') && (charNum <= 'F')) {
			return (byte) (charNum - 'A' + 10);
		} else if ((charNum >= 'a') && (charNum <= 'f')) {
			return (byte) (charNum - 'a' + 10);
		} else {
			return (byte) (charNum - 48);
		}
	}

	/**
	 * @param num 原数据
	 * @param a   原进制数，2~36
	 * @param b   转进制数
	 * @return String 转后数据
	 */
	public static String numA2B(String num, int a, int b) {
		return new BigInteger(num, a).toString(b);
	}

	/**
	 *  十六进制字符串转int，相当于Integer.parseInt(xx,16)
	 * @param hexNum 字符串
	 * @return int
	 */
	public static int hexString2Int(String hexNum) {
		return bytesToInt(toHexBytes(hexNum));
	}

	/**
	 * 合并字节数组
	 * @param bytesList 字节组列表
	 * @return byte[]
	 */
	public static byte[] mergerBytes(List<byte[]> bytesList) {
		int len = 0;
		for (byte[] bytes : bytesList) {
			len += bytes.length;
		}
		byte[] bytesArr = new byte[len];
		len = 0;
		for (byte[] bytes : bytesList) {
			System.arraycopy(bytes, 0, bytesArr, len, bytes.length);
			len += bytes.length;
		}
		return bytesArr;
	}

	/**
	 * 字节组转字符串
	 * @param bytes 字节组
	 * @return 字符串
	 */
	public static String bytesToHexString(byte[] bytes) {
		StringBuffer sb = new StringBuffer();
		for (byte b : bytes) {
			sb.append(String.format("%02x", b));
		}
		return sb.toString().toLowerCase();
	}

	/**
	 * 分割字节组
	 * @param srcBytes 源字节组
	 * @param lens 长度组
	 * @return byte[][]
	 */
	public static byte[][] splitBytes(byte[] srcBytes, int... lens) {
		byte[][] bytess = new byte[lens.length][];
		int index = 0;
		for (int i = 0; i < lens.length; i++) {
			int len = lens[i];
			bytess[i] = new byte[len];
			System.arraycopy(srcBytes, index, bytess[i], 0, len);
			index +=lens[i];
		}
		return bytess;
	}

	/**
	 * 合并字节组
	 * @param bytesArray 字节组
	 * @return byte[]
	 */
	public static byte[] mergerBytes(byte[]... bytesArray) {
		int len = 0;
		for (byte[] bytes : bytesArray) {
			len += bytes.length;
		}
		byte[] bytesArr = new byte[len];
		len = 0;
		for (byte[] bytes : bytesArray) {
			System.arraycopy(bytes, 0, bytesArr, len, bytes.length);
			len += bytes.length;
		}
		return bytesArr;
	}

	/**
	 * 长度不够，使用byte'0'补齐
	 *
	 * @param content 内容
	 * @param len     总长度
	 * @return byte[]
	 */
	public static byte[] fillZeroBytes(String content, int len) {
		byte[] bytes = new byte[len];
		if (content == null) {
			content = "";
		}
		byte[] contentBytes = content.getBytes();
		System.arraycopy(contentBytes, 0, bytes, 0, contentBytes.length);
		return bytes;
	}

	/**
	 * 由于byte[]中会有byte0存在，会影响转为String，所以在转后过滤掉
	 *
	 * @param bytes 内容
	 * @return string
	 */
	public static String getStringByFillZeroBytes(byte[] bytes) {
		return new String(bytes).replaceAll("[^\\x01-\\xFF]", "");
	}

	public static byte[] StringToBytes(String s) {
		byte[] strBytes = new byte[s.length()];
		for (int i = 0; i < s.length(); i++) {
			char a = s.charAt(i);
			strBytes[i] = (byte) a;
		}
		return strBytes;
	}

	public static long bytes2DirectLong(byte[] bytes) {
		return Long.parseLong(bytesToHexString(bytes));
	}

	public static byte[] directLong2bytes(long id) {
		String idstr = String.valueOf(id);
		return toHexBytes(String.format(Locale.CHINA, "%0" + (8 - idstr.length()) + "d%s", 0, idstr));
	}
}
