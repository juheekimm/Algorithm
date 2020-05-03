package bj_6497_전력난;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	private static class Node implements Comparable<Node> {
		int s, e, w;
		Node (int s, int e, int w) {
			this.s = s;
			this.e = e;
			this.w = w;
		}
		@Override
		public int compareTo (Node o) {
			return this.w - o.w;
		}
	}
	
	private static int m, n, parents[], turnon, sum;
	private static PriorityQueue<Node> pq;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		while (true) {
			st = new StringTokenizer(br.readLine());
			m = Integer.parseInt(st.nextToken());
			n = Integer.parseInt(st.nextToken());
			if (m == 0 && n == 0) break;

			pq = new PriorityQueue<>();
			turnon = sum = 0;
			
			parents = new int[m];
			Arrays.fill(parents, -1);
			
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				pq.add(new Node(a, b, c));
				sum += c;
			}
			kruskal();
			System.out.println(sum - turnon);
		}
	}

	private static void kruskal() {
		int num = 0;
		
		while (!pq.isEmpty() && num < m - 1) {
			Node temp = pq.poll();
			if (union(temp.s, temp.e)) {
				num++;
				turnon += temp.w;
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