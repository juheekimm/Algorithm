package bj_01260_DFS와BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_my {
	static int vertex;	//����
	static int edge;	//����
	static int[][] graph;
	static boolean[] isVisit;
	
	
	public static void main(String[] args) throws IOException {
		Queue<Integer> q = new LinkedList<Integer>();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		vertex = Integer.parseInt(st.nextToken());
		edge = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(st.nextToken());

		//������ 1���� �����ϹǷ� �Ϻη� 1�� ũ�� ����
		graph = new int[vertex + 1][vertex + 1];
		int n, m;
		for (int i = 0; i < edge; i++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			graph[n][m] = 1;
			graph[m][n] = 1;
		
		}
		isVisit = new boolean[vertex + 1];
		isVisit[start] = true;
		dfs(start);
		
		System.out.println();
		isVisit = new boolean[vertex + 1];
		q.add(start);
		isVisit[start] = true;
		bfs(q);
	}

	private static void dfs(int ver) {
		System.out.print(ver + " ");
		for (int j = 0; j < vertex + 1; j++) {
			if (graph[ver][j] == 1 && !isVisit[j]) {
				isVisit[j] = true;
				dfs(j);
			}
		}
	}
	
	private static void bfs(Queue<Integer> q) {
		while(!q.isEmpty()) {
			int ver = q.poll();
			System.out.print(ver + " ");
			for (int j = 0; j < vertex + 1; j++) {
				if (graph[ver][j] == 1 && !isVisit[j]) {
					q.add(j);
					isVisit[j] = true;
				}
			}
		}
	}
}