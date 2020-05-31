package bj_17472_다리만들기2;

import java.io.*;
import java.util.*;

public class Main_200531 {
	
	static class Node implements Comparable<Node> {
		int x, y, w;
		Node (int x, int y, int w) {
			this.x = x;
			this.y = y;
			this.w = w;
		}
		@Override
		public int compareTo(Node o) {
			return this.w - o.w;
		}
	}
	
	static int N, M, size = 0, map[][], bridge[][], parents[], min = 0;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static PriorityQueue<Node> pq = new PriorityQueue<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1)
					map[i][j] = -1;
			}
		}
		
		startPoint();
		bridge = new int[size][size];
		for (int i = 0; i < size; i++)
			Arrays.fill(bridge[i], 10);
		
		getBridgeLen();
		for (int i = 0; i < size; i++)
			for (int j = i + 1; j < size; j++)
				if (bridge[i][j] != 10)
					pq.add(new Node(i, j, bridge[i][j]));
		
		parents = new int[size];
		Arrays.fill(parents, -1);
		
		if (kruskal())
			System.out.println(min);
		else
			System.out.println(-1);
	}

	private static boolean kruskal() {
		int count = 0;
		while (!pq.isEmpty()) {
			Node temp = pq.poll();
			if (union(temp.x, temp.y)) {
				min += temp.w;
				count++;
			}
			if (count == size - 1) return true;
		}
		return false;
	}

	private static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if (aRoot != bRoot) {
			parents[bRoot] = aRoot;
			return true;
		}
		return false;
	}

	private static int find(int a) {
		if (parents[a] < 0)
			return a;

		return parents[a] = find(parents[a]);
	}

	private static void getBridgeLen() {
		//가로 
		for (int i = 0; i < N; i++) {
			int prev = -1, count = 0;
			for (int j = 0; j < M; j++) {
				if (map[i][j] != 0) {
					if (prev != -1 && count != 1) {
						if (bridge[prev - 1][map[i][j] - 1] > count) {
							bridge[prev - 1][map[i][j] - 1] = count;
							bridge[map[i][j] - 1][prev - 1] = count;
						}
					}
					prev = map[i][j];
					count = 0;
				} else {
					count++;
				}
			}
		}
		//세로
		for (int j = 0; j < M; j++) {
			int prev = -1, count = 0;
			for (int i = 0; i < N; i++) {
				if (map[i][j] != 0) {
					if (prev != -1 && count != 1) {
						if (bridge[prev - 1][map[i][j] - 1] > count) {
							bridge[prev - 1][map[i][j] - 1] = count;
							bridge[map[i][j] - 1][prev - 1] = count;
						}
					}
					prev = map[i][j];
					count = 0;
				} else {
					count++;
				}
			}
		}
	}

	private static void startPoint() {
		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++)
				if (map[i][j] == -1) {
					map[i][j] = ++size;
					dfs(i, j, size);
				}
	}

	private static void dfs(int x, int y, int s) {
		for (int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			if (nx < 0 || nx >= N || ny < 0 || ny >= M || map[nx][ny] != -1)
				continue;	//break와의 차이?
			
			map[nx][ny] = s;
			dfs(nx, ny, s);
		}
	}
}