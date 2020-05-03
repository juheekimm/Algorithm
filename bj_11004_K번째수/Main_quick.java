package bj_11004_K번째수;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//QuickSort
public class Main_quick {
	
	static int N, K, data[];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		data = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			data[i] = Integer.parseInt(st.nextToken());
		
		quick(0, N - 1);
		System.out.println(data[K - 1]);
	}

	private static void quick(int start, int end) {
		if (start == end) return;
		
		int mid = partition(start, end);
		quick(start, mid - 1);
		quick(mid, end);
	}

	private static int partition(int start, int end) {
		int pivot = data[(start + end) >> 1];
		while (start <= end) {
			if (data[start] < pivot) start++;
			if (data[end] > pivot) end--;
			
			if (start <= end)
				swap(start++, end--);
		}
		return start;
	}

	private static void swap(int a, int b) {
		int temp = data[a];
		data[a] = data[b];
		data[b] = temp;
	}
}