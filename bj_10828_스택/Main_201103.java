package bj_10828_스택;

import java.io.*;
import java.util.*;

public class Main_201103 {
	static LinkedList<Integer> stack = new LinkedList<Integer>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int N = stoi(br.readLine());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			switch (st.nextToken()) {
			case "push":	push(stoi(st.nextToken())); break;
			case "pop":		sb.append(pop() + "\n");	break;
			case "size":	sb.append(size() + "\n");		break;
			case "empty":	sb.append(isEmpty() + "\n");	break;
			case "top":		sb.append(top() + "\n");		break;
			}
		}
		System.out.println(sb);
	}

	private static Object top() {
		return stack.size() > 0 ? stack.get(stack.size() - 1) : - 1;
	}

	private static int isEmpty() {
		return stack.size() == 0 ? 1 : 0;
	}

	private static int size() {
		return stack.size();
	}

	private static int pop() {
		return stack.size() > 0 ? stack.removeLast() : -1;
	}

	private static void push(int num) {
		stack.add(num);
	}
	
	private static int stoi(String str) {
		return Integer.parseInt(str);
	}
}