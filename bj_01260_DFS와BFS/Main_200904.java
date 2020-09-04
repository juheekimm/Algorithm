package bj_01260_DFS와BFS;

import java.io.*;
import java.util.*;

public class Main_200904 {
	
	static int N, M, V;
	static LinkedList<Integer>[] list;
	static StringBuilder sb = new StringBuilder();
	static boolean[] dfsCheck, bfsCheck;
	static Queue<Integer> q = new LinkedList<Integer>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		V = stoi(st.nextToken());
		
		dfsCheck = new boolean[N + 1];
		bfsCheck = new boolean[N + 1];
		
		list = new LinkedList[N + 1];
		for (int i = 1; i <= N; i++)
			list[i] = new LinkedList<Integer>();
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = stoi(st.nextToken());
			int b = stoi(st.nextToken());
			list[a].add(b);
			list[b].add(a);
		}
		
		for (int i = 1; i <= N; i++)
			Collections.sort(list[i]);
		
		dfsCheck[V] = true;
		dfs(V);

		sb.append("\n");
		bfsCheck[V] = true;
		q.add(V);
		bfs();
		
		System.out.println(sb);
	}

	private static void bfs() {
		while (!q.isEmpty()) {
			int temp = q.poll();
			sb.append(temp + " ");
			int size = list[temp].size();
			for (int i = 0; i < size; i++) {
				if (bfsCheck[list[temp].get(i)]) continue;
				bfsCheck[list[temp].get(i)] = true;
				q.add(list[temp].get(i));
			}
		}
	}

	private static void dfs(int now) {
		sb.append(now + " ");

		int size = list[now].size();
		for (int i = 0; i < size; i++) {
			if (dfsCheck[list[now].get(i)]) continue;
			dfsCheck[list[now].get(i)] = true;
			dfs(list[now].get(i));
		}
	}

	private static int stoi(String str) {
		return Integer.parseInt(str);
	}
}