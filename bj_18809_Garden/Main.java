package bj_18809_Garden;

import java.io.*;
import java.util.*;

public class Main {
	
	static class Node {
		int x, y, c, t;
		Node (int x, int y) {
			this.x = x;
			this.y = y;
		}
		Node (int x, int y, int c, int t) {
			this(x, y);
			this.c = c;
			this.t = t;
		}
	}
	static int N, M, G, R, size, map[][], copyMap[][], color[][], combiGreen[], combiRed[], max = 0, now;
	static boolean[] visit;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static ArrayList<Node> enableList = new ArrayList<Node>();
	static Queue<Node> q = new LinkedList<Node>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		copyMap = new int[N][M];
		color = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2)
					enableList.add(new Node(i, j));
			}
		}
		
		size = enableList.size();
		visit = new boolean[size];
		
		combiGreen = new int[G];
		combiRed = new int[R];
		combiGreen(G, 0, 0);
		System.out.println(max);
	}

	private static void combiGreen(int n, int k, int idx) {
		if (n == k) {
			combiRed(R, 0, 0);
			return;
		}
		for (int i = idx; i < size; i++) {
			if (visit[i]) continue;
			visit[i] = true;
			combiGreen[k] = i;
			combiGreen(n, k + 1, i + 1);
			visit[i] = false;
			combiGreen[k] = -1;	//굳이 안해줘도 되지만 의미상 넣음 
		}
	}

	private static void combiRed(int n, int k, int idx) {
		if (n == k) {
			for (int i = 0; i < N; i++) {
				copyMap[i] = map[i].clone();
				Arrays.fill(color[i], 0);
			}
			now = 0;
			q.clear();
			for (int i = 0; i < G; i++) {
				q.add(new Node(enableList.get(combiGreen[i]).x, enableList.get(combiGreen[i]).y, 1, 3));
				copyMap[enableList.get(combiGreen[i]).x][enableList.get(combiGreen[i]).y] = 3;
				color[enableList.get(combiGreen[i]).x][enableList.get(combiGreen[i]).y] = 1;
			}
			for (int i = 0; i < R; i++) {
				q.add(new Node(enableList.get(combiRed[i]).x, enableList.get(combiRed[i]).y, 2, 3));
				copyMap[enableList.get(combiRed[i]).x][enableList.get(combiRed[i]).y] = 3;
				color[enableList.get(combiRed[i]).x][enableList.get(combiRed[i]).y] = 2;
			}
			bfs();
			if (max < now) max = now;
			return;
		}
		for (int i = idx; i < size; i++) {
			if (visit[i]) continue;
			visit[i] = true;
			combiRed[k] = i;
			combiRed(n, k + 1, i + 1);
			visit[i] = false;
			combiRed[k] = -1;	//굳이 안해줘도 되지만 의미상 넣음 
		}		
	}

	private static void bfs() {
		while (!q.isEmpty()) {
			Node temp = q.poll();
			
			for (int d = 0; d < 4; d++) {
				int nx = temp.x + dx[d];
				int ny = temp.y + dy[d];
				int nc = temp.c;
				int nt = temp.t + 1;
				
				if (color[temp.x][temp.y] == -1) continue;
				
				//0(호수) 또는 -1(이미 꽃핀 곳)
				if (nx < 0 || nx >= N || ny < 0 || ny >= M || copyMap[nx][ny] <= 0) continue;
				
				if (copyMap[nx][ny] == 1 || copyMap[nx][ny] == 2) {
					copyMap[nx][ny] = nt;
					color[nx][ny] = nc;
					q.add(new Node(nx, ny, nc, nt));
				} else if (copyMap[nx][ny] == nt) {	
					if (color[nx][ny] != nc) {	//꽃 피울 수 있는 곳이면
						copyMap[nx][ny] = -1;
						color[nx][ny] = -1;
						now++;
					}
				}
			}
		}
	}
}