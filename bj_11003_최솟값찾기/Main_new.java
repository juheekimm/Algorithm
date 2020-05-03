package bj_11003_최솟값찾기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_new {
	
	private static int N, L;
	
	private static class SegTree {
		int[] data;
		int size;
		int height = 1;
		
		SegTree (int size) {
			while ((1 << height) < size)
				height++;
			
			this.size = 1 << (height + 1);
			data = new int[this.size];
		}
		
		int create (int now, int left, int right, int[] arr) {
			if (left == right)
				return data[now] = arr[left];
			
			int next = now << 1;
			int mid = (left + right) >> 1;
			
			return data[now] = Math.min(create(next, left, mid, arr), create(next + 1, mid + 1, right, arr));
		}
		
		int query (int now, int left, int right, int L, int R) {
			if (L > right || R < left)
				return Integer.MAX_VALUE;
			
			if (L <= left && right <= R)
				return data[now];
			
			int next = now << 1;
			int mid = (left + right) >> 1;
			
			return Math.min(query(next, left, mid, L, R), query(next + 1, mid + 1, right, L, R));
		}
	}
	
	static SegTree segTree;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		segment(arr);
	}

	private static void segment(int[] arr) {
		StringBuilder sb = new StringBuilder();
		
		segTree = new SegTree(N);
		segTree.create(1, 0, N - 1, arr);
		
		int s = 0;
		for (int i = 0; i < N; i++) {
			s = i - L + 1;
			if (s < 0) s = 0;
			sb.append(segTree.query(1, 0, N - 1, s, i) + " ");
		}
		System.out.println(sb);
	}
}