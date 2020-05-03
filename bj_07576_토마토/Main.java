package bj_07576_토마토;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	static class Node {
		int x, y, day;
		Node(int x, int y, int day) {
			this.x = x;
			this.y = y;
			this.day = day;
		}
	}
	
	static int m, n, map[][];
	static Queue<Node> q = new LinkedList<>();

	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static boolean[][] visit;
	
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		m = s.nextInt();
		n = s.nextInt();
		map = new int[n][m];
		visit = new boolean[n][m];
		
		boolean allTomato = true;	//모든 토마토가 익어있을 경우를 체크 
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				map[i][j] = s.nextInt();
				if (map[i][j] == 1) {
					q.add(new Node(i, j, 1));
					visit[i][j] = true;
				} else if (map[i][j] == 0) {
					allTomato = false;	//하나라도 안익은 토마토가 있으면 false 
				}
			}
		}
		if (allTomato) {	//애초에 모든 토마토가 익어있었다면 bfs 돌 필요 X 
			System.out.println("0");
			return;
		}
		
		bfs();
		checkTomato();
	}


	private static void checkTomato() {
		boolean existNoTomato = false;	//안익은 토마토가 있는지 여부 있으면 true 
		int max = 0;
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 0) {
					existNoTomato = true;
					break;
				}
				if (max < map[i][j])
					max = map[i][j];
			}
		}
		
		if (existNoTomato) {	//여전히 안익은 토마토가 하나라도 있다면 
			System.out.println("-1");
		} else {				//아니라면 가장 큰 값을 출력 
			System.out.println(max);
		}
	}


	private static void bfs() {
		while (!q.isEmpty()) {
			Node temp = q.poll();	//큐에서 빼오는 함수 
			
			//사방탐색을 위한 for문 
			for (int d = 0; d < 4; d++) {
				int nx = temp.x + dx[d];
				int ny = temp.y + dy[d];
				
				//범위체크 + 익지 않은 토마토가 아닌 경우는 pass 
				if (nx < 0 || nx >= n || ny < 0 || ny >= m || visit[nx][ny] || map[nx][ny] != 0)
					continue;
				
				map[nx][ny] = temp.day;
				visit[nx][ny] = true;
				q.add(new Node(nx, ny, temp.day + 1));
			}
		}
	}
}












