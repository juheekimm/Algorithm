package bj_01306_달려라홍준;

import java.io.*;
import java.util.*;

public class Main_210114_segtree {
	
	private static int N, M, arr[];

	private static class SegTree {
		int data[];
		int size;
		int height = 1;
		
		SegTree (int size) {
			while ((1 << height) < size)
				height++;
			
			this.size = 1 << (height + 1);
			data = new int[this.size];
		}
		
		int create (int now, int left, int right) {
			if (left == right)
				return data[now] = arr[left - 1];
			
			int next = now << 1;
			int mid = (left + right) >> 1;
			
			return data[now] = Math.max(create(next, left, mid), create(next + 1, mid + 1, right));
		}
		
		int query (int now, int left, int right, int L, int R) {
			if (L > right || R < left)
				return -1;
			
			if (L <= left && right <= R)
				return data[now];
			
			int next = now << 1;
			int mid = (left + right) >> 1;
			
			return Math.max(query(next, left, mid, L, R), query(next + 1, mid + 1, right, L, R));
		}
	}
	
	private static SegTree segTree;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			arr[i] = stoi(st.nextToken());
		
		segTree = new SegTree(N);
		segTree.create(1, 1, N);
		
		int l = 1, r = 2 * M - 1;
		while (true) {
			if (r > N) break;
			sb.append(segTree.query(1, 1, N, l, r) + " ");
			l++;
			r++;
		}
		System.out.println(sb);		
	}

	private static int stoi(String str) {
		return Integer.parseInt(str);
	}
}
