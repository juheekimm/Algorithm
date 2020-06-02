package bj_17779_게리맨더링2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_200602_주석ver {

	static int N, min = Integer.MAX_VALUE, map[][];
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
			for (int j = 0; j < N; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		findStart();
		System.out.println(min);
	}

	private static void findStart() {
		for (int i = 0; i <= N - 3; i++) {
			for (int j = 1; j <= N - 2; j++) {
//				visit[i][j] = true;
				backTracking(i, j, 0, i, j, 1, 0, 1);
//				visit[i][j] = false;
			}
		}
	}

	private static void backTracking(int x, int y, int dir, int sx, int sy, int d1, int d2, int now) {
		if (dir != 0 && x == sx && y == sy) {
			
			System.out.println("<< d1 " + d1 + " d2 " + d2 + " now " + now + " dir " + dir);
			System.out.println("=======================");
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					System.out.print((visit[i][j] ? 1 : 0) + " ");
				}
				System.out.println();
			}
			System.out.println("=======================");
			
			if (x == sx && y == sy) {
				int sum = sumCheck(x, y, d1, now);
				if (min > sum) min = sum;
				return;
			}
			else return;
		}
		
		for (int d = 0; d < 2; d++) {
			if (dir + d == 4) return;
			
			int nx = x + dx[dir + d];
			int ny = y + dy[dir + d];
			
			if (nx < 0 || nx >= N || ny < 0 || ny >= N || visit[nx][ny]) {
				continue;
//			} else if (visit[nx][ny]) {
//				if (!(nx == sx && ny == sy)) {
//					continue;
//				}
			}
			
//			if (d2 + 1 == now && dir == 2 && nx == sx && ny == sy)
			
			if (d == 1) {
				if ((dir == 0 && d1 == 0) || (dir == 1 && d2 == 0))
					return;
				if (dir == 2 && d1 != now)
					return;
			}
			
//			System.out.println("<< d1 " + d1 + " d2 " + d2 + " now " + now + " dir " + dir);
//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < N; j++) {
//					System.out.print((visit[i][j] ? 1 : 0) + " ");
//				}
//				System.out.println();
//			}
//			System.out.println();
			
			int nd1 = d1, nd2 = d2;

			visit[nx][ny] = true;
			if (d == 0) {
				if (dir == 0) nd1++;
				if (dir == 1) nd2++;
				backTracking(nx, ny, dir + d, sx, sy, nd1, nd2, now + 1);
			} else {
				if (dir == 0) nd2 = 1;
				backTracking(nx, ny, dir + d, sx, sy, nd1, nd2, 2);
			}
			visit[nx][ny] = false;
		}
	}

	private static int sumCheck(int x, int y, int d1, int d2) {
		int[] sum = new int[5];
		
		for (int i = 0; i < x + d1 - 1; i++)
			for (int j = 0; j <= y; j++) {
				if (visit[i][j]) break;
				System.out.println("i " + i + " j " + j);
				sum[0] += map[i][j];
			}
		
		System.out.println("sum1");
		
		for (int i = 0; i < x + d2; i++)
			for (int j = N - 1; j > y; j--) {
//			for (int j = y; j < N; j++)
				if (visit[i][j]) break;
				System.out.println("i " + i + " j " + j);
				sum[1] += map[i][j];
			}
		System.out.println("sum2");

		for (int i = x + d1 - 1; i < N; i++)
			for (int j = 0; j < y - d1 + d2; j++) {
				if (visit[i][j]) break;
				System.out.println("i " + i + " j " + j);
				sum[2] += map[i][j];
			}
		System.out.println("sum3");
		
		for (int i = x + d2; i < N; i++)
			for (int j = N - 1; j >= y - d1 + d2; j--) {
//			for (int j = y - d1 + d2 - 1; j < N; j++)
				if (visit[i][j]) break;
				System.out.println("i " + i + " j " + j);
				sum[3] += map[i][j];
			}
		System.out.println("sum4");
		
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				sum[4] += map[i][j];
		
		sum[4] = sum[4] - sum[0] - sum[1] - sum[2] - sum[3];
		System.out.println(Arrays.toString(sum));
		Arrays.sort(sum);
		return sum[4] - sum[0];
	}
}














