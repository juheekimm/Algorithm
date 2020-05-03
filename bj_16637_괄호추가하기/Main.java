package bj_16637_괄호추가하기;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
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
        if (N == 1) {
			System.out.println(num[0]);
			return;
		}
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
		dfs(idx+1);
		permu[idx] = true;
		dfs(idx+2);
		permu[idx] = false;
	}

	private static int calc() {
		int[] temp = new int[size + 1];
		boolean[] visit = new boolean[size];
		
		int rslt = 0;
		boolean init = true;
		
		for (int i = 0; i < size; i++) {
			if (permu[i]) {
				temp[i + 1] = switchs(num[i], num[i + 1], operator[i]);
				visit[i] = true;
				i++;
			} else {
				if (!visit[i])
					temp[i] = num[i];
			}
		}
		if (!permu[size - 1])
			temp[size] = num[size];

		int op = 0;
		for (int i = 0; i < size; i++) {
			if (!visit[i]) {
				if (init) {
					rslt = temp[i];
					init = false;
					op = i;
				}
				
				for (int j = i + 1; j < size; j++) {
					if (!visit[j]) {
						rslt = switchs(rslt, temp[j], operator[op]);
						op = j;
						i = j - 1;
						break;
					}
				}
			}
		}
		rslt = switchs(rslt, temp[size], operator[op]);
		if (size == 1 && visit[0]) rslt = temp[1];
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