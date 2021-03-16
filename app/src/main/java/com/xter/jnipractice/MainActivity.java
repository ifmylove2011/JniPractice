package com.xter.jnipractice;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;

import com.xter.jnipractice.util.BytesUtils;

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
		Log.i("jni", "Profile=" + SomeFunc.genProfile());

		byte[] b1 = BytesUtils.intTo1Bytes(1);
		byte[] b2 = BytesUtils.intTo4Bytes(45);
		byte[] b3 = "委员称香港的教育必须向祖国学习".getBytes();
		byte[] buffer = BytesUtils.mergerBytes(b1,b2,b3);
//		Log.i("jni", BytesUtils.bytesToHexString(buffer));
		Log.i("jni", "Secret=" + SomeFunc.genSecret(buffer));


	}

	private void testCall(){
		SomeFunc.callMethodFunc(this);
	}
}
