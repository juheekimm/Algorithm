package bj_14501_퇴사_c;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2 {

	private static int N, T[], P[], maxPrice = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		T = new int[N];
		P = new int[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			T[i] = Integer.parseInt(st.nextToken());
			P[i] = Integer.parseInt(st.nextToken());
		}

		dfs(0, 0);
		System.out.println(maxPrice);
	}

	private static void dfs(int now, int price) {
		if (now <= N && maxPrice < price)
			maxPrice = price;
		if (now >= N)
			return;
		dfs(now + 1, price);
		dfs(now + T[now], price + P[now]);
	}
}