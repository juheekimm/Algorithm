package bj_02003_수들의합2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, M, arr[];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		System.out.println(twoPointer());
	}

	private static int twoPointer() {
		int sum = 0, result = 0, s = 0, e = 0;
		
		while (true) {
			if (sum >= M) sum -= arr[s++];
			else if (e == N) break;
			else sum += arr[e++];
			
			if (sum == M) result++;
		}
		return result;
	}
}