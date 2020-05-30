package bj_02573_빙산;

import java.io.*;
import java.util.*;

public class Main_print {
	static class Node {
		int x, y, val;
		Node(int x, int y, int val) {
			this.x = x;
			this.y = y;
			this.val = val;
		}
	}
	static int r, c, step = -1, size, map[][], copymap[][];
	static int dx[] = {0, -1, 0, 1};
	static int dy[] = {-1, 0, 1, 0};
	static boolean[][] visit;
	static Queue<Node> iceq = new LinkedList<>();
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
					iceq.add(new Node(i, j, map[i][j]));
			}
		}
		
		for (int i = 0; i < r; i++)
			copymap[i] = map[i].clone();
		do {
			step++;
			visit = new boolean[r][c];
			print();
			visitprint();
			for (int i = 0; i < r; i++)
				copymap[i] = map[i].clone();
			startPoint();
		} while(sink());
		
		System.out.println("0");
	}

	private static boolean sink() {
		size = iceq.size();
		if (size == 0) return false; //더이상 남은 빙산이 없으면 false 리턴
		int nx, ny;
		
		for (int i = 0; i < size; i++) {
			Node temp = iceq.poll();
			int minus = 0;
			
			for (int d = 0; d < 4; d++) {
				nx = temp.x + dx[d];
				ny = temp.y + dy[d];
				
				if (nx < 0 || nx >= r || ny < 0 || ny >= c)
					continue;
				
				if (copymap[nx][ny] == 0)
					minus++;
			}
			if (temp.val > minus) {
				map[temp.x][temp.y] = temp.val - minus;
				iceq.add(new Node(temp.x, temp.y, temp.val - minus));
			} else {
				map[temp.x][temp.y] = 0;
			}
		}
		return true;
	}
	
	private static void startPoint() {
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (map[i][j] != 0) {
					
					visit[i][j] = true;
					bfsq.add(new Node(i, j, map[i][j]));
					bfs();
					return;
				}
			}
		}
	}

	private static void bfs() {
//		System.out.println("야들어오냐1");
		int nx, ny, cnt = 0;
		while (!bfsq.isEmpty()) {
//			System.out.println("야들어오냐2");
			Node temp = bfsq.poll();
			cnt++;
			for (int d = 0; d < 4; d++) {
//				System.out.println("야들어오냐3");
				nx = temp.x + dx[d];
				ny = temp.y + dy[d];
				
				if (nx < 0 || nx >= r || ny < 0 || ny >= c || map[nx][ny] == 0 || visit[nx][ny])
					continue;
				
				visit[nx][ny] = true;
				bfsq.add(new Node(nx, ny, map[nx][ny]));
				
			}
		}
		System.out.println("현재 cnt " + cnt + " 전체size " + iceq.size());

		if (cnt != iceq.size()) {
			System.out.println(step);
			System.exit(0);
		}
		
	}
	private static void print() {
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}	
	
	private static void visitprint() {
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (visit[i][j])
					System.out.print("1 ");
				else
					System.out.print("0 ");
//				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}	
}