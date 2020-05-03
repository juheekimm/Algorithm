package bj_16236_아기상어;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	private static class Node {
		int x, y, size, nowSize, step;

		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		public Node(int x, int y, int step) {
			this(x, y);
			this.step = step;
		}
		public Node(int x, int y, int size, int nowSize) {
			this(x, y);
			this.size = size;
			this.nowSize = nowSize;
		}
	}
	private static int N, rsltTime = 0, map[][];
	private static int[] dx = {0, -1, 0, 1}, dy = {1, 0, -1, 0};
	private static boolean[][] visit;
	private static Node shark;
	private static Queue<Node> q, eat;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		q = new LinkedList<>();
		eat = new LinkedList<>();
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visit = new boolean[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 9) {
					shark = new Node(i, j, 2, 0);
					q.add(new Node(i, j, 0));
					visit[i][j] = true;
				}
			}
		}
		bfs();
		System.out.println(rsltTime);
	}

	private static void bfs() {
		boolean eatFishExist = true;
		while (eatFishExist) {
			eatFishExist = false;
			int size = q.size();

			for (int i = 0; i < size; i++) {
				Node temp = q.poll();
				for (int d = 0; d < 4; d++) {
					int nx = temp.x + dx[d];
					int ny = temp.y + dy[d];
					
					if (nx < 0 || nx >= N || ny < 0 || ny >= N || visit[nx][ny]) continue;
					
					if (map[nx][ny] != 0) {
						if (shark.size > map[nx][ny]) {
							eat.add(new Node(nx, ny, temp.step + 1));
						} else if (shark.size < map[nx][ny]) {
							continue;
						}
					}
					//물고기가 있든없든 큐에 넣고 visit 표시 
					visit[nx][ny] = true;
					q.add(new Node(nx, ny, temp.step + 1));
					eatFishExist = true;
				}
			}
			
			if (!eat.isEmpty()) {
				fishEat();
				visit = new boolean[N][N];
				q.clear();
				q.add(new Node(shark.x, shark.y));
				visit[shark.x][shark.y] = true;
			}
				
		}
	}

	private static void fishEat() {
		Node now = eat.poll();
		while (!eat.isEmpty()) {
			Node temp = eat.poll();
			if (now.x > temp.x) {
				now = temp;
			} else if (now.x == temp.x) {
				if (now.y > temp.y) {
					now = temp;
				}
			}
		}
		rsltTime += now.step;
		map[shark.x][shark.y] = 0;
		shark.nowSize++;
		if (shark.size == shark.nowSize) {
			shark.size++;
			shark.nowSize = 0;
		}
		shark.x = now.x;
		shark.y = now.y;
		map[shark.x][shark.y] = 9;
	}
}