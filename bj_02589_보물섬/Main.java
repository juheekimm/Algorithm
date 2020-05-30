package bj_02589_보물섬;

import java.io.*;
import java.util.*;

//190814 2h 이중 BFS 148020 356
//일단 단지번호붙이기처럼 연결된 구역에 A부터 차례로 표시하고
//단지마다 돌면서 visit을 사용해서 최단 경로를 찾고

//main_bfs_one 참고->이거 다 필요 없음.. bfs의 특성 자체가 visit을 이용해서 무조건 최단 경로를 찾는 것이기 때문에
//그냥 startpoint로 출발점마다 그때마다 visit 초기화시키면서 bfs 돌리고 step 가장 긴 거 뽑으면 끝임.
public class Main {
	static class Node {
		int x, y, step;
		Node(int x, int y, int step) {
			this.x = x;
			this.y = y;
			this.step = step;
		}
	}
	static char ch = 'A';
	static int n, m, maxCount = 0;
	static int[] dx = { 0, -1, 0, 1 };
	static int[] dy = { -1, 0, 1, 0 };
	static char[][] map;
	static boolean[][] visit;
	static Queue<Node> q = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new char[n][m];

		for (int i = 0; i < n; i++) {
			char[] ch = br.readLine().toCharArray();
			for (int j = 0; j < m; j++)
				map[i][j] = ch[j];
		}
		startPoint();
//		for (int i = 0; i < n; i++)
//			System.out.println(Arrays.toString(map[i]));
		searchMaxRoute();
		System.out.println(maxCount);
	}
	
	//시작점 찾아서
	private static void startPoint() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 'L') {
					q.add(new Node(i, j, 1));
					drawRouteBfs();
					ch++;
				}
			}
		}
	}
	//단지마다 구역표시하고
	private static void drawRouteBfs() {
		while (!q.isEmpty()) {
			Node temp = q.poll();
			int nx, ny;
			for (int d = 0; d < 4; d++) {
				nx = temp.x + dx[d];
				ny = temp.y + dy[d];

				if (nx < 0 || nx >= n || ny < 0 || ny >= m || map[nx][ny] != 'L')
					continue;
			
				map[nx][ny] = ch;
				q.add(new Node(nx, ny, temp.step + 1));
			}
		}
	}
	//구역마다 가장 긴 경로를 찾는다. 한 구역을 모두 돌면서 시작점마다 BFS를 돌린다.
	private static void searchMaxRoute() {
		while (--ch >= 65) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (map[i][j] == ch) {
						visit = new boolean[n][m];
						
						visit[i][j] = true;
						q.add(new Node(i, j, 0));
						maxCount = Math.max(maxCount, routeCountBfs(ch));
					}
				}
			}
		}
	}
	//한 BFS에서 가장 긴 경로를 찾는다.
	private static int routeCountBfs(char c) {
		int max = 0;
		while (!q.isEmpty()) {
			Node temp = q.poll();
			max = Math.max(max, temp.step);
			int nx, ny;
			for (int d = 0; d < 4; d++) {
				nx = temp.x + dx[d];
				ny = temp.y + dy[d];

				if (nx < 0 || nx >= n || ny < 0 || ny >= m || visit[nx][ny] || map[nx][ny] != c)
					continue;
				
				visit[nx][ny] = true;
//				visitPrint();
				q.add(new Node(nx, ny, temp.step + 1));
			}
		}
		return max;
	}
	
//	private static void visitPrint() {
//		for (int i = 0; i < n; i++) {
//			for (int j = 0; j < m; j++) {
//				System.out.print((visit[i][j] ? 1 : 0) + " ");
//			}
//			System.out.println();
//		}
//		System.out.println();
//	}
}