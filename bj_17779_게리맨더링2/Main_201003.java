package bj_17779_게리맨더링2;

import java.io.*;
import java.util.*;

public class Main_201003 {
	
	static int N, map[][], sx, sy, total = 0, min = Integer.MAX_VALUE;
	static int[] dx = {1, 1, -1, -1};
	static int[] dy = {-1, 1, 1, -1};
	static boolean[][] visit;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visit = new boolean[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				total += map[i][j];
			}
		}
		
		for (int i = 0; i < N - 2; i++) {
			for (int j = 0; j < N; j++) {
				int ni = i + dx[0];
				int nj = j + dy[0];
				
				if (ni < 0 || ni >= N || nj < 0 || nj >= N) continue;
				
				visit[ni][nj] = true;
				sx = i; sy = j;
				backTracking(ni, nj, 0, 1, 0, 0, 0);
				visit[ni][nj] = false;
			}
		}
		System.out.println(min);
	}

	private static void backTracking(int x, int y, int d, int d1, int d2, int d3, int d4) {
		if (d > 3 || d1 < d3 || d2 < d4) return;
		if (x == sx && y == sy && d == 3) {
			getPersonCount(x, y, d1, d2);
			return;
		}
		
		for (int dir = 0; dir < 2; dir++) {
			if (d == 3 && dir == 1) break;
			
			int nx = x + dx[d + dir];
			int ny = y + dy[d + dir];
			
			if (nx < 0 || nx >= N || ny < 0 || ny >= N || visit[nx][ny]) continue;

			visit[nx][ny] = true;
			if (dir == 0) {
				if (d == 0)
					backTracking(nx, ny, d, d1 + 1, d2, d3, d4);
				else if (d == 1)
					backTracking(nx, ny, d, d1, d2 + 1, d3, d4);
				else if (d == 2)
					backTracking(nx, ny, d, d1, d2, d3 + 1, d4);
				else
					backTracking(nx, ny, d, d1, d2, d3, d4 + 1);
			} else {
				if (d == 0)
					backTracking(nx, ny, d + 1, d1, d2 + 1, d3, d4);
				else if (d == 1)
					backTracking(nx, ny, d + 1, d1, d2, d3 + 1, d4);
				else if (d == 2)
					backTracking(nx, ny, d + 1, d1, d2, d3, d4 + 1);
			}
			visit[nx][ny] = false;
		}
	}

	private static void getPersonCount(int x, int y, int d1, int d2) {
		int[] sum = new int[5];
		for (int j = 0; j <= y; j++) {
			for (int i = 0; i < x + d1; i++) {
				if (visit[i][j]) break;
				sum[0] += map[i][j];
			}
		}
		
		for (int j = y + 1; j < N; j++) {
			for (int i = 0; i <= x + d2; i++) {
				if (visit[i][j]) break;
				sum[1] += map[i][j];
			}
		}
		
		for (int j = 0; j < y - d1 + d2; j++) {
			for (int i = N - 1; i >= x + d1; i--) {
				if (visit[i][j]) break;
				sum[2] += map[i][j];
			}
		}
		
		for (int j = y - d1 + d2; j < N; j++) {
			for (int i = N - 1; i >= x + d2 + 1; i--) {
				if (visit[i][j]) break;
				sum[3] += map[i][j];
			}
		}
		
		sum[4] = total - sum[0] - sum[1] - sum[2] - sum[3];
		Arrays.sort(sum);
		min = Math.min(min, sum[4] - sum[0]);
	}
}