package com.xter.jnipractice.entity;

import java.util.ArrayList;

/**
 * @Author XTER
 * @Date 2021/3/12 15:37
 * @Description 试验嵌套JNI
 */
public class Profile {
	public String address;
	public String school;
	public String degree;
	public String phoneNumber;
	public int salary;
	public ArrayList<String> experience;

	@Override
	public String toString() {
		return "Profile{" +
				"address='" + address + '\'' +
				", school='" + school + '\'' +
				", degree='" + degree + '\'' +
				", phoneNumber='" + phoneNumber + '\'' +
				", salary=" + salary +
				", experience=" + experience +
				'}';
	}
}
