package bj_15685_드래곤커브;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main2 {
	
	private static final int SIZE = 101;
	private static int N, count = 0;
	private static int[] dx = {1, 0, -1, 0}, dy = {0, -1, 0, 1};
	private static boolean[][] map;
	private static LinkedList<Integer> list;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		map = new boolean[SIZE][SIZE];
		N = Integer.parseInt(br.readLine());
		
		int x, y, d, g;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			g = Integer.parseInt(st.nextToken());
			
			list = new LinkedList<Integer>();
			list.add(d);
			curveMake(g);
			drawCurveMap(x, y);
		}
		checkMap();
		System.out.println(count);
	}

	private static void checkMap() {
		for (int i = 0; i < SIZE - 1; i++)
			for (int j = 0; j < SIZE - 1; j++)
				if (map[i][j] && map[i][j + 1] && map[i + 1][j] && map[i + 1][j + 1])
					count++;
	}

	private static void drawCurveMap(int x, int y) {
		int nx = x, ny = y;
		int size = list.size();

		map[y][x] = true;
		for (int i = 0; i < size; i++) {
			int d = list.get(i);
			
			nx += dx[d]; ny += dy[d];
			map[ny][nx] = true;
		}
	}

	private static void curveMake(int curve) {
		for (int c = 0; c < curve; c++) {
			int size = list.size();
			for (int i = 1; i <= size; i++)
				list.add((list.get(size - i) + 1) % 4);
		}
	}
}