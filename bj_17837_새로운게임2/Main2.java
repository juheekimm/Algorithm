package bj_17837_새로운게임2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main2 {
	private static class Node {
		int n, d;
		Node (int n, int d) {
			this.n = n;
			this.d = d;
		}
	}
	
	private static int N, K, color[][], horse[][];
	private static LinkedList<Node>[][] map;
	private static int[] dx = {0, 1, 0, -1}, dy = {1, 0, -1, 0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		color = new int[N][N];
		horse = new int[K][2];
		map = new LinkedList[N][N];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				map[i][j] = new LinkedList<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++)
				color[i][j] = Integer.parseInt(st.nextToken());
		}
		
		int a, b, c;
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken()) - 1;
			b = Integer.parseInt(st.nextToken()) - 1;
			c = Integer.parseInt(st.nextToken());
			
			if (c == 1) c = 0;
			else if (c == 4) c = 1;
			
			horse[i][0] = a;
			horse[i][1] = b;
			
			map[a][b].add(new Node(i, c));
		}
		game();
	}

	private static void game() {
		for (int t = 1; t <= 1000; t++) {
			for (int k = 0; k < K; k++) {
				int x = horse[k][0];
				int y = horse[k][1];
				int num = searchOrder(k, x, y);
				int d = map[x][y].get(num).d;
				
				int nx = x + dx[d];
				int ny = y + dy[d];
				
				if (nx < 0 || nx >= N || ny < 0 || ny >= N || color[nx][ny] == 2) {
					map[x][y].get(num).d = d = (d + 2) % 4;
					nx = x + dx[d];
					ny = y + dy[d];
					
					if (nx < 0 || nx >= N || ny < 0 || ny >= N || color[nx][ny] == 2)
						continue;
					
					if (move(x, y, nx, ny, num, 0)) {
						System.out.println(t);
						return;
					}
				} else {
					if (move(x, y, nx, ny, num, color[nx][ny])) {
						System.out.println(t);
						return;
					}
				}
			}
		}
		System.out.println("-1");
	}

	private static boolean move(int x, int y, int nx, int ny, int num, int order) {
		while (map[x][y].size() > num) {
			Node temp = null;
			if (order == 0)
				temp = map[x][y].remove(num);
			else 
				temp = map[x][y].removeLast();
			
			horse[temp.n][0] = nx;
			horse[temp.n][1] = ny;
			map[nx][ny].add(temp);
		}
		
		if (map[nx][ny].size() >= 4)
			return true;
		
		return false;
	}

	private static int searchOrder(int n, int x, int y) {
		for (int i = 0; i < map[x][y].size(); i++)
			if (map[x][y].get(i).n == n)
				return i;
		
		return -1;
	}
}






















