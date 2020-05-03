package bj_01238_파티;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

//200106 20m 처음에는 뭔지 감도 못잡았는데 다익스트라 공부 + 문제 2개 풀고나니까 쉽게 풀림 
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
	private static int n, m, x;
	private static PriorityQueue<Node> pq;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = stoi(st.nextToken());
		m = stoi(st.nextToken());
		x = stoi(st.nextToken());
		
		int[] dist = new int[n + 1];
		int[] reverseDist = new int[n + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		Arrays.fill(reverseDist, Integer.MAX_VALUE);
		
		ArrayList<Node>[] list = new ArrayList[n + 1];
		ArrayList<Node>[] reverseList = new ArrayList[n + 1];
		for (int i = 0; i <= n; i++) {
			list[i] = new ArrayList<Node>();
			reverseList[i] = new ArrayList<Node>();
		}
			
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = stoi(st.nextToken());
			int b = stoi(st.nextToken());
			int c = stoi(st.nextToken());
			list[a].add(new Node(b, c));
			reverseList[b].add(new Node(a, c));
		}
		
		//각 점에서 x번으로 가는 다익스트
		pq = new PriorityQueue<>();
		pq.add(new Node(x, 0));
		reverseDist[x] = 0;
		dijkstra(reverseDist, reverseList);
		
		//x번에서 각 점으로 가는 다익스트라 
		pq = new PriorityQueue<>();
		pq.add(new Node(x, 0));
		dist[x] = 0;
		dijkstra(dist, list);

		int max = -1;
		for (int i = 1; i <= n; i++)
			if (max < dist[i] + reverseDist[i])
				max = dist[i] + reverseDist[i];

		System.out.println(max);
	}

	private static void dijkstra(int[] dist, ArrayList<Node>[] list) {
		Node temp;
		
		while (!pq.isEmpty()) {
			temp = pq.poll();
			int now = temp.index;
			int nowDist = temp.distance;
			
			if (dist[now] < nowDist) continue;
			
			for (int i = 0; i < list[now].size(); i++) {
				int next = list[now].get(i).index;
				int nextDist = dist[now] + list[now].get(i).distance;
				
				if (dist[next] < nextDist) continue;
				
				dist[next] = nextDist;
				pq.add(new Node(next, nextDist));
			}
		}
	}

	private static int stoi(String str) {
		return Integer.parseInt(str);
	}
}