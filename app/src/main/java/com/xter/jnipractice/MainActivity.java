package com.xter.jnipractice;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		testFunc();
//		testCall();
	}

	private void testFunc() {
		Log.i("jni", "version=" + SomeFunc.getVersion());
		Log.i("jni", "IntArray=" + Arrays.toString(SomeFunc.getIntArray(21)));
		Log.i("jni", "IntArray=" + Arrays.toString(SomeFunc.getStringArray(9)));
		Rect rect = new Rect(11,22,33,44);
		Log.i("jni", "IntArray=" + Arrays.toString(SomeFunc.getRectValue(rect)));
		Log.i("jni", "User=" + SomeFunc.genUser());
	}

	private void testCall(){
		SomeFunc.callMethodFunc(this);
	}
}
