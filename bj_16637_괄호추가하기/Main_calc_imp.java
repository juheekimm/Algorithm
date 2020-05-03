package bj_16637_괄호추가하기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_calc_imp {
	private static int size, maxRslt = Integer.MIN_VALUE, num[];
	private static char operator[];
	private static boolean permu[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		size = N / 2;
		
		num = new int[size + 1];
		operator = new char[size];
		permu = new boolean[size];
		String temp = br.readLine();
		for (int i = 0; i < N; i++) {
			if (i % 2 == 0)num[i / 2] = temp.charAt(i) - '0';
			else operator[i / 2] = temp.charAt(i);
		}
		//이렇게 코드 개선하면 없어도 된다!
//        if (N == 1) {
//			System.out.println(num[0]);
//			return;
//		}
		dfs(0);
		System.out.println(maxRslt);
	}

	private static void dfs(int idx) {
		if (idx > size + 1) return;
		
		if (idx >= size) {
			int rslt = calc();
			if (maxRslt < rslt) maxRslt = rslt;
			return;
		}
		//이번 턴에는 안뽑고 다음턴으로 넘어간다 
		dfs(idx+1);
		
		//뽑고 다다음턴으로 넘어간다 (어차피 이번턴에 뽑으면 다음 턴에는 못 뽑음) 
		permu[idx] = true;
		dfs(idx+2);
		permu[idx] = false;
	}

	private static int calc() {
		int[] temp = num.clone();
		boolean[] visit = new boolean[size + 1];
		
		int rslt = 0;
		boolean init = true;
		
		for (int i = 0; i < size; i++) {
			if (permu[i]) {
				temp[i] = 0;
				temp[i + 1] = switchs(num[i], num[i + 1], operator[i]);
				visit[i] = true;
				i++;
			}
		}

		int prev = 0;
		for (int i = 0; i < size + 1; i++) {
			if (init) {
				if (!visit[i]) {
					rslt = temp[i];
					init = false;
					prev = i;
				}
			}
				
			for (int j = i + 1; j < size + 1; j++) {
				if (!visit[j]) {
					rslt = switchs(rslt, temp[j], operator[prev]);
					i = j - 1;
					prev = j;
					break;
				}
			}
		}
		return rslt;
	}

	private static int switchs(int a, int b, char op) {
		switch (op) {
		case '+': return a + b;
		case '-': return a - b;
		case '*': return a * b;
		}
		return 0;
	}
}