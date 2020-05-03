package bj_14501_퇴사;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_fail {

	private static int N, T[], P[], maxPrice;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		T = new int[N + 1];
		P = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			T[i] = Integer.parseInt(st.nextToken());
			P[i] = Integer.parseInt(st.nextToken());
		}
		
		dfs(1 + T[1], P[1]);
		dfs(2 + T[2], P[2]);
		
		System.out.println(maxPrice);
	}

	private static void dfs(int end, int price) {
		if (end > N + 1) return;
		
		if (maxPrice < price) maxPrice = price;
		
		dfs(end + T[end], price + P[end]);

		if (end > N + 1) return;
		dfs(end + 1 + T[end + 1], price + P[end + 1]);
	}
}









