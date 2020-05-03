package bj_07576_토마토;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//200108 15m, 그냥 다 돌고 나서 남아있는 토마토 체크하는 방식. 생각보다 시간차이 별로 안나서 뭘로 해도 상관 없을 듯. 
public class Main_bfs {
	
	private static class Node {
		int x, y, day;
		Node (int x, int y, int day) {
			this.x = x;
			this.y = y;
			this.day = day;
		}
	}
	
	private static int m, n, map[][], maxDay = 1;
	private static int[] dx = {0, -1, 0, 1}, dy = {-1, 0, 1, 0};
	private static Queue<Node> q = new LinkedList<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1)
					q.add(new Node(i, j, 1));
			}
		}
		
		bfs();
		checkTomato();
	}

	private static void checkTomato() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 0) {
					System.out.println("-1");
					System.exit(0);
				}
			}
		}
		System.out.println(maxDay);
	}

	private static void bfs() {
		while (!q.isEmpty()) {
			Node temp = q.poll();
			for (int d = 0; d < 4; d++) {
				int nx = temp.x + dx[d];
				int ny = temp.y + dy[d];
				
				if (nx < 0 || nx >= n || ny < 0 || ny >= m || map[nx][ny] != 0) continue;
				
				map[nx][ny] = 1;
				q.add(new Node(nx, ny, temp.day + 1));
				maxDay = maxDay < temp.day ? temp.day : maxDay;
			}
		}
	}
}