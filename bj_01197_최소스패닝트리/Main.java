package bj_01197_최소스패닝트리;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//200104 20m / 47236 472ms / 크루스칼 이해 안돼서 배열로 구현한 코드 보고 우선순위 큐로 풀어봄 
//나중에 다시 한번 풀어보기!
public class Main {
	
	private static class Node implements Comparable<Node> {
		int s, e, v;
		Node (int s, int e, int v) {
			this.s = s;
			this.e = e;
			this.v = v;
		}
		@Override
		public int compareTo(Node o) {
			return this.v - o.v;	//오름차순 정렬 
		}
	}

	private static int v, e, parents[];
	private static PriorityQueue<Node> pq = new PriorityQueue<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		v = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());
		
		parents = new int[v + 1];
		Arrays.fill(parents, -1);
		
		for (int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			pq.add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		Node temp;
		int sum = 0;
		while (!pq.isEmpty()) {
			temp = pq.poll();
			if (union(temp.s, temp.e)) sum += temp.v;
		}
		System.out.println(sum);
	}

	private static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if (aRoot != bRoot) {
			parents[bRoot] = aRoot;
			return true;
		}
		return false;
	}

	private static int find(int a) {
		if (parents[a] < 0) return a;
		return parents[a] = find(parents[a]);
	}
}