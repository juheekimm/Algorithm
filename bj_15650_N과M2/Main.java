package bj_15650_N과M2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n, m, arr[];
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();

		n = str.charAt(0) - '0';
		m = str.charAt(2) - '0';
		arr = new int[n];
		
		combi(0, 1);
		System.out.println(sb);
	}

	private static void combi(int k, int idx) {
		if (k == m) {
			for (int i = 0; i < m; i++)
				sb.append(arr[i]).append(" ");
			sb.append("\n");
			return;
		}
		
		for (int i = idx; i <= n; i++) {
			arr[k] = i;
			combi(k + 1, i + 1);
		}
	}
}