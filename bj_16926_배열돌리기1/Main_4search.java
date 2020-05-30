package bj_16926_배열돌리기1;
import java.util.Scanner;

public class Main_4search {
	static int n;
	static int m;
	static int[][] map;
	static int turnNum;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	
	//190717 40m. 4��Ž�� ������� �ٽ� Ǯ��
	//105296	1536
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		n = s.nextInt();
		m = s.nextInt();
		turnNum = s.nextInt();
		map = new int[n][m];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				map[i][j] = s.nextInt();
			}
		}

		turnMap();
		print();
	}
	
	private static void turnMap() {
		int forNum = Math.min(n, m) / 2;
		int[] temp = new int[forNum];
		
		for (int tNum = 0; tNum < turnNum; tNum++) {
			for (int t = 0; t < forNum; t++) {
				int direction = 0;
				int x = t, y = t;
				temp[t] = map[x][y];
				while(direction < 4) {
					int nx = x + dx[direction];
					int ny = y + dy[direction];
					if (nx < t || nx >= n - t || ny < t || ny >= m - t){
						direction++;
					} else {
						map[x][y] = map[nx][ny];
						x = nx;
						y = ny;
					}
				}
				map[x + 1][y] = temp[t];
			}
		}
	}
	
	private static void print() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
}