package bj_017136_색종이붙이기;

import java.io.*;
import java.util.StringTokenizer;

//9시 30분
public class Main2 {
	static int size, square[], map[][], sizemap[][], tempmap[][];
	// 오른쪽, 아래쪽, 대각선오른쪽아래
	static int[] dx = { 0, 1, 1 };
	static int[] dy = { 1, 0, 1 };
	static boolean[][] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		map = new int[10][10];
		sizemap = new int[10][10];
		tempmap = new int[10][10];
		visit = new boolean[10][10];
		square = new int[] { 0, 5, 5, 5, 5, 5 };
		for (int i = 0; i < 10; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 10; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		findStartPoint();
		print();
		minSquareFindDfs();
//      visitprint();
	}

	private static void findStartPoint() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (map[i][j] == 1) {
					size = 1;
//               visit[i][j] = true;
					squareSizeCheck(i, j, 1);

					sizemap[i][j] = size;
//               System.out.println(size);
				}
			}
		}
	}

	private static boolean squareSizeCheck(int x, int y, int sizeUp) {
		int nx, ny;
		// 오른쪽 체크
		for (int s = 0; s < sizeUp; s++) {
			nx = x + s;
			ny = y + sizeUp;

			if (nx > 10 || ny > 10)
				continue;
			if (map[nx][ny] == 0)
				return false;
		}
		// 아래쪽 체크
		for (int s = 0; s < sizeUp; s++) {
			nx = x + sizeUp;
			ny = y + s;

			if (nx > 10 || ny > 10)
				continue;
			if (map[nx][ny] == 0)
				return false;
		}

		// 대각선 체크, 사이즈체크는 위에서 다 했으므로 여기서는 괜찮음.
		if (map[x + sizeUp][y + sizeUp] == 0)
			return false;

		// 다 통과했다면
//      for (int s = 0; s < sizeUp; s++) {
//         visit[x + s][y + sizeUp] = true;
//         visit[x + sizeUp][y + s] = true;
//      }
//      visit[x + sizeUp][y + sizeUp] = true;

		size = sizeUp + 1;
		squareSizeCheck(x, y, sizeUp + 1);
		return true;
	}

	private static void minSquareFindDfs() {
		int cnt;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {

				if (sizemap[i][j] != 0) {
					cnt = -1;
					while (++cnt < sizemap[i][j]) {
						if (!visit[i][j]) {
							tempmap[i][j] = cnt + 1;
							visit[i][j] = true;
							for (int s = 0; s < cnt; s++) {
								visit[i + s][j + cnt] = true;
								visit[i + cnt][j + s] = true;
							}
							visit[i + cnt][j + cnt] = true;

							minSquareFindDfs();

							// 백트래킹
							tempmap[i][j] = 0;
							visit[i][j] = false;
							for (int s = 0; s < cnt; s++) {
								visit[i + s][j + cnt] = false;
								visit[i + cnt][j + s] = false;
							}
							visit[i + cnt][j + cnt] = false;
						}
					}

				}

			}
		}
//      visitprint();
//      print();
		return;
	}

	private static void print() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				System.out.printf("%d ", sizemap[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}

	private static void visitprint() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (visit[i][j])
					System.out.print("1 ");
				else
					System.out.print("0 ");
			}
			System.out.println();
		}
		System.out.println();
	}
}