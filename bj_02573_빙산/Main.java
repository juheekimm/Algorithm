package bj_02573_빙산;

import java.io.*;
import java.util.*;

//190817. 3h. 쓸데없이 오기부리다가 오래 걸림. 쉽게 가자. 최적화는 나중에.
//Main_쉽게: 쉽게 -> Main_쉽게2 더쉽게
public class Main {
	static class Node {
		int x, y;
		Node(int x, int y) {
			this.x = x;
			this.y = y;
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
					iceq.add(new Node(i, j));
			}
		}
		do {
			step++;
			visit = new boolean[r][c];
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
			if (map[temp.x][temp.y] > minus) {
				map[temp.x][temp.y] = map[temp.x][temp.y] - minus;
				iceq.add(new Node(temp.x, temp.y));
			} else {
				map[temp.x][temp.y] = 0;
			}
		}
		return true;
	}
	
	private static void startPoint() {
		for (int i = 0; i < r; i++)
			for (int j = 0; j < c; j++)
				if (map[i][j] != 0) {
					
					visit[i][j] = true;
					bfsq.add(new Node(i, j));
					bfs();
					return;
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
				
				if (nx < 0 || nx >= r || ny < 0 || ny >= c || map[nx][ny] == 0 || visit[nx][ny])
					continue;
				visit[nx][ny] = true;
				bfsq.add(new Node(nx, ny));
				
			}
		}
		if (cnt != iceq.size()) {
			System.out.println(step);
			System.exit(0);
		}
	}
}