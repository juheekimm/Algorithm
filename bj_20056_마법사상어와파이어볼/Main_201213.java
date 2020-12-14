package bj_20056_마법사상어와파이어볼;

import java.io.*;
import java.util.*;

public class Main_201213 {
	
	static class Node {
		int m, s, d;
		Node (int m, int s, int d) {
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}
	static int N, M, K;
	static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
	static LinkedList<Node>[][] map, tempMap;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new LinkedList[N][N];
		tempMap = new LinkedList[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = new LinkedList<Node>();
				tempMap[i][j] = new LinkedList<Node>();
			}
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			map[r][c].add(new Node(m, s, d));
		}
		
		for (int i = 0; i < K; i++) {
			ballMove();
			drawMap();
		}
		System.out.println(getSum());
	}

	private static int getSum() {
		int sum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < map[i][j].size(); k++) {
					sum += map[i][j].get(k).m;
				}
			}
		}
		return sum;
	}

	private static void drawMap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (tempMap[i][j].size() == 1) {
					map[i][j].add(tempMap[i][j].removeFirst());
				} else if (tempMap[i][j].size() > 1) {
					int weigthSum = tempMap[i][j].get(0).m;
					int speedSum = tempMap[i][j].get(0).s;
					int prevDir = tempMap[i][j].get(0).d % 2;
					boolean sameDir = true;
					
					for (int k = 1; k < tempMap[i][j].size(); k++) {
						weigthSum += tempMap[i][j].get(k).m;
						speedSum += tempMap[i][j].get(k).s;
						if (prevDir != tempMap[i][j].get(k).d % 2) 
							sameDir = false;
					}
					
					if (weigthSum / 5 == 0) {
						tempMap[i][j].clear();
						continue;
					}
					
					int initDir = sameDir ? 0 : 1;
					for (int k = 0; k < 4; k++) {
						map[i][j].add(new Node(weigthSum / 5, speedSum / tempMap[i][j].size(), initDir + k * 2));
					}
					tempMap[i][j].clear();
				}
			}
		}
	}

	private static void ballMove() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0, size = map[i][j].size(); k < size; k++) {
					int nx = (i + map[i][j].get(0).s * dx[map[i][j].get(0).d] + 1000 * N) % N;
					int ny = (j + map[i][j].get(0).s * dy[map[i][j].get(0).d] + 1000 * N) % N;
					tempMap[nx][ny].add(map[i][j].removeFirst());
				}
			}
		}
	}
}