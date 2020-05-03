package bj_14501_퇴사;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

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
		
		for (int i = 1; i <= N; i++)
			dfs(i + T[i], P[i]);
		
		System.out.println(maxPrice);
	}
	
	private static void dfs(int end, int price) {
		if (end > N + 1) return;
		if (maxPrice < price) maxPrice = price;

		for (int i = end; i <= N; i++)
			dfs(i + T[i], price + P[i]);
	}
}