package bj_03190_뱀;

import java.io.*;
import java.util.*;

public class Main_200530 {
	
	static int N, K, L, map[][];
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static LinkedList<int[]> snake = new LinkedList<int[]>();
	static Map<Integer, String> dir = new HashMap<Integer, String>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			map[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = 1;
		}
		
		L = Integer.parseInt(br.readLine());
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			dir.put(Integer.parseInt(st.nextToken()), st.nextToken());
		}
		
		snake.add(new int[] {0, 0});
		map[0][0] = -1;	//뱀이 있는곳은 -1로 표시
		
		System.out.println(move());
	}

	private static int move() {
		int time = 0;
		int x = 0, y = 0, d = 1;
		
		while (true) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			if (nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny] == -1)
				break;
			
			if (map[nx][ny] == 0) {
				map[snake.getLast()[0]][snake.getLast()[1]] = 0;
				snake.removeLast();
			}
			
			//머리는 무조건 이동 
			map[nx][ny] = -1;
			snake.addFirst(new int[] {nx, ny});
			
			x = nx;
			y = ny;
			
			time++;
			if (dir.containsKey(time)) {
				String direction = dir.get(time);
				if (direction.equals("L")) d = (d - 1 + 4) % 4;
				else d = (d + 1) % 4;
			}
		}
		return time + 1;
	}
}