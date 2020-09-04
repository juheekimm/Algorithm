package bj_04963_섬의개수;

import java.io.*;
import java.util.*;

public class Main_200904 {

	static int N, M;
	static boolean[][] map;
	static int[] dx = {0, 1, 0, -1, 1, 1, -1, -1};
	static int[] dy = {1, 0, -1, 0, -1, 1, -1, 1};
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		while (true) {
			st = new StringTokenizer(br.readLine());
			M = stoi(st.nextToken());
			N = stoi(st.nextToken());
			
			if (M == 0 && N == 0) break;
		
			map = new boolean[N][M];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					if (stoi(st.nextToken()) == 1)
						map[i][j] = true;
				}
			}
			sb.append(findStart() + "\n");
		}
		System.out.println(sb);
	}

	private static int findStart() {
		int count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j]) {
					map[i][j] = false;
					dfs(i, j);
					count++;
				}
			}
		}
		return count;
	}

	private static void dfs(int x, int y) {
		for (int d = 0; d < 8; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			if (nx < 0 || nx >= N || ny < 0 || ny >= M || !map[nx][ny]) continue;
			
			map[nx][ny] = false;
			dfs(nx, ny);
		}
	}

	private static int stoi(String str) {
		return Integer.parseInt(str);
	}
}