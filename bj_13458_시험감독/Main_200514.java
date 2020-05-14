package bj_13458_시험감독;

import java.io.*;
import java.util.*;

public class Main_200514 {
	
	static int N, B, C, people[];
	static long count = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		people = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			people[i] = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		count = N;
		for (int i = 0; i < N; i++) {
			people[i] -= B;
			if (people[i] > 0) {
				count += people[i] / C;
				if (people[i] % C != 0)
					count++;
			}
		}
		System.out.println(count);
	}
}