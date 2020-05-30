package bj_17144_미세먼지안녕;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//7:15~
public class Main_출력 {
	static class Node {
		int x, y, val;
		Node(int x, int y, int val) {
			this.x = x;
			this.y = y;
			this.val = val;
		}
	}
	
	static int r, c, t, map[][];
	static Node top, bottom;
//	static Node[] filter = new Node[2];
	//dx[0]은 top, dx[1]은 bottom
	static Queue<Node> dustq = new LinkedList<>();
	
	static int[][] dx = {{-1, 0, 1, 0}, {1, 0, -1, 0}};
	static int[][] dy = {{0, 1, 0, -1}, {0, 1, 0, -1}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());
		
		map = new int[r][c];
		
		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < c; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == -1 && top == null) {
					top = new Node(i, j, -1);
					bottom = new Node(i + 1, j, -1);
				}
			}
		}
		
		for (int ts = 0; ts < t; ts++) {
			dustSpreadCheck();
			dustUpdate();
			print();
			rotateSimul();
			print();
			dustVolumnCheck();
			System.out.println("------------");
		}
	}



	private static void dustSpreadCheck() {
		int nx, ny, cnt;
		
		
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (map[i][j] != 0 && map[i][j] != -1)
					dustq.add(new Node(i, j, map[i][j]));
			}
		}
		int size = dustq.size();
		Node temp;
		
		for (int i = 0; i < size; i++) {
			temp = dustq.poll();
			cnt = 0;
			
			if (temp.val >= 5) {
				for (int d = 0; d < 4; d++) {
					nx = temp.x + dx[0][d];
					ny = temp.y + dy[0][d];
					
					if (nx < 0 || nx >= r || ny < 0 || ny >= c
							|| (nx == top.x && ny == top.y)
							|| (nx == bottom.x && ny == bottom.y))
						continue;
					
					cnt++;
					dustq.add(new Node(nx, ny, temp.val / 5));
				}
				map[temp.x][temp.y] = temp.val - (temp.val / 5) * cnt;
			}
		}
	}
	
	private static void dustUpdate() {
		Node temp;
		while (!dustq.isEmpty()) {
			temp = dustq.poll();
			map[temp.x][temp.y] += temp.val;
		}
	}

	private static void rotateSimul() {
		int x, y, nx, ny, dir;
//		for (int ts = 0; ts < t; ts++) {
		//위쪽공기청정기
		dir = 0;
		x = top.x;
		y = top.y;
		
		while(dir < 4) {
			nx = x + dx[0][dir];
			ny = y + dy[0][dir];
			
			//nx 최대 조건 조심!
			if (nx < 0 || nx > top.x || ny < 0 || ny >= c) {
//				System.out.println("통과 nx: " + nx + " ny:" + ny);
				dir++;
			} else {
//				System.out.println("변함 nx: " + nx + " ny:" + ny);
				map[x][y] = map[nx][ny];
				x = nx;
				y = ny;
			}
		}
		map[top.x][top.y] = -1;
		map[top.x][top.y + 1] = 0;
		
		//아래쪽공기청정기
		dir = 0;
		x = bottom.x;
		y = bottom.y;
		while(dir < 4) {
			nx = x + dx[1][dir];
			ny = y + dy[1][dir];
			
			//nx 최대 조건 조심!
			if (nx < bottom.x || nx >= r || ny < 0 || ny >= c) {
				dir++;
			} else {
				map[x][y] = map[nx][ny];
				x = nx;
				y = ny;
			}
		}
		map[bottom.x][bottom.y] = -1;
		map[bottom.x][bottom.y + 1] = 0;
//		}
	}
	
	private static void dustVolumnCheck() {
		int dustTotal = 0;
		for (int i = 0; i < r; i++)
			for (int j = 0; j < c; j++)
				if (map[i][j] != 0 && map[i][j] != -1)
					dustTotal += map[i][j];
		System.out.println(dustTotal);
	}
	
	private static void print() {
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				System.out.printf("%2d ", map[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}

}
