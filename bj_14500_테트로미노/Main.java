package bj_14500_테트로미노;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	private static int N, M, max = Integer.MIN_VALUE, map[][];
	private static int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};
	private static boolean[][] visit;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visit = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) 
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		findStart();
		System.out.println(max);
	}

	private static void findStart() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				visit[i][j] = true;
				backTracking(i, j, map[i][j], 1);
				visit[i][j] = false;
			}
		}
	}

	private static void backTracking(int x, int y, int sum, int step) {
		if (step == 4) {
			if (max < sum) max = sum;
			return;
		}
		
		for (int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			if (nx < 0 || nx >= N || ny < 0 || ny >= M || visit[nx][ny])
				continue;
			
			visit[nx][ny] = true;
			backTracking(nx, ny, sum + map[nx][ny], step + 1);
			
			if (step == 2) {	//T자 모양으로 된 폴리오미노를 위해서 
				for (int d2 = 0; d2 < 4; d2++) {
					int nx2 = x + dx[d2];
					int ny2 = y + dy[d2];
					
					if (nx2 < 0 || nx2 >= N || ny2 < 0 || ny2 >= M || visit[nx2][ny2])
						continue;
					
					visit[nx2][ny2] = true;
					backTracking(nx2, ny2, sum + map[nx][ny] + map[nx2][ny2], step + 2);
					visit[nx2][ny2] = false;
				}
			}
			
			visit[nx][ny] = false;
		}
	}
}