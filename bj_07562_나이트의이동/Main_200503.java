package bj_07562_나이트의이동;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_200503 {

	static int N, ex, ey, map[][];
	static int[] dx = {1, 1, 2, 2, -1, -1, -2, -2};
	static int[] dy = {-2, 2, -1, 1, -2, 2, -1, 1};
	static Queue<int[]> q = new LinkedList<>();
	static boolean visit[][];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			visit = new boolean[N][N];
			
			st = new StringTokenizer(br.readLine());
			int sx = Integer.parseInt(st.nextToken());
			int sy = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			ex = Integer.parseInt(st.nextToken());
			ey = Integer.parseInt(st.nextToken());
			
			if (sx == ex && sy == ey) {
				sb.append("0\n");
				continue;
			}
			
			visit[sx][sy] = true;
			q.add(new int[] {sx, sy, 1});
			sb.append(bfs() + "\n");
		}
		
		System.out.println(sb);
	}

	private static int bfs() {
		while (!q.isEmpty()) {
			int[] temp = q.poll();
			
			for (int d = 0; d < 8; d++) {
				int nx = temp[0] + dx[d];
				int ny = temp[1] + dy[d];
				
				if (nx < 0 || nx >= N || ny < 0 || ny >= N || visit[nx][ny]) continue;
				
				if (nx == ex && ny == ey) {
					q.clear();
					return temp[2];
				}
				
				visit[nx][ny] = true;
				q.add(new int[] {nx, ny, temp[2] + 1});
			}
		}
		return -1;
	}
}