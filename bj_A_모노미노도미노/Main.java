package bj_A_모노미노도미노;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

	static boolean[][] green, blue;
	static int result = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		init();
		int N = stoi(st.nextToken());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int kind = stoi(st.nextToken());
			int x = stoi(st.nextToken());
			int y = stoi(st.nextToken());

			switch (kind) {
			case 1:
				shift(move(green, x, y, x, y, false), green);
				shift(move(blue, y, x, y, x, false), blue);
				break;
			case 2:
				shift(move(green, x, y + 1, x, y, true), green);
				shift(move(blue, y + 1, x, y, x, true), blue);
				break;
			case 3:
				shift(move(green, x + 1, y, x, y, true), green);
				shift(move(blue, y, x + 1, y, x, true), blue);
				break;
			}

			check(green);
			check(blue);
//			print();
		}
		System.out.println(result);
		System.out.println(calc());
	}

	private static void check(boolean[][] map) {
		int count = 0;
		while ((count = checkLine(map)) != 0) {
			result += count;
			downFall(map);
		}
	}

	private static void init() {
		green = new boolean[7][4];
		blue = new boolean[7][4];
	}

	private static int move(boolean[][] map, int x1, int y1, int x2, int y2, boolean flag) {
		if(flag) {
			x1 = x1 == x2 ? 0 : 1;
		} else {
			x1 = 0;
		}
		x2 = 0;

		while (x1 < 6 && x2 < 6 && !map[x1 + 1][y1] && !map[x2 + 1][y2]) {
			x1++;
			x2++;
		}
		map[x1][y1] = true;
		map[x2][y2] = true;
		return Math.min(x1, x2);
	}

	private static void shift(int num, boolean[][] map) {
		if(num == 0) {
			System.out.println("!!!!");
		}
		if (num >= 3) {
			return;
		}
		for (int t = num; t <= 2; t++) {
			for (int i = 6; i > 0; i--) {
				for (int j = 0; j < 4; j++) {
					map[i][j] = map[i - 1][j];
				}
			}
		}
	}

	private static void downFall(boolean[][] map) {
		for (int j = 0; j < 4; j++) {
			for (int i = 6; i >= 1; i--) {
				boolean flag = false;
				while (!map[i][j]) {
					for (int idx = i; idx >= 1; idx--) {
						map[idx][j] = map[idx - 1][j];
						if (map[idx][j]) {
							flag = true;
						}
					}
					if (!flag) {
						break;
					}
				}
			}
		}
	}

	private static int checkLine(boolean[][] map) {
		int count = 0;
		for (int i = 1; i < 7; i++) {
			if (map[i][0] && map[i][1] && map[i][2] && map[i][3]) {
				count++;
				map[i][0] = false;
				map[i][1] = false;
				map[i][2] = false;
				map[i][3] = false;
			}
		}
		return count;
	}

	private static int calc() {
		int count = 0;
		for (int i = 1; i < 7; i++) {
			for (int j = 0; j < 4; j++) {
				if (green[i][j]) {
					count++;
				}
			}
		}
		for (int j = 0; j < 4; j++) {
			for (int i = 1; i < 7; i++) {
				if (blue[i][j]) {
					count++;
				}
			}
		}
		return count;
	}

	private static void print() {
		System.out.println(" green : ");
		for (int i = 1; i < 7; i++) {
			for (int j = 0; j < 4; j++) {
				System.out.print(green[i][j] ? "■" : "□");
			}
			System.out.println();
		}
		System.out.println("\n blue : ");
		for (int j = 0; j < 4; j++) {
			for (int i = 1; i < 7; i++) {
				System.out.print(blue[i][j] ? "■" : "□");
			}
			System.out.println();
		}
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}

}
