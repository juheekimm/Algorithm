package bj_02447_별찍기10;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main2 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		char[][] map = recursion(N);
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++)
				sb.append(map[i][j]);
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static char[][] recursion(int n) {
		if (n == 1)
			return new char[][]{{'*'}};
			
		char[][] map = recursion(n / 3);
		int len = map.length;
		char[][] ans = new char[len * 3][len * 3];
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				for (int x = 0; x < len; x++)
					for (int y = 0; y < len; y++)
						if (i == 1 && j == 1)
							ans[i * (n / 3) + x][j * (n / 3) + y] = ' ';
						else
							ans[i * (n / 3) + x][j * (n / 3) + y] = map[x][y];
			}
		}
		return ans;
	}
}