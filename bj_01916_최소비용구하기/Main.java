package bj_01916_최소비용구하기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

//200107 15m 기본 다익스트라 문제. 8월에는 손도 못댔는데 드디어 풀었다ㅠ 
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
	private static int n, m, s, e, dist[];
	private static ArrayList<Node>[] list;
	private static PriorityQueue<Node> pq = new PriorityQueue<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = stoi(br.readLine());
		m = stoi(br.readLine());
		
		dist = new int[n + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		list = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++)
			list[i] = new ArrayList<>();
	
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			list[stoi(st.nextToken())].add(new Node(stoi(st.nextToken()), stoi(st.nextToken())));
		}
	
		st = new StringTokenizer(br.readLine());
		s = stoi(st.nextToken());
		e = stoi(st.nextToken());
	
		pq.add(new Node(s, 0));
		dist[s] = 0;
		dijkstra();
		System.out.println(dist[e]);
	}

	private static void dijkstra() {
		while (!pq.isEmpty()) {
			Node temp = pq.poll();
			int now = temp.index;
			int nowDistance = temp.distance;
			
			if (dist[now] < nowDistance) continue;
			
			for (int i = 0; i < list[now].size(); i++) {
				int next = list[now].get(i).index;
				int nextDistance = dist[now] + list[now].get(i).distance;
				
				if (dist[next] < nextDistance) continue;
				
				dist[next] = nextDistance;
				pq.add(new Node(next, nextDistance));
			}
		}
	}

	private static int stoi(String str) {
		return Integer.parseInt(str);
	}
}