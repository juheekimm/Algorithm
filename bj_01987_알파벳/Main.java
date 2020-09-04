package bj_01987_알파벳;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int r, c, maxCnt = 0;
	static char[][] map;
	static int[] dx = {0, -1, 0, 1};
	static int[] dy = {-1, 0, 1, 0};
	static boolean[] apbCheck = new boolean[26];
	static boolean[][] visit;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		map = new char[r][c];
		visit = new boolean[r][c];
	
		for (int i = 0; i < r; i++) {
			map[i] = br.readLine().toCharArray();
			System.out.println(Arrays.toString(map[i]));
		}
		visit[0][0] = true;
		apbCheck[map[0][0] - 97] = true;
		dfs(0, 0, 1);
	}

	private static void dfs(int x, int y, int cnt) {
		int nx, ny;
		for (int d = 0; d < 4; d++) {
			nx = x + dx[d];
			ny = y + dy[d];
		
			if (nx < 0 || nx >= r || ny < 0 || ny >= c || visit[nx][ny] || apbCheck[map[nx][ny] - 97])
				continue;
			
			visit[nx][ny] = true;
			apbCheck[map[nx][ny] - 97] = true;
			dfs(nx, ny, cnt + 1);
			visit[nx][ny] = false;
			apbCheck[map[nx][ny] - 97] = false;
			
		}
		
	}

}
