package bj_01427_소트인사이드;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_quick {

	static char[] data;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		data = br.readLine().toCharArray();
		int len = data.length;

		quick(0, len - 1);
		for (int i = len - 1; i >= 0; i--)
			System.out.print(data[i]);
	}

	private static void quick(int start, int end) {
		if (start == end)
			return;

		int mid = partition(start, end);
		quick(start, mid - 1);
		quick(mid, end);
	}

	private static int partition(int L, int R) {
		int pivot = data[(L + R) >> 1];

		while (L <= R) {
			while (data[L] < pivot)
				L++;
			while (pivot < data[R])
				R--;
			if (L <= R) {
				swap(L++, R--);
			}
		}
		return L;
	}

	private static void swap(int a, int b) {
		char temp = data[a];
		data[a] = data[b];
		data[b] = temp;
	}
}