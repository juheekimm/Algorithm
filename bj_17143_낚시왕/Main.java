package bj_17143_낚시왕;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static class Shark {
		int x, y, d, dist, size;

		public Shark(int x, int y) {
			this.x = x;
			this.y = y;
		}
		public Shark(int x, int y, int dist, int d, int size) {
			this(x, y);
			this.dist = dist;
			this.d = d;
			this.size = size;
		}
	}
	
	private static int R, C, M, map1[][], map2[][], sum = 0, sharkCount = 0;
	private static int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};
	private static Queue<Shark> q = new LinkedList<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map1 = new int[R + 1][C + 2];
		map2 = new int[R + 1][C + 2];
		
		int r, c, s, d, z;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			s = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			z = Integer.parseInt(st.nextToken());
			if (d == 1)			d = 0;
			else if (d == 4)	d = 1;
			
			q.add(new Shark(r, c, s, d, z));
			map1[r][c] = z;
		}
		
		int manX = 0;
		while (manX <= C) {
			if (M == 0 || M == sharkCount) break;
			if (manX % 2 == 0) {
				manCatch(++manX, map1);
				fishMove(map1, map2);
			} else {
				manCatch(++manX, map2);
				fishMove(map2, map1);
			}
		}
		System.out.println(sum);
	}

	private static void fishMove(int[][] prev, int[][] now) {
		int size = q.size();
		for (int i = 0; i < size; i++) {
			Shark temp = q.poll();
			if (prev[temp.x][temp.y] != temp.size) continue;
			oneMove(temp, now);
			prev[temp.x][temp.y] = 0;
		}
	}

	private static void oneMove(Shark temp, int[][] now) {
		int nx = temp.x, ny = temp.y, d = temp.d, dist = temp.dist, size = temp.size;
		
		if (d == 0 || d == 2) dist = dist % ((R - 1) * 2);
		else dist = dist % ((C - 1) * 2);
		
		for (int i = 0; i < dist; i++) {
			if (nx + dx[d] <= 0 || nx + dx[d] >= R + 1 || ny + dy[d] <= 0 || ny + dy[d] >= C + 1)
				d = (d + 2) % 4;
			
			nx += dx[d];
			ny += dy[d];
		}
		
		//이미 누군가 있는데 그 애가 지금 나보다 크다면 
		if (now[nx][ny] > size) return;
		
		//아무도 없거나, 내가 이전에 있는 애보다 크다면 
		now[nx][ny] = size;
		q.add(new Shark(nx, ny, dist, d, size));
	}

	private static void manCatch(int y, int[][] map) {
		for (int x = 1; x <= R; x++) {
			if (map[x][y] != 0) {
				sum += map[x][y];
				map[x][y] = 0;
				sharkCount++;
				break;
			}
		}
	}
}