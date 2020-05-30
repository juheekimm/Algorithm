package bj_16926_배열돌리기1;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_4search_BufferedReader {
	static int n;
	static int m;
	static int[][] map;
	static int turnNum;
	//dx, dy 순서 중요!
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	
	//190717 40m. 4��Ž�� ������� �ٽ� Ǯ�� + 10m BufferedReader ��� ver
	//42628	1060
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		turnNum = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
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
					//범위를 벗어났다 -> 한 변 돌리기가 끝났다.
					//이때 범위는 t부터 n - t. 여기서 t가 중요!
					if (nx < t || nx >= n - t || ny < t || ny >= m - t){
						direction++;
					} else {
						map[x][y] = map[nx][ny];
						x = nx;
						y = ny;
					}
				}
				map[x + 1][y] = temp[t];
				//이게 더 직관적인 표현일 듯
//				map[t + 1][t] = temp[t];
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