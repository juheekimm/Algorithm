package bj_03055_탈출;

import java.util.*;
import java.io.*;

//14524kb	96ms
//알고리즘 과제로 받아서 다시 풀어봄
public class Main_191110 {
	private static class Node {
		int x, y;
		Node (int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static int r, c, endx, endy, waterSize, goSize;
	static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
	static char[][] map;
	static Queue<Node> q = new LinkedList<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		map = new char[r][c];
		
		String temp;
		int gox = 0, goy = 0;
		
		for (int i = 0; i < r; i++) {
//			map[i] = br.readLine().toCharArray();	//각각원소 읽어야해서 chatAt사용
			temp = br.readLine();
			for (int j = 0; j < c; j++) {
				map[i][j] = temp.charAt(j);
				
				if (map[i][j] == 'S') {
					gox = i; goy = j;
					goSize++;
				} else if (map[i][j] == 'D') {
					endx = i;
					endy = j;
				}
			}
		}
		q.add(new Node(gox, goy));	//고슴도치를 먼저 넣어준다
		
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (map[i][j] == '*') {
					waterSize++;
					q.add(new Node(i, j));
				}
			}
		}
		bfs();
	}

	private static void bfs() {
		int tempSize, nx, ny, time = 0;
		Node temp;
		
		while (!q.isEmpty()) {
			time++;

			//고슴도치 먼저 이동
			tempSize = goSize;
			goSize = 0;
			for (int i = 0; i < tempSize; i++) {
				temp = q.poll();
				
				if (map[temp.x][temp.y] != 'S') continue;	//고슴도치 칸에 고슴도치가 없으면 물에 잠긴거니까 넘어간다
				map[temp.x][temp.y] = '.';					//고슴도치가 있으면 그 칸은 지워주고 시작(이동할거니까)
				
				for (int d = 0; d < 4; d++) {
					nx = temp.x + dx[d];
					ny = temp.y + dy[d];
					
					if (nx < 0 || nx >= r || ny < 0 || ny >= c || map[nx][ny] == 'S'
							|| map[nx][ny] == '*' || map[nx][ny] == 'X')
						continue;
					
					if (map[nx][ny] == 'D') {
						System.out.println(time);
						return;
					}
					goSize++;
					map[nx][ny] = 'S';
					q.add(new Node(nx, ny));
				}
			}
			
			//물 퍼뜨리기
			tempSize = waterSize;
			waterSize = 0;
			for (int i = 0; i < tempSize; i++) {
				temp = q.poll();
				
				for (int d = 0; d < 4; d++) {
					nx = temp.x + dx[d];
					ny = temp.y + dy[d];
					
					if (nx < 0 || nx >= r || ny < 0 || ny >= c || map[nx][ny] == '*' 
							|| map[nx][ny] == 'D' || map[nx][ny] == 'X')	//물,비버,돌
						continue;
					
					waterSize++;
					map[nx][ny] = '*';
					q.add(new Node(nx, ny));
				}
			}
		}
		System.out.println("KAKTUS");	//다돌았는데도 못가면 출력
	}
}