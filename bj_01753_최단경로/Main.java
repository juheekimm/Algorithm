package bj_01753_최단경로;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	private static class Node implements Comparable<Node> {
		int index, distance;
		Node (int index, int distance) {
			this.index = index;
			this.distance = distance;
		}
		@Override
		public int compareTo (Node o) {
			return this.distance - o.distance;
		}
	}
	
	private static int v, e, k, dist[];
	private static ArrayList<Node>[] list;
	private static PriorityQueue<Node> pq = new PriorityQueue<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		v = stoi(st.nextToken());
		e = stoi(st.nextToken());
		k = stoi(br.readLine());
		
		dist = new int[v + 1];
		list = new ArrayList[v + 1];
		for (int i = 0; i <= v; i++) {
			dist[i] = Integer.MAX_VALUE;
			list[i] = new ArrayList<Node>();
		}
			
		for (int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			list[stoi(st.nextToken())].add(new Node(stoi(st.nextToken()), stoi(st.nextToken())));
		}
		
		dist[k] = 0;
		pq.add(new Node(k, 0));

		dijkstra();
		
		for (int i = 1; i <= v; i++) {
			if (i == k)
				sb.append("0\n");
			else if (dist[i] == Integer.MAX_VALUE)
				sb.append("INF\n");
			else
				sb.append(dist[i] + "\n");
		}
		System.out.println(sb);
	}

	private static void dijkstra() {
		Node temp;
		
		while (!pq.isEmpty()) {
			temp = pq.poll();
			int now = temp.index;
			int nowDist = temp.distance;
			
			if (dist[now] < nowDist) continue;
			
			for (int i = 0; i < list[now].size(); i++) {
				int next = list[now].get(i).index;
				int nextDist = dist[now] + list[now].get(i).distance;
				
				if (dist[next] <= nextDist) continue;
				
				dist[next] = nextDist;
				pq.add(new Node(next, nextDist));
			}
		}
	}

	private static int stoi(String str) {
		return Integer.parseInt(str);
	}
}