package bj_03055_탈출;

import java.io.*;
import java.util.*;

//190816. 3h. Bfs
//혼자 풀었으나, bfs 두개 사용해서 풀었음.
//Main_twobfs_improve : 여전히 bfs 두개 사용하지만, 복잡도 개선 및 Queue에서 for문 사용.
//Main_onebfs		  : 설명 들음. 선입선출이라는 큐의 특성을 이용해서 큐와 bfs 하나로 풀었음.
public class Main {
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
		boolean waterExist = false;
		
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
					waterExist = true;
				} else if (map[i][j] == 'S') {	//고슴도치면
					goVisit[i][j] = true;
					goq.add(new Node(i, j, 0));
				}
			}
		}
		if (waterExist) {		//물이 존재할 때
			if (!waterBfs())
				System.out.println("KAKTUS");
		}
		else  {					//물이 없을 때
			if (!goBfs(true))
				System.out.println("KAKTUS");
		}
	}

	private static boolean waterBfs() {
		//물 bfs
		int tempWater = 1;
		while (!waterq.isEmpty()) {
			Node water = waterq.poll();
			int nx, ny;
			for (int d = 0; d < 4; d++) {
				nx = water.x + dx[d];
				ny = water.y + dy[d];
				
				if (nx < 0 || nx >= r || ny < 0 || ny >= c || waterVisit[nx][ny] || map[nx][ny] == 'X' || map[nx][ny] == 'D')
					continue;
				
				waterVisit[nx][ny] = true;
				waterq.add(new Node(nx, ny, water.step + 1));
			}
			if(waterq.isEmpty() || goq.isEmpty()) break;
			if (waterq.peek().step == tempWater) {
				if (goBfs(false)) return true;
				tempWater++;
			}
		}
		if (waterq.isEmpty() && !goq.isEmpty()) {
			return goBfs(true);
		}
		return false;
	}
	
	private static boolean goBfs(boolean flag) {
		//고슴도치 bfs
		int nx, ny;
		int tempGo = goq.peek().step;
		
		while (!goq.isEmpty() && (flag || tempGo == goq.peek().step)) {
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