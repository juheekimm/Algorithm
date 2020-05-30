package bj_02239_스도쿠;

import java.io.*;
import java.util.Arrays;

public class Main2 {
 
	static int[][] map = new int[9][9];
//	static int[][] temp = new int[9][9];
	static boolean[] numVisit;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 9; i++) {
			String[] temp = br.readLine().split("");
			for (int j = 0; j < 9; j++) {
				map[i][j] = Integer.parseInt(temp[j]);
			}
		}
//		numVisit = new boolean[10];
//		test(0, 0);
//		print();
//		System.out.println(zeroJump(0));
		dfs(zeroJump(0));
	}
	
	//x, y값 넘겨줄때는 경계값처리 해야하지만 cnt로 쓰면 할 필요 없음
	private static boolean dfs(int cnt) {
		//종료조건
		if (cnt >= 80) {
			System.out.println("안오지너");
			print();
			return true;
		}
		
		int x = cnt / 9;
		int y = cnt % 9;
		numVisit = new boolean[10];
		
		//중복된 수가 있으면 돌아간다.
//		if (!numCheck(x, y)) {
//			return;
//		}
		
		numCheck(x, y);
		for (int k = 1; k < 10; k++) {
			if (numVisit[k]) continue;
			numVisit[k] = true;
			map[x][y] = k;
			
			print();
			if(dfs(zeroJump(cnt))) {
				return true;
			}
			
			map[x][y] = 0;
			numVisit[k] = false;
		}
		return false;
	}
	
	private static boolean numCheck(int x, int y) {
		boolean[] tempCheck = new boolean[10];
		for (int j = 0; j < 9; j++) { // 행체크
			if (map[x][j] != 0) {
				if (tempCheck[map[x][j]]) {
					return false; //만약 이미 들어간 숫자가 또 있다면
				} else {
					numVisit[map[x][j]] = true; 
					tempCheck[map[x][j]] = true;
				}
			}
		}
		
		tempCheck = new boolean[10];
		for (int i = 0; i < 9; i++) { //열체크
			if (map[i][y] != 0) {
				if (tempCheck[map[i][y]]) {
					return false; 
				} else {
					numVisit[map[i][y]] = true; 
					tempCheck[map[i][y]] = true;
				}
			}
		}
		
		tempCheck = new boolean[10];
		int xNum = (x / 3) * 3;
		int yNum = (y / 3) * 3;
		for (int k = 0; k < 9; k++) { //9칸체크
			
			if (map[xNum + (k / 3)][yNum + (k % 3)] != 0) {
				if (tempCheck[map[xNum + (k / 3)][yNum + (k % 3)]]) {
					return false;
				} else {
					numVisit[map[xNum + (k / 3)][yNum + (k % 3)]] = true;
					tempCheck[map[xNum + (k / 3)][yNum + (k % 3)]] = true;
				}
			}
		}
		return true;
	}
	
	
//	private static boolean test(int x, int y) {
//		boolean[] tempCheck = new boolean[10];
//		for (int j = 0; j < 9; j++) { // 행체크
//			if (map[x][j] != 0) {
//				if (numVisit[map[x][j]] && tempCheck[map[x][j]])
//					return false; //만약 이미 들어간 숫자가 또 있다면
//				else {
//					numVisit[map[x][j]] = true; 
//					tempCheck[map[x][j]] = true;
//					temp[x][j] = 9;
//				}
//			}
//		}
//		
//		tempCheck = new boolean[10];
//		for (int i = 0; i < 9; i++) { //열체크
//			if (map[i][y] != 0) {
//				if (numVisit[map[i][y]] && tempCheck[map[i][y]])
//					return false; 
//				else {
//					numVisit[map[i][y]] = true; 
//					tempCheck[map[i][y]] = true;
//					temp[i][y] = 9;
//				}
//			}
//		}
//		
//		tempCheck = new boolean[10];
//		for (int k = 0; k < 9; k++) { //9칸체크
//			if (map[((x / 3) * 3) + (k / 3)][((y / 3) * 3) + (k % 3)] != 0) { //9칸체크
//				if (numVisit[map[((x / 3) * 3) + (k / 3)][((y / 3) * 3) + (k % 3)]] && tempCheck[map[(x / 3 * 3) + (k / 3)][(y / 3 * 3) + (k % 3)]])
//					return false;
//				else {
//					numVisit[map[((x / 3) * 3) + (k / 3)][((y / 3) * 3) + (k % 3)]] = true;
//					tempCheck[map[((x / 3) * 3) + (k / 3)][((y / 3) * 3) + (k % 3)]] = true;
//					temp[((x / 3) * 3) + (k / 3)][((y / 3) * 3) + (k % 3)] = 9;
//				}
//			}
//		}
//		return true;
//	}
	
	private static int zeroJump(int cnt) {
		int x, y;
		while (cnt != 80) {
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
		System.out.println();
	}
}
