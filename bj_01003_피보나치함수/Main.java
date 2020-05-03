package bj_01003_피보나치함수;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int[][] check;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			check = new int[N + 1][2];
			check[0][0] = 1;
			if (N >= 1) {
				check[1][1] = 1;
				for (int i = 2; i <= N; i++) {
					check[i][0] = check[i - 1][0] + check[i - 2][0];
					check[i][1] = check[i - 1][1] + check[i - 2][1];
				}
			}
			System.out.println(check[N][0] + " " + check[N][1]);
		}
	}
}