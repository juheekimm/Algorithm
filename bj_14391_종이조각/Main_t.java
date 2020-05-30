package bj_14391_종이조각;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//190811. 3h이상. 포기. DFS + 백트래킹 중요한 개념.
//설명 듣고 정리
public class Main_t {

	private static class Node {
		int x, y;

		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int n, m, maxSum = -1;
	static int[][] map;
	static boolean[][] visit;
	static int[] dx = { 0, 1 };
	static int[] dy = { 1, 0 };

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
		dfs(0);
		System.out.println(maxSum);
	}

	private static Node startPoint() {
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				if (!visit[i][j])
					return new Node(i, j);

		return new Node(-1, -1);
	}

	// dir: 0이면 우측, 1이면 아래
	private static void dfs(int val) {

		Node s = startPoint();
		if (s.x == -1) {
			maxSum = Math.max(val, maxSum);
			return;
		}
		// 오른쪽으로 진행
		for (int ny = s.y; ny < m; ny++) {
			// 들어올 때 이미 방문 안한 점임. 그러니까 visit 체크
			if (visit[s.x][ny]) {
				return;
			}
			dfs(val + createPaper(s.x, s.y, 0, ny - s.y));
			for (int j = 0; j <= ny - s.y; j++) { // 후처리
				visit[s.x][s.y + j] = false;
			}
			
		}
		// 아래쪽으로 진행
		for (int nx = s.x + 1; nx < n; nx++) {
			// 들어올 때 이미 방문 안한 점임. 그러니까 visit 체크
			if (visit[nx][s.y]) {
				return;
			}
			dfs(val + createPaper(s.x, s.y, 1, nx - s.x));
			for (int i = 0; i <= nx - s.x; i++) { // 후처리
				visit[s.x + i][s.y] = false;
			}
		}
	}

	private static int createPaper(int x, int y, int dir, int size) {
		int allSum = 0;

		for (int i = 0; i <= size; i++) {
			int nx = x + dx[dir] * i;
			int ny = y + dy[dir] * i;
			visit[nx][ny] = true;
			allSum = map[nx][ny] + allSum * 10;
		}
		return allSum;
	}

	private static void print() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++)
				System.out.print(map[i][j]);
			System.out.println();
		}
	}
}