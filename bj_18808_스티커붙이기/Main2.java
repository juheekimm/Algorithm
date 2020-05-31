package bj_18808_스티커붙이기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2 {

	static int N, M, K, map[][], sticker[][][];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		sticker = new int[K][][];
		
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			sticker[i] = new int[n][m];
			
			for (int j = 0; j < n; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 0; k < m; k++)
					sticker[i][j][k] = Integer.parseInt(st.nextToken());
			}
		}
		simulation();
		System.out.println(numCount());
	}

	private static int numCount() {
		int count = 0;
		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++)
				if (map[i][j] == 1)
					count++;
		return count;
	}

	private static void simulation() {
		int x = 0, y = 0;
		for (int k = 0; k < K; k++) {
			int[] index = search(x, y, k);
			if (index[0] != -1) {
				x = index[0];
				y = index[1];
			}
		}
	}

	private static int[] search(int x, int y, int k) {
		for (int i = x; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (i == x && j < y) continue;
				
				if (map[i][j] == 0) {
					int[][] temp = sticker[k];
					for (int d = 0; d < 4; d++) {
						if (enableCheck(x, y, temp)) {
							attach(x, y, temp);
							return new int[] {i, j};
						} else {
							temp = directionChange(temp);
						}
					}
				}
			}
		}
		for (int i = x; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (i == x && j < y) continue;
				
				if (map[i][j] == 0) {
					int[][] temp = sticker[k];
					for (int d = 0; d < 4; d++) {
						if (enableCheck(x, y, temp)) {
							attach(x, y, temp);
							return new int[] {i, j};
						} else {
							temp = directionChange(temp);
						}
					}
				}
			}
		}
		return new int[] {-1, -1};
	}

	private static int[][] directionChange(int[][] temp) {
		int n = temp.length, m = temp[0].length;
		int[][] change = new int[m][n];
		for (int i = n - 1; i >= 0; i--)
			for (int j = 0; j < m; j++)
				change[j][n - 1 - i] = temp[i][j];
		return change;
	}

	private static void attach(int x, int y, int[][] temp) {
		int n = temp.length, m = temp[0].length;
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				map[x + i][y + j] = temp[i][j];
	}

	private static boolean enableCheck(int x, int y, int[][] temp) {
		int n = temp.length, m = temp[0].length;
		if (x + n >= N || y + m >= M) return false;
		
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				if (map[x + i][y + j] == 1)
					return false;
		
		return true;
	}
}