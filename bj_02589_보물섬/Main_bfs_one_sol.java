package bj_02589_보물섬;

import java.io.*;
import java.util.*;

//bfs의 특성 자체가 visit을 이용해서 무조건 최단 경로를 찾는 것이기 때문에
//그냥 startpoint로 출발점마다 그때마다 visit 초기화시키면서 bfs 돌리고 step 가장 긴 거 뽑으면 끝임.
public class Main_bfs_one_sol {
	static class Node {
		int x, y, step;
		Node(int x, int y, int step) {
			this.x = x;
			this.y = y;
			this.step = step;
		}
	}
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
		System.out.println(maxCount);
	}
	
	//시작점 찾아서
	private static void startPoint() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
 				visit = new boolean[n][m];
				visit[i][j] = true;
				q.add(new Node(i, j, 0));
				searchMaxRoute();
			}
		}
	}
	
	//bfs돌면서 최장 경로 찾는다
	private static void searchMaxRoute() {
		int max = -1;
		while (!q.isEmpty()) {
			Node temp = q.poll();
			max = Math.max(max, temp.step);
			int nx, ny;
			for (int d = 0; d < 4; d++) {
				nx = temp.x + dx[d];
				ny = temp.y + dy[d];

				if (nx < 0 || nx >= n || ny < 0 || ny >= m || visit[nx][ny] || map[nx][ny] != 'L')
					continue;
			
				visit[nx][ny] = true;
				q.add(new Node(nx, ny, temp.step + 1));
			}
		}
		maxCount = Math.max(maxCount, max);
	}
}