package bj_10942_팰린드롬;

import java.io.*;
import java.util.*;

public class Main_201027 {
	
	static int N, arr[], M;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		N = stoi(br.readLine());
		arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = stoi(st.nextToken());
		}
		
		M = stoi(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = stoi(st.nextToken()) - 1;
			int e = stoi(st.nextToken()) - 1;
			if (palindrome(s, e))
				sb.append("1\n");
			else
				sb.append("0\n");
		}
		System.out.println(sb);
	}

	private static boolean palindrome(int s, int e) {
		while (s <= e) {
			if (arr[s++] != arr[e--])
				return false;
		}
		return true;
	}

	private static int stoi(String str) {
		return Integer.parseInt(str);
	}
}