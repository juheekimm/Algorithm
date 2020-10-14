package bj_17837_새로운게임2;

import java.io.*;
import java.util.*;

public class Main_201014 {
	
	static class Horse {
		int x, y, d;
		public Horse(int x, int y, int d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}
	}
	static LinkedList<Integer>[][] map;
	static Horse[] horses;
	static int N, K, color[][];
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = stoi(st.nextToken());
		K = stoi(st.nextToken());
		
		color = new int[N][N];
		map = new LinkedList[N][N];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				map[i][j] = new LinkedList<Integer>();
		
		horses = new Horse[K];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++)
				color[i][j] = stoi(st.nextToken());
		}
		
		int x, y, d;
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			x = stoi(st.nextToken()) - 1;
			y = stoi(st.nextToken()) - 1;
			d = stoi(st.nextToken());
			if (d == 4) d = 1;
			else if (d == 1) d = 0;
			horses[i] = new Horse(x, y, d);
			map[x][y].add(i);
		}
		
		System.out.println(simulation());
	}

	private static int simulation() {
		int turn = 0;
		int x, y, d, nx, ny;
		
		while (++turn <= 1000) {
			for (int i = 0; i < K; i++) {
				x = horses[i].x;
				y = horses[i].y;
				d = horses[i].d;
			
				nx = x + dx[d];
				ny = y + dy[d];
				
				if (nx < 0 || nx >= N || ny < 0 || ny >= N || color[nx][ny] == 2) {
					d = (d + 2) % 4;
					nx = x + dx[d];
					ny = y + dy[d];
					horses[i].d = d;
					
					if (nx < 0 || nx >= N || ny < 0 || ny >= N || color[nx][ny] == 2)
						continue;
				}
				
				if (move(i, x, y, nx, ny, color[nx][ny] == 1))
					return turn;
			}
		}
		return -1;
	}

	private static boolean move(int n, int x, int y, int nx, int ny, boolean isReverse) {
		int order = searchHorse(n, x, y);
		for (int i = order, len = map[x][y].size(); i < len; i++) {
			if (isReverse) {	//빨간색 
				int num = map[x][y].getLast();
				horses[num].x = nx;
				horses[num].y = ny;
				map[nx][ny].add(map[x][y].removeLast());
			} else {			//흰색 
				int num = map[x][y].get(order);
				horses[num].x = nx;
				horses[num].y = ny;
				map[nx][ny].add(map[x][y].remove(order));
			}
		}
		if (map[nx][ny].size() >= 4) return true;
		else return false;
	}

	private static int searchHorse(int n, int x, int y) {
		for (int i = 0; i < map[x][y].size(); i++)
			if (map[x][y].get(i) == n)
				return i;
		return 0;
	}

	private static int stoi(String str) {
		return Integer.parseInt(str);
	}
}