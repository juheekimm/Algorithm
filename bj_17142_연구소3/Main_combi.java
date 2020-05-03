package bj_17142_연구소3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_combi {
	
	private static class Node {
		int x, y, t;
		Node (int x, int y, int t) {
			this.x = x;
			this.y = y;
			this.t = t;
		}
	}
	private static int N, M, min = Integer.MAX_VALUE, map[][], copyMap[][];
	private static boolean combi[];
	private static int[] dx = {0, -1, 0, 1}, dy = {1, 0, -1, 0};
	private static LinkedList<Node> list;
	private static Queue<Node> q;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		list = new LinkedList<Node>();
		q = new LinkedList<Node>();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		copyMap = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) list.add(new Node(i, j, 1));
			}
		}
		
		combi = new boolean[list.size()];
		combi(0, 0);
		
		if (min == Integer.MAX_VALUE) System.out.println("-1");
		else System.out.println(min);
	}

	private static void combi(int k, int idx) {
		if (M == k) {
			makeCopyMap();
			addQueueVirus();
			int time = spreadVirus();
			if (checkTime())
				if (min > time)
					min = time;
		}
		
		for (int i = idx; i < combi.length; i++) {
			combi[i] = true;
			combi(k + 1, i + 1);
			combi[i] = false;
		}
	}

	private static boolean checkTime() {
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				if (copyMap[i][j] == 0)
					return false;
		
		return true;
	}

	private static int spreadVirus() {
		int time = 0;
		while (!q.isEmpty()) {
			Node temp = q.poll();
			
			for (int d = 0; d < 4; d++) {
				int nx = temp.x + dx[d];
				int ny = temp.y + dy[d];
				
				if (nx < 0 || nx >= N || ny < 0 || ny >= N || copyMap[nx][ny] == 1 || copyMap[nx][ny] >= 3) continue;
				
				if (copyMap[nx][ny] == 2 && map[nx][ny] == 2) {
					copyMap[nx][ny] = 3;
					q.add(new Node(nx, ny, temp.t + 1));
				} else if (copyMap[nx][ny] == 0) {
					copyMap[nx][ny] = temp.t;
					q.add(new Node(nx, ny, temp.t + 1));
					if (time < temp.t) time = temp.t;
				}
			}
		}
		return time;
	} 

	private static void addQueueVirus() {
		for (int i = 0; i < combi.length; i++) {
			if (combi[i]) {
				q.add(list.get(i));
				copyMap[list.get(i).x][list.get(i).y] = 3;
			}
		}
	}

	private static void makeCopyMap() {
		for (int i = 0; i < N; i++)
			copyMap[i] = map[i].clone();
	}
}