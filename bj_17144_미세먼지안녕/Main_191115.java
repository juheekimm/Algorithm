package bj_17144_미세먼지안녕;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//191115 1h 10m. 빨리 푼다고 풀었는데.. 쉽지 않았음. 구현자체는 어렵지않았으나 1시간안에 풀기에는 아직 무리인듯
//맵을 매번 새로 생성하기에는 무리, 두개로 번갈아가면서 해주는게 포인트 같다.
//map1, map2로 if로 분기해서 풀다가 3차원으로 하면 된다는걸 깨달음
//이 매개변수 방식은 처음 시도해봤는데 된다니 신기하군
//채점번호 16130179가 오랜만에 다시푼거고 나머지는 시간줄여보려고 이것저것 해본것
public class Main_191115 {

	static int r, c, T, f1, f2, map[][][];
	static int[] dx1 = {-1, 0, 1, 0};
	static int[] dx2 = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		map = new int[2][r][c];
		
		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < c; j++) {
				map[0][i][j] = Integer.parseInt(st.nextToken());
				
				if (map[0][i][j] == -1 && f1 == 0) {
					f1 = i;
					f2 = i + 1;
				}
			}
		}
		map[0][f1][0] = 0;
		map[0][f2][0] = 0;
		
		int dust = 0, idx, nextIdx;
		for (int t = 0; t < T; t++) {
			idx = t % 2;
			nextIdx = (t + 1) % 2;
			dustSpread(map[idx], map[nextIdx]);
			upFilter(map[nextIdx]);
			downFilter(map[nextIdx]);
		}
		
		idx = T % 2;
		for (int i = 0; i < r; i++)
			for (int j = 0; j < c; j++)
				dust += map[idx][i][j];
		
		System.out.println(dust);
	}

	private static void upFilter(int[][] map) {
		int x = f1, y = 0;
		for (int d = 0; d < 4; d++) {
			int nx = x + dx1[d];
			int ny = y + dy[d];
			while(nx >= 0 && nx <= f1 && ny >= 0 && ny < c) {
				map[x][y] = map[nx][ny];
				x = nx;
				y = ny;
				nx += dx1[d];
				ny += dy[d];
			}
		}
		map[f1][0] = 0;
		map[f1][1] = 0;
	}
	
	private static void downFilter(int[][] map) {
		int x = f2, y = 0;
		for (int d = 0; d < 4; d++) {
			int nx = x + dx2[d];
			int ny = y + dy[d];
			while(nx >= f2 && nx < r && ny >= 0 && ny < c) {
				map[x][y] = map[nx][ny];
				x = nx;
				y = ny;
				nx += dx2[d];
				ny += dy[d];
			}
		}
		map[f2][0] = 0;
		map[f2][1] = 0;
	}

	private static void dustSpread(int[][] m1, int[][] m2) {
		int dustCount = 0;
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (m1[i][j] > 0) dustCount++;
				if ((i == f1 && j == 0) || (i == f2 && j == 0)) continue;
				
				if (m1[i][j] >= 5) {
					int cnt = 0, val = m1[i][j] / 5;
					for (int d = 0; d < 4; d++) {
						int nx = i + dx1[d];
						int ny = j + dy[d];
						
						if (nx < 0 || nx >= r || ny < 0 || ny >= c || (nx == f1 && ny == 0) || (nx == f2 && ny == 0))
							continue;
						
						cnt++;
						m2[nx][ny] += val;
					}
					m2[i][j] += (m1[i][j] - cnt * val);
					m1[i][j] = 0;
				} else {
					m2[i][j] += m1[i][j];
					m1[i][j] = 0;
				}
			}
		}
		if (dustCount == 0) {
			System.out.println(0);
			System.exit(0);
		}
	}
}