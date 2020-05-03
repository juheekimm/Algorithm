package bj_01806_부분합;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
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
		
		System.out.println(slide());
	}

	private static int slide() {
		int s = 0, e = 0, sum = 0, min = N + 1;
		
		while (true) {
			if (sum >= S) sum -= arr[s++];
			else if (e == N) break;
			else sum += arr[e++];
			
			if (sum >= S)
				if (min > e - s)
					min = e - s;
		}
        if (min == N + 1) min = 0;
		return min;
	}
}