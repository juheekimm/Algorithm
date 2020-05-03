package bj_15684_사디리조작;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main2 {
	
	private static int N, M, H, map[][], permu[], minRow, size;
	private static ArrayList<Integer> need;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		need = new ArrayList<Integer>();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		map = new int[H + 1][N];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			map[a][b] = 1;
			map[a][b + 1] = -1;
		}
//		for (int i = 0; i < H; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
//		System.out.println(">>>>>>");
		
		searchOddNum();
		size = need.size();
		if (size > 3) {
			System.out.println("-1");
		} else {
			permu = new int[size];
//			System.out.println(need.get(0));
//			System.out.println(need.get(1));
//			System.out.println(need.get(2));
			if (permu(size, 0))
				System.out.println(size);
			else
				System.out.println("-1");
		}
	}

	private static boolean permu(int n, int k) {
		if (n == k) {
			if (ladderDraw()) {
//				System.out.println(Arrays.toString(permu));
//				for (int i = 0; i < H; i++) {
//					System.out.println(Arrays.toString(map[i]));
//				}
//				System.out.println();
				if (ladderCheck())
					return true;
				ladderDelete();
			}
			return false;
		}
		
		for (int i = 0; i < H; i++) {
			if (map[i][need.get(k)] == 1 || map[i][need.get(k)] == -1) continue;
			
			permu[k] = i;
			if (permu(n, k + 1)) return true;
			permu[k] = 0;
		}
		
		return false;
	}

	private static boolean ladderCheck() {
		for (int j = 0; j < N; j++) {
			int nx = 0, ny = j, prev = 0;
			
			while (nx <= H) {	
				if (map[nx][ny] == 0) {
					prev = 0;
					nx++;
				} else {
					if (prev != 0) {
						prev = 0;
						nx++;
					} else {
						if (map[nx][ny] == 1) ny++;
						else if (map[nx][ny] == -1) ny--;
						prev = 1;
					}
				}
			}
			
			if (ny != j) return false;
		}
		return true;
	}

	private static boolean ladderDraw() {
		for (int i = 0; i < size; i++) {
			if (map[permu[i]][need.get(i)] != 0 || map[permu[i]][need.get(i) + 1] != 0)
				return false;
		}
		
		for (int i = 0; i < size; i++) {
			map[permu[i]][need.get(i)] = 1;
			map[permu[i]][need.get(i) + 1] = -1;
		}
		return true;
	}

	private static void ladderDelete() {
		for (int i = 0; i < size; i++) {
			map[permu[i]][need.get(i)] = 0;
			map[permu[i]][need.get(i) + 1] = 0;
		}
	}


	private static void searchOddNum() {
		for (int j = 0; j < N - 1; j++) {
			int num = 0;
			for (int i = 0; i < H; i++)
				if (map[i][j] == 1) num++;
			if (num % 2 == 1) need.add(j);
		}
	}
}











