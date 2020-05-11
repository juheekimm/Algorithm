package bj_15683_감시;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_200511 {
	
	static class Node {
		int num, x, y, nextDir;
		Node (int num, int x, int y, int nextDir) {
			this.num = num;
			this.x = x;
			this.y = y;
			this.nextDir = nextDir;
		}
	}
	
	static int N, M, map[][], copymap[][], permu[], cctvNum, ans = 64;
	static int[][][] dir = {{{}},
			{{0}, {1}, {2}, {3}}, 
			{{0, 2}, {1, 3}},
			{{0, 1}, {1, 2}, {2, 3}, {3, 0}},
			{{0, 1, 2}, {1, 2, 3}, {2, 3, 0}, {3, 0, 1}},
			{{0, 1, 2, 3}}};
	static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
	static LinkedList<Node> cctvList = new LinkedList<>();
	static Queue<Node> q = new LinkedList<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		copymap = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] > 0 && map[i][j] < 6)
					cctvList.add(new Node(map[i][j], i, j, 0));
			}
		}
		cctvNum = cctvList.size();
		permu = new int[cctvNum];
		permu(cctvNum, 0);
		System.out.println(ans);
	}

	private static boolean permu(int n, int k) {
		if (n == k) {
			for (int i = 0; i < N; i++)
				copymap[i] = map[i].clone();
			
			for (int i = 0; i < cctvNum; i++) {
				Node temp = cctvList.get(i);
				for (int j = 0; j < dir[cctvList.get(i).num][permu[i]].length; j++)
					q.add(new Node(temp.num, temp.x, temp.y, dir[cctvList.get(i).num][permu[i]][j]));
			}
			
			cctvDraw();
			int count = copymapCheck();
			if (ans > count) ans = count;
			if (ans == 0) return true;
			return false;
		}
		
		for (int i = 0; i < dir[cctvList.get(k).num].length; i++) {
			permu[k] = i;
			permu(n, k + 1);
			permu[k] = 0;	//덮일거라 상관없긴함 
		}
		return false;
	}

	private static int copymapCheck() {
		int count = 0;
		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++)
				if (copymap[i][j] == 0)
					count++;
		return count;
	}

	private static void cctvDraw() {
		while (!q.isEmpty()) {
			Node temp = q.poll();
			
			int nx = temp.x + dx[temp.nextDir];
			int ny = temp.y + dy[temp.nextDir];
			
			if (nx < 0 || ny < 0 || nx >= N || ny >= M || copymap[nx][ny] == 6) continue;
			
			copymap[nx][ny] = temp.num;
			q.add(new Node(temp.num, nx, ny, temp.nextDir));
		}
	}
}