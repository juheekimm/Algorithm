package bj_06118_숨바꼭질;

import java.io.*;
import java.util.*;

public class Main_200513 {
	
	static class Node implements Comparable<Node> {
		int idx, weight;
		Node (int idx, int weight) {
			this.idx = idx;
			this.weight = weight;
		}
		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}
	}
	static int N, M, dist[];
	static ArrayList<Integer>[] list;
	static PriorityQueue<Node> pq = new PriorityQueue<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		dist = new int[N];
		list = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			dist[i] = Integer.MAX_VALUE;
			list[i] = new ArrayList<Integer>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			list[a].add(b);
			list[b].add(a);
		}
		
		dist[0] = 0;
		pq.add(new Node(0, 0));
		dijkstra();
		searchMaxDist();
	}

	private static void searchMaxDist() {
		int max = 0, idx = 0, cnt = 0;
		for (int i = 0, len = dist.length; i < len; i++) {
			if (max < dist[i]) {
				max = dist[i];
				idx = i;
				cnt = 1;
			} else if (max == dist[i]) {
				cnt++;
			}
		}
		System.out.println((idx + 1) + " " + max + " " + cnt);
	}

	private static void dijkstra() {
		while (!pq.isEmpty()) {
			Node temp = pq.poll();
			int now = temp.idx;
			int nowDist = temp.weight;
			
			if (dist[now] < nowDist) continue;
			
			for (int i = 0, size = list[now].size(); i < size; i++) {
				int next = list[now].get(i);
				
				if (dist[next] <= dist[now] + 1) continue;
				
				dist[next] = dist[now] + 1;
				pq.add(new Node(next, dist[next]));
			}
		}
	}
}