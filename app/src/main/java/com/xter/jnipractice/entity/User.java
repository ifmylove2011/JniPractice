package com.xter.jnipractice.entity;

/**
 * @Author XTER
 * @Date 2021/3/11 17:04
 * @Description 试验嵌套JNI
 */
public class User {

	public String name;
	public int age;
	public boolean female;
	public Profile profile;

	@Override
	public String toString() {
		return "User{" +
				"name='" + name + '\'' +
				", age=" + age +
				", female=" + female +
				", profile=" + profile +
				'}';
	}
}
