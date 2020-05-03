package bj_06118_숨바꼭질;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
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
		public int compareTo(Node o) {
			return this.distance - o.distance;
		}
	}
	private static int n, m, dist[];
	private static ArrayList<Integer>[] list;
	private static PriorityQueue<Node> pq;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
	
		dist = new int[n + 1];
		for (int i = 2; i < n + 1; i++)	//1은 출발점이니까 0으로 비워두기!
			dist[i] = Integer.MAX_VALUE;
		
		list = new ArrayList[n + 1];
		//배열로 쓰는 ArrayList는 반드시 하나씩 생성 해주기!
		//for (int i = 0; i < n + 1; i++)	가독성 향상 
		for (int i = 1; i <= n; i++)	
			list[i] = new ArrayList<>();
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list[a].add(b);
			list[b].add(a);
		}
		
		pq = new PriorityQueue<>();
		pq.add(new Node(1, 0));
		
		while (!pq.isEmpty()) {
			Node temp = pq.poll();
			int now = temp.index;
			int nowDistance = temp.distance;
			
			if (dist[now] < nowDistance) continue;
			
			for (int i = 0; i < list[now].size(); i++) {
				int next = list[now].get(i);
				int nextDistance = nowDistance + 1;
				
				if (dist[next] <= nextDistance) continue;
					
				dist[next] = nextDistance;
				pq.add(new Node(next, nextDistance));
			}
		}
		
		int num = 1, distance = 0, cnt = 1;
		for (int i = 2; i < n + 1; i++) {
			if (distance < dist[i]) {
				distance = dist[i];
				num = i;
				cnt = 1;
			} else if (distance == dist[i]) {
				cnt++;	//num은 어차피 작은 수부터 돌기 때문에 굳이 안해줘도 됨 
			}
		}
		System.out.println(num + " " + distance + " " + cnt);
	}
}