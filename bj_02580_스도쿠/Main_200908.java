package bj_02580_스도쿠;

import java.io.*;
import java.util.*;

public class Main_200908 {
	
	static class Node {
		int x, y;
		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static int N, map[][], visit[][][];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		map = new int[9][9];
		for (int i = 0; i < 9; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		visit = new int[9][9][10];
		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++)
				if (map[i][j] != 0)
					marking(i, j, map[i][j], 1);
		
		backTracking(0);
	}

	private static void marking(int x, int y, int val, int count) {
		int xStart = x / 3 * 3;
		int yStart = y / 3 * 3;

		for (int k = 0; k < 9; k++) {
			visit[x][k][val] += count;
			visit[k][y][val] += count;
		}
		
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				visit[xStart + i][yStart + j][val] += count;
	}

	private static boolean backTracking(int num) {
		Node temp = findStart(num / 9);
		int x = temp.x;
		int y = temp.y;
		if (temp.x == -1) {
			print();
			return true;
		}
		
		for (int i = 1; i <= 9; i++) {
			if (visit[x][y][i] > 0) continue;
			
			marking(x, y, i, 1);
			map[x][y] = i;
			if (backTracking(x * 9 + y + 1)) return true;
			
			marking(x, y, i, -1);
			map[x][y] = 0;
		}
		return false;
	}

	private static void print() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++)
				sb.append(map[i][j] + " ");
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static Node findStart(int x) {
		for (int i = x; i < 9; i++)
			for (int j = 0; j < 9; j++)
				if (map[i][j] == 0)
					return new Node(i, j);
		return new Node(-1, -1);
	}
}