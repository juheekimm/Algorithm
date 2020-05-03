package bj_17244_아맞다우산;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

public class Main2 {

	private static int n, m, num, number[];
	private static int[] dx = {0, -1, 0, 1};
	private static int[] dy = {-1, 0, 1, 0};
	private static char[][] map;
	private static boolean[][] visit;
	private static Queue<int[]> q = new LinkedList<>();
	private static ArrayList<int[]> list = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int sx, sy, ex, ey, minSum = Integer.MAX_VALUE;
		sx = sy = ex = ey = 0;
		
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
	
		map = new char[n][m];
		for (int i = 0; i < n; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 'S') {
					sx = i; sy = j;
				} else if (map[i][j] == 'E') {
					ex = i; ey = j;
				} else if (map[i][j] == 'X') {
					list.add(new int[]{i, j});
				}
			}
		}
		num = list.size();
		number = new int[num];
		for (int i = 0; i < num; i++)
			number[i] = i;

		
		do {
			int tempStartX = sx, tempStartY = sy;
			int tempSum = 0;
			for (int i = 0; i < num; i++) {
				q.add(new int[]{tempStartX, tempStartY, 0});
				visit = new boolean[n][m];
				visit[tempStartX][tempStartY] = true;
				
				int[] tempEnd = bfs(list.get(number[i])[0], list.get(number[i])[1]);
				tempStartX = tempEnd[0];
				tempStartY = tempEnd[1];
				tempSum += tempEnd[2];
				
				if (minSum != Integer.MAX_VALUE && tempSum > minSum) break;
			}
//			q.add(new int[]{tempStartX, tempStartY, 0});
//			visit = new boolean[n][m];
//			visit[tempStartX][tempStartY] = true;
//			int[] tempEnd = bfs(ex, ey);
//			tempSum += tempEnd[2];
			
			minSum = minSum > tempSum ? tempSum : minSum;

			if (tempSum == 12) {
				System.out.println(Arrays.toString(number));
			}
		} while (nextPermutaion());
		
		System.out.println(minSum);
	}

	private static int[] bfs(int ex, int ey) {
		while (!q.isEmpty()) {
			int[] temp = q.poll();
			
			for (int d = 0; d < 4; d++) {
				int nx = temp[0] + dx[d];
				int ny = temp[1] + dy[d];
				
				if (nx < 0 || nx >= n || ny < 0 || ny >= m || visit[nx][ny]) continue;
				
				if (nx == ex && ny == ey)
					return new int[]{nx, ny, temp[2] + 1};
				
				visit[nx][ny] = true;
				q.add(new int[]{nx, ny, temp[2] + 1});
			}
		}
		return new int[]{-1, -1, 0};
	}

	private static boolean nextPermutaion() {
		int i = num - 1;
		while (i > 0 && number[i - 1] >= number[i]) --i;
		if (i == 0) return false;
		
		int j = num - 1;
		while (number[i - 1] >= number[j]) --j;
		
		swap(i - 1, j);
		
		j = num - 1;
		while (i < j) swap(i++, j--);
		
		return true;
	}

	private static void swap(int i, int j) {
		int temp = number[i];
		number[i] = number[j];
		number[j] = temp;
	}
}
