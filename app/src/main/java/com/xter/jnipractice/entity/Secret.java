package com.xter.jnipractice.entity;

import com.xter.jnipractice.util.BytesUtils;

/**
 * @Author XTER
 * @Date 2021/3/12 16:26
 * @Description 试验嵌套JNI
 */
public class Secret {

	public byte[] order = new byte[1];
	public byte[] len = new byte[4];
	public byte[] plan;

	@Override
	public String toString() {
		return BytesUtils.bytesToHexString(order).toUpperCase() +
				" | " + BytesUtils.bytesToHexString(len).toUpperCase() +
				" | " + new String(plan).replaceAll("[^\\x00-\\xFF\\u4e00-\\u9fa5]", "")
				;
	}
}
