package bj_02982_국왕의방문;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	private static class Node implements Comparable<Node> {
		int index, distance;
		boolean delay = false;
		
		Node (int index, int distance) {
			this.index = index;
			this.distance = distance;
		}
		Node (int index, int distance, boolean delay) {
			this(index, distance);
			this.delay = true;
		}
		@Override
		public int compareTo (Node o) {
			return this.distance - o.distance;
		}
	}
	
	private static int N, M, A, B, K, G, g[], dist[], time[][], delayTime[], totalTime, prevt, nowt;
	private static ArrayList<Node>[] list;
	private static PriorityQueue<Node> pq = new PriorityQueue<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());

		st = new StringTokenizer(br.readLine());
		A = stoi(st.nextToken());
		B = stoi(st.nextToken());
		K = stoi(st.nextToken());
		G = stoi(st.nextToken());
		
		g = new int[G];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < G; i++)
			g[i] = stoi(st.nextToken());
		
		dist = new int[N + 1];
		list = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			dist[i] = Integer.MAX_VALUE;
			list[i] = new ArrayList<Node>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = stoi(st.nextToken());
			int b = stoi(st.nextToken());
			int c = stoi(st.nextToken());
			list[a].add(new Node(b, c));
			list[b].add(new Node(a, c));
			totalTime += c;
		}
		
		time = new int[totalTime + 1][2];
		delayTime = new int[totalTime + 1];
		
		int t = 0;
		for (int i = 0; i < G - 1; i++) {
			for (int j = 0; j < list[g[i]].size(); j++) {
				
				if (list[g[i]].get(j).index == g[i + 1]) {
					if (nowt != 0) prevt = nowt;
					nowt = t;
					Arrays.fill(delayTime, prevt, nowt, nowt);
					Arrays.fill(time, t, t + list[g[i]].get(j).distance, new int[] {g[i], g[i + 1]});
					t += list[g[i]].get(j).distance;
					break;
				}
			}
		}
		dist[A] = K;
		pq.add(new Node(A, K));

		dijkstra();
		System.out.println(dist[B] - K);
	}

	private static void dijkstra() {
		while (!pq.isEmpty()) {
			Node temp = pq.poll();
			int now = temp.index;
			int nowDist = temp.distance;

			if (now == B) return;
//			if (dist[now] < nowDist) continue;

			for (int i = 0; i < list[now].size(); i++) {
//				if (dist[now] == Integer.MAX_VALUE) continue;
				int next = list[now].get(i).index;
				int nextDist = dist[now] + list[now].get(i).distance;
				if (temp.delay)	nextDist = nowDist + list[now].get(i).distance;
					
				if (dist[next] <= nextDist) continue;

				if (nowDist < nowt) {
					if (time[nowDist][0] == now && time[nowDist][1] == next
							|| time[nowDist][1] == now && time[nowDist][0] == next) {
						dist[now] = nowDist;
						pq.add(new Node(now, delayTime[nowDist], true));
						continue;
					}
				}
				dist[next] = nextDist;
				pq.add(new Node(next, nextDist));
			}
		}
	}

	private static int stoi(String str) {
		return Integer.parseInt(str);
	}
}