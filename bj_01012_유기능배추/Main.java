package bj_01012_유기능배추;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M, K, count;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static boolean[][] map;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = stoi(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			M = stoi(st.nextToken());
			N = stoi(st.nextToken());
			K = stoi(st.nextToken()); 
			
			count = 0;
			map = new boolean[N][M];
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int x = stoi(st.nextToken());
				int y = stoi(st.nextToken());
				map[y][x] = true;
			}
			startPoint();
			sb.append(count + "\n");
		}
		System.out.println(sb);
	}

	private static void startPoint() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j]) {
					map[i][j] = false;
					dfs(i, j);
					count++;
				}
			}
		}
	}

	private static void dfs(int i, int j) {
		for (int d = 0; d < 4; d++) {
			int nx = i + dx[d];
			int ny = j + dy[d];
			if (nx < 0 || nx >= N || ny < 0 || ny >= M || !map[nx][ny]) continue;
			
			map[nx][ny] = false;
			dfs(nx, ny);
		}
	}

	private static int stoi(String str) {
		return Integer.parseInt(str);
	}
}









