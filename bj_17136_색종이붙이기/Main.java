package bj_17136_색종이붙이기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static class Node {
		int x, y;
		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	private static int minPaper = Integer.MAX_VALUE, paper[];
	private static boolean[][] map, visit;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		paper = new int[] {5, 5, 5, 5, 5};
		map = new boolean[10][10];
		visit = new boolean[10][10];
		
		for (int i = 0; i < 10; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 10; j++)
				if (Integer.parseInt(st.nextToken()) == 1)
					map[i][j] = true;
				else 
					map[i][j] = false;
		}
		
		dfs(0, 0);
		
		if (minPaper == Integer.MAX_VALUE)
			System.out.println("-1");
		else
			System.out.println(minPaper);
	}

	//이거랑 findStart 둘다 매개변수 필요 없다! 
	private static void dfs(int x, int y) {
		Node temp = findStart(x, y);
		if (temp.x == -1 && temp.y == -1) {
			checkPaper();
			return;
		}

		int nx = temp.x;
		int ny = temp.y;
		
		for (int k = 5; k >= 1; k--) {
			if (paper[k - 1] > 0) {
				paper[k - 1]--;
				if (fill(nx, ny, k)) {
					dfs(nx, ny);
					remove(nx, ny, k);
				}
				paper[k - 1]++;
			}
		}
	}

	private static void checkPaper() {
		//findStart를 매번 (0, 0) 부터 돌게 하면 이것도 필요 없다!
		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				if (map[i][j] != visit[i][j])
					return;

		int min = 0;
		for (int i = 0; i < 5; i++)
			min += (5 - paper[i]);
		
		if (minPaper > min) minPaper = min;
	}

	private static void remove(int x, int y, int k) {
		for (int i = 0; i < k; i++)
			for (int j = 0; j < k; j++)
				visit[x + i][y + j] = false;
		
	}

	private static boolean fill(int x, int y, int k) {
		for (int i = 0; i < k; i++)
			for (int j = 0; j < k; j++)
				if (x + i >= 10 || y + j >= 10 || !map[x + i][y + j] || visit[x + i][y + j])
					return false;
		
		for (int i = 0; i < k; i++)
			for (int j = 0; j < k; j++)
				visit[x + i][y + j] = true;
		
		return true;
	}

	private static Node findStart(int x, int y) {
		for (int i = x; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (i == x && j < y) continue;
				if (map[i][j] && !visit[i][j])
					return new Node(i, j);
			}
		}
		return new Node(-1, -1);
	}
}