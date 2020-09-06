package bj_09663_NQUEEN;

import java.util.Scanner;

public class Main_200906 {
	
	static int N, map[][], count = 0;
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		N = s.nextInt();
		map = new int[N][N];
		
		findStart();
		System.out.println(count);
	}

	private static void findStart() {
		for (int j = 0; j < N; j++) {
			visitCheck(0, j, 1);
			backTracking(1);
			visitCheck(0, j, -1);
		}
	}

	private static void backTracking(int depth) {
		if (depth == N) {
			count++;
			return;
		}
		
		for (int j = 0; j < N; j++) {
			if (map[depth][j] == 0) {
				visitCheck(depth, j, 1);
				backTracking(depth + 1);
				visitCheck(depth, j, -1);
			}
		}
	}

	private static void visitCheck(int x, int y, int val) {
		for (int i = 0; i < N; i++) {
			map[i][y] += val;
			map[x][i] += val;
			
			if (x - i >= 0 && y - i >= 0)
				map[x - i][y - i] += val;
			
			if (x - i >= 0 && y + i < N)
				map[x - i][y + i] += val;	
			
			if (x + i < N && y - i >= 0)
				map[x + i][y - i] += val;
			
			if (x + i < N && y + i < N)
				map[x + i][y + i] += val;
		}
	}
}










