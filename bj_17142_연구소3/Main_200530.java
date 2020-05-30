package bj_17142_연구소3;

import java.io.*;
import java.util.*;

public class Main_200530 {
	
	static class Node {
		int x, y, t;
		Node (int x, int y, int t) {
			this.x = x;
			this.y = y;
			this.t = t;
		}
	}
	static int N, M, size, map[][], copyMap[][], min = Integer.MAX_VALUE, nowMax, combi[];
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static ArrayList<Node> virus = new ArrayList<Node>();
	static Queue<Node> q = new LinkedList<Node>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		copyMap = new int[N][N];
		
//		boolean emptyExist = false;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2)
					virus.add(new Node(i, j, 3));
//				if (map[i][j] == 0)
//					emptyExist = true;
			}
		}
//		if (!emptyExist) {
//			System.out.println(0);
//			return;
//		}

		size = virus.size();
		combi = new int[M];
		combi(M, 0, 0);
		if (min == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(min);
	}

	private static void combi(int n, int k, int idx) {
		if (n == k) {
			for (int i = 0; i < N; i++)
				copyMap[i] = map[i].clone();
			
			nowMax = 0;
			q.clear();
			for (int i = 0; i < n; i++) {
				q.add(new Node(virus.get(combi[i]).x, virus.get(combi[i]).y, 3));
				copyMap[virus.get(combi[i]).x][virus.get(combi[i]).y] = 3;
			}
			bfs();
			if (!zeroExistCheck())
				if (min > nowMax)
					min = nowMax;
			return;
		}
		for (int i = idx; i < size; i++) {
			combi[k] = i;
			combi(n, k + 1, i + 1);
			combi[k] = -1;	//사실 안해줘도 되긴 함 
		}
	}

	private static boolean zeroExistCheck() {
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				if (copyMap[i][j] == 0)
					return true;
		return false;
	}

	private static void bfs() {
		while (!q.isEmpty()) {
			Node temp = q.poll();
			for (int d = 0; d < 4; d++) {
				int nx = temp.x + dx[d];
				int ny = temp.y + dy[d];
				int nt = temp.t + 1;
				
				if (nx < 0 || nx >= N || ny < 0 || ny >= N || copyMap[nx][ny] == 1 || copyMap[nx][ny] > 2)
					continue;

				//비활성바이러스일때는 q에 넣어주되 시간체크는 하지 않는다 
				if (copyMap[nx][ny] != 2)
					if (nowMax < nt - 3) nowMax = nt - 3;
				
				q.add(new Node(nx, ny, nt));
				copyMap[nx][ny] = nt;
			}
		}
	}
}