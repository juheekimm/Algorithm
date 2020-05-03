package bj_01275_커피숍2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_0부터N빼기1까지 {

	private static class SegTree {
		long[] data;
		int size;
		int height = 1;

		SegTree(int size) {
			while ((1 << height) < size)
				height++;

			this.size = 1 << (height + 1);
			data = new long[this.size];
		}

		long create(int now, int left, int right, int[] arr) {
			if (left == right)
				return data[now] = arr[left];

			int next = now << 1;
			int mid = (left + right) >> 1;

			return data[now] = create(next, left, mid, arr) + create(next + 1, mid + 1, right, arr);
		}

		long query(int now, int left, int right, int L, int R) {
			if (L > right || R < left)
				return 0;

			if (L <= left && right <= R)
				return data[now];

			int next = now << 1;
			int mid = (left + right) >> 1;

			return query(next, left, mid, L, R) + query(next + 1, mid + 1, right, L, R);
		}

		long update(int now, int left, int right, int target, int val) {
			if (!(left <= target && target <= right))
				return data[now];

			if (left == right)
				return data[now] = val;

			int next = now << 1;
			int mid = (left + right) >> 1;

			return data[now] = update(next, left, mid, target, val) + update(next + 1, mid + 1, right, target, val);
		}

		void print() {
			int num = 2;
			for (int i = 1; i < size; i++) {
				if (num == i) {
					System.out.println();
					num <<= 1;
				}
				System.out.format("%2d ", data[i]);
			}
			System.out.println();
		}
	}

	static int N, Q;
	static SegTree segTree;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());

		int[] arr = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(st.nextToken());

		segTree = new SegTree(N);
		segTree.create(1, 0, N - 1, arr);

		for (int i = 0; i < Q; i++) {
//			segTree.print();
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			if (x < y)
				sb.append(segTree.query(1, 0, N - 1, x, y) + "\n");
			else 
				sb.append(segTree.query(1, 0, N - 1, y, x) + "\n");
			
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken());
			segTree.update(1, 0, N - 1, a, b);
		}
		System.out.println(sb);
	}
}