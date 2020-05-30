package bj_01916;

import java.io.*;
import java.util.*;

public class Main {
	private static class Node implements Comparable<Node>{
		int curr, weight;
		Node(int curr, int weight) {
			this.curr = curr;
			this.weight = weight;
		}
		@Override
		public int compareTo(Node o) { 
			return this.weight - o.weight;
		}
	}
	static int city, bus, start, dest;
	static int[] result;
	static int[][] map;
	static PriorityQueue<Node> pq = new PriorityQueue<>();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		city = stio(br.readLine());
		bus = stio(br.readLine());
		
		map = new int[city + 1][city + 1];	//출발, 도착지 번호로 계산할거라서 일부러 1 크게 생성.
		result = new int[city + 1];
		
		int x, y, weight;
		for (int i = 0; i < bus; i++) {
			st = new StringTokenizer(br.readLine());
			x = stio(st.nextToken());
			y = stio(st.nextToken());
			weight = stio(st.nextToken());
			
			if (map[x][y] != 0)
				map[x][y] = Math.min(weight, map[x][y]);
			else
				map[x][y] = weight;
		}
		st = new StringTokenizer(br.readLine());
		start = stio(st.nextToken());
		dest = stio(st.nextToken());
		
		pq.add(new Node(start, 0));
		routeSearch();
		System.out.println(result[dest]);
	}
	
	private static void routeSearch() {
		while(!pq.isEmpty()) {
			Node temp = pq.poll();
			addRoute(temp.curr, temp.weight);
		}
	}

	private static void addRoute(int currStart, int currWeight) {
		for (int i = 1; i < map.length; i++) {
			if (map[currStart][i] != 0 && (result[i] == 0 || result[currStart] + map[currStart][i] < result[i])) {
				result[i] = result[currStart] + map[currStart][i];
				pq.add(new Node(i, result[i]));
//				System.out.printf("%d에서 %d로 현재result%d\n", currStart, i, map[currStart][i]);
			}
		}
	}

	private static int stio(String str) {
		return Integer.parseInt(str);
	}
}