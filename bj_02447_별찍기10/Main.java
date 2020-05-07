package bj_02447_별찍기10;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

//더 효율 ? 
public class Main {
	
	static char[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		
		for (int i = 0; i < N; i++)
			Arrays.fill(map[i], '*');
		
		recursion(N);

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++)
				sb.append(map[i][j]);
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static void recursion(int n) {
		if (n == 3) {
			map[1][1] = ' ';
			return;
		}
		
		int len = n / 3;
		recursion(len);
		
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				for (int x = 0; x < len; x++)
					for (int y = 0; y < len; y++)
						if (i == 1 && j == 1)
							map[i * len + x][j * len + y] = ' ';
						else
							map[i * len + x][j * len + y] = map[x][y];
	}
}