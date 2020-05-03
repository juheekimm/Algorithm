package bj_11003_최솟값찾기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main2 {
	
	private static int N, L, arr[];
	private static LinkedList<Integer> ans = new LinkedList<Integer>();
	
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
		ans.add(0);
		System.out.print(arr[0] + " ");
		for (int i = 1; i < N; i++) {
			int size = ans.size();
			for (int j = 1; j <= size; j++) {
//				System.out.println("Size " + size + " j " + j);
				if (arr[ans.get(size - j)] > arr[i])
					ans.remove(size - j);
			}
			ans.add(i);
			
			if (ans.get(0) <= i - L)
				ans.removeFirst();
			
			System.out.print(arr[ans.get(0)] + " ");
			
//			for (int j = 0; j < L; j++) {
//				if (i - j < 0) break;
//				if (ans.get(ans.size()) > arr[i - j])
//					ans.removeLast();
//			}
		}
	}
}


















