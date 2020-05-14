package bj_14889_스타트와링크;

import java.io.*;
import java.util.StringTokenizer;

public class Main_200514 {
	
	static int N, map[][], min = Integer.MAX_VALUE;
	static boolean[] combi;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		combi = new boolean[N];
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		combi[0] = true;
		combi(N / 2, 1, 1);
		System.out.println(min);
	}

	private static boolean combi(int n, int k, int idx) {
		if (n == k) {
			
			int a = 0, b = 0, size = N / 2;
			int[] A = new int[size];
			int[] B = new int[size];
			
			for (int i = 0; i < N; i++) {
				if (combi[i]) A[a++] = i;
				else B[b++] = i;
			}
			int nowMin = Math.abs(powerDifCheck(A) - powerDifCheck(B));
			if (min > nowMin) min = nowMin;
			if (min == 0) return true;
			else return false;
		}
		
		for (int i = idx; i < N; i++) {
			combi[i] = true;
			if (combi(n, k + 1, i + 1)) return true;
			combi[i] = false;
		}
		return false;
	}

	private static int powerDifCheck(int[] temp) {
		int sum = 0, size = N / 2;
		for (int i = 0; i < size; i++) {
			for (int j = i; j < size; j++) {
				sum += map[temp[i]][temp[j]];
				sum += map[temp[j]][temp[i]];
			}
		}
		return sum;
	}
}