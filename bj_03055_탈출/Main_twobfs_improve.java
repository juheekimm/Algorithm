package bj_03055_탈출;

import java.io.*;
import java.util.*;

//190816. 3h. Bfs
//원래 bfs 두개 활용해서 푼 방식에서 가독성 및 복잡도 개선한 코드.
//while문으로 한스텝 도는거 체크하는게 아니라, 한 스텝마다 사이즈 재서 그만큼만 돌리기 때문에 훨씬 코드가 쉬워진다.
public class Main_twobfs_improve {
	private static class Node {
		int x, y, step;
		Node(int x, int y, int step) {
			this.x = x;
			this.y = y;
			this.step = step;
		}
	}
	private static int r, c;
	private static char[][] map;
	private static int[] dx = {0, -1, 0, 1};
	private static int[] dy = {-1, 0, 1, 0};
	private static boolean[][] waterVisit, goVisit;
	private static Queue<Node> waterq = new LinkedList<>();
	private static Queue<Node> goq = new LinkedList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		map = new char[r][c];
		waterVisit = new boolean[r][c];
		goVisit = new boolean[r][c];
		
		for (int i = 0; i < r; i++) {
			char[] ch = br.readLine().toCharArray();
			for (int j = 0; j < c; j++) {
				map[i][j] = ch[j];
				
				if (map[i][j] == '*') {			//물이면
					waterVisit[i][j] = true;
					waterq.add(new Node(i, j, 0));
				} else if (map[i][j] == 'S') {	//고슴도치면
					goVisit[i][j] = true;
					goq.add(new Node(i, j, 0));
				}
			}
		}
		
		while(true) {
			waterBfs();
			if (goBfs()) return;
			if (goq.isEmpty()) break;
		}
		System.out.println("KAKTUS");
	}

	
	private static void waterBfs() {
		int waterSize = waterq.size();
		int nx, ny;
		
		for (int i = 0; i < waterSize; i++) {
			Node water = waterq.poll();
			
			for (int d = 0; d < 4; d++) {
				nx = water.x + dx[d];
				ny = water.y + dy[d];
				
				if (nx < 0 || nx >= r || ny < 0 || ny >= c || waterVisit[nx][ny] || map[nx][ny] == 'X' || map[nx][ny] == 'D')
					continue;
				
				waterVisit[nx][ny] = true;
				waterq.add(new Node(nx, ny, water.step + 1));
			}
		}
	}
	
	private static boolean goBfs() {
		int goSize = goq.size();
		int nx, ny;
		
		for (int i = 0; i < goSize; i++) {
			Node go = goq.poll();
			
			for (int d = 0; d < 4; d++) {
				nx = go.x + dx[d];
				ny = go.y + dy[d];
				
				if (nx < 0 || nx >= r || ny < 0 || ny >= c || waterVisit[nx][ny] || goVisit[nx][ny])
					continue;
				
				if (map[nx][ny] == 'D') {
					System.out.println(go.step + 1);
					return true;
				} else if(map[nx][ny] == '.') {
					goVisit[nx][ny] = true;
					goq.add(new Node(nx, ny, go.step + 1));
				}
			}
		}
		return false;
	}
}