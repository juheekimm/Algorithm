package bj_02573_빙산;

import java.io.*;
import java.util.*;

public class Main_쉽게2 {
	
	static class Node {
		int x, y, val;
		Node(int x, int y, int val) {
			this.x = x;
			this.y = y;
			this.val = val;
		}
	}
	static int r, c, step = 0, map[][];
	static int dx[] = {0, -1, 0, 1};
	static int dy[] = {-1, 0, 1, 0};
	static boolean[][] visit;
	static Queue<Node> bfsq = new LinkedList<>();
	static Queue<Node> sinkq = new LinkedList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		map = new int[r][c];
		
		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < c; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		while (true) {
			visit = new boolean[r][c];
			step++;
			if(!sink()) break;
			update();
			startPoint();
		}
		System.out.println("0");
	}

	//몇만큼 가라앉을지 세서 sinkq에 넣는 함수
	//바로 반영하면 인접한 수에 영향을 줄 수 있으므로. 이 방법 쓰면 copymap 안 써도 된다!
	
	//만약 얼음이 더이상 없다면, false를 return!
	private static boolean sink() {
		boolean existIce = false; 
		int nx, ny;
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				
				if (map[i][j] != 0) {
					existIce = true;
					int minus = 0;
					for (int d = 0; d < 4; d++) {
						nx = i + dx[d];
						ny = j + dy[d];
						
						if (nx < 0 || nx >= r || ny < 0 || ny >= c)
							continue;
						
						if (map[nx][ny] == 0)
							minus++;
					}
					sinkq.add(new Node(i, j, minus));
				}
			}
		}
		return existIce ? true : false;
	}
	
	//sinkq에 넣었던 값들을 map에 반영해주는 함수
	private static void update() {
		while (!sinkq.isEmpty()) {
			Node temp = sinkq.poll();
			if (map[temp.x][temp.y] > temp.val)
				map[temp.x][temp.y] -= temp.val;
			else
				map[temp.x][temp.y] = 0;
		}
	}
	
	private static void startPoint() {
		boolean oneTurn = false;
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (map[i][j] != 0) {
					if (oneTurn && !visit[i][j]) {
						System.out.println(step);
						System.exit(0);
					}
					visit[i][j] = true;
					bfsq.add(new Node(i, j, map[i][j]));
					bfs();
					oneTurn = true;
				}
			}
		}
	}

	private static void bfs() {
		int nx, ny;
		while (!bfsq.isEmpty()) {
			Node temp = bfsq.poll();
			
			for (int d = 0; d < 4; d++) {
				nx = temp.x + dx[d];
				ny = temp.y + dy[d];
				
				if (nx < 0 || nx >= r || ny < 0 || ny >= c || map[nx][ny] == 0 || visit[nx][ny])
					continue;
				
				visit[nx][ny] = true;
				bfsq.add(new Node(nx, ny, map[nx][ny]));
			}
		}
	}
}