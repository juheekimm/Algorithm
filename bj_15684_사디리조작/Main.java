package bj_15684_사디리조작;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	private static int N, M, H, map[][];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		map = new int[H + 1][N];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			map[a][b] = 1;
			map[a][b + 1] = -1;
		}
		
		if (searchOddNum() > 3) {
			System.out.println("-1");
			return;
		} else {
			for (int i = 0; i <= 3; i++)
				if (dfs(0, 0, 0, i))
					return;
		}
		System.out.println("-1");
	}

	private static boolean dfs(int x, int y, int cnt, int size) {
		if (cnt == size) {
			if (ladderCheck()) {
				System.out.println(size);
				return true;
			}
			return false;
		}
		
		for (int i = x; i < H; i++) {
			for (int j = y; j < N - 1; j++) {
//				if (map[i][j] != 1 || map[i][j] != -1) continue;	이건 안됨!
				if (map[i][j] != 0 || map[i][j + 1] != 0) continue;
				
				map[i][j] = 1;
				map[i][j + 1] = -1;
				if (dfs(i, j + 1, cnt + 1, size)) return true;
				map[i][j] = 0;
				map[i][j + 1] = 0;
			}
			y = 0;
		}
		return false;
	}

	private static boolean ladderCheck() {
		for (int j = 0; j < N; j++) {
			int nx = 0, ny = j;
			
			while (nx <= H) {	
				if (map[nx][ny] == 1) ny++;
				else if (map[nx][ny] == -1) ny--;
				nx++;
			}
			if (ny != j) return false;
		}
		return true;
	}

	private static int searchOddNum() {
		int oddNum = 0;
		for (int j = 0; j < N - 1; j++) {
			int num = 0;
			for (int i = 0; i < H; i++)
				if (map[i][j] == 1) num++;
			if (num % 2 == 1) oddNum++;
		}
		return oddNum;
	}
}