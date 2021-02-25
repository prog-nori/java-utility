package example;

import java.io.File;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import utility.FileUtility;
import utility.StringUtility;

public class Example extends Object {
	public static void main(String[] args) {
		String pwd = System.getProperty("user.dir");
		System.out.println(pwd);
		(new Example()).exampleOfStringUtility(Arrays.asList(args));
		return;
	}

	private void exampleOfStringUtility(List<String> aList) {
		StringUtility aStringUtility = new StringUtility();
		aStringUtility.join("吾輩", "は", "ちんこである");
		System.out.println(aStringUtility);

		StringUtility anotherStringUtility = new StringUtility("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
		System.out.println(anotherStringUtility.get(1, 3));

		System.out.printf("%nexampleOfIfleUtility1%n");
		this.exampleOfFileUtility1();

		System.out.printf("%nexampleOfIfleUtility2%n");
		this.exampleOfFileUtility2();

		System.out.printf("%nexampleOfIfleUtility3%n");
		this.exampleOfFileUtility3();

		System.out.printf("%nexampleOfIfleUtility4%n");
		this.exampleOfFileUtility4();

		return;
	}

	private void exampleOfFileUtility1() {
		FileUtility aFileUtility = new FileUtility("./ren.txt");
		AtomicInteger anAtomicInteger = new AtomicInteger();
		aFileUtility.readlines(aLine -> {
			System.out.printf("%d: %s%n", anAtomicInteger.getAndIncrement(), aLine);
		});
		return;
	}

	private void exampleOfFileUtility2() {
		AtomicInteger anAtomicInteger = new AtomicInteger();
		FileUtility.readlines(new File("./ren.txt"), aLine -> {
			System.out.printf("%d: %s%n", anAtomicInteger.getAndIncrement(), aLine);
		});
		return;
	}

	private void exampleOfFileUtility3() {
		AtomicInteger anAtomicInteger = new AtomicInteger();
		FileUtility.readlines("./ren.txt", aLine -> {
			System.out.printf("%d: %s%n", anAtomicInteger.getAndIncrement(), aLine);
		});
		return;
	}

	private void exampleOfFileUtility4() {
		FileUtility.readlinesWithIndex("./ren.txt", (aLine, anIndex) -> {
			System.out.printf("%d: %s%n", anIndex, aLine);
		});
		return;
	}
}
