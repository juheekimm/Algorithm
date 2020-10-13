package bj_10813_공바꾸기;

import java.io.*;
import java.util.*;

public class Main_201001 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		for (int i = 0; i < N; i++)
			arr[i] = i + 1;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if (a == b) {
				continue;
			} else {
				int temp = arr[a - 1];
				arr[a - 1] = arr[b - 1];
				arr[b - 1] = temp;
			}
		}
		for (int i = 0; i < N; i++)
			sb.append(arr[i] + " ");
		System.out.println(sb);
	}
}
