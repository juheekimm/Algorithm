package bj_16938_캠프준비;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_191110 {
	static int N, L, R, X, count;
	static int[] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());

		arr = new int[N];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(st.nextToken());

		dfs(0, 0, Integer.MAX_VALUE, -1, 0);
		System.out.println(count);
	}

	private static void dfs(int idx, int sum, int min, int max, int cnt) {
		if (cnt >= 2) {
			if (sum > R) // 합이 커져버리면 얘는 가망이 없음
				return;
			if (sum >= L && max - min >= X) // 모든 조건을 충족하면
				count++;
		}

		for (int i = idx; i < N; i++) {
			dfs(i + 1, sum + arr[i], Math.min(arr[i], min), Math.max(arr[i], max), cnt + 1);
		}
	}
}