package bj_02075_N번째큰수;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_imp {
	
	private static int N, arr[][], h[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		h = new int[N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}
		Arrays.fill(h, N - 1);
		
		System.out.println(findMaxVal());
	}

	private static int findMaxVal() {
		int max = 0, idx;
		
		for (int step = 0; step < N; step++) {
			max = arr[h[0]][0];
			idx = 0;
			
			for (int j = 0; j < N; j++) {
				if (max < arr[h[j]][j]) {
					max = arr[h[j]][j];
					idx = j;
				}
			}
			h[idx]--;
		}
		
		return max;
	}
}