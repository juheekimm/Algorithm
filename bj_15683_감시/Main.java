package bj_15683_감시;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	private static class Node {
		int x, y, no, dir;
		public Node(int x, int y, int no) {
			this.x = x;
			this.y = y;
			this.no = no;
		}
		public Node(int x, int y, int no, int dir) {
			this(x, y, no);
			this.dir = dir;
		}
	}
	
	private static int N, M, map[][], copyMap[][], min, permu[], size;
	private static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
	private static boolean[][][] cctvDir = {{{}},
											{{true, false, false, false}, {false, true, false, false}, {false, false, true, false}, {false, false, false, true}},
											{{true, true, false, false}, {false, false, true, true}},
											{{true, false, false, true}, {true, false, true, false}, {false, true, true, false}, {false, true, false, true}},
											{{true, true, true, false}, {true, true, false, true}, {true, false, true, true}, {false, true, true, true}},
											{{true, true, true, true}}};
	private static ArrayList<Node> cctv;
	private static Queue<Node> q;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		min = Integer.MAX_VALUE;
		cctv = new ArrayList<>();
		q = new LinkedList<>();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		copyMap = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] >= 1 && map[i][j] <= 5)
					cctv.add(new Node(i, j, map[i][j]));
			}
		}
		
		size = cctv.size();
		permu = new int[size];
		permu(size, 0);
		
		System.out.println(min);
	}

	private static boolean permu(int n, int k) {
		if (n == k) {
			copyMapMake();
			dirCheck();
			int count = noCctvCheck();
			if (min > count) min = count;
			if (count == 0) return true;
			return false;
		}
		
		for (int i = 0; i < 4; i++) {
			if (cctv.get(k).no == 5) {
				if (i > 0) continue;
			} else if (cctv.get(k).no == 2) {
				if (i > 1) continue;
			}
			permu[k] = i;
			if (permu(n, k + 1)) return true;
			permu[k] = 0;
		}
		return false;
	}

	private static void dirCheck() {
		for (int i = 0; i < size; i++) {
			for (int d = 0; d < 4; d++) {
				if (cctvDir[cctv.get(i).no][permu[i]][d]) {
					int nx = cctv.get(i).x + dx[d];
					int ny = cctv.get(i).y + dy[d];
					
					if (nx < 0 || nx >= N || ny < 0 || ny >= M || copyMap[nx][ny] == 6) continue;
					
					copyMap[nx][ny] = 1;
					q.add(new Node(nx, ny, 0, d));
				}
			}
		}
		bfs();
	}
	
	private static void bfs() {
		while (!q.isEmpty()) {
			Node temp = q.poll();
			int nx = temp.x + dx[temp.dir];
			int ny = temp.y + dy[temp.dir];
			
			if (nx < 0 || nx >= N || ny < 0 || ny >= M || copyMap[nx][ny] == 6) continue;
			
			copyMap[nx][ny] = 1;
			q.add(new Node(nx, ny, 0, temp.dir));
		}
	}

	private static int noCctvCheck() {
		int count = 0;
		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++)
				if (copyMap[i][j] == 0)
					count++;
		return count;
	}

	private static void copyMapMake() {
		for (int i = 0; i < N; i++)
			copyMap[i] = map[i].clone();
	}
}