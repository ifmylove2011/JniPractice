package com.xter.jnipractice;

/**
 * @Author XTER
 * @Date 2021/3/11 17:04
 * @Description
 */
public class User {

	public String name;
	public int age;
	public boolean female;

	@Override
	public String toString() {
		return "User{" +
				"name='" + name + '\'' +
				", age=" + age +
				", female=" + female +
				'}';
	}
}
