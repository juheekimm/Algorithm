package bj_02042_구간합구하기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_create없는버전 {
	
	private static int N, M, K;
	private static long arr[];
	
	private static class SegTree {
		long data[];
		int size;
		int height = 1;
		
		SegTree (int size) {
			while ((1 << height) < size)
				height++;
			
			this.size = 1 << (height + 1);
			data = new long[this.size];
		}
		
		long query (int now, int left, int right, int L, int R) {
			if (L > right || R < left)
				return 0;
			
			if (L <= left && right <= R)
				return data[now];
			
			int next = now << 1;
			int mid = (left + right) >> 1;
			
			return query(next, left, mid, L, R) + query(next + 1, mid + 1, right, L, R);
		}
		
		long update (int now, int left, int right, int targetIndex, long updateVal) {
			if (!(left <= targetIndex && targetIndex <= right))
				return data[now];
			
			if (left == right)
				return data[now] = updateVal;
			
			int next = now << 1;
			int mid = (left + right) >> 1;
			
			return data[now] = update(next, left, mid, targetIndex, updateVal) + update(next + 1, mid + 1, right, targetIndex, updateVal);
		}
	}
	
	private static SegTree segTree;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new long[N];
		
		segTree = new SegTree(N);

		for (int i = 1; i <= N; i++)
			segTree.update(1, 1, N, i, Long.parseLong(br.readLine()));
		
		for (int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long c = Long.parseLong(st.nextToken());
			
			if (a == 1)
				segTree.update(1, 1, N, b, c);
			else
				sb.append(segTree.query(1, 1, N, b, (int)c) + "\n");
		}
		System.out.println(sb);
	}
}