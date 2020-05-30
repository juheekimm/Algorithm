package bj_15684_사다리조작;

import java.io.*;
import java.util.*;

public class Main {
	static int n, m, h;
	static boolean map[][];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = stoi(st.nextToken());
		m = stoi(st.nextToken());
		h = stoi(st.nextToken());
		
		map = new boolean[h + 2][n + 1];
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			map[stoi(st.nextToken())][stoi(st.nextToken())] = true;
		}
		
		if(removeBranch()) {			//가지치기
			System.out.println("-1");
			return;
		}
		
		for (int ladderSize = 0; ladderSize <= 3; ladderSize++)
			arrangeLadder(1, 1, 0, ladderSize);

		System.out.println("-1");
	}

	private static boolean removeBranch() {
		int cnt, totalCnt = 0;
		for (int j = 1; j < n + 1; j++) {
			cnt = 0;
			for (int i = 1; i < h + 2; i++)
				if (map[i][j]) cnt++;
			
			if (cnt % 2 == 1) totalCnt++; //열마다 사다리 개수가  홀수개인 열의 개수를 센다
		}
		if (totalCnt > 3) return true;
		else return false;
	}
	
	private static void arrangeLadder(int x, int y, int cnt, int size) {
		if (cnt == size) {
			if (ladder()) {
				System.out.println(size);
				System.exit(0);
			}
			return;
		}
		
		int j = y;
		for (int i = x; i < h + 1; i++) {
			while (j <= n - 1) {
				if (map[i][j + 1]) j += 3;
				else if (map[i][j]) j += 2;
				else if (map[i][j - 1]) j++;
				else {	//나,좌우 모두 안칠해져있으면
					map[i][j] = true;
					arrangeLadder(i, j + 2, cnt + 1, size);
					map[i][j] = false;
					j++;
				}
			}
			j = 1;
		}
	}

	private static boolean ladder() {
		int i, tempy;
		
		for (int j = 1; j < n; j++) {
			i = 0;
			tempy = j;
			
			while (i < h + 2) {
				if (map[i][tempy - 1])	//왼쪽에 가로줄이 있다면 왼쪽으로 이동
					tempy--;
				else if (map[i][tempy])	//오른쪽에 가로줄이 있다면
					tempy++;

				i++;
			}
			if (tempy != j) return false;
		}
		return true;
	}
	
	public static int stoi(String str) {
		return Integer.parseInt(str);
	}
}