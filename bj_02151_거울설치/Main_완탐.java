package bj_02151_거울설치;

import java.io.*;
import java.util.*;

public class Main_완탐 {
	private static class Node {
		int x, y, dir;
		Node (int x, int y) {
			this.x = x;
			this.y = y;
		}
		Node (int x, int y, int dir) {
			this(x, y);
			this.dir = dir;
		}
	}
	
	static int n, mirrorSize, permu[];
	static char[][] map;
	static boolean[][] visit;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static Node start, end;
	static ArrayList<Node> list = new ArrayList<>();
	static Queue<Node> q = new LinkedList<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		map = new char[n][n];
		visit = new boolean[n][n];
		
		int dir = 0;
		for (int i = 0; i < n; i++) {
			char[] ch = br.readLine().toCharArray();
			for (int j = 0; j < n; j++) {
				map[i][j] = ch[j];
				
				if (map[i][j] == '!') {
					list.add(new Node(i, j));
					map[i][j] = '.';
				}
				
				if (map[i][j] == '#') {
					if (start == null) {
						start = new Node(i, j);
					} else {
						end = new Node(i, j);
					}
				}
			}
		}
		mirrorSize = list.size();
		permu = new int[mirrorSize];

		for (int i = 0; i <= mirrorSize; i++) {	//거울이 없을 수도 있으니까 0부터
			permu(i, 0, 0);
		}
	}

	private static void permu(int permusize, int k, int idx) {
		if (permusize == k) {
//			permu[0] = 2;
//			permu[1] = 9;
//			permu[2] = 10;
//			permu[3] = 12;
//			permu[4] = 13;
			
//			System.out.println(Arrays.toString(permu));
			mirrorDraw('@', permusize);
			
			for (int i = 0; i < 4; i++) {
				q.add(new Node(start.x, start.y, i));
			}
			if (mirrorTest()) {
				System.out.println(permusize);
				System.exit(0);
			}
			
			for (int i = 0; i < n; i++) {
				Arrays.fill(visit[i], false);
			}
//			print();
			
			mirrorDraw('.', permusize);	//백트래킹
			return;
		}
		
		for (int i = idx; i < mirrorSize; i++) {
			permu[k] = i;
			permu(permusize, k + 1, i + 1);
			permu[k] = 0;
		}
		
	}

	private static void print() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}

	private static boolean mirrorTest() {
		Node temp;
		int nx, ny;
		
		while (!q.isEmpty()) {
			temp = q.poll();
			
			nx = temp.x + dx[temp.dir];
			ny = temp.y + dy[temp.dir];
			
			if (nx < 0 || nx >= n || ny < 0 || ny >= n || visit[nx][ny])
				continue;
				
			
			if (map[nx][ny] == '.') {
				while (true) {
					nx += dx[temp.dir];
					ny += dy[temp.dir];
					
					if (nx < 0 || nx >= n || ny < 0 || ny >= n || visit[nx][ny] || map[nx][ny] != '.') {
						nx -= dx[temp.dir];
						ny -= dy[temp.dir];
						break;
					}
				}
				visit[nx][ny] = true;
//				System.out.printf("nx:%d ny:%d\n", nx, ny);
				q.add(new Node(nx, ny, temp.dir));
			} else if (map[nx][ny] == '@') {
				if (temp.dir / 2 == 0) {
					q.add(new Node(nx, ny, 2));
					q.add(new Node(nx, ny, 3));
				} else if (temp.dir / 2 == 1) {
					q.add(new Node(nx, ny, 0));
					q.add(new Node(nx, ny, 1));
				}
				visit[nx][ny] = true;
			} else if (map[nx][ny] == '*') {
				continue;
			} else if (nx == end.x && ny == end.y) {
				return true;
			}
		}
		return false;
	}
	
	private static void mirrorDraw(char c, int size) {
		for (int i = 0; i < size; i++) {
			map[list.get(permu[i]).x][list.get(permu[i]).y] = c;
		}
	}
}