package bj_17135_캐슬디펜스;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
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
	private static int N, M, D, map[][], copyMap[][], combi[], minRemainEnemy = Integer.MAX_VALUE, enemy = 0;
	private static int[] dx = {0, -1, 0}, dy = {-1, 0, 1};
	private static Queue<Node> q, deleteList, realDelete;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		q = new LinkedList<>();
		deleteList = new LinkedList<>();
		realDelete = new LinkedList<>();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		combi = new int[3];
		map = new int[N + 1][M];
		copyMap = new int[N + 1][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) enemy++;
			}
		}
		combi(3, 0, 0);
		System.out.println(enemy - minRemainEnemy);
	}
	
	private static void combi(int n, int k, int idx) {
		if (n == k) {
			copyMapMake();
			enemyKill();
			getRemainEnemy();
			return;
		}
		for (int i = idx; i < M; i++) {
			combi[k] = i;
			combi(n, k + 1, i + 1);
			combi[k] = 0;	//그냥 의미상 
		}
	}

	private static void getRemainEnemy() {
		int remain = 0;
		
		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++)
				if (copyMap[i][j] == 1)
					remain++;
		
		if (minRemainEnemy > remain) minRemainEnemy = remain;
	}

	private static void enemyKill() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < 3; j++) {
				q.add(new Node(N - i, combi[j], 0, combi[j], N - i));
				bfs();
				q.clear();
				if (deleteList.size() > 0) selectKill();
			}
			realKill();
		}
	}
	
	private static void bfs() {
		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				Node temp = q.poll();
				
				if (temp.dist > D) return;
				
				if (temp.shooterX != temp.x && copyMap[temp.x][temp.y] == 1)
					deleteList.add(new Node(temp.x, temp.y));
				
				for (int d = 0; d < 3; d++) {
					int nx = temp.x + dx[d];
					int ny = temp.y + dy[d];
					
					if (nx < 0 || nx >= N + 1 || ny < 0 || ny >= M) continue;
					q.add(new Node(nx, ny, temp.dist + 1, temp.no, temp.shooterX));
				}
			}
			if (deleteList.size() > 0) return;
		}
	}
	
	private static void realKill() {
		while (!realDelete.isEmpty()) {
			Node temp = realDelete.poll();
			copyMap[temp.x][temp.y] = 0;
		}
	}

	private static void selectKill() {
		int x = 0, y = M - 1;
		while (!deleteList.isEmpty()) {
			Node temp = deleteList.poll();
			if (y >= temp.y) {
				x = temp.x;
				y = temp.y;
			}
		}
		realDelete.add(new Node(x, y));
	}
	
	private static void copyMapMake() {
		for (int i = 0; i <= N; i++)
			copyMap[i] = map[i].clone();
	}
}
//1. 조합을 만들고 매번 카피맵 갱신 
//2. 그 조합으로 매 턴 한칸씩 올라가면서 쏜다
//쏠수있는애들은 visit에 표시. 
//이 때 한명씩 거리만큼 돌면서 가장가깝거나 가장왼쪽에 있는애를 실제로 죽이는데 이건  copymap에 표시한다 
//마지막에 copymap을 count한다 
//count를 maxcount로 갱신한다 