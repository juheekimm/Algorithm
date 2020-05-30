package bj_03109_빵집;

import java.io.*;
import java.util.*;

public class Main {
	static private class Node implements Comparable<Node> {
		int num, count;

		Node(int num, int count) {
			this.num = num;
			this.count = count;
		}

		@Override
		public int compareTo(Node n) {
			int result = this.count - n.count;
			if (result == 0)
				result = this.num - n.num;
			return result;
		}
	}

	static int r, c, k, rSize = 3, cSize = 3, tempSize;
	static int[][] map = new int[102][102];
	static int[] count = new int[101];
	static List<Node> list = new LinkedList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}

		for (int time = 0; time <= 100; time++) {
			tempSize = 0;
			if (rSize >= cSize)
				rSort();
			else
				cSort();

			
			if (map[r][c] == k) {
				System.out.println(time);
				return;
			}
			Arrays.fill(count, 0);
		}
		System.out.println(-1);
	}

	private static void print() {
		for (int i = 0; i < 102; i++) {
			for (int j = 0; j < 102; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("------------------------");
	}

	private static void rSort() {
		for (int i = 0; i < rSize; i++) {
			for (int j = 0; j < cSize; j++)
				count[map[i]
						[j]]++;
			counting();
			sortArr(i, true);
			print();
			list.clear();
		}
		rSize = tempSize;
	}

	private static void cSort() {
		for (int j = 0; j < cSize; j++)
			for (int i = 0; i < rSize; i++) {
				count[map[i][j]]++;
			counting();
			sortArr(j, false);
			list.clear();
		}
		cSize = tempSize;
	}

	private static void sortArr(int idx, boolean rFlag) {
		int i, size = list.size();

		if (rFlag) {
			if (size >= 50) {
				for (i = 0; i < 50; i += 2) {
					map[idx][i] = list.get(i).num;
					map[idx][i + 1] = list.get(i).count;
				}
				tempSize = 100;
			} else {
				for (i = 0; i < size; i += 2) {
					map[idx][i] = list.get(i).num;
					map[idx][i + 1] = list.get(i).count;
				}

				if (map[idx][i] != 0) {
					while (i < 100 && map[idx][i] != 0)
						map[idx][i++] = 0;
				}
				if (tempSize < size * 2)
					tempSize = size * 2;
			}
		} else {
			if (size >= 50) {
				for (i = 0; i < 50; i += 2) {
					map[i][idx] = list.get(i).num;
					map[i + 1][idx] = list.get(i).count;
				}
				tempSize = 100;
			} else {
				for (i = 0; i < size; i += 2) {
					map[i][idx] = list.get(i).num;
					map[i + 1][idx] = list.get(i).count;
				}

				if (map[i][idx] != 0) {
					while (i < 100 && map[idx][i] != 0)
						map[i++][idx] = 0;
				}
				if (tempSize < size * 2)
					tempSize = size * 2;
			}
		}
	}

	private static void counting() {
		for (int i = 1; i <= 100; i++)
			list.add(new Node(i, count[i]));
		Collections.sort(list);
	}

}
