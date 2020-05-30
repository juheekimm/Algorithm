package bj_01600_말이되고픈원숭이;

import java.io.*;
import java.util.*;

public class Main_191112 {

	private static class Node {
		int x, y, horse, step;
		public Node(int x, int y, int horse, int step) {
			this.x = x;
			this.y = y;
			this.horse = horse;
			this.step = step;
		}
	}
	
	static int k, w, h, map[][], horseNowStep, nx, ny;
	static boolean[][][] visit;
	static Node temp;
	static Queue<Node> q = new LinkedList<>();
	//0-3까지 원숭이. 원숭이를 먼저 배치한 이유는 말 이동횟수를 다 쓴 경우 바로 for문 break하기 위해. 그리고 나름 우하단 먼저 배치해봄.
	static int[] dx = {0, 1, 0, -1, 1, 2, 1, 2, -1, -2, -1, -2};
	static int[] dy = {1, 0, -1, 0, 2, 1, -2, -1, 2, 1, -2, -1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		k = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		w = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		map = new int[h][w];
		visit = new boolean[h][w][k + 1];	//0부터 k까지 다 들어가야하므로 k + 1
		
		for (int i = 0; i < h; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < w; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		q.add(new Node(0, 0, 0, 1));
		bfs();
	}

	private static void bfs() {
		while (!q.isEmpty()) {
			temp = q.poll();
			
			for (int d = 0; d < 12; d++) {
				nx = temp.x + dx[d];
				ny = temp.y + dy[d];
				horseNowStep = d < 4 ? temp.horse : temp.horse + 1;
				
				if (horseNowStep > k) break;	//말이 뛸수 있는 횟수 다 뛰었으면 for문 더 돌 필요 X(앞의 원숭이만 돌면 된다)
				
				if (nx == h - 1 && ny == w - 1) {
					System.out.println(temp.step);
					System.exit(0);
				}

				if (nx < 0 || nx >= h || ny < 0 || ny >= w || visit[nx][ny][horseNowStep] || map[nx][ny] == 1)
					continue;
				
				visit[nx][ny][horseNowStep] = true;
				q.add(new Node(nx, ny, horseNowStep, temp.step + 1));
			}
		}
		System.out.println(-1);
	}
}