package bj_19238_스타트택시;

import java.io.*;
import java.util.*;

public class Main_201014 {
	
	static class Node implements Comparable<Node>{
		int x, y, dist, num;
		Node (int x, int y, int dist, int num) {
			this.x = x;
			this.y = y;
			this.dist = dist;
			this.num = num;
		}
		@Override
		public int compareTo(Node o) {
			if (this.dist == o.dist) {
				if (this.x == o.x) {
					return this.y - o.y;
				} else {
					return this.x - o.x;
				}
			} else {
				return this.dist - o.dist;
			}
		}
	}
	
	static int N, M, oil, map[][], dist[], people[][], endPoint[][], taxix, taxiy;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static boolean[][] visit;
	static Queue<Node> q = new LinkedList<>();
	static PriorityQueue<Node> pq = new PriorityQueue<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		oil = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		visit = new boolean[N][N];
		
		people = new int[M][2];
		endPoint = new int[M][2];
		dist = new int[M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		taxix = Integer.parseInt(st.nextToken()) - 1;
		taxiy = Integer.parseInt(st.nextToken()) - 1;
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			people[i][0] = Integer.parseInt(st.nextToken()) - 1;
			people[i][1] = Integer.parseInt(st.nextToken()) - 1;
			endPoint[i][0] = Integer.parseInt(st.nextToken()) - 1;
			endPoint[i][1] = Integer.parseInt(st.nextToken()) - 1;
		}
		
		//본격적으로 시작하기 전에 미리 승객과 도착지간의 거리를 전부 구해놓음 
		//이때, 승객과 도착지간의 거리가 0이면, 그건 이어질 수 없다는 뜻이므로 -1을 출력하고 종료 
		if (!getAllStartToEndDistance()) {
			System.out.println("-1");
			return;
		}
		
		for (int t = 0; t < M; t++) {
			pq.clear();
			//현재 택시의 위치와 사람들간의 거리를 전부 구해서 pq에 넣음 
			getTaxiToAllPeopleDistance();
			
			Node temp = pq.poll();
			//택시와 사람간의 거리가 0인데, 위치가 같지 않은데 거리가 0인것은 이동할 수 없다는 뜻이므로 종료 
			if (!(taxix == people[temp.num][0] && taxiy == people[temp.num][1]) && temp.dist == 0) {
				oil = -1;
				break;
			}
			
			//temp.dist는 태우러 가기 위한 거리, dist[temp.num]은 태우러 간 뒤의 출발지-목적지 거리 
			oil -= temp.dist + dist[temp.num];
			
			if (oil < 0) {
				oil = -1;
				break;
			} else {
				oil += dist[temp.num] * 2;
				taxix = endPoint[temp.num][0];
				taxiy = endPoint[temp.num][1];
				people[temp.num][0] = -1;
			}
		}
		System.out.println(oil);
	}

	private static void getTaxiToAllPeopleDistance() {
		for (int i = 0; i < M; i++) {
			//이미 이동을 완료한 사람은 계산하지 않는다 
			if (people[i][0] == -1) continue;
			if (taxix == people[i][0] && taxiy == people[i][1]) {
				pq.add(new Node(taxix, taxiy, 0, i));
				continue;
			}
			for (int n = 0; n < N; n++) {
				Arrays.fill(visit[n], false);
			}
			q.clear();
			visit[taxix][taxiy] = true;
			q.add(new Node(taxix, taxiy, 1, i));
			bfs(people[i][0], people[i][1], false);
		}
	}

	private static boolean getAllStartToEndDistance() {
		for (int i = 0; i < M; i++) {
			for (int n = 0; n < N; n++) {
				Arrays.fill(visit[n], false);
			}
			q.clear();
			visit[people[i][0]][people[i][1]] = true;
			q.add(new Node(people[i][0], people[i][1], 1, i));
			bfs(endPoint[i][0], endPoint[i][1], true);
			//이동할 수 없다면 false를 리턴한다 (출발지와 목적지와 같은 경우는 없음) 
			if (dist[i] == 0) return false;
		}
		return true;
	}

	private static void bfs(int ex, int ey, boolean allDist) {
		while (!q.isEmpty()) {
			Node temp = q.poll();
			for (int d = 0; d < 4; d++) {
				int nx = temp.x + dx[d];
				int ny = temp.y + dy[d];
				
				if (nx < 0 || nx >= N || ny < 0 || ny >= N || visit[nx][ny] || map[nx][ny] == 1)
					continue;
				
				if (nx == ex && ny == ey) {
					if (allDist)
						dist[temp.num] = temp.dist;
					else
						pq.add(new Node(nx, ny, temp.dist, temp.num));
					return;
				}
				
				visit[nx][ny] = true;
				q.add(new Node(nx, ny, temp.dist + 1, temp.num));
			}
		}
	}
}