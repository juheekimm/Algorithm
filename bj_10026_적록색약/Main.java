package bj_10026_적록색약;

import java.io.*;
import java.util.*;

public class Main {
	
	static int N, map[][];
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static boolean[][] check;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		check = new boolean[N][N];
		
		for (int i = 0; i < N; i++) {
			char[] temp = br.readLine().toCharArray();
			for (int j = 0; j < N; j++) {
				switch (temp[j]) {
				case 'R': map[i][j] = 1; break;
				case 'B': map[i][j] = 0; break;
				case 'G': map[i][j] = -1; break;
				}
			}
		}
		sb.append(findStart(false) + " ");
	
		for (int i = 0; i < N; i++)
			Arrays.fill(check[i], false);
		sb.append(findStart(true));

		System.out.println(sb);
	}

	private static int findStart(boolean flag) {
		int count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!check[i][j]) {
					check[i][j] = true;
					dfs(i, j, map[i][j], flag);
					count++;
				}
			}
		}
		return count;
	}

	private static void dfs(int x, int y, int color, boolean flag) {
		for (int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			if (nx < 0 || nx >= N || ny < 0 || ny >= N || check[nx][ny]) continue;
			
			if (map[nx][ny] == color || (flag && map[nx][ny] == (color * -1))) {
				check[nx][ny] = true;
				dfs(nx, ny, color, flag);
			}
		}
	}
}