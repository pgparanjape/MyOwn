package com.pgp.test;

public class RegularExpression {

	public static void main(String[] args) {

		// String withNewLine = "Zelle -29057379";
		String withNewLine = "Warnings \r-------------------\rThe config change causes the following "
				+ "nodes to be terminated:\r  Zelle -29057379";
		withNewLine = withNewLine.replace("\n", "").replace("\r", "");
		System.out.println(withNewLine);
		System.out.println(withNewLine.matches("(.*)Zelle -(\\d*)"));
		// System.out.println(withNewLine.matches("Warnings (.*) Zelle(.*)(\\d*)"));
	}
}
