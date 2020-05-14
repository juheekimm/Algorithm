package bj_14888_연산자끼워넣기;

import java.io.*;
import java.util.*;

public class Main_200514 {
	
	static int N, OP, num[], min = Integer.MAX_VALUE, max = Integer.MIN_VALUE, op[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		OP = N - 1;
		
		num = new int[N];
		op = new int[OP];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			num[i] = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int[] temp = new int[4];
		for (int i = 0; i < 4; i++)
			temp[i] = Integer.parseInt(st.nextToken());
		
		int idx = 0;
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < temp[i]; j++)
				op[idx++] = i;
		
		do {
			calculate();
		} while (nextPermutation());
		
		System.out.println(max + "\n" + min);
	}
	
	private static void calculate() {
		int prev = num[0];
		
		for (int i = 0; i < OP; i++) {
			switch (op[i]) {
			case 0: prev += num[i + 1]; break;
			case 1: prev -= num[i + 1]; break;
			case 2: prev *= num[i + 1]; break;
			case 3: if (num[i + 1] == 0) return;
					prev /= num[i + 1]; break;
			}
		}
		if (max < prev) max = prev;
		if (min > prev) min = prev;
	}

	public static boolean nextPermutation() {
		int i = OP - 1;
		
		while (i > 0 && op[i - 1] >= op[i]) --i;
		if (i == 0) return false;
		
		int j = OP - 1;
		while (op[i - 1] >= op[j]) --j;
		swap(i - 1, j);
		
		j = OP - 1;
		while (i < j) swap(i++, j--);
		
		return true;
	}

	private static void swap(int a, int b) {
		int temp = op[a];
		op[a] = op[b];
		op[b] = temp;
	}
}