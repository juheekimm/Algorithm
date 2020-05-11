package bj_01753_최단경로;

import java.io.*;
import java.util.*;

public class Main_200511 {
	
	static class Node implements Comparable<Node> {
		int index, distance;
		Node (int index, int distance) {
			this.index = index;
			this.distance = distance;
		}
		
		@Override
		public int compareTo(Node o) {
			return this.distance - o.distance;
		}
	}
	
	static int V, E, dist[], S;
	static ArrayList<Node>[] list;
	static PriorityQueue<Node> pq = new PriorityQueue<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(br.readLine()) - 1;
		
		dist = new int[V];
		list = new ArrayList[V];
		for (int i = 0; i < V; i++) {
			dist[i] = Integer.MAX_VALUE;
			list[i] = new ArrayList<>();
		}
			
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken()) - 1;
			int v = Integer.parseInt(st.nextToken()) - 1;
			int w = Integer.parseInt(st.nextToken());
			list[u].add(new Node(v, w));
		}
		
		dist[S] = 0;
		pq.add(new Node(S, 0));
		dijkstra();
		for (int i = 0; i < V; i++) {
			if (dist[i] == Integer.MAX_VALUE)
				sb.append("INF\n");
			else
				sb.append(dist[i] + "\n");
		}
		System.out.println(sb);
	}

	private static void dijkstra() {
		while (!pq.isEmpty()) {
			Node temp = pq.poll();
			//지금 점에 대해, 이 점을 최소 비용으로 갈 수 있는지 체크 
			int now = temp.index;
			int nowDistance = temp.distance;
			
			if (dist[now] < nowDistance) continue;
			
			for (int i = 0; i < list[now].size(); i++) {
				//다음에 갈 점에 대해 체크 
				int next = list[now].get(i).index;
				int nextDistance = dist[now] + list[now].get(i).distance;
				
				if (dist[next] <= nextDistance) continue;
				
				dist[next] = nextDistance;
				pq.add(new Node(next, nextDistance));
			}
		}
	}
}