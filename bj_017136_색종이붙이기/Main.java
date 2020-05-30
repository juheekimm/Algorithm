package bj_017136_색종이붙이기;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
	private static class Node {
		int x, y;
		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static int minPaper = Integer.MAX_VALUE, size, paper[], map[][];
	static boolean[][] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		map = new int[10][10];
		visit = new boolean[10][10];
		paper = new int[] { 0, 5, 5, 5, 5, 5 };
		
		for (int i = 0; i < 10; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 10; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		minSquareFindDfs();
		System.out.println(minPaper == Integer.MAX_VALUE ? -1 : minPaper);
	}

	private static Node findStartPoint() {
		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				if (map[i][j] == 1 && !visit[i][j])
					return new Node(i, j);
		
		return new Node(-1, -1);
	}

	private static void minSquareFindDfs() {
		Node temp = findStartPoint();
		if (temp.x == -1) {
			paperCount();
			return;
		}
		
		for (int i = 5; i >= 1; i--) {
			if (isPossible(temp.x, temp.y, i)) {
				minSquareFindDfs();
				visitChange(temp.x, temp.y, i, false);		//백트래킹
			}
		}
	}

	private static void paperCount() {
		int cnt = 0;
		for (int i = 1; i <= 5; i++)
			cnt += paper[i];
		cnt = 25 - cnt;
		
		if (minPaper > cnt) minPaper = cnt;
	}

	private static boolean isPossible(int x, int y, int size) {
		if (paper[size] <= 0) return false;	//그 크기에서 남아있는 색종이가 없으면

		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				if (x + i < 0 || x + i >= 10 || y + j < 0 || y + j >= 10 || map[x + i][y + j] != 1 || visit[x + i][y + j])
					return false;
		
		visitChange(x, y, size, true); //여기까지 왔으면 다 통과한 것이므로 visit체크 해주고
		return true;
	}
	
	private static void visitChange(int x, int y, int size, boolean flag) {
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				visit[x + i][y + j] = flag;
		
		if (flag) paper[size]--;
		else paper[size]++;
	}
}