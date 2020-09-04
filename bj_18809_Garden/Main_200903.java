package bj_18809_Garden;

import java.io.*;
import java.util.*;

public class Main_200903 {
	
	static class Node {
		int x, y, time, color;
		
		Node (int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		Node (int x, int y, int time, int color) {
			this(x, y);
			this.time = time;
			this.color = color;
		}	
	}
	
	static int N, M, G, R, gcombi[], rcombi[], map[][], copyMap[][], colorMap[][], SIZE, count, max = 0;
	static boolean check[];
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	static ArrayList<Node> enableList = new ArrayList<Node>();
	static Queue<Node> greenQ = new LinkedList<Node>();
	static Queue<Node> redQ = new LinkedList<Node>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		G = stoi(st.nextToken());
		R = stoi(st.nextToken());
		
		gcombi = new int[G];
		rcombi = new int[R];
		map = new int[N][M];
		copyMap = new int[N][M];
		colorMap = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = stoi(st.nextToken());
				if (map[i][j] == 2)
					enableList.add(new Node(i, j));
				
				switch (map[i][j]) {
				case 0: map[i][j] = -1;	break;
				case 1: map[i][j] = -2;	break;
				case 2: map[i][j] = -3;	break;
				}
			}
		}
		SIZE = enableList.size();
		check = new boolean[SIZE];
		greenCombi(G, 0, 0);
		System.out.println(max);
	}

	private static void greenCombi(int n, int k, int idx) {
		if (n == k) {
			redCombi(R, 0, 0);
			return;
		}
		
		for (int i = idx; i < SIZE; i++) {
			if (check[i]) continue;
			check[i] = true;
			gcombi[k] = i;
			greenCombi(n, k + 1, i + 1);
			check[i] = false;
		}
	}

	private static void redCombi(int n, int k, int idx) {
		if (n == k) {
			count = 0;
			copyMapMake();
			bfs();
			if (max < count) max = count;
			
			return;
		}
		for (int i = idx; i < SIZE; i++) {
			if (check[i]) continue;
			check[i] = true;
			rcombi[k] = i;
			redCombi(n, k + 1, i + 1);
			check[i] = false;
		}
	}

	private static void bfs() {
		while (!greenQ.isEmpty()) {
			int gsize = greenQ.size();
			for (int i = 0; i < gsize; i++) {
				Node temp = greenQ.poll();
				
				if (copyMap[temp.x][temp.y] == -1) continue;
				
				for (int d = 0; d < 4; d++) {
					int nx = temp.x + dx[d];
					int ny = temp.y + dy[d];
					
					if (nx < 0 || nx >= N || ny < 0 || ny >= M || copyMap[nx][ny] >= -1)
						continue;
					
					copyMap[nx][ny] = temp.time;
					colorMap[nx][ny] = 3;
					greenQ.add(new Node(nx, ny, temp.time + 1, temp.color));
				}
			}
			
			int rsize = redQ.size();
			for (int i = 0; i < rsize; i++) {
				Node temp = redQ.poll();
				for (int d = 0; d < 4; d++) {
					int nx = temp.x + dx[d];
					int ny = temp.y + dy[d];
					
					if (nx < 0 || nx >= N || ny < 0 || ny >= M || copyMap[nx][ny] == -1)
						continue;
					
					if (copyMap[nx][ny] < -1) {
						copyMap[nx][ny] = temp.time;
						colorMap[nx][ny] = 4;
						redQ.add(new Node(nx, ny, temp.time + 1, temp.color));
						
					} else {
						if (copyMap[nx][ny] == temp.time && colorMap[nx][ny] == 3) {
							count++;
							copyMap[nx][ny] = -1;	//괜찮은가..?
							colorMap[nx][ny] = 0;
						}
					}
				}
			}
		}
	}

	private static void copyMapMake() {
		for (int i = 0; i < N; i++) {
			copyMap[i] = map[i].clone();
			Arrays.fill(colorMap[i], 0);
		}
		
		greenQ.clear();
		for (int i = 0; i < G; i++) {
			Node temp = enableList.get(gcombi[i]);
			copyMap[temp.x][temp.y] = 0;
			colorMap[temp.x][temp.y] = 3;
			greenQ.add(new Node(temp.x, temp.y, 1, 3));
		}
		
		redQ.clear();
		for (int i = 0; i < R; i++) {
			Node temp = enableList.get(rcombi[i]);
			copyMap[temp.x][temp.y] = 0;
			colorMap[temp.x][temp.y] = 4;
			redQ.add(new Node(temp.x, temp.y, 1, 4));
		}
	}

	private static int stoi(String str) {
		return Integer.parseInt(str);
	}
}