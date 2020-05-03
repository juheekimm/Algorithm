package bj_13458_시험감독;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	private static int N, B, C, arr[];
	private static long sum;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		getNum();
		System.out.println(sum);
	}
	
	private static void getNum() {
		for (int i = 0; i < N; i++)
			arr[i] -= B;
		sum = N;
		
		for (int i = 0; i < N; i++) {
			if (arr[i] > 0) {
				if (arr[i] % C == 0)
					sum += arr[i] / C;
				else
					sum += (arr[i] / C) + 1;
			}
		}
	}
}