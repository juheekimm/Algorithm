package bj_06118_숨바꼭질;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//200106 다익스트라 공부 위해 이 코드 보고 풀었음 https://bcp0109.tistory.com/63
//근데 visit도 이해 못했고.. 헷갈리니까 이코드 말고 다음번에 제출한 코드 (16808364) 보고 이해할것!
public class Main_no {

	private static class Node implements Comparable<Node> {
		int index, distance;
		Node (int index, int cost) {
			this.index = index;
			this.distance = cost;
		}
		@Override 
		public int compareTo(Node o) {
			return this.distance - o.distance;
		}
	}
	
	private static int n, m;
	private static ArrayList<Integer>[] list;
	private static PriorityQueue<Node> pq;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
	
		list = new ArrayList[n + 1];
		for (int i = 0; i < n + 1; i++)
			list[i] = new ArrayList<>();
		
		int a, b;
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			list[a].add(b);
			list[b].add(a);
		}
		
		int number = 2, maxDistance = -1, count = 1;
		
		pq = new PriorityQueue<>();
		boolean[] visit = new boolean[n + 1];
		
		pq.add(new Node(1, 0));
		visit[1] = true;
		
		Node temp;
		while (!pq.isEmpty()) {
			temp = pq.poll();
			
			if (maxDistance < temp.distance) {	//지금 거리가 더 멀다 
				maxDistance = temp.distance;
				number = temp.index;
				count = 1;
			} else if (maxDistance == temp.distance) {
				number = number > temp.index ? temp.index : number;
				count++;
			}
			
			for (int i = 0; i < list[temp.index].size(); i++) {
				if (visit[list[temp.index].get(i)]) continue;
				
				pq.add(new Node(list[temp.index].get(i), temp.distance + 1));
				visit[list[temp.index].get(i)] = true;
			}
		}
		
		System.out.println(number + " " + maxDistance + " " + count);
	}
}