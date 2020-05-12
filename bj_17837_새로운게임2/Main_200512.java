package bj_17837_새로운게임2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_200512 {
	
	static class Node {
		int num, x, y, dir, now;

		public Node(int num, int x, int y, int dir, int now) {
			super();
			this.num = num;
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.now = now;
		}
	}
	
	static int N, K, color[][];
	static LinkedList<Node>[][] map;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static Node[] horse;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		color = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++)
				color[i][j] = Integer.parseInt(st.nextToken());
		}
		
		map = new LinkedList[N][N];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				map[i][j] = new LinkedList<>();
		
		horse = new Node[K + 1];
		for (int i = 1; i <= K; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken());
			if (d == 1) d = 0;
			else if (d == 4) d = 1;
			horse[i] = new Node(i, x, y, d, 0);
			map[x][y].add(horse[i]);
		}
		System.out.println(move());
	}

	private static int move() {
		int turn = 0;
		while (true) {
			if (++turn > 1000) return -1;
			for (int i = 1; i <= K; i++) {
				
				Node temp = horse[i];
				int nx = temp.x + dx[temp.dir];
				int ny = temp.y + dy[temp.dir];
				
				if (nx < 0 || ny < 0 || nx >= N || ny >= N || color[nx][ny] == 2) {
					temp.dir = (temp.dir + 2) % 4;
					nx = temp.x + dx[temp.dir];
					ny = temp.y + dy[temp.dir];
					
					if (nx < 0 || ny < 0 || nx >= N || ny >= N || color[nx][ny] == 2) continue;
				}
				
				if (color[nx][ny] == 0) {
					int size = map[temp.x][temp.y].size() - temp.now;
					int now = temp.now;
					int x = temp.x;
					int y = temp.y;
					for (int j = 0; j < size; j++) {
						map[x][y].get(now).now = map[nx][ny].size();
						map[nx][ny].add(map[x][y].remove(now));
						map[nx][ny].getLast().x = nx;
						map[nx][ny].getLast().y = ny;
					}
					if (map[nx][ny].size() >= 4) return turn;
					
				} else if (color[nx][ny] == 1) {
					int size = map[temp.x][temp.y].size() - temp.now;
					int x = temp.x;
					int y = temp.y;
					for (int j = 0; j < size; j++) {
						map[x][y].get(map[x][y].size() - 1).now = map[nx][ny].size();
						map[nx][ny].add(map[x][y].remove(map[x][y].size() - 1));
						map[nx][ny].getLast().x = nx;
						map[nx][ny].getLast().y = ny;
					}
					if (map[nx][ny].size() >= 4) return turn;
				}
			}
		}
	}
}