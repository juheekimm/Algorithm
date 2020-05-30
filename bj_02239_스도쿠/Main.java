package bj_02239_스도쿠;

import java.io.*;

//190810. 1h이상. DFS + 백트래킹
//너무 어렵게 생각했음. 한개씩 다 넣으면서 그 값의 가로, 세로, 정사각형 9칸 검사하면서 넘어가면 됨.
//별로 안 어려운 문제인데 너무 돌아갔음.
//dfs를 강제 종료하고 싶을 때는 dfs 함수의 반환값을 boolean으로 주고, 원하는 때에 true를 return하면 됨!
public class Main {
 
	static int[][] map = new int[9][9];
	static boolean[] numVisit;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int i = 0; i < 9; i++) {
			String[] temp = br.readLine().split("");
			for (int j = 0; j < 9; j++) {
				map[i][j] = Integer.parseInt(temp[j]);
			}
		}
		
		dfs(zeroJump(0));
	}
	
	//x, y값 넘겨줄때는 경계값처리 해야하지만 cnt로 쓰면 할 필요 없음
	private static boolean dfs(int cnt) {
		//종료조건
		if (cnt >= 81) {
			print();
			return true;
		}
		
		int x = cnt / 9;
		int y = cnt % 9;
		numVisit = new boolean[10];
		
		for (int k = 1; k < 10; k++) {
			if (overlapCheck(x, y, k)) continue;

			numVisit[k] = true;
			map[x][y] = k;
			
			if(dfs(zeroJump(cnt))) return true; //종료조건 만나면 모든 작업을 끝내버림. 가장 작은 한 경우만 출력하면 되니까.
			
			numVisit[k] = false;
			map[x][y] = 0;
		}
		return false;
	}
	
	private static boolean overlapCheck(int x, int y, int val) {
		int xNum = (x / 3) * 3;
		int yNum = (y / 3) * 3;

		for (int k = 0; k < 9; k++) { // 행체크
			if (map[x][k] == val || map[k][y] == val || map[xNum + (k / 3)][yNum + (k % 3)] == val)
				return true;
		}
		
		return false;
	}
	
	private static int zeroJump(int cnt) {
		int x, y;
		while (cnt != 81) {
			x = cnt / 9;
			y = cnt % 9;
			if (map[x][y] == 0) return cnt;
			else cnt++;
		}
		return cnt;
	}
	
	private static void print() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}
}