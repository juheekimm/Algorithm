package bj_02468_안전영역;

import java.io.*;
import java.util.*;

public class Main_200904 {

	static int N, map[][], max = 1;
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	static boolean[][] check;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = stoi(br.readLine());
		map = new int[N][N];
		check = new boolean[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++)
				map[i][j] = stoi(st.nextToken());
		}
		
		for (int i = 1; i <= 100; i++) {
			for (int j = 0; j < N; j++)
				Arrays.fill(check[j], false);
			
			int safeZone = findStart(i);
			if (max < safeZone) max = safeZone;
		}
		System.out.println(max);
	}

	private static int findStart(int h) {
		int count = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!check[i][j] && map[i][j] > h) {
					check[i][j] = true;
					dfs(i, j, h);
					count++;
				}
			} 
		}
		return count;
	}

	private static void dfs(int x, int y, int h) {
		for (int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
			
			if (!check[nx][ny] && map[nx][ny] > h) {
				check[nx][ny] = true;
				dfs(nx, ny, h);
			}
		}
	}

	private static int stoi(String str) {
		return Integer.parseInt(str);
	}
}