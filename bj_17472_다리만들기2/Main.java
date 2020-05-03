package bj_17472_다리만들기2;
		
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	
	private static class Node implements Comparable<Node> {
		int x, y, val;
		Node (int x, int y, int val) {
			this.x = x;
			this.y = y;
			this.val = val;
		}
		@Override
		public int compareTo(Node o) {
			return this.val - o.val;
		}
	}

	private static int N, M, map[][], parents[], lenSum, islandNum;
	private static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
	private static Queue<Node> q;
	private static PriorityQueue<Node> pq;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		q = new LinkedList<>();
		pq = new PriorityQueue<>();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) map[i][j] = -1;
			}
		}
		
		findStart();
		startBridge();
		
		parents = new int[islandNum - 1];
		Arrays.fill(parents, -1);
		bridegeUnion();
		
		if (allBridegeUnionCheck())
			System.out.println(lenSum);
		else
			System.out.println("-1");
	}

	private static boolean allBridegeUnionCheck() {
		int num = 0;
		for (int i = 0; i < parents.length; i++)
			if (parents[i] < 0) num++;
		
		if (num == 1) return true;
		else return false;
	}

	private static void bridegeUnion() {
		while (!pq.isEmpty()) {
			Node temp = pq.poll();
			if (union(temp.x - 1, temp.y - 1))
				lenSum += temp.val;
		}
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
		if (parents[a] < 0)
			return a;
		return parents[a] = find(parents[a]);
	}

	private static void startBridge() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] != 0) {
					if (i > 0 && map[i - 1][j] == 0) oneBridge(i, j, 0, map[i][j]);
					if (i < N - 1 && map[i + 1][j] == 0) oneBridge(i, j, 1, map[i][j]);
					if (j > 0 && map[i][j - 1] == 0) oneBridge(i, j, 2, map[i][j]);
					if (j < M - 1 && map[i][j + 1] == 0) oneBridge(i, j, 3, map[i][j]);
				}
			}
		}
	}

	private static void oneBridge(int x, int y, int d, int startNo) {
		int nx = x;
		int ny = y;
		
		while (true) {
			nx += dx[d];
			ny += dy[d];
			
			if (nx < 0 || nx >= N || ny < 0 || ny >= M) return;
			if (map[nx][ny] == 0) {
				continue;
			} else {
				int len = 0;
				if (d == 0 || d == 1)  {
					if (Math.abs(nx - x) == 2) return;
					len = Math.abs(nx - x) - 1;
				} else {
					if (Math.abs(ny - y) == 2) return;
					len = Math.abs(ny - y) - 1;
				}
				
				pq.add(new Node(startNo, map[nx][ny], len));
				return;
			}
		}
	}

	private static void findStart() {
		int idx = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == -1) {
					map[i][j] = idx;
					q.add(new Node(i, j, idx));
					bfs();
					idx++;
				}
			}
		}
		islandNum = idx;
	}

	private static void bfs() {
		while (!q.isEmpty()) {
			Node temp = q.poll();
			for (int d = 0; d < 4; d++) {
				int nx = temp.x + dx[d];
				int ny = temp.y + dy[d];
				
				if (nx < 0 || nx >= N || ny < 0 || ny >= M || map[nx][ny] != -1) continue;
				
				map[nx][ny] = temp.val;
				q.add(new Node(nx, ny, temp.val));
			}
		}
	}
}