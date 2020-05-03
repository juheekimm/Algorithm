package bj_14502_연구소;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static class Node {
		int x, y;
		Node (int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	private static int N, M, max = Integer.MIN_VALUE, map[][], copyMap[][];
	private static int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};
	private static LinkedList<Node> virusList;
	private static Queue<Node> q;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		virusList = new LinkedList<Main.Node>();
		q = new LinkedList<Main.Node>();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		copyMap = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) virusList.add(new Node(i, j));
			}
		}
		
		backTraking(0, 0, 0);
		System.out.println(max);
	}

	private static void backTraking(int x, int y, int step) {
		if (step == 3) {
			copyMapMake();
			copyVirusQueue();
			spreadVirus();
			safeFieldCount();
			return;
		}
		
		for (int i = x; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (i == x && j < y) continue;
				
				if (map[i][j] == 0) {
					map[i][j] = 1;
					backTraking(i, j + 1, step + 1);
					map[i][j] = 0;
				}
			}
		}
	}

	private static void spreadVirus() {
		while (!q.isEmpty()) {
			Node temp = q.poll();
			
			for (int d = 0; d < 4; d++) {
				int nx = temp.x + dx[d];
				int ny = temp.y + dy[d];
				
				//map이 visit 역할을 대신 해줌 
				if (nx < 0 || nx >= N || ny < 0 || ny >= M || copyMap[nx][ny] != 0) continue;
				
				copyMap[nx][ny] = 1;
				q.add(new Node(nx, ny));
			}
		}
	}
	
	private static void copyVirusQueue() {
		for (int i = 0; i < virusList.size(); i++)
			q.add(virusList.get(i));
	}
	
	private static void safeFieldCount() {
		int count = 0;
		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++)
				if (copyMap[i][j] == 0)
					count++;
		
		if (max < count) max = count;
	}

	private static void copyMapMake() {
		for (int i = 0; i < N; i++)
			copyMap[i] = map[i].clone();
	}
}