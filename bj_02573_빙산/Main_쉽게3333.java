package bj_02573_빙산;

import java.io.*;
import java.util.*;

public class Main_쉽게3333 {
	
	static class Node {
		int x, y, val;
		Node(int x, int y, int val) {
			this.x = x;
			this.y = y;
			this.val = val;
		}
	}
	static int r, c, step = 0, size, map[][], copymap[][];
	static int dx[] = {0 , -1, 0, 1};
	static int dy[] = {-1, 0, 1, 0};
	static boolean[][] visit;
	static Queue<Node> bfsq = new LinkedList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		map = new int[r][c];
		copymap = new int[r][c];
		
		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < c; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] != 0)
					size++;
			}
		}
		
		do {
			visit = new boolean[r][c];
			for (int i = 0; i < r; i++)
				copymap[i] = map[i].clone();
			startPoint();
			size = 0;
			step++;
		} while(sink());

		System.out.println("0");
	}

	private static boolean sink() {
		int nx, ny;
		
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				
				if (map[i][j] != 0) {
					int minus = 0;
					for (int d = 0; d < 4; d++) {
						nx = i + dx[d];
						ny = j + dy[d];
						
						if (nx < 0 || nx >= r || ny < 0 || ny >= c)
							continue;
						
						if (copymap[nx][ny] == 0)
							minus++;
					}
					if (map[i][j] > minus) {
						map[i][j] -= minus;
						size++;
					} else {
						map[i][j] = 0;
					}
				}
			}
		}
		return size > 0 ? true : false;
	}
	
	private static void startPoint() {
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (copymap[i][j] != 0) {
					
					visit[i][j] = true;
					bfsq.add(new Node(i, j, copymap[i][j]));
					bfs();
					return;
				}
			}
		}
	}

	private static void bfs() {
		int nx, ny, cnt = 0;
		while (!bfsq.isEmpty()) {
			Node temp = bfsq.poll();
			cnt++;
			
			for (int d = 0; d < 4; d++) {
				nx = temp.x + dx[d];
				ny = temp.y + dy[d];
				
				if (nx < 0 || nx >= r || ny < 0 || ny >= c || copymap[nx][ny] == 0 || visit[nx][ny])
					continue;
				visit[nx][ny] = true;
				bfsq.add(new Node(nx, ny, copymap[nx][ny]));
			}
		}
		if (cnt != size) {
			System.out.println(step);
			System.exit(0);
		}
	}
}