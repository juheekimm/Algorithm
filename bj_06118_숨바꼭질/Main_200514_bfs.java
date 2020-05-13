package bj_06118_숨바꼭질;

import java.io.*;
import java.util.*;

public class Main_200514_bfs {
	
	static class Node {
		int idx, weight;
		Node (int idx, int weight) {
			this.idx = idx;
			this.weight = weight;
		}
	}
	static int N, M, visit[];
	static ArrayList<Integer>[] list;
	static Queue<Node> q = new LinkedList<>();
	
	//bfs. pq대신 q. //dist 대신 visit?
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		visit = new int[N];
		list = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			visit[i] = Integer.MAX_VALUE;
			list[i] = new ArrayList<Integer>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			list[a].add(b);
			list[b].add(a);
		}
		
		visit[0] = 0;
		q.add(new Node(0, 0));
		bfs();
		searchMaxDist();
	}

	private static void searchMaxDist() {
		int max = 0, idx = 0, cnt = 0;
		for (int i = 0, len = visit.length; i < len; i++) {
			if (max < visit[i]) {
				max = visit[i];
				idx = i;
				cnt = 1;
			} else if (max == visit[i]) {
				cnt++;
			}
		}
		System.out.println((idx + 1) + " " + max + " " + cnt);
	}

	private static void bfs() {
		while (!q.isEmpty()) {
			Node temp = q.poll();
			int now = temp.idx;
			int nowDist = temp.weight;
			
			if (visit[now] < nowDist) continue;
			
			for (int i = 0, size = list[now].size(); i < size; i++) {
				int next = list[now].get(i);
				
				if (visit[next] <= nowDist) continue;
				
				visit[next] = visit[now] + 1;
				q.add(new Node(next, visit[next]));
			}
		}
	}
}