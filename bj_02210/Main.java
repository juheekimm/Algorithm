package bj_02210;

import java.io.*;
import java.util.HashSet;
import java.util.StringTokenizer;

// 190805 갓케 힌트. 20m
public class Main {

	private static String[][] map;
	private static int[] dx = {0, -1, 0, 1};
	private static int[] dy = {-1, 0, 1, 0};
	private static HashSet<String> set = new HashSet<>();
	private static String str;
	private static int count = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		map = new String[5][5];

		for (int i = 0; i < 5; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 5; j++) {
				map[i][j] = st.nextToken();
			}
		}
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				str = "";
				dfs(i, j);
			}
		}
		System.out.println(count);
	}

	private static void dfs(int x, int y) {
		int nx, ny;
		for (int i = 0; i < 4; i++) {
			if (str.length() == 6) {
				duplCheck();
				return;
			}
			nx = x + dx[i];
			ny = y + dy[i];
			
			if (nx < 0 || nx >= 5 || ny < 0 || ny >= 5)
				continue;
			
			str += map[nx][ny];
			dfs(nx, ny);
			str = str.substring(0, str.length() - 1);
		}
	}
		
	private static void duplCheck() {
		if (set.add(str))
			count++;
	}
}