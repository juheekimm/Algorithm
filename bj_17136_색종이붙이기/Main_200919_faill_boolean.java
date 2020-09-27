package bj_17136_색종이붙이기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_200919_faill_boolean {
	
	static class Node {
		int x, y;
		Node (int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static int paper[], map[][];
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
		
		if (!backTracking(0)) {
			System.out.println(-1);
		} else {
			int count = 0;
			for (int size = 1; size <= 5; size++)
				count += paper[size];
			System.out.println(25 - count);
		}
	}

	private static boolean backTracking(int startN) {
		Node temp = findStart(startN);
		int x = temp.x;
		int y = temp.y;
		if (x == -1)
			return successCheck();
		
		for (int size = 1; size <= 5; size++) {
			if (paper[size] == 0) continue;
			
			if (enableCheck(x, y, size)) {
				paper[size]--;
				drawer(x, y, size, true);
				if (backTracking(x)) return true;
				paper[size]++;
				drawer(x, y, size, false);
			}
		}
		return false;
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

	private static Node findStart(int x) {
		for (int i = x; i < 10; i++)
			for (int j = 0; j < 10; j++)
				if (map[i][j] == 1 && !visit[i][j])
					return new Node(i, j);
		
		return new Node(-1, -1);
	}
}














