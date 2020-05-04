package bj_01427_소트인사이드;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_quick_200504 {
	
	static char[] ch;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder()
;		ch = br.readLine().toCharArray();
		
		int len = ch.length;
		quick(0, len - 1);
		for (int i = len - 1; i >= 0; i--)
			sb.append(ch[i]);
		System.out.println(sb);
	}

	private static void quick(int start, int end) {
		if (start == end)
			return;
		
		int mid = partition(start, end);
		quick(start, mid - 1);
		quick(mid, end);
	}

	private static int partition(int L, int R) {
		int pivot = ch[(L + R) >> 1];
		
		while (L <= R) {
			while (ch[L] < pivot) L++;
			while (pivot < ch[R]) R--;
			
			if (L <= R) swap(L++, R--);
		}
		return L;
	}

	private static void swap(int i, int j) {
		char temp = ch[i];
		ch[i] = ch[j];
		ch[j] = temp;
	}
}
