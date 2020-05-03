package bj_17135_캐슬디펜스;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main2 {
	private static class Node {
		int x, y, dist, no, shooterX;
		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
		Node(int x, int y, int dist, int no, int shooterX) {
			this(x, y);
			this.dist = dist;
			this.no = no;
			this.shooterX = shooterX;
		}
	}
	private static int N, M, D, map[][], combi[], maxKill = 0;
	private static int[] dx = {0, -1, 0}, dy = {-1, 0, 1};
	private static boolean kill[][][];
	private static Queue<Node> q, ans;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		q = new LinkedList<>();
		ans = new LinkedList<>();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		combi = new int[3];
		map = new int[N + 1][M];
		kill = new boolean[N][M][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		getKillCase();
		combi(3, 0, 0);
		System.out.println(maxKill);
	}
	
	private static void combi(int n, int k, int idx) {
		if (n == k) {
			getKillNum();
			return;
		}

		for (int i = idx; i < M; i++) {
			combi[k] = i;
			combi(n, k + 1, i + 1);
			combi[k] = 0;	//그냥 의미상 
		}
	}

	private static void getKillNum() {
		int killNum = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				for (int k = 0; k < 3; k++) {
					if (kill[i][j][combi[k]]) {
						killNum++;
						break;
					}
				}
			}
		}
		if (maxKill < killNum) maxKill = killNum;
	}
	
	private static void getKillCase() {
		for (int j = 0; j < M; j++) {
			oneKill(j);
		}
	}

	private static void oneKill(int y) {
		for (int i = 0; i < N; i++) {
			q.add(new Node(N - i, y, 0, y, N - i));
			bfs();
			q.clear();
			if (ans.size() > 0) selectKill(y);
		}
	}
	
	private static void selectKill(int no) {
		int x = 0, y = M - 1;
		while (!ans.isEmpty()) {
			Node temp = ans.poll();
			if (y >= temp.y) {
				x = temp.x;
				y = temp.y;
			}
		}
		kill[x][y][no] = true;
	}

	private static void bfs() {
		while (!q.isEmpty()) {
			int size = q.size();
//			System.out.println("사이즈별 ");
			for (int i = 0; i < size; i++) {
				Node temp = q.poll();
				
//				System.out.println(temp.dist);
				if (temp.dist > D) return;
				
				if (temp.shooterX != temp.x && map[temp.x][temp.y] == 1 && !kill[temp.x][temp.y][temp.no])
					ans.add(new Node(temp.x, temp.y));
				
				for (int d = 0; d < 3; d++) {
					int nx = temp.x + dx[d];
					int ny = temp.y + dy[d];
					
					if (nx < 0 || nx >= N + 1 || ny < 0 || ny >= M) continue;
					q.add(new Node(nx, ny, temp.dist + 1, temp.no, temp.shooterX));
				}
			}
			if (ans.size() > 0) return;
		}
	}
}
//1. 각 자리마다 죽일 수 있는 애를 맵에 표시. 3차원 배열[][][]
// 아래 빼고 3중 dir로 돌면서 거리별로 잡는다. 
//2. 조합으로 궁수 조합을 찾는다.
//3. 궁수 조합마다 2중for문으로 맵을 돌면서 적의 개수를 세고, 최대치를 구한다.



































