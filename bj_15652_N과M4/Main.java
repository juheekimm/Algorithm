package bj_15652_N과M4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M, combi[];
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		combi = new int[M];
		combi(M, 0, 1);
		System.out.println(sb);
	}

	private static void combi(int n, int k, int idx) {
		if (n == k) {
			for (int i = 0; i < M; i++)
				sb.append(combi[i] + " ");
			sb.append("\n");
			return;
		}
		
		for (int i = idx; i <= N; i++) {
			combi[k] = i;
			combi(n, k + 1, i);
			combi[k] = 0;	//없어도 되긴 함 
		}		
	}
}
