package bj_02151_거울설치;

import java.io.*;
import java.util.*;

//190901 bfs 문제. 2h만에 완탐으로 풀었으나 메모리가 터짐->해결 실패
//배워서 아이디어로 풀어서 통과.
//핵심은 1) visit체크를 큐에 넣을 때 하는게 아니라 큐에서 뺄 때 한다
//2) 큐에서 뺀 노드에서 한방향으로 쭉 가면서 만나는 !들을 다 큐에 넣는다. 이러면 그 줄 끝에서 탐색에 실패해도 다음 노드에서 탐색하는 효과를 낸다.

public class Main {
	private static class Node {
		int x, y, dir, step;
		Node (int x, int y) {
			this.x = x;
			this.y = y;
		}
		Node (int x, int y, int dir, int step) {
			this(x, y);
			this.dir = dir;
			this.step = step;
		}
	}
	
	static int n;
	static char[][] map;
	static boolean[][] visit;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static Node start, end;
	static ArrayList<Node> list = new ArrayList<>();
	static Queue<Node> q = new LinkedList<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		map = new char[n][n];
		visit = new boolean[n][n];
		
		for (int i = 0; i < n; i++) {
			char[] ch = br.readLine().toCharArray();
			for (int j = 0; j < n; j++) {
				map[i][j] = ch[j];
				
				if (map[i][j] == '#') {
					if (start == null) {
						start = new Node(i, j);
						visit[i][j] = true;
					} else {
						end = new Node(i, j);
					}
				}
			}
		}
		
		for (int i = 0; i < 4; i++)			//시작점 사방탐색하기
			q.add(new Node(start.x, start.y, i, 0));

		bfs();
	}

	private static void bfs() {
		Node temp;
		int nx, ny;
		
		while (!q.isEmpty()) {
			temp = q.poll();
			
			visit[temp.x][temp.y] = true;
			
			nx = temp.x + dx[temp.dir];
			ny = temp.y + dy[temp.dir];
			
			if (nx < 0 || nx >= n || ny < 0 || ny >= n || visit[nx][ny])
				continue;
			
			//한번 q에서 뽑아온 값으로 그 줄 끝까지 가기
			while (map[nx][ny] != '*') {
				
				if (map[nx][ny] == '!') {
					if (temp.dir / 2 == 0) {
						q.add(new Node(nx, ny, 2, temp.step + 1));
						q.add(new Node(nx, ny, 3, temp.step + 1));
					} else if (temp.dir / 2 == 1) {
						q.add(new Node(nx, ny, 0, temp.step + 1));
						q.add(new Node(nx, ny, 1, temp.step + 1));
					}
					
				} else if (map[nx][ny] == '#') {
					System.out.println(temp.step);
					System.exit(0);
				}
				
				nx += dx[temp.dir];
				ny += dy[temp.dir];
				
				if (nx < 0 || nx >= n || ny < 0 || ny >= n || visit[nx][ny])
					break;
			}
		}
	}
}