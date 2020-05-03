package bj_14888_연산자끼워넣기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	private static int N, num[], operator[], min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		num = new int[N];
		operator = new int[N - 1];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			num[i] = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int start = 0, end = 0;
		for (int i = 0; i < 4; i++) {
			int temp = Integer.parseInt(st.nextToken());
			end += temp;
			Arrays.fill(operator, start, end, i);
			start += temp;
		}
		
		do {
			calc();
		} while (nextPermutation());
		
		System.out.println(max);
		System.out.println(min);
	}

	private static boolean nextPermutation() {
		int i = N - 2;
		while (i > 0 && operator[i - 1] >= operator[i]) i--;
		if (i == 0) return false;
		
		int j = N - 2;
		while (operator[i - 1] >= operator[j]) j--;
		swap(i - 1, j);
		
		j = N - 2;
		while (i < j) swap(i++, j--);

		return true;
	}

	private static void swap(int i, int j) {
		int temp = operator[i];
		operator[i] = operator[j];
		operator[j] = temp;
	}

	private static void calc() {
		int now = num[0];
		for (int i = 0; i < N - 1; i++) {
			switch (operator[i]) {
			case 0: now += num[i + 1]; break;
			case 1: now -= num[i + 1]; break;
			case 2: now *= num[i + 1]; break;
			case 3: 
				if (num[i + 1] == 0) return;
				now /= num[i + 1]; break;
			}
		}
		
		if (min > now) min = now;
		if (max < now) max = now;
	}
}