package bj_11003_최솟값찾기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_deque {
	
	private static int N, L, arr[];
	private static Deque<Integer> ans = new LinkedList<Integer>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		slide();
	}

	private static void slide() {
		StringBuilder sb = new StringBuilder();
		ans.add(0);
		sb.append(arr[0] + " ");
		
		for (int i = 1; i < N; i++) {
			int size = ans.size();
			for (int j = 1; j <= size; j++) {
				int temp = ans.removeLast();
				if (arr[temp] <= arr[i])
					ans.addFirst(temp);
//				if (arr[ans.getLast()] > arr[i])
//					ans.remove(size - j);
			}
			ans.addLast(i);
			
			if (ans.getFirst() <= i - L)
				ans.removeFirst();
			
			sb.append(arr[ans.getFirst()] + " ");
		}
		System.out.println(sb);
	}
}