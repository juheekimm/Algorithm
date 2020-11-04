package programmers;

import java.util.*;

public class 전화번호목록_prev {

	public boolean solution(String[] phone_book) {
		StringBuilder sb;

		boolean answer = true;
		for (int i = 0; i < phone_book.length; i++) {
			phone_book[i] = phone_book[i].replace(" ", "");
		}
		Arrays.sort(phone_book, new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				return s1.length() - s2.length();
			}
		});
		HashSet<String> set = new HashSet<String>();

		for (int i = 0; i < phone_book.length; i++) {
			sb = new StringBuilder();
			for (int j = 0; j < phone_book[i].length(); j++) {
				sb.append(phone_book[i].charAt(j));
				if (set.contains(sb.toString())) {
					return false;
				}
			}
			set.add(phone_book[i]);
		}
		return true;
	}
}
