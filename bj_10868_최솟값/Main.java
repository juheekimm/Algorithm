package bj_10868_최솟값;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	private static int N, M;
	
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
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(br.readLine());
		
		segTree = new SegTree(N);
		segTree.create(1, 0, N - 1, arr);
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			sb.append(segTree.query(1, 0, N - 1, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1) + "\n");
		}
		System.out.println(sb);
	}
}