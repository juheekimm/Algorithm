package bj_19238_스타트택시;

import java.io.*;
import java.util.*;

public class Main_201014_주석 {
	
	static class Node implements Comparable<Node>{
		int x, y, dist, num;
		Node (int x, int y, int dist, int num) {
			this.x = x;
			this.y = y;
			this.dist = dist;
			this.num = num;
		}
		@Override
		public int compareTo(Node o) {
			if (this.dist == o.dist) {
				if (this.x == o.x) {
					return this.y - o.y;
				} else {
					return this.x - o.x;
				}
			} else {
				return this.dist - o.dist;
			}
		}
	}
	
	static int N, M, oil, map[][], dist[], people[][], endPoint[][], taxix, taxiy;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static boolean[][] visit;
	static Queue<Node> q = new LinkedList<>();
	static PriorityQueue<Node> pq = new PriorityQueue<>();
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		oil = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		visit = new boolean[N][N];
		
		people = new int[M][2];
		endPoint = new int[M][2];
		dist = new int[M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		taxix = Integer.parseInt(st.nextToken()) - 1;
		taxiy = Integer.parseInt(st.nextToken()) - 1;
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			people[i][0] = Integer.parseInt(st.nextToken()) - 1;
			people[i][1] = Integer.parseInt(st.nextToken()) - 1;
			endPoint[i][0] = Integer.parseInt(st.nextToken()) - 1;
			endPoint[i][1] = Integer.parseInt(st.nextToken()) - 1;
		}
		getAllStartToEndDistance();
		
		for (int t = 0; t < M; t++) {
			pq.clear();
			getTaxiToAllPeopleDistance();
			Node temp = pq.poll();
			System.out.println("Before " + oil);
			oil -= temp.dist + dist[temp.num];
			System.out.println("태우러 " + temp.dist + "목적지까지 " + dist[temp.num]);
			System.out.println("After " + oil);
			if (oil < 0) {
				oil = -1;
				break;
			} else {
				oil += dist[temp.num] * 2;
				taxix = endPoint[temp.num][0];
				taxiy = endPoint[temp.num][1];
				people[temp.num][0] = -1;
				System.out.println("Refill " + oil + "\n");
				
			}
		}
		System.out.println(oil);
	}


	private static void getTaxiToAllPeopleDistance() {
		for (int i = 0; i < M; i++) {
			if (people[i][0] == -1) continue;
			for (int n = 0; n < N; n++) {
				Arrays.fill(visit[n], false);
			}
			q.clear();
			visit[taxix][taxiy] = true;
			q.add(new Node(taxix, taxiy, 1, i));
			bfs(people[i][0], people[i][1], false);
		}
	}


	private static void getAllStartToEndDistance() {
		for (int i = 0; i < M; i++) {
			for (int n = 0; n < N; n++) {
				Arrays.fill(visit[n], false);
			}
			q.clear();
			visit[people[i][0]][people[i][1]] = true;
			q.add(new Node(people[i][0], people[i][1], 1, i));
			bfs(endPoint[i][0], endPoint[i][1], true);
		}
	}


	private static void bfs(int ex, int ey, boolean allDist) {
		while (!q.isEmpty()) {
			Node temp = q.poll();
			for (int d = 0; d < 4; d++) {
				int nx = temp.x + dx[d];
				int ny = temp.y + dy[d];
				
				if (nx < 0 || nx >= N || ny < 0 || ny >= N || visit[nx][ny] || map[nx][ny] == 1)
					continue;
				
				if (nx == ex && ny == ey) {
					if (allDist)
						dist[temp.num] = temp.dist;
					else
						pq.add(new Node(nx, ny, temp.dist, temp.num));
					return;
				}
				
				visit[nx][ny] = true;
				q.add(new Node(nx, ny, temp.dist + 1, temp.num));
			}
		}
	}
}




























