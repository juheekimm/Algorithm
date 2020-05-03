package bj_14499_주사위굴리기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main2 {
	
	private static int N, M, x, y, K, map[][], cuve[], k[], reverse[] = {0, 6, 5 ,4 ,3 ,2, 1};
	private static int[] dx = {0, 1, 0, -1}, dy = {1, 0, -1, 0};
	private static int[][][] dir = {{{}, {3, 2, 4, 5}, {3, 6, 4, 1}, {}, {}, {3, 1, 4, 6}, {3, 5, 4, 2}},
			{{}, {2, 4, 5, 3}, {}, {2, 1, 5, 6}, {2, 6, 5, 1}, {}, {2, 3, 5, 4}},
			{{}, {}, {1, 3, 6, 4}, {1, 5, 6, 2}, {1, 2, 6, 5}, {1, 4, 6, 3}, {}}};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		k = new int[K];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			k[i] = Integer.parseInt(st.nextToken());
			if (k[i] == 1) k[i] = 0;
			else if (k[i] == 4) k[i] = 1;
		}
		
		cuve = new int[7];
		move();
	}

	private static void move() {
		int cuvedir = 0, now = 6, right = 3, move = 0;
		int nx, ny, d;
		
		for (int i = 0; i < K; i++) {
			d = k[i];
			
			nx = x + dx[d];
			ny = y + dy[d];

			
			if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
			
			System.out.println("cuvedir " + cuvedir + " now " + now + " d " + d + " move" + move);
			System.out.println("prev " + now);
			int next = dir[cuvedir][now][(d + move) % 4];
			int prevdir = cuvedir;
			
			if (d % 2 == 0) {
				if (cuvedir == 0) {
					if (now == 1 || now == 6) {
						cuvedir = 2;
						if (now == 1 && d == 0) move = 2;
						else if (now == 6 && d == 2) move = 2;
						else move = 0;
					} else {
						cuvedir = 1;
						if (now == 2 && d == 0) move = 2;
						else if (now == 5 && d == 2) move = 2;
						else move = 0;
					}
					
				} else if (cuvedir == 1) {
					if (now == 1 || now == 6) {
						cuvedir = 2;
						if (now == 1 && d == 0) move = 2;
						else if (now == 6 && d == 2) move = 2;
						else move = 0;
					} else {
						cuvedir = 0;
						if (now == 3 && d == 0) move = 2;
						else if (now == 4 && d == 2) move = 2;
						else move = 0;
					}
					
				} else if (cuvedir == 2) {
					if (now == 1 || now == 6) {
						cuvedir = 1;
						if (now == 2 && d == 0) move = 2;
						else if (now == 5 && d == 2) move = 2;
						else move = 0;
					} else {
						cuvedir = 0;
						if (now == 3 && d == 0) move = 2;
						else if (now == 4 && d == 2) move = 2;
						else move = 0;
					}
				}
//				System.out.println("cuvedir " + cuvedir + " now " + now + " d " + d + " move" + move);
//				now = dir[cuvedir][now][(d + move) % 4];
			}
			now = next;
			
			if (map[nx][ny] == 0)
				map[nx][ny] = cuve[now];
			else {
				cuve[now] = map[nx][ny];
				map[nx][ny] = 0;
			}
			System.out.println("nx " + nx + " ny " + ny);
			System.out.println(Arrays.toString(cuve));
			
//			System.out.println("cuvedir " + cuvedir);
//			System.out.println("now " + now);
//			System.out.println("d + move) % 4 " + (d + move) % 4);
			System.out.println(cuve[reverse[now]] + "\n");
				
			x = nx;
			y = ny;
		}
		
	}
}



























