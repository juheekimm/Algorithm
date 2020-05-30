package bj_16945_매직스퀘어로변경하기;

import java.util.Scanner;

public class Main {
	
	static int[][] map = new int[3][3];
	static int[][] square = new int[3][3];
	static boolean[] numVisit = new boolean[9];
	static int cost = Integer.MAX_VALUE;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				map[i][j] = sc.nextInt();
		
		makeSquareDfs(0);
		System.out.println(cost);
	}
	
	public static void makeSquareDfs(int cnt) {
		if (cnt >= 9) {
			if (checkSquare()) {
				int tempCost = calcCost();
				cost = Math.min(tempCost, cost);
			}
			return;
		}

		for (int i = 0; i < 9; i++) {
			if (numVisit[i]) continue;
			
			numVisit[i] = true;
			square[cnt / 3][cnt % 3] = i + 1;
			
			makeSquareDfs(cnt + 1);
			square[cnt / 3][cnt % 3] = 0;
			numVisit[i] = false; //백트래킹
		}
	}
	
	public static boolean checkSquare() {
		int leftVer = 0, rightVer = 0;
		for (int i = 0; i < 3; i++) {
			//대각선 체크
			leftVer += square[2 - i][i];
			rightVer += square[i][i];
			
			//가로세로선 체크
			int rVer = 0, cVer = 0;
			for (int j = 0; j < 3; j++) {
				rVer += square[i][j]; //가로 합 계산
				cVer += square[j][i]; //세로 합 계산
			}
			if (rVer != 15 || cVer != 15) return false;
		}
		if (leftVer != 15 || rightVer != 15) return false;
		
		return true;
	}
	
	private static int calcCost() {
		int cs = 0;
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				cs += Math.abs(map[i][j] - square[i][j]);
		
		return cs;
	}
}