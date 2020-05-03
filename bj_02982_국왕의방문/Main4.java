package bj_02982_국왕의방문;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

public class Main4 {
	
	private static class Node implements Comparable<Node> {
		int index, distance;
		boolean delay;
		Node (int index, int distance) {
			this.index = index;
			this.distance = distance;
			this.delay = false;
		}
		Node (int index, int distance, boolean delay) {
			this.index = index;
			this.distance = distance;
			this.delay = true;
		}
		@Override
		public int compareTo (Node o) {
			return this.distance - o.distance;
		}
	}
	
	private static int N, M, A, B, K, G, g[], dist[], time[][], delayTime[], totalTime, nowTime, prevt, nowt;
	private static ArrayList<Node>[] list;
	private static PriorityQueue<Node> pq = new PriorityQueue<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		
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
					
					if (nowt != 0)
						prevt = nowt;
					nowt = t;
					Arrays.fill(delayTime, prevt, nowt, nowt);
					Arrays.fill(time, t, t + list[g[i]].get(j).distance, new int[] {g[i], g[i + 1]});
					t += list[g[i]].get(j).distance;
					break;
				}
			}
		}
//		System.out.println(Arrays.toString(delayTime));
		
		dist[A] = K;
		pq.add(new Node(A, K));

//		for (int i = 0; i < time.length; i++)
//			System.out.println(Arrays.toString(time[i]));
		dijkstra();
		System.out.println(dist[B] - K);
//		System.out.println(Arrays.toString(dist));
//		System.out.println(nowTime);
//		System.out.println(sb);
	}

//	private static void delayEndTimeCheck(int prevt, int nowt) {
//		for (int i = prevt; i < nowt; i++) {
//			delayTime[i] = nowt;
//		}
//	}

	private static void dijkstra() {
		Node temp;
		
		nowTime = K;
		while (!pq.isEmpty()) {
			temp = pq.poll();
			int now = temp.index;
			int nowDist = temp.distance;

			if (now == B) return;
//			if (dist[now] < nowDist) continue;
//			System.out.println("1nowtime " + nowTime + " nowDist " + nowDist);
			
//			if (nowTime == K)
//				nowTime += (nowDist);
//			else
				nowTime = nowDist;
			for (int i = 0; i < list[now].size(); i++) {
				int next = list[now].get(i).index;
				if (dist[now] == Integer.MAX_VALUE) continue;
				
				int nextDist = dist[now] + list[now].get(i).distance;
				if (temp.delay)
					nextDist = nowDist + list[now].get(i).distance;
					
				if (dist[next] <= nextDist) continue;
//				if (nowTime + nextDist <= totalTime && nowTime + nextDist < nowt) {
				if (nowTime < nowt) {
//					System.out.println("ㅇ");
//					System.out.println("2nowtime " + nowTime + " nowDist " + nowDist);
					if (time[nowDist][0] == now && time[nowDist][1] == next
							|| time[nowDist][1] == now && time[nowDist][0] == next) {
//						System.out.println("nextDist " + nextDist + " delayTime[nowTime]) " + delayTime[nowTime]);
						dist[now] = nowDist;
						pq.add(new Node(now, delayTime[nowTime], true));
						continue;
					}
				}
				
//				nowTime += next
				dist[next] = nextDist;
				pq.add(new Node(next, nextDist));
			}
		}
	}

	private static int stoi(String str) {
		return Integer.parseInt(str);
	}
}