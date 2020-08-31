package bj_07576_토마토;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_200826 {

	static class Node {
		int x, y;
		Node (int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int N, M, count = 0, map[][];
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static Queue<Node> queue = new LinkedList<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1)
					queue.add(new Node(i, j));
			}
		}
		bfs();
		checkTomato();
	}

	private static void checkTomato() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0) {
					System.out.println("-1");
					return;
				}
			}
		}
		System.out.println(count - 1);
	}

	private static void bfs() {
		int nx, ny;
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {				

				Node temp = queue.poll();
				for (int d = 0; d < 4; d++) {
					nx = temp.x + dx[d];
					ny = temp.y + dy[d];
					
					if (nx < 0 || nx >= N || ny < 0 || ny >= M || map[nx][ny] != 0) continue;
					
					map[nx][ny] = 1;
					queue.add(new Node(nx, ny));
				}
			}
			count++;
		}
	}
}