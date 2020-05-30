package bj_15649_N과M1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_StringBuilder {

	static int n, m, arr[];
	static boolean[] check;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n];
		
		check = new boolean[n];
		permu(0);
		System.out.println(sb);
	}

	private static void permu(int k) {
		if (m == k) {
			for (int i = 0; i < m; i++)
				sb.append(arr[i]).append(" ");
			sb.append("\n");
			return;
		}
		
		for (int i = 1; i <= n; i++) {
			if (check[i - 1]) continue;
			
			check[i - 1] = true;
			arr[k] = i;
			permu(k + 1);
			arr[k] = 0;
			check[i - 1] = false;
		}
	}
}