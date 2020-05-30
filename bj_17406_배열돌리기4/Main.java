package bj_17406_배열돌리기4;

import java.io.*;
import java.util.StringTokenizer;

//190818 3h 시뮬레이션, permutation 배열 범위에서 꽤 고생했음
//배열돌리기1 코드 보고 +permu부분은 힌트받음
public class Main {
	static int n, m, k, minVal = Integer.MAX_VALUE, turnOrder[], map[][], copymap[][], turn[][]; 
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	static boolean[] turnOrderVisit;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = stio(st.nextToken());
		m = stio(st.nextToken());
		k = stio(st.nextToken());
		
		map = new int[n + 1][m + 1];
		copymap = new int[n + 1][m + 1];
		turn = new int[k][3];
		turnOrder = new int[k];
		turnOrderVisit = new boolean[k];
		
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= m; j++) {
				map[i][j] = stio(st.nextToken());
			}
		}
		
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			turn[i][0] = stio(st.nextToken());
			turn[i][1] = stio(st.nextToken());
			turn[i][2] = stio(st.nextToken());
		}
		
		turnOrderPermu(0);
		System.out.println(minVal);
	}
	
	private static void turnOrderPermu(int cnt) {
		if (cnt == k) {
			for (int i = 1; i <= n; i++) {
				copymap[i] = map[i].clone();
			}
			arrTurn();
			minVal = Math.min(minVal, minArrValSearch());
			return;
		}
		
		for (int i = 0; i < k; i++) {
			if (!turnOrderVisit[i]) {
				
				turnOrderVisit[i] = true;
				turnOrder[cnt] = i;
				
				turnOrderPermu(cnt + 1);
				
				turnOrderVisit[i] = false;
			}
		}
	}

	private static void arrTurn() {
		for (int i = 0; i < k; i++) {
			int index = turnOrder[i];
			int r = turn[index][0], c = turn[index][1], s = turn[index][2];
			int turnNum = s;	//식 도출 과정 : (r+s)-(r-s)=2s, 2s / 2
			int[] temp = new int[turnNum];
			
			for (int t = 0; t < turnNum; t++) {
				int dir = 0, x = r - s + t, y = c - s + t;
				temp[t] = copymap[x][y];
				
				while (dir < 4) {
					int nx = x + dx[dir];
					int ny = y + dy[dir];
					
					if (nx < r - s + t || nx > r + s - t || ny < c - s + t || ny > c + s - t) {
						dir++;
					} else {
						copymap[x][y] = copymap[nx][ny];
						x = nx;
						y = ny;
					}
				}
				copymap[r - s + t][c - s + t + 1] = temp[t];
			}
		}
	}
	
	private static int minArrValSearch() {
		int tempMin = Integer.MAX_VALUE;
		
		for (int i = 1; i <= n; i++) {
			int rowMin = 0;
			for (int j = 1; j <= m; j++)
				rowMin += copymap[i][j];
			
			tempMin = Math.min(tempMin, rowMin);
		}
		return tempMin;
	}
	
	public static int stio(String str) {
		return Integer.parseInt(str);
	}
}