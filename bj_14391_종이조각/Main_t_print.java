package bj_14391_종이조각;

import java.io.*;
import java.util.StringTokenizer;

//190811. 3h이상. 포기. DFS + 백트래킹 중요한 개념.
//DFS 변화 모습 보기 위해 print부분 추가.
public class Main_t_print {
	
	private static class Node {
		int x, y;
		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static int n, m, maxSum = -1;
	static int[][] map;
	static int[][] depthMap;
	static boolean[][] visit;
	static int[] dx = { 0, 1 };
	static int[] dy = { 1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new int[n][m];
		depthMap = new int[n][m];
		visit = new boolean[n][m];

		for (int i = 0; i < n; i++) {
			String temp = br.readLine();
			for (int j = 0; j < m; j++)
				map[i][j] = temp.charAt(j) - '0';
		}
		
		dfs(0, 1);
		System.out.println(maxSum);
	}

	private static Node startPoint() {
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				if (!visit[i][j])
					return new Node(i, j);

		//for문을 다 돌았는데 모든 점을 다 방문한 상태라서 더 방문할 곳이 없다면 -1을 리턴
		return new Node(-1, -1);
	}

	// dir: 0이면 우측, 1이면 아래
	private static void dfs(int val, int depth) {
		Node s = startPoint();
		if (s.x == -1) {
			maxSum = Math.max(val, maxSum);
			print();
			return;
		}
		
		// 오른쪽으로 진행. 지금 받은 점부터 하나씩 키워감.
		for (int ny = s.y; ny < m; ny++) {
			
			//만약 이번 턴의 점이 이미 방문한 점이라면 return
			if (visit[s.x][ny])
				return;
			
			//지금까지의 값 합계 + 이번 네모의 값 더해주기
			dfs(val + createSquare(s.x, s.y, 0, ny - s.y, depth), depth + 1);
			
			for (int j = 0; j <= ny - s.y; j++) // 후처리
				visit[s.x][s.y + j] = false;
			
		}
		// 아래쪽으로 진행
		for (int nx = s.x + 1; nx < n; nx++) {
			// 들어올 때 이미 방문 안한 점임. 그러니까 visit 체크
			if (visit[nx][s.y]) {
				return;
			}
			dfs(val + createSquare(s.x, s.y, 1, nx - s.x, depth), depth + 1);
			for (int i = 0; i <= nx - s.x; i++) { // 후처리
				visit[s.x + i][s.y] = false;
			}
		}
	}

	private static int createSquare(int x, int y, int dir, int size, int depth) {
		int squareSum = 0;

		for (int i = 0; i <= size; i++) {
			int nx = x + dx[dir] * i;
			int ny = y + dy[dir] * i;
			visit[nx][ny] = true;
			depthMap[nx][ny] = depth;
			squareSum = map[nx][ny] + squareSum * 10;
		}
		return squareSum;
	}

	private static void print() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++)
				System.out.printf("%2d ", depthMap[i][j]);
			System.out.println();
		}
		System.out.println();
	}
}