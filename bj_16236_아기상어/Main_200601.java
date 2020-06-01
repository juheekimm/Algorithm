package bj_16236_아기상어;

import java.io.*;
import java.util.*;

public class Main_200601 {
	
	static class Node implements Comparable<Node> {
		int x, y, size, nowEat, dist;
		Node (int x, int y) {
			this.x = x;
			this.y = y;
		}
		Node (int x, int y, int size, int nowEat, int dist) {
			this(x, y);
			this.size = size;
			this.nowEat = nowEat;
			this.dist = dist;
		}
		@Override
		public int compareTo(Node o) {
			if (this.dist != o.dist)
				return this.dist - o.dist;
			
			if (this.x != o.x)
				return this.x - o.x;
			
			return this.y - o.y;
		}
	}
	
	static int N, map[][], time = 0;
	static int[] dx = {0, -1, 0, 1};
	static int[] dy = {1, 0, -1, 0};
	static boolean[][] visit;
	
	static Node shark;
	static Queue<Node> sharkQ = new LinkedList<>();
	static PriorityQueue<Node> fish = new PriorityQueue<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visit = new boolean[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 9) {
					shark = new Node(i, j, 2, 0, 0);
					visit[i][j] = true;
				}
			}
		}
		sharkQ.add(shark);
		bfs();
		System.out.println(time);
	}

	private static void bfs() {
		while (true) {
			while (!sharkQ.isEmpty()) {
				Node temp = sharkQ.poll();
				for (int d = 0; d < 4; d++) {
					int nx = temp.x + dx[d];
					int ny = temp.y + dy[d];
					int ndist = temp.dist + 1;
					
					if (nx < 0 || nx >= N || ny < 0 || ny >= N || visit[nx][ny] || temp.size < map[nx][ny])
						continue;
					
					if (map[nx][ny] != 0 && temp.size > map[nx][ny])
						fish.add(new Node(nx, ny, temp.size, temp.nowEat, ndist));
					
					visit[nx][ny] = true;
					sharkQ.add(new Node(nx, ny, temp.size, temp.nowEat, ndist));
				}
			}
			if (fish.size() == 0) return;
			
			map[shark.x][shark.y] = 0;
			shark = fish.poll();

			time += shark.dist;
			shark.dist = 0;

			map[shark.x][shark.y] = 9;
			
			for (int i = 0; i < N; i++)
				Arrays.fill(visit[i], false);
			visit[shark.x][shark.y] = true;

			shark.nowEat++;
			if (shark.size == shark.nowEat) {
				shark.size++;
				shark.nowEat = 0;
			}
			fish.clear();
			sharkQ.add(shark);
		}
	}
}