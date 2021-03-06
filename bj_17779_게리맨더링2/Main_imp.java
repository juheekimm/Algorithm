package bj_17779_게리맨더링2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_imp {
	
	private static int sx, sy, N, map[][], min = Integer.MAX_VALUE, totalSum = 0;
	private static int[] dx = {1, 1, -1, -1}, dy = {-1, 1, 1, -1};
	private static boolean[][] visit;
	
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
				totalSum += map[i][j];
			}
		}
		findStart();
		System.out.println(min);
	}

	private static void findStart() {
		for (int i = 0; i <= N - 3; i++) {
			for (int j = 1; j <= N - 2; j++) {
				visit[i][j] = true;
				sx = i; sy = j;
				backTracking(i, j, 0, 0, 0);
				visit[i][j] = false;
			}
		}
	}

	private static void backTracking(int x, int y, int d, int d1, int d2) {
		if (d > 3) return;
		
		int nx = x + dx[d];
		int ny = y + dy[d];
		
		if (nx < 0 || nx >= N || ny < 0 || ny >= N) return;

		if (sx == nx && sy == ny) {
			countCheck(nx, ny, d1, d2);
			return;
		}

		if (visit[nx][ny]) return;
			
		if (d == 0) d1++;
		else if (d == 1) d2++;
		
		visit[nx][ny] = true;
		backTracking(nx, ny, d, d1, d2);
		backTracking(nx, ny, d + 1, d1, d2);
		visit[nx][ny] = false;
	}

	private static void countCheck(int x, int y, int d1, int d2) {
		int[] sum = new int[5];
		for (int i = 0; i < x + d1; i++) {	//1
			for (int j = 0; j <= y; j++) {
				if (visit[i][j]) break;
				sum[0] += map[i][j];
			}
		}
		
		for (int i = 0; i <= x + d2; i++) {	//2
			for (int j = N - 1; j > y; j--) {
				if (visit[i][j]) break;
				sum[1] += map[i][j];
			}
		}
		
		for (int i = x + d1; i < N; i++) {	//3
			for (int j = 0; j < y + d2 - d1; j++) {
				if (visit[i][j]) break;
				sum[2] += map[i][j];
			}
		}
		
		for (int i = x + d2 + 1; i < N; i++) {	//4
			for (int j = N - 1; j >= y + d2 - d1; j--) {
				if (visit[i][j]) break;
				sum[3] += map[i][j];
			}
		}
		
//		sum[4] = map[x][y] + map[x + d1 + d2][y + d2 - d1];
//		for (int i = x + 1; i < x + d1 + d2; i++) {	//5
//			boolean flag = false;
//			for (int j = 0; j < N; j++) {
//				
//				if (!flag) {
//					if (visit[i][j]) {
//						flag = true;
//						sum[4] += map[i][j];
//					} 
//				} else {
//					sum[4] += map[i][j];
//					if (visit[i][j])
//						break;
//				}
//			}
//		}
		sum[4] = totalSum - sum[0] - sum[1] - sum[2] - sum[3];
		
		Arrays.sort(sum);
		int rslt = sum[4] - sum[0];
		if (min > rslt) min = rslt;
	}
}