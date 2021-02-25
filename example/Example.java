package javautility.example;

import java.util.Arrays;
import java.util.List;

import utility.StringUtility;

public class Example extends Object {
	public static void main(String[] args) {
		Example.exampleOfStringUtility(Arrays.asList(args));
		return;
	}

	private static void exampleOfStringUtility(List<String> aList) {
		StringUtility aStringUtility = new StringUtility();
		aStringUtility.join("吾輩", "は", "猫である");
		System.out.println(aStringUtility);
		return;
	}
}
