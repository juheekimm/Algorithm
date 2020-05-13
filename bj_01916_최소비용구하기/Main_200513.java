package bj_01916_최소비용구하기;

import java.io.*;
import java.util.*;

public class Main_200513 {
	static class Node implements Comparable<Node> {
		int idx, w;
		Node (int idx, int w) {
			this.idx = idx;
			this.w = w;
		}
		@Override
		public int compareTo(Node o) {
			return this.w - o.w;
		}
	}
	
	static int N, M, dist[], S, E;
	static ArrayList<Node>[] list;
	static PriorityQueue<Node> pq = new PriorityQueue<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		dist = new int[N];
		list = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			dist[i] = Integer.MAX_VALUE;
			list[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			list[Integer.parseInt(st.nextToken()) - 1].add(new Node(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken())));
		}

		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken()) - 1;
		E = Integer.parseInt(st.nextToken()) - 1;
		
		dist[S] = 0;
		pq.add(new Node(S, 0));
		dijkstra();
		System.out.println(dist[E]);
	}

	private static void dijkstra() {
		while (!pq.isEmpty()) {
			Node temp = pq.poll();
			int now = temp.idx;
			int nowDist = temp.w;
			
			if (dist[now] < nowDist) continue;
			
			for (int i = 0, size = list[now].size(); i < size; i++) {
				int next = list[now].get(i).idx;
				int nextDist = dist[now] + list[now].get(i).w;
				
				if (dist[next] <= nextDist) continue;
				
				dist[next] = nextDist;
				pq.add(new Node(next, nextDist));
			}
		}
	}
}