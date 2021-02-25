package example;

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
		aStringUtility.join("吾輩", "は", "ちんこである");
		System.out.println(aStringUtility);
		StringUtility anotherStringUtility = new StringUtility("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
		System.out.println(anotherStringUtility.get(1, 3));
		return;
	}
}
