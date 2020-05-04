package bj_10971_외판원순회2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, map[][], min = 987654321;
	static boolean[] visit;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visit = new boolean[N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		backTracking(0, 0, 0, 0);
		System.out.println(min);
	}

	private static void backTracking(int start, int now, int cnt, int sum) {
		if (cnt == N && start == now) {
			min = Math.min(min, sum);
			return;
		}
	
		for (int i = 0; i < N; i++) {
			if (!visit[i] && map[now][i] != 0) {
				if (cnt == N-1 && i != start)
					return;

				visit[i] = true;
				backTracking(start, i, cnt + 1, sum + map[now][i]);
				visit[i] = false;
			}
		}
	}
}