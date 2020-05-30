package bj_03055_탈출;

import java.io.*;
import java.util.*;

//190816. Bfs. 설명 듣고 큐, bfs 하나만 이용해서 다시 풀었음.
//큐의 특성 상 선입 선출이기 때문에 큐 하나로도 충분히 가능하다!
public class Main_onebfs {
	private static class Node {
		int x, y, step;
		boolean isWater;
		Node(int x, int y, int step, boolean isWater) {
			this.x = x;
			this.y = y;
			this.step = step;
			this.isWater = isWater;	//0이 물, 1이 고슴도치
		}
	}
	private static int r, c;
	private static char[][] map;
	private static int[] dx = {0, -1, 0, 1};
	private static int[] dy = {-1, 0, 1, 0};
	private static boolean[][] waterVisit, goVisit;
	private static Queue<Node> q = new LinkedList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		map = new char[r][c];
		waterVisit = new boolean[r][c];
		goVisit = new boolean[r][c];
		
		int tx = 0, ty = 0;
		for (int i = 0; i < r; i++) {
			char[] ch = br.readLine().toCharArray();
			for (int j = 0; j < c; j++) {
				map[i][j] = ch[j];
				
				if (map[i][j] == '*') {			//물이면
					waterVisit[i][j] = true;
					q.add(new Node(i, j, 0, true));
				} else if (map[i][j] == 'S') {	//고슴도치면
					tx = i;
					ty = j;
				}
			}
		}
		goVisit[tx][ty] = true;
		q.add(new Node(tx, ty, 0, false));
		
		if(!bfs())
			System.out.println("KAKTUS");
	}

	private static boolean bfs() {
		//물 bfs
		while (!q.isEmpty()) {
			Node n = q.poll();
			int nx, ny;
			boolean isWater;
			
			for (int d = 0; d < 4; d++) {
				nx = n.x + dx[d];
				ny = n.y + dy[d];
				isWater = n.isWater;
				
				if (nx < 0 || nx >= r || ny < 0 || ny >= c || waterVisit[nx][ny])
					continue;
				
				if (isWater) {	//물이면
					if (map[nx][ny] == '.' || map[nx][ny] == 'S') {
						waterVisit[nx][ny] = true;
						q.add(new Node(nx, ny, n.step + 1, isWater));
					}
				} else {		//고슴도치면
					if (map[nx][ny] == '.' && !goVisit[nx][ny]) {
						goVisit[nx][ny] = true;
						q.add(new Node(nx, ny, n.step + 1, isWater));
					} else if (map[nx][ny] == 'D') {
						System.out.println(n.step + 1);
						return true;
					}
				}
				
			}
		}
		return false;
	}
}