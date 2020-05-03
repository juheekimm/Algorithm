package bj_17136_색종이붙이기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_novisit {
	private static class Node {
		int x, y;
		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	private static int minPaper = Integer.MAX_VALUE, paper[], map[][];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		paper = new int[] {5, 5, 5, 5, 5};
		map = new int[10][10];
		
		for (int i = 0; i < 10; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 10; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		backTraking();
		
		if (minPaper == Integer.MAX_VALUE)
			System.out.println("-1");
		else
			System.out.println(minPaper);
	}

	private static void backTraking() {
		Node temp = findStart();
		if (temp.x == -1 && temp.y == -1) {
			countPaper();
			return;
		}

		int nx = temp.x;
		int ny = temp.y;
		
		for (int k = 5; k >= 1; k--) {
			if (paper[k - 1] > 0) {
				paper[k - 1]--;
				if (isPossible(nx, ny, k)) {
					fill(nx, ny, k);
					backTraking();
					fill(nx, ny, k);
				}
				paper[k - 1]++;
			}
		}
	}

	private static void countPaper() {
		int min = 0;
		for (int i = 0; i < 5; i++)
			min += (5 - paper[i]);
		
		if (minPaper > min) minPaper = min;
	}

	private static void fill(int x, int y, int k) {
		for (int i = 0; i < k; i++)
			for (int j = 0; j < k; j++)
				map[x + i][y + j] *= -1;
	}

	private static boolean isPossible(int x, int y, int k) {
		for (int i = 0; i < k; i++)
			for (int j = 0; j < k; j++)
				if (x + i >= 10 || y + j >= 10 || map[x + i][y + j] != 1)
					return false;
		
		return true;
	}

	private static Node findStart() {
		//무조건 완탐이니까 x, y는 필요 없다!
		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				if (map[i][j] == 1)
					return new Node(i, j);

		return new Node(-1, -1);
	}
}