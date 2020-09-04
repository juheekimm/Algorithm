package bj_14502_연구소;

import java.io.*;
import java.util.*;

public class Main_200902 {
	
	static class Node {
		int x, y;
		Node (int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int N, M, count, maxCount = 0, combi[], map[][], copyMap[][], SIZE;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static ArrayList<Node> emptyList = new ArrayList<Node>();
	static ArrayList<Node> virusList = new ArrayList<Node>();
	static Queue<Node> q = new LinkedList<Node>(); 
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		combi = new int[3];
		map = new int[N][M];
		copyMap = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 0)
					emptyList.add(new Node(i, j));
				else if (map[i][j] == 2)
					virusList.add(new Node(i, j));
			}
		}
		SIZE = emptyList.size();
		combi(3, 0, 0);
		System.out.println(maxCount);
	}

	private static void combi(int n, int k, int idx) {
		if (n == k) {
			count = SIZE - 3;
			copyMapMake();
			
			q.clear();
			for (int i = 0; i < virusList.size(); i++)
				q.add(virusList.get(i));
			
			bfs();
			if (maxCount < count) maxCount = count;
			return;
		}
		
		for (int i = idx; i < SIZE; i++) {
			combi[k] = i;
			combi(n, k + 1, i + 1);
			//combi[k] = 0;
		}
	}

	private static void bfs() {
		while (!q.isEmpty()) {
			Node temp = q.poll();
			for (int d = 0; d < 4; d++) {
				int nx = temp.x + dx[d];
				int ny = temp.y + dy[d];
				
				if (nx < 0 || nx >= N || ny < 0 || ny >= M || copyMap[nx][ny] != 0)
					continue;
				
				count--;
				copyMap[nx][ny] = 2;
				q.add(new Node(nx, ny));
			}
		}
	}

	private static void copyMapMake() {
		for (int i = 0; i < N; i++)
			copyMap[i] = map[i].clone();
		
		for (int i = 0; i < 3; i++)
			copyMap[emptyList.get(combi[i]).x][emptyList.get(combi[i]).y] = 1;
	}
}