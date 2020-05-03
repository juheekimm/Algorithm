package bj_01647_도시분할계획;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main2 {
	
	private static class Node implements Comparable<Node> {
		int s, e, v;
		Node (int s, int e, int v) {
			this.s = s;
			this.e = e;
			this.v = v;
		}
		@Override
		public int compareTo(Node o) {
			return this.v - o.v;
		}
	}
	
	private static int N, M, min, parents[];
	private static PriorityQueue<Node> pq;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		pq = new PriorityQueue<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			pq.add(new Node(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken())));
		}
		
		parents = new int[N];
		Arrays.fill(parents, -1);
		
		wayMake();
		System.out.println(min);
	}

	private static void wayMake() {
		int num = 0;
		while (!pq.isEmpty()) {
			Node temp = pq.poll();
			if (union(temp.s, temp.e)) {
				min += temp.v;
				num++;
			}
			
			if (num == N - 2) return;
		}
	}

	private static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if (aRoot != bRoot) {
			parents[bRoot] = aRoot;
			return true;
		}
		return false;
	}

	private static int find(int a) {
		if (parents[a] < 0)
			return a;
		return parents[a] = find(parents[a]);
	}
}