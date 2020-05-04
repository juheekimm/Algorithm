package bj_11004_K번째수;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//MergeSort
public class Main_merge {
	
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
		
		merge(0, N - 1);
		System.out.println(data[K - 1]);
	}

	private static void merge(int start, int end) {
		if (start == end) return;
		
		int mid = (start + end) >> 1;
		merge(start, mid);
		merge(mid + 1, end);
		mergeSort(start, mid, end);
	}

	private static void mergeSort(int start, int mid, int end) {
		int L = start, R = mid + 1, idx = 0;
		int[] temp = new int[end - start + 1];
		
		while (L <= mid && R <= end) {
			if (data[L] < data[R])
				temp[idx++] = data[L++];
			else
				temp[idx++] = data[R++];
		}
		
		while (L <= mid)
			temp[idx++] = data[L++];

		while (R <= end)
			temp[idx++] = data[R++];
		
		for (int i = 0; i < temp.length; i++)
			data[start + i] = temp[i];
	}
}