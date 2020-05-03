package bj_10989_수정렬하기3;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	static int size, data[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		size = Integer.parseInt(br.readLine());
		data = new int[size];
		for (int i = 0; i < size; i++)
			data[i] = Integer.parseInt(br.readLine());

		quick(0, size - 1);
		
		for (int i = 0; i < size; i++)
			System.out.println(data[i]);
	}

	private static void quick(int start, int end) {
		if (start == end) return;
		
		int mid = partition(start, end);
		quick(start, mid - 1);
		quick(mid, end);
	}

	private static int partition(int L, int R) {
		int pivot = data[(L + R) >> 1];
		
		while (L <= R) {
			while (data[L] < pivot) L++;
			while (pivot < data[R]) R--;
			if (L <= R)
				swap(L++, R--);
		}
		return L;
	}

	private static void swap(int i, int j) {
		int temp = data[i];
		data[i] = data[j];
		data[j] = temp;
	}
}