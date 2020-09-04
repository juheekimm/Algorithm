package bj_07562_나이트의이동;

import java.io.*;
import java.util.*;

public class Main_200902 {
	
	static class Node {
		int x, y, step;
		Node (int x, int y, int step) {
			this.x = x;
			this.y = y;
			this.step = step;
		}
	}
	
	static int I, sx, sy, ex, ey;
	static int[] dx = {-1, -2, -2, -1, 1, 2, 2, 1};
	static int[] dy = {-2, -1, 1, 2, 2, 1, -1, -2};
	static boolean[][] map;
	static Queue<Node> q = new LinkedList<Node>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			I = Integer.parseInt(br.readLine());
			map = new boolean[I][I];
			
			st = new StringTokenizer(br.readLine());
			sx = Integer.parseInt(st.nextToken());
			sy = Integer.parseInt(st.nextToken());
			q.clear();
			q.add(new Node(sx, sy, 0));
			map[sx][sy] = true;
			
			st = new StringTokenizer(br.readLine());
			ex = Integer.parseInt(st.nextToken());
			ey = Integer.parseInt(st.nextToken());
			
			sb.append(bfs() + "\n");
		}
		System.out.println(sb);
	}

	private static int bfs() {
		while (!q.isEmpty()) {
			Node temp = q.poll();
			if (temp.x == ex && temp.y == ey)
				return temp.step;

			for (int d = 0; d < 8; d++) {
				int nx = temp.x + dx[d];
				int ny = temp.y + dy[d];
				
				if (nx < 0 || nx >= I || ny < 0 || ny >= I || map[nx][ny]) continue;
				
				map[nx][ny] = true;
				q.add(new Node(nx, ny, temp.step + 1));
			}
		}
		return -1;
	}
}