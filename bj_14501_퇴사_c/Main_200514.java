package bj_14501_퇴사_c;

import java.io.*;
import java.util.*;

public class Main_200514 {
	
	static int N, max = 0, t[], p[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		t = new int[N];
		p = new int[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			t[i] = Integer.parseInt(st.nextToken());
			p[i] = Integer.parseInt(st.nextToken());
		}
		recursion(0, 0);
		System.out.println(max);
	}

	private static void recursion(int idx, int sum) {
		if (idx > N) return;
		if (idx == N) {
			if (max < sum) max = sum;
			return;
		}
		recursion(idx + t[idx], sum + p[idx]);
		recursion(idx + 1, sum);
	}
}