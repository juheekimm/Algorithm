package bj_17142_연구소3;

import java.io.*;
import java.util.*;

public class Main_200902 {
	
	static class Node {
		int x, y, step;
		Node (int x, int y, int step) {
			this.x = x;
			this.y = y;
			this.step = step;
		}
	}
	
	static int N, M, combi[], map[][], copyMap[][], min = Integer.MAX_VALUE;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static ArrayList<Node> virus = new ArrayList<Node>();
	static Queue<Node> q = new LinkedList<Node>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		
		combi = new int[M];
		map = new int[N][N];
		copyMap = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = stoi(st.nextToken());
				if (map[i][j] == 2)
					virus.add(new Node(i, j, 1));
				
				if (map[i][j] == 0)
					map[i][j] = -2;
				else if (map[i][j] == 1)
					map[i][j] = -3;
				else
					map[i][j] = -1;
			}
		}
		
		combi(M, 0, 0);
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	}

	private static void combi(int n, int k, int idx) {
		if (n == k) {
			q.clear();
			copyMapMake();
			int now = bfs();
			if (check())
				if (min > now)
					min = now;
			return;
		}
		
		for (int i = idx, size = virus.size(); i < size; i++) {
			combi[k] = i;
			combi(n, k + 1, i + 1);
		}
	}

	private static boolean check() {
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				if (copyMap[i][j] == -2)
					return false;
			
		return true;
	}

	private static int bfs() {
		int count = 0;
		while (!q.isEmpty()) {
			Node temp = q.poll();
			for (int d = 0; d < 4; d++) {
				int nx = temp.x + dx[d];
				int ny = temp.y + dy[d];
				
				if (nx < 0 || nx >= N || ny < 0 || ny >= N || copyMap[nx][ny] < -2 || copyMap[nx][ny] > -1)
					continue;
				
				if (copyMap[nx][ny] == -1)
					if (check())
						return count;
				
				count = temp.step;
				copyMap[nx][ny] = temp.step;
				q.add(new Node(nx, ny, temp.step + 1));
			}
		}
		return count;
	}

	private static void copyMapMake() {
		for (int i = 0; i < N; i++)
			copyMap[i] = map[i].clone();
		
		for (int i = 0; i < M; i++) {
			Node temp = virus.get(combi[i]);
			copyMap[temp.x][temp.y] = 0;
			q.add(temp);
		}
	}

	private static int stoi(String str) {
		return Integer.parseInt(str);
	}
}