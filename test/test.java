package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class test {
	public static void main(String[] args) {
		char[] ch = new char[20];
		System.out.println((int)ch[0]);
		System.out.println(ch[0]);
		System.out.println();
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		Collections.sort(list);
		list.add(3);
		list.get(3);
	}
}
