package bj_17244_아맞다우산;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	
	private static int n, m, sx, sy, ex, ey, minSum = Integer.MAX_VALUE, num, number[];
	private static int[] dx = {0, -1, 0, 1}, dy = {-1, 0, 1, 0};
	private static char[][] map;
	private static boolean[][] visit;
	private static Queue<int[]> q = new LinkedList<>();
	private static ArrayList<int[]> list = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		map = new char[n][m];

		//맵 입력 
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
		
		//permutation을 위한 배열 생성 
		num = list.size();
		number = new int[num];
		for (int i = 0; i < num; i++)
			number[i] = i;
		
		//물건이 하나도 없을 경우 
		if (num == 0) {
			q.add(new int[] {sx, sy, 0});
			visit = new boolean[n][m];
			visit[q.peek()[0]][q.peek()[1]] = true;
			System.out.println(bfs(ex, ey));
			System.exit(0);
		}
		
		do {
			int tempSum = 0;
			for (int i = 0; i <= num; i++) {

				q.clear();
				if (i == 0)		//시작점을 위한 
					q.add(new int[] {sx, sy, 0});
				else
					q.add(new int[] {list.get(number[i - 1])[0], list.get(number[i - 1])[1], 0});

				visit = new boolean[n][m];
				visit[q.peek()[0]][q.peek()[1]] = true;
				
				if (i < num)
					tempSum += bfs(list.get(number[i])[0], list.get(number[i])[1]);
				else			//끝점을 위한 
					tempSum += bfs(ex, ey);
				
				if (tempSum > minSum) break;	//가지치기
			}
			minSum = minSum > tempSum ? tempSum : minSum;
		} while (nextPermutaion());
		
		System.out.println(minSum);
	}

	private static int bfs(int endX, int endY) {
		while (!q.isEmpty()) {
			int[] temp = q.poll();
			
			for (int d = 0; d < 4; d++) {
				int nx = temp[0] + dx[d];
				int ny = temp[1] + dy[d];
				
				if (nx < 0 || nx >= n || ny < 0 || ny >= m || visit[nx][ny] || map[nx][ny] == '#') continue;
				
				if (nx == endX && ny == endY) return temp[2] + 1;
				
				visit[nx][ny] = true;
				q.add(new int[]{nx, ny, temp[2] + 1});
			}
		}
		return 0;
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