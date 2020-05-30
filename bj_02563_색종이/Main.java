package bj_02563_색종이;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		int[][] map = new int[100][100];
		int n = s.nextInt();
		int r, c;
		for (int t = 0; t < n; t++) {
			r = s.nextInt();
			c = s.nextInt();
			for (int i = r; i < r + 10 && i < 100; i++) {
				for (int j = c; j < c + 10 && j < 100; j++) {
					map[i][j] = 1;
				}
			}
		}
		
		int cnt = 0;
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (map[i][j] == 1)
					cnt++;
			}
		}
		System.out.println(cnt);
		
	}
}
