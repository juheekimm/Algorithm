package bj_01197_최소스패닝트리;

import java.io.*;
import java.util.*;

public class Main_200511 {
	
	static class Node implements Comparable<Node> {
		int s, e, w;
		Node (int s, int e, int w) {
			this.s = s;
			this.e = e;
			this.w = w;
		}
		@Override
		public int compareTo(Node o) {
			return this.w - o.w;
		}
	}
	
	static int V, E, parents[], weight = 0;
	static PriorityQueue<Node> pq = new PriorityQueue<>();

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		parents = new int[V];
		Arrays.fill(parents, -1);
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			pq.add(new Node(Integer.parseInt(st.nextToken()) - 1,
							Integer.parseInt(st.nextToken()) - 1,
							Integer.parseInt(st.nextToken())));
		}
		
		kruskal();
		System.out.println(weight);
	}

	private static void kruskal() {
		int count = 0;
		while (!pq.isEmpty() && count != V) {
			Node temp = pq.poll();
			if (union(temp.s, temp.e)) {
				weight += temp.w;
				count++;
			}
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
