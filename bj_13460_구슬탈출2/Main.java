package bj_13460_구슬탈출2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	private static class Node {
		int rx, ry, bx, by, d, count;
		Node (int rx, int ry) {
			this.rx = rx;
			this.ry = ry;
		}
		Node (int rx, int ry, int bx, int by, int d, int count) {
			this(rx, ry);
			this.bx = bx;
			this.by = by;
			this.d = d;
			this.count = count;
		}
	}
	private static int N, M, map[][];
	private static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
	private static Node R, B;
	private static Queue<Node> q = new LinkedList<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			char[] temp = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				if (temp[j] == '#') map[i][j] = -1;
				else if (temp[j] == 'R') R = new Node(i, j);
				else if (temp[j] == 'B') B = new Node(i, j);
				else if (temp[j] == 'O') map[i][j] = 1;
			}
		}
		for (int d = 0; d < 4; d++)
			q.add(new Node(R.rx, R.ry, B.rx, B.ry, d, 1));
		
		System.out.println(bfs());
	}

	private static int bfs() {
		while (!q.isEmpty()) {
			Node temp = q.poll();
			if (temp.count > 10) return -1;
			
			for (int d = 0; d < 4; d++) {
				if (d == temp.d) continue;
				Node r, b;
				
				boolean flag = true;
				//빨간거 먼저 이동 
				if (firstMoveCheck(temp, d)) {
					r = move(new Node(temp.rx, temp.ry), d, false);
					if (r.rx == -1 && r.ry == -1) flag = false;
					
					b = move(new Node(temp.bx, temp.by, r.rx, r.ry, 0, 0), d, flag);
					
				} else {
					b = move(new Node(temp.bx, temp.by), d, false);
					if (b.rx == -1 && b.ry == -1) flag = false;

					r = move(new Node(temp.rx, temp.ry, b.rx, b.ry, 0, 0), d, flag);
				}
				
				if (b.rx == -1 && b.ry == -1) continue;
				if (r.rx == -1 && r.ry == -1) return temp.count;
				
				q.add(new Node(r.rx, r.ry, b.rx, b.ry, d, temp.count + 1));
			}
		}
		return -1;
	}

	private static Node move(Node temp, int dir, boolean prev) {
		int nx = temp.rx, ny = temp.ry;
		
		while (nx >= 0 && nx < N && ny >= 0 && ny < M && map[nx][ny] != -1) {
			nx += dx[dir];
			ny += dy[dir];
			
			if (map[nx][ny] == 1) return new Node(-1, -1);
			if (prev && nx == temp.bx && ny == temp.by) break;
		}
		return new Node(nx - dx[dir], ny - dy[dir]);
	}

	//true면 red 먼저 false면 blue 먼저 
	private static boolean firstMoveCheck(Node temp, int d) {
		int rx = temp.rx;
		int ry = temp.ry;
		int bx = temp.bx;
		int by = temp.by;
		
		if (d < 2) {	//상하 
			if (ry == by) {						//같은줄에 있는데 
				if (d == 0) {					
					if (rx < bx) return true;	//위쪽으로 기울이는데 빨간게 더 위에있으면 
				} else {
					if (rx > bx) return true;	//아래쪽으로 기울이는데 빨간게 더 밑에있으면 
				}
			}
		} else {		//좌우 
			if (rx == bx) {						//같은줄에 있는데 
				if (d == 2) {					//
					if (ry < by) return true;	//왼쪽으로 기울이는데 빨간게 더 왼쪽에있으면 
				} else {
					if (ry > by) return true;	//오른쪽으로 기울이는데 빨간게 더 오른쪽에 있으면  
				}
			}
		}
		return false;
	}
}