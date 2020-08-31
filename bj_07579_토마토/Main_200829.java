package bj_07579_토마토;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_200829 {
	
	static class Node {
		int x, y, z;
		Node (int z, int x, int y) {
			this.z = z;
			this.x = x;
			this.y = y;
		}
	}
	static int h, n, m, count = 0, map[][][];
	static int[] dx = {-1, 0, 1, 0, 0, 0};
	static int[] dy = {0, 1, 0, -1, 0, 0};
	static int[] dz = {0, 0, 0, 0, -1, 1};
	
	static Queue<Node> queue = new LinkedList<Node>();
	
	public static void main(String[] args) throws Exception {
		//h n m 순서 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		
		map = new int[h][n][m];
		
		for (int k = 0; k < h; k++) {
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < m; j++) {
					map[k][i][j] = Integer.parseInt(st.nextToken());
					if (map[k][i][j] == 1) {
						queue.add(new Node(k, i, j));
					}
				}
			}
		}
		bfs();
		check();
	}

	private static void check() {
		for (int k = 0; k < h; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {	
					if (map[k][i][j] == 0) {
						System.out.println(-1);
						return;
					}
				}
			}
		}
		System.out.println(count - 1);
	}

	private static void bfs() {
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				
				Node temp = queue.poll();
				for (int d = 0; d < 6; d++) {
					int nz = temp.z + dz[d];
					int nx = temp.x + dx[d];
					int ny = temp.y + dy[d];
					
					if (nz < 0 || nz >= h || nx < 0 || nx >= n || ny < 0 || ny >= m || map[nz][nx][ny] != 0)
						continue;
					
					map[nz][nx][ny] = 1;
					queue.add(new Node(nz, nx, ny));
				}
			}
			count++;
		}
	}
}