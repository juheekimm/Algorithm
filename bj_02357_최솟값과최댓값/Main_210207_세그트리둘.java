package bj_02357_최솟값과최댓값;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_210207_세그트리둘 {
	private static int N, M, arr[];

	private static class MaxSegTree {
		int[] max;
		int size;
		int height = 1;

		MaxSegTree(int size) {
			while ((1 << height) < size)
				height++;

			this.size = 1 << (height + 1);
			max = new int[this.size];
		}

		int maxCreate(int now, int left, int right) {
			if (left == right)
				return max[now] = arr[left - 1];

			int next = now << 1;
			int mid = (left + right) >> 1;

			return max[now] = Math.max(maxCreate(next, left, mid), maxCreate(next + 1, mid + 1, right));
		}

		int maxQuery(int now, int left, int right, int L, int R) {
			if (L > right || R < left)
				return -1;

			if (L <= left && right <= R)
				return max[now];

			int next = now << 1;
			int mid = (left + right) >> 1;

			return Math.max(maxQuery(next, left, mid, L, R), maxQuery(next + 1, mid + 1, right, L, R));
		}
	}

	private static class MinSegTree {
		int[] min;
		int size;
		int height = 1;

		MinSegTree(int size) {
			while ((1 << height) < size)
				height++;

			this.size = 1 << (height + 1);
			min = new int[this.size];
		}

		int minCreate(int now, int left, int right) {
			if (left == right)
				return min[now] = arr[left - 1];

			int next = now << 1;
			int mid = (left + right) >> 1;

			return min[now] = Math.min(minCreate(next, left, mid), minCreate(next + 1, mid + 1, right));
		}

		int minQuery(int now, int left, int right, int L, int R) {
			if (L > right || R < left)
				return Integer.MAX_VALUE;

			if (L <= left && right <= R)
				return min[now];

			int next = now << 1;
			int mid = (left + right) >> 1;

			return Math.min(minQuery(next, left, mid, L, R), minQuery(next + 1, mid + 1, right, L, R));
		}
	}

	private static MaxSegTree maxSegTree;
	private static MinSegTree minSegTree;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = stoi(st.nextToken());
		M = stoi(st.nextToken());

		arr = new int[N];
		for (int i = 0; i < N; i++)
			arr[i] = stoi(new StringTokenizer(br.readLine()).nextToken());

		maxSegTree = new MaxSegTree(N);
		minSegTree = new MinSegTree(N);
		maxSegTree.maxCreate(1, 1, N);
		minSegTree.minCreate(1, 1, N);

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int l = stoi(st.nextToken());
			int r = stoi(st.nextToken());
			sb.append(minSegTree.minQuery(1, 1, N, l, r) + " " + maxSegTree.maxQuery(1, 1, N, l, r) + "\n");
		}
		System.out.println(sb);
	}

	private static int stoi(String str) {
		return Integer.parseInt(str);
	}
}