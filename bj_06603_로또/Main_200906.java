package bj_06603_로또;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_200906 {
	
	static int K, arr[], combi[];
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		combi = new int[6];
		
		while (true) {
			st = new StringTokenizer(br.readLine());
			K = stoi(st.nextToken());
			if (K == 0) break;
			
			arr = new int[K];
			for (int i = 0; i < K; i++)
				arr[i] = stoi(st.nextToken());
			
//			Arrays.fill(combi, 0);	굳이 필요없긴 
			combi(6, 0, 0);
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static void combi(int n, int k, int idx) {
		if (n == k) {
			for (int i = 0; i < 6; i++)
				sb.append(arr[combi[i]] + " ");
			sb.append("\n");
			
			return;
		}
		for (int i = idx; i < K; i++) {
			combi[k] = i;
			combi(n, k + 1, i + 1);
			//combi[k] = 0;
		}
	}

	private static int stoi(String str) {
		return Integer.parseInt(str);
	}
}