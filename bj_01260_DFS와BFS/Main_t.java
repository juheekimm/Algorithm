package bj_01260_DFS와BFS;

import java.io.*;
import java.util.*;

public class Main_t {
	static int vertex;	//����
	static int edge;	//����
	static ArrayList<Integer>[] graph;
	static boolean[] isVisit;
	
	public static void main(String[] args) throws IOException {
		Queue<Integer> q = new LinkedList<Integer>();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		vertex = stoi(st.nextToken());
		edge = stoi(st.nextToken());
		int start = stoi(st.nextToken());
		
		//������ 1���� �����ϹǷ� �Ϻη� 1�� ũ�� ����
		graph = new ArrayList[vertex+1];
		for(int i = 1; i <= vertex; i++) {
			graph[i] = new ArrayList<>();
		}
		
		int n, m;
		for (int i = 0; i < edge; i++) {
			st = new StringTokenizer(br.readLine());
			n = stoi(st.nextToken());
			m = stoi(st.nextToken());
			graph[n].add(m);
			graph[m].add(n);
		}
		
		for(int i = 1; i <= vertex; i++) {
			Collections.sort(graph[i], new Comparator<Integer>() {
				@Override
				public int compare(Integer o1, Integer o2) {
					return o1-o2;
				}
			});
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
		for (int a : graph[ver]) {
			if (!isVisit[a]) {
				isVisit[a] = true;
				dfs(a);
			}
		}
	}
	
	private static void bfs(Queue<Integer> q) {
		while(!q.isEmpty()) {
			int ver = q.poll();
			System.out.print(ver + " ");
			for (int a : graph[ver]) {
				if (!isVisit[a]) {
					q.add(a);
					isVisit[a] = true;
				}
			}
		}
	}
	
	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}