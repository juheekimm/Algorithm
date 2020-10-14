package bj_17822_원판돌리기;

import java.io.*;
import java.util.*;

public class Main_201014_주석ver {

	static class Node {
		int x, y, num;
		Node (int x, int y, int num) {
			this.x = x;
			this.y = y;
			this.num = num;
		}
	}
	static int N, M, T, map[][];
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	static boolean[][] visit;
	static Queue<Node> q;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
	
		map = new int[N][M];
		visit = new boolean[N][M];
		q = new LinkedList<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int x, d, k;
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());

			for (int i = x - 1; i < N; i += x) {
				System.out.println("x " + i);
				move(i, d == 0, k);
			}
			print();
			if (existNum()) {
				for (int n = 0; n < N; n++)
					Arrays.fill(visit[n], false);
				q.clear();
				
				if (!searchRemoveNum()) {
					editNum();
				}
			}
			print();
		}
		
		System.out.println(getSum());
	}
	
	

	private static void print() {
		for (int i = 0; i < N; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
		System.out.println();
	}



	private static int getSum() {
		int sum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] != -1) {
					sum += map[i][j];
				}
			}
		}
		return sum;
	}

	private static void editNum() {
		int count = 0;
		double sum = 0.0, avg = 0.0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] != -1) {
					sum += map[i][j];
					count++;
				}
			}
		}
		
		avg = sum / count;
		System.out.println(avg);
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] != -1) {
					if (avg > map[i][j])
						map[i][j]++;
					else if (avg < map[i][j])
						map[i][j]--;
				}
			}
		}
	}

	private static boolean searchRemoveNum() {
		boolean sameNum = false;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] != -1 && !visit[i][j]) {
					q.add(new Node(i, j, map[i][j]));
					visit[i][j] = true;
					if (bfs()) {
						sameNum = true;
						map[i][j] = -1;
					}
				}
			}
		}
		return sameNum;
	}

	private static boolean bfs() {
		boolean existSameNum = false;
		
		while (!q.isEmpty()) {
			Node temp = q.poll();
			for (int d = 0; d < 4; d++) {
				int nx = temp.x + dx[d];
				int ny = temp.y + dy[d];
				
				if (ny == -1) ny = M - 1;
				else if (ny == M) ny = 0;
				
				if (nx < 0 || nx >= N || ny < 0 || ny >= M || visit[nx][ny] || map[nx][ny] != temp.num) continue;
				
				visit[nx][ny] = true;
				q.add(new Node(nx, ny, temp.num));
				map[nx][ny] = -1;
				existSameNum = true;
			}
		}
		return existSameNum;
	}

	private static boolean existNum() {
		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++)
				if (map[i][j] != -1)
					return true;
		
		return false;
	}

	private static void move(int n, boolean isRight, int k) {
		if (isRight) {
			int[] temp = map[n].clone();
			for (int i = 0; i < M; i++)
				map[n][(i + k) % M] = temp[i];
		} else {
			int[] temp = map[n].clone();
			for (int i = 0; i < M; i++)
				map[n][(i - k + M) % M] = temp[i];
		}
	}
}












