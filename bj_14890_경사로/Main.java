package bj_14890_경사로;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	private static int N, L, load = 0, map[][];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		loadCheck();
		System.out.println(load);
	}

	private static void loadCheck() {
		for (int i = 0; i < N; i++) {	//가로체크
			int count = 1, prev = map[i][0];
			boolean flag = true;
			int[] visit = new int[N];
			
			for (int j = 1; j < N; j++) {
				if (prev == map[i][j]) {
					count++;
				} else if (prev == map[i][j] - 1 && (count >= L || L == 1)) {	//올라갈 때 
					if (visit[j - 1] < 0) {
						flag = false;
						break;
					}
					prev = map[i][j];
					count = 0;
					visit[j] = 1;
				} else if (prev == map[i][j] + 1 && isPossibleLoad(i, j, true, map[i][j])) {	//내려갈 때 
//					if (visit[j] > 0) {
//						flag = false;
//						break;
//					}
					prev = map[i][j];
					count = 0;
					visit[j + L - 1] = -1;
					j += L - 1;
				} else {
					flag = false;
					break;
				}
			}
			if (flag) {
//				System.out.println("가로 " + i + "번째");
				load++;
			}
		}
		
		for (int j = 0; j < N; j++) {	//세로체크
			int count = 1, prev = map[0][j];
			boolean flag = true;
			int[] visit = new int[N];
			
			for (int i = 1; i < N; i++) {
				if (prev == map[i][j]) {
					count++;
				} else if (prev == map[i][j] -1 && (count >= L || L == 1)) {
					if (visit[i - 1] < 0) {
						flag = false;
						break;
					}
					prev = map[i][j];
					count = 0;
					visit[i] = 1;
				} else if (prev == map[i][j] + 1 && isPossibleLoad(i, j, false, map[i][j])) {
					prev = map[i][j];
					count = 0;
					visit[i + L - 1] = -1;
					i += L - 1;
				} else {
					flag = false;
					break;
				}
			}
			if (flag) {
//				System.out.println("세로 " + j + "번째");
				load++;
			}
		}
	}

	private static boolean isPossibleLoad(int x, int y, boolean dir, int height) {
		if (dir) {	//가로 
			for (int k = 0; k < L; k++) {
				int ny = y + k;
				if (ny >= N || map[x][ny] != height)
					return false;
			}
		} else {	//세로 
			for (int k = 0; k < L; k++) {
				int nx = x + k;
				if (nx >= N || map[nx][y] != height)
					return false;
			}
		}
		return true;
	}
}



















