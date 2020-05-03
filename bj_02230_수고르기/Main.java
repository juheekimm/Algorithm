package bj_02230_수고르기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M, arr[];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		
		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(br.readLine());
		
		Arrays.sort(arr);
		System.out.println(twoPointer());
	}

	private static int twoPointer() {
		int min = Integer.MAX_VALUE, s = 0, e = 0;
		
		while (true) {
			if (arr[e] - arr[s] >= M) s++;
			else e++;
			
			if (e == N || s == N) break;
			
			if (arr[e] - arr[s] >= M)
				if (min > arr[e] - arr[s])
					min = arr[e] - arr[s];
		}
		return min;
	}
}