package bj_01182_부분수열의합;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_200906 {
	
	static int N, S, arr[], count = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		
//		if (arr[0] == S) count++;
		dfs(0, 0);
		//dfs(1, arr[0]);
		System.out.println(count);
	}

	private static void dfs(int now, int sum) {
		if (now == N) return;
		
		if (sum + arr[now] == S) count++;
		dfs(now + 1, sum);
		dfs(now + 1, sum + arr[now]);
	}
}
