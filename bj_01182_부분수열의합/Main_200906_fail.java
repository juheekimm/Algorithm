package bj_01182_부분수열의합;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_200906_fail {
	
	static int N, S, arr[];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		int sum = 0, count = 0;
		for (int i = 0; i < N; i++) {
			sum = arr[i];
			if (sum == S) count++;
			for (int j = i + 1; j < N; j++) {
				sum += arr[j];
				if (sum == S) count++;
			}
		}
		System.out.println(count);
	}
}
