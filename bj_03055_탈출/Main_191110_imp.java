package bj_03055_탈출;

import java.util.*;
import java.io.*;

//14524kb	96ms
//알고리즘 과제로 받아서 다시 풀어봄. 물-고슴도치 중 어떤게 먼저 이동하는지는 상관없다. 풀리긴 함
//그러나 문제의 의도는 물이 먼저 이동하는 것!!
//대신 고슴도치가 물에 잠겼다해서(현재맵이 물이라해서) 그 고슴도치를 죽이면 안됨! 고슴도치가 어차피 움직일거니까. 맵의 값은 바뀌어도 됨(어차피 큐에서 살아있으니까)
//다음 시간에 물이 찰 예정인 칸으로 고슴도치는 이동할 수 없다. => 물을 먼저 움직이라는 의미
//근데 고슴도치가 물사이에 섞여있으면 안되는 이유는? => 물이 다 퍼지고 고슴도치가 움직여야 하니까. 고슴도치가 먼저 움직여도 되지만, 중간에 있는건 안된다.
public class Main_191110_imp {
	private static class Node {
		int x, y, time;
		boolean isWater;
		Node (int x, int y, int time, boolean isWater) {
			this.x = x;
			this.y = y;
			this.time = time;
			this.isWater = isWater;
		}
	}
	static int r, c, endx, endy;
	static int[] dx = {0, -1, 0, 1}, dy = {-1, 0, 1, 0};
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
					gox = i;
					goy = j;
				} else if (map[i][j] == 'D') {
					endx = i;
					endy = j;
				} else if (map[i][j] == '*') {
					q.add(new Node(i, j, 1, true));
				}
			}
		}
		q.add(new Node(gox, goy, 1, false));
		bfs();
	}

	private static void bfs() {
		int nx, ny;
		Node temp;
		
		while (!q.isEmpty()) {
			temp = q.poll();
			
			for (int d = 0; d < 4; d++) {
				nx = temp.x + dx[d];
				ny = temp.y + dy[d];
				if (nx < 0 || nx >= r || ny < 0 || ny >= c)
					continue;
				
				if (temp.isWater) {	//물이면
					if (map[nx][ny] == '.' || map[nx][ny] == 'S') {
						map[nx][ny] = '*';
						q.add(new Node(nx, ny, temp.time + 1, true));
					}
				} else {			//고슴도치면
					if (map[nx][ny] == '.') {
						map[nx][ny] = 'S';
						q.add(new Node(nx, ny, temp.time + 1, false));
					} else if (map[nx][ny] == 'D') {
						System.out.println(temp.time);
						return;
					}
				}
			}
		}
		System.out.println("impossible");	//다돌았는데도 못가면 출력
	}
}