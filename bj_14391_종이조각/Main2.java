package bj_14391_종이조각;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2 {

	static int n, m, maxSum = -1;
	static int[][] map;
	static boolean[][] visit;
	static int[] dx = {0, 1};
	static int[] dy = {1, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		visit = new boolean[n][m];
		
		for (int i = 0; i < n; i++) {
			String temp = br.readLine();
			for (int j = 0; j < m; j++) {
				map[i][j] = temp.charAt(j) - '0';
			}
		}
//		print();
		visit[0][0] = true;
		dfs(0, -1, map[0][0], 0);
		System.out.println(maxSum);
	}
	
	//dir: -1이면 기본, 0이면 우측, 1이면 아래
	private static void dfs(int idx, int dir, int val, int sum) {
		if (idx >= m * n) {//종료조건
			maxSum = Math.max(maxSum, sum);
			System.out.println("안오냐");
			return;
		}
		int nx = idx / m, ny = idx % m;
		if (dir == -1) {
			for (int d = 0; d < 2; d++) {
				dfs(idx, d, map[nx][ny], 0);
				System.out.println(idx);
				sum += val;
			}
		} else {
			while(true) {
				nx += dx[dir];
				ny += dy[dir];
				
				if (nx < 0 || nx >= n || ny < 0 || ny >= m || visit[nx][ny]) {
					dfs(idx, -1, map[idx / m][idx % m], 0);
					sum += val;
					break;
				}
				
				val = val * 10 + map[nx][ny];
				visit[nx][ny] = true;
				
				dfs(idx + 1, dir, val, sum);
				
				val /= 10;
				visit[nx][ny] = false;
			}
		}
		
		
	}






	private static void print() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++)
				System.out.print(map[i][j]);
			System.out.println();
		}
	}
	
}
