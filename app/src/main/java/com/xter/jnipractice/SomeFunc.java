package com.xter.jnipractice;

import android.graphics.Rect;

import com.xter.jnipractice.entity.Profile;
import com.xter.jnipractice.entity.Secret;
import com.xter.jnipractice.entity.User;

/**
 * @Author XTER
 * @Date 2021/3/5 9:38
 * @Description
 */
public class SomeFunc {

	static{
		System.loadLibrary("start");
	}

	public static native int getVersion();
	public static native int[] getIntArray(int size);
	public static native String[] getStringArray(int size);
	public static native int[] getRectValue(Rect rect);
	public static native void callMethodFunc(MainActivity activity);
	public static native User genUser();
	public static native Profile genProfile();
	public static native Secret genSecret(byte[] buffer);
}
