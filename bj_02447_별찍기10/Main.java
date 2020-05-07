package bj_02447_별찍기10;

import java.io.BufferedReader;
import java.io.InputStreamReader;

//처음에는 다 칠해놓고 빈칸만 비우는게(Main2) 더 효율적일줄 알았지만 별 차이가 없다. 왜일까?
//아마 어차피 다 덮어야 해서 그런듯! (별도 빈칸도 결국에는 새로 그리게 되니까) 
public class Main {
	
	static char[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		
		recursion(N);

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++)
				sb.append(map[i][j]);
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static void recursion(int n) {
		if (n == 0) {
			map[0][0] = '*';
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