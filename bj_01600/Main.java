package bj_01600;

import java.io.*;
import java.util.*;

public class Main {
	private static class Node {
		int x, y, stepk, step;
		Node(int x, int y, int stepk, int step) {
			this.x = x;
			this.y = y;
			this.stepk = stepk;
			this.step = step;
		}
	}
	static int k, h, w;
	static int[][] map;
	static boolean[][][] visit;
	static Queue<Node> q;
	
	//idx 0-3까지 원숭이, 4-11까지는 말. 
	static int[][] dir = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}, {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		k = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		w = Integer.parseInt(st.nextToken());	//가로
		h = Integer.parseInt(st.nextToken());	//세로
		
		map = new int[h][w];
		visit = new boolean[h][w][k + 1];
		q = new LinkedList<>();
		
		for (int i = 0; i < h; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < w; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		q.add(new Node(0, 0, 0, 0));
		monkeyBfs();
	}

	private static void monkeyBfs() {
		while(!q.isEmpty()) {
			Node temp = q.poll();
			int nx, ny;
			
			//갓케 팁
			if (temp.x == h - 1 && temp.y == w - 1) {
				System.out.println(temp.step);
				return;
			}
			
			for (int i = 0; i < dir.length; i++) {
				//누구 턴이냐에 따라 말이 뛴 횟수가 달라짐.
				int thisHorseStep = i < 4 ? temp.stepk : temp.stepk + 1;
				if (temp.stepk >= k) {//말이 뛸 기회가 없으면
					if (i == 4)		//index가 말 차례가 되는 순간 반복문 종료
						break;
				}
				nx = temp.x + dir[i][0];
				ny = temp.y + dir[i][1];
				
//				if (temp.x == h - 1 && temp.y == w - 1) {
//					System.out.println(temp.step + 1);
//					return;
//				}
				
				if (nx < 0 || nx >= h || ny < 0 || ny >= w || map[nx][ny] == 1 || visit[nx][ny][thisHorseStep])
					continue;
				
				visit[nx][ny][thisHorseStep] = true;
				q.add(new Node(nx, ny, thisHorseStep, temp.step + 1));
			}
		}
		//다 돌았는데도 도착 못하면
		System.out.println("-1");
	}
}