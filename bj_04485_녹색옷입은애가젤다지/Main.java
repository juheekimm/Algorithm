package bj_04485_녹색옷입은애가젤다지;

import java.io.*;
import java.util.*;

public class Main {
	static class Node implements Comparable<Node>{
		int x, y, weight;
		Node (int x, int y, int weight) {
			this.x = x;
			this.y = y;
			this.weight = weight;
		}
		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}
	}
	static int N, map[][], dist[][];
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static PriorityQueue<Node> pq = new PriorityQueue<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int idx = 1;
		while (true) {
			N = Integer.parseInt(br.readLine());
			if (N == 0) break;
			
			dist = new int[N][N];
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					dist[i][j] = Integer.MAX_VALUE;
				}
			}
			
			pq.clear();
			dist[0][0] = map[0][0];
			pq.add(new Node(0, 0, map[0][0]));
			dijkstra();
			sb.append("Problem " + idx++ + ": " + dist[N - 1][N - 1] + "\n");
		}
		System.out.println(sb);
	}

	private static void dijkstra() {
		while (!pq.isEmpty()) {
			Node temp = pq.poll();
			int px = temp.x;
			int py = temp.y;
			
			if (px < 0 || py < 0 || px >= N || py >= N) continue;
			
			if (dist[px][py] < temp.weight) continue;
			
			for (int d = 0; d < 4; d++) {
				int nx = px + dx[d];
				int ny = py + dy[d];
				
				if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
				
				int nowDist = map[nx][ny] + dist[px][py];
				if (dist[nx][ny] <= nowDist) continue;
				
				dist[nx][ny] = nowDist;
				pq.add(new Node(nx, ny, nowDist));
			}
		}
	}
}