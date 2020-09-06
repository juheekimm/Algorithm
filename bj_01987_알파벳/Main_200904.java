package bj_01987_알파벳;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_200904 {

	static int N, M;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
	static boolean[] alphabet;
	static char[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		alphabet = new boolean[26];

		for (int i = 0; i < N; i++) {
			char[] temp = br.readLine().toCharArray();
			for (int j = 0; j < M; j++)
				map[i][j] = temp[j];
		}

		// 어차피 한번 선택한 문자는 또 못가서 굳이 visit 필요 없음
		alphabet[map[0][0] - 'A'] = true;
		System.out.println(dfs(0, 0));
	}

	private static int dfs(int x, int y) {
		int temp = 1;
		for (int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];

			if (nx < 0 || nx >= N || ny < 0 || ny >= M || alphabet[map[nx][ny] - 'A'])
				continue;

			alphabet[map[nx][ny] - 'A'] = true;
			temp = Math.max(temp, dfs(nx, ny) + 1);
			alphabet[map[nx][ny] - 'A'] = false;
		}
		return temp;
	}
}