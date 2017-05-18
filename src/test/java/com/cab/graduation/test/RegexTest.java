package com.cab.graduation.test;

import java.util.regex.Pattern;

public class RegexTest {
	public static void main(String[] args) {
		String regex="^9|([1-9]\\d)|(1[0-4]\\d)|(150)$";
		String str="150";
		boolean r=str.matches(regex);
		System.out.println(r);
	}
}
