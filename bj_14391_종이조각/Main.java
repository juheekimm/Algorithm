package bj_14391_종이조각;

import java.io.*;
import java.util.StringTokenizer;

//190811. 실패 후 배운 코드 보고 다시 풀었음
//코드 보고 따라 친 수준. 나중에 하나도 안보고 다시 짜보기
public class Main {
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
	static int[] dx = { 0, 1 }; //index 0이 오른쪽, 1이 아래쪽
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
			for (int j = 0; j < m; j++)
				map[i][j] = temp.charAt(j) - '0';
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
	
	private static void dfs(int sum) {
		Node s = startPoint();
		if (s.x == -1) {
			maxSum = maxSum > sum ? maxSum : sum;
			return;
		}
		
		for (int ny = s.y; ny < m; ny++) {
			if (visit[s.x][ny]) return;
			
			dfs(sum + createSquare(s.x, s.y, ny - s.y, 0));
			
			for (int j = s.y; j <= ny; j++)
				visit[s.x][j] = false;
		}
		
		for (int nx = s.x + 1; nx < n; nx++) {
			if (visit[nx][s.y]) return;
			
			dfs(sum + createSquare(s.x, s.y, nx - s.x, 1));
			
			for (int i = s.x; i <= nx; i++)
				visit[i][s.y] = false;
		}
	}

	private static int createSquare(int x, int y, int size, int dir) {
		int squareSum = 0;
		int nx, ny;
		for (int i = 0; i <= size; i++) {
			nx = x + dx[dir] * i;
			ny = y + dy[dir] * i;
			visit[nx][ny] = true;
			squareSum = squareSum * 10 + map[nx][ny];
		}
		return squareSum;
	}
}