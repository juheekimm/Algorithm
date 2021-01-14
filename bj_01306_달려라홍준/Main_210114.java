package bj_01306_달려라홍준;

import java.io.*;
import java.util.*;

//sliding window -> runtime error
public class Main_210114 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = stoi(st.nextToken());
		int M = stoi(st.nextToken());
		
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			arr[i] = stoi(st.nextToken());
		
		int[] count = new int[1000000];
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder());
		
		//init value setting
		for (int i = 0; i < 2 * M - 1; i++) {
			if (count[arr[i]] == 0)
				pq.add(arr[i]);
			
			count[arr[i]]++;
		}
		sb.append(pq.peek() + " ");
		
		int l = 0, r = 2 * M - 1;
		while (true) {
			if (r >= N) break;
			
			if (count[arr[r]] == 0)
				pq.add(arr[r]);
			
			count[arr[r]]++;
			count[arr[l]]--;
			
			if (count[arr[l]] == 0 && pq.peek() == arr[l])
				pq.poll();
			
			sb.append(pq.peek() + " ");
			
			l++;
			r++;
		}
		System.out.println(sb);		
	}

	private static int stoi(String str) {
		return Integer.parseInt(str);
	}
}
