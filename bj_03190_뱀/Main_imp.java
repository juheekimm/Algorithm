package bj_03190_뱀;

import java.io.*;
import java.util.*;

public class Main_imp {
	private static class Node {
		int x, y;
		Node (int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	private static int n, k, l, time, nowDir, nowRotation, head[], map[][], dir[][];
	private static LinkedList<int[]> snake = new LinkedList<int[]>();
	private static int[] dx = {0, 1, 0, -1};	//우하좌상 순
	private static int[] dy = {1, 0, -1, 0};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		
		k = Integer.parseInt(br.readLine());
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			map[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = 5;
		}
		
		l = Integer.parseInt(br.readLine());
		dir = new int[l][2];

		int t;	char d;
		for (int i = 0; i < l; i++) {
			st = new StringTokenizer(br.readLine());
			t = Integer.parseInt(st.nextToken());
			d = st.nextToken().charAt(0);
			dir[i][0] = t;
			dir[i][1] = (d == 'L') ? -1 : 1;	//-1이면 왼쪽, 1이면 오른쪽 
		}
		
		head = new int[] {0, 0};
		snake.add(new int[]{0, 0});
		map[0][0] = 1;
		while (true) {
			time++;
			
			head[0] += dx[nowDir];
			head[1] += dy[nowDir];
			snake.addFirst(new int[]{head[0], head[1]});
			
			if (head[0] < 0 || head[0] >= n || head[1] < 0 || head[1] >= n || map[head[0]][head[1]] == 1) {
				break;

			} else {
				if (map[head[0]][head[1]] != 5) {	//이동한 칸에 사과가 없다면 꼬리 지우기  
					map[snake.get(snake.size() - 1)[0]][snake.get(snake.size() - 1)[1]] = 0;
					snake.remove(snake.size() - 1);
				}
				map[head[0]][head[1]] = 1; 		//새로 이동한 칸에 뱀 그리기
			}
			
			if (nowRotation < l && dir[nowRotation][0] == time) {
				nowDir = (nowDir + dir[nowRotation][1] + 4) % 4;
				nowRotation++;
			}
		}
		System.out.println(time);
	}
}