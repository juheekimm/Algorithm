package bj_02504_괄호의값;

import java.io.*;
import java.util.*;

public class Main_201103 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		char[] ch = br.readLine().toCharArray();
		int len = ch.length;
		int[] num = new int[31];
		Stack<Character> stack = new Stack<Character>();
		int now = 0;
		
		for (int i = 0; i < len; i++) {
			switch (ch[i]) {
			case '(': case '[' :
				stack.push(ch[i]);
				now++;
				break;
				
			case ')':
				now--;
				if (stack.isEmpty()) {
					System.out.println(0);
					return;
				}
				if (stack.pop() != '(') {
					System.out.println(0);
					return;
				}
				if (num[now + 1] == 0) {
					num[now] += 2;
				} else {
					num[now] += num[now + 1] * 2;
					num[now + 1] = 0;
				}
				break;
				
			case ']':
				now--;
				if (stack.isEmpty()) {
					System.out.println(0);
					return;
				}
				if (stack.pop() != '[') {
					System.out.println(0);
					return;
				}
				if (num[now + 1] == 0) {
					num[now] += 3;
				} else {
					num[now] += num[now + 1] * 3;
					num[now + 1] = 0;
				}
				break;
			}
		}
		if (!stack.isEmpty()) {
			System.out.println(0);
			return;
		}
		System.out.println(num[0]);
	}
}