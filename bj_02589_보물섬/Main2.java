package bj_02589_보물섬;

import java.io.*;
import java.util.*;

public class Main2 {
	static class Node {
		int x, y, step;
		Node(int x, int y, int step) {
			this.x = x;
			this.y = y;
			this.step = step;
		}
	}
	static char ch = 'A';
	static int n, m, maxCount = -1;
	static int[] dx = { 0, -1, 0, 1 };
	static int[] dy = { -1, 0, 1, 0 };
	static char[][] map;
	static int[][] visit;
	static Queue<Node> q = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new char[n][m];
		visit = new int[n][m];

		for (int i = 0; i < n; i++) {
			char[] ch = br.readLine().toCharArray();
			for (int j = 0; j < m; j++)
				map[i][j] = ch[j];
		}
		startPoint();
		for (int i = 0; i < n; i++)
			System.out.println(Arrays.toString(map[i]));
	}

//	private static void startPoint() {
//		q.clear();
//		q.add(new Node(1, 1, 1));
//		bfs();
//		maxRoute(1, 1);
//	}
	
	private static void startPoint() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 'L') {
					q.clear();
					q.add(new Node(i, j, 0));
					bfs();
					maxRoute(i, j);
				}
			}
		}
	}
	
//	private static void endPoint() {
//		for (int i = 0; i < n; i++) {
//			for (int j = 0; j < m; j++) {
//				if (map[i][j] == 'L') {
//					q.clear();
//				}
//			}
//		}
//	}

	private static void bfs() {
		int minStep = Integer.MAX_VALUE;
		while (!q.isEmpty()) {
			Node temp = q.poll();
			int nx, ny;
			for (int d = 0; d < 4; d++) {
				nx = temp.x + dx[d];
				ny = temp.y + dy[d];

				if (nx < 0 || nx >= n || ny < 0 || ny >= m || map[nx][ny] != 'L')
					continue;
			   
				
				
				
				if (visit[nx][ny] == 0)
					visit[nx][ny] = temp.step;
				else
					visit[nx][ny] = Math.min(visit[nx][ny], temp.step + 1);
//				 System.out.println(visit[nx][ny]);
				if(nx == 4 && ny == 1)
					return;
				q.add(new Node(nx, ny, temp.step + 1));
			}

		}
	}

	private static void maxRoute(int x, int y) {
		int maxi = -1, maxj = -1, maxVal = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (maxVal < visit[i][j]) {
					maxVal = visit[i][j];
					maxi = i;
					maxj = j;
				}
				map[i][j] = ch;
			}
		}
		System.out.printf("x : %d y : %d i : %d j : %d max : %d\n", x, y, maxi, maxj, maxVal);
	}

}
