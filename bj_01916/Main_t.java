package bj_01916;

import java.io.*;
import java.util.*;

public class Main_t {
	private static class Node implements Comparable<Node>{
		int x, y, weight;
		Node(int x, int y, int weight) {
			this.x = x;
			this.y = y;
			this.weight = weight;
		}
		@Override
		public int compareTo(Node o) { 
			return this.weight - o.weight;
		}
	}
	static int city, bus, start, dest, cnt = 0;
	static int[][] map;
	static ArrayList<Node>[] list;
	static PriorityQueue<Node> pq = new PriorityQueue<>();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		city = stio(br.readLine());
		bus = stio(br.readLine());
		list = new ArrayList[city+1];
		for(int i = 1; i <= city; i++) {
			list[i] = new ArrayList<>();
		}
		
		
		map = new int[city + 1][city + 1];	//출발, 도착지 번호로 계산할거라서 일부러 1 크게 생성.
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
		
		pq.add(new Node(0, start, 0));
		routeSearch();
	}
	
	private static void routeSearch() {
		int prevStart, currStart, currWeight;
		while(true) {
			Node temp = pq.poll();
			prevStart = temp.x; //이전 x값
			currStart = temp.y; //이전 y값 == 현재 x값
			currWeight = temp.weight;
			
			addRoute(prevStart, currStart, currWeight);

			if (currStart == dest) {
				System.out.println(currWeight);
				return;
			}
		}
	}

	private static void addRoute(int prevStart, int currStart, int currWeight) {
		for (int i = 1; i < map.length; i++) {
			//이전 노드로 돌아가지 않게끔 조건 추가. 즉, 이전 출발지와 현재 가려는 곳이 같지 않을 때.
			if (map[currStart][i] != 0 && prevStart != i) {
				pq.add(new Node(currStart, i, map[currStart][i] + currWeight));
//				System.out.printf("%d에서 %d로. weight는 %d\n", currStart, i, map[currStart][i] + currWeight);
			}
		}
	}

	private static int stio(String str) {
		return Integer.parseInt(str);
	}
}