package bj_11550_puyopuyo;

import java.io.*;
import java.util.*;

public class Main {
	
	static int N = 12, M = 6, map[][], nowMax = 0;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static boolean[][] visit;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		map = new int[N][M];
		visit = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			char[] ch = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				switch (ch[j]) {
				case 'R': map[i][j] = 1; break;
				case 'G': map[i][j] = 2; break;
				case 'B': map[i][j] = 3; break;
				case 'P': map[i][j] = 4; break;
				case 'Y': map[i][j] = 5; break;
				}
			}
		}
		System.out.println(bomb());
	}

	private static int bomb() {
		int count = 0;
		
		while (true) {
			if (!findStart()) break;
			down();
			count++;
			for (int i = 0; i < N; i++)
				Arrays.fill(visit[i], false);
		}
		return count;
	}

	private static void down() {
		for (int j = 0; j < M; j++) {
			int[] temp = new int[N];
			int idx = N - 1;
			for (int i = N - 1; i >= 0; i--) {
				if (map[i][j] != 0)
					temp[idx--] = map[i][j];
			}
			for (int i = N - 1; i >= 0; i--) {
				map[i][j] = temp[i];
			}
		}
	}

	private static boolean findStart() {
		boolean flag = false;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!visit[i][j] && map[i][j] != 0) {
					visit[i][j] = true;
					nowMax = 1;
					countDFS(i, j, map[i][j]);
					if (nowMax >= 4) {
						flag = true;
						removeDFS(i, j, map[i][j]);
						map[i][j] = 0;
					}
				}
			}
		}
		return flag;
	}

	private static void removeDFS(int x, int y, int color) {
		for (int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			if (nx < 0 || nx >= N || ny < 0 || ny >= M || map[nx][ny] != color)
				continue;
			
			map[nx][ny] = 0;
			removeDFS(nx, ny, color);
		}		
	}

	private static void countDFS(int x, int y, int color) {
		for (int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			if (nx < 0 || nx >= N || ny < 0 || ny >= M || visit[nx][ny] || map[nx][ny] != color)
				continue;
			
			nowMax++;
			
			visit[nx][ny] = true;
			countDFS(nx, ny, color);
		}
	}
}