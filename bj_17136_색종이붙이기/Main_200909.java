package bj_17136_색종이붙이기;

import java.io.*;
import java.util.*;

public class Main_200909 {
	
	static class Node {
		int x, y;
		Node (int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static int min = Integer.MAX_VALUE, paper[], map[][];
	static boolean visit[][];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		map = new int[10][10];
		visit = new boolean[10][10];
		paper = new int[]{0, 5, 5, 5, 5, 5};
				
		for (int i = 0; i < 10; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 10; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		backTracking();
		if (min == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(min);
	}

	private static void backTracking() {
		Node temp = findStart();
		int x = temp.x;
		int y = temp.y;
		if (x == -1) {
			if (successCheck()) {
				int count = 0;
				for (int size = 1; size <= 5; size++)
					count += paper[size];
				min = Math.min(min, 25 - count);
			}
			return;
		}
		for (int size = 1; size <= 5; size++) {
			if (paper[size] == 0) continue;
			
			if (enableCheck(x, y, size)) {
				paper[size]--;
				drawer(x, y, size, true);
				backTracking();
				paper[size]++;
				drawer(x, y, size, false);
			}
		}
	}

	private static void drawer(int x, int y, int size, boolean isDraw) {
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				visit[x + i][y + j] = isDraw;
		
	}

	private static boolean enableCheck(int x, int y, int size) {
		if (x + size - 1 >= 10 || y + size - 1 >= 10)
			return false;
		
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				if (visit[x + i][y + j] || map[x + i][y + j] == 0)
					return false;
		
		return true;
	}

	private static boolean successCheck() {
		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				if (map[i][j] == 1 && !visit[i][j])
					return false;
		
		return true;
	}

	private static Node findStart() {
		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				if (map[i][j] == 1 && !visit[i][j])
					return new Node(i, j);
		
		return new Node(-1, -1);
	}
}