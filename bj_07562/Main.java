package bj_07562;

import java.io.*;
import java.util.*;

public class Main {
	private static class Node {
		int x, y, step;
		Node(int x, int y, int step) {
			this.x = x;
			this.y = y;
			this.step = step;
		}
	}
	static int tc, n, targetx, targety;
	static int[][] map;
	static boolean[][] visit;
	static Queue<Node> q;
	
	static int[][] dir = {{-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		tc = Integer.parseInt(br.readLine());
		int nowx, nowy;
		
		for (int t = 1; t <= tc; t++) {
			n = Integer.parseInt(br.readLine());
			map = new int[n][n];
			visit = new boolean[n][n];
			q = new LinkedList<>();
			
			st = new StringTokenizer(br.readLine());
			nowx = Integer.parseInt(st.nextToken());
			nowy = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			targetx = Integer.parseInt(st.nextToken());
			targety = Integer.parseInt(st.nextToken());
			
			visit[nowx][nowy] = true;
			q.add(new Node(nowx, nowy, 1));
			nightMove();
		}
	}

	private static void nightMove() {
		while (!q.isEmpty()) {
			
			Node temp = q.poll();

			for (int i = 0; i < 8; i++) {
				int nx = temp.x + dir[i][0];
				int ny = temp.y + dir[i][1];
				
				if (nx < 0 || nx >= n || ny < 0 || ny >= n || visit[nx][ny])
					continue;
				
				if (nx == targetx && ny == targety) {
					System.out.println(temp.step);
					return;
				}
				visit[nx][ny] = true;
				q.add(new Node(nx, ny, temp.step + 1));
			}
		}
		System.out.println("0");
	}
}