package bj_19237_어른상어;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_201016 {

	static class Shark {
		int x, y, dir, px, py;

		public Shark(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int time, N, M, K, map[][], tempMap[][], sharkCount, pDir[][][], sharkType[][];
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static Shark[] sharks;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		K = stoi(st.nextToken());

		map = new int[N][N];
		tempMap = new int[N][N];
		sharkType = new int[N][N];
		sharks = new Shark[M + 1];
		pDir = new int[M + 1][4][4];
		sharkCount = M;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = stoi(st.nextToken());
				if (map[i][j] != 0) {
					sharks[map[i][j]] = new Shark(i, j);
					sharkType[i][j] = map[i][j];
					map[i][j] = K;
				}
			}
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= M; i++) {
			int temp = stoi(st.nextToken());
			if (temp == 4)
				temp = 1;
			else if (temp == 1)
				temp = 0;
			sharks[i].dir = temp;
		}

		for (int i = 1; i <= M; i++) {
			for (int j = 0; j < 4; j++) {
				st = new StringTokenizer(br.readLine());
				int tempj = j;
				if (j == 1)
					tempj = 2;
				else if (j == 2)
					tempj = 3;
				else if (j == 3)
					tempj = 1;

				for (int k = 0; k < 4; k++) {
					int temp = stoi(st.nextToken());
					if (temp == 4)
						temp = 1;
					else if (temp == 1)
						temp = 0;
					pDir[i][tempj][k] = temp;
				}
			}
		}

		time = 0;
		while (time++ <= 1000) {

			for (int i = 0; i < N; i++)
				Arrays.fill(tempMap[i], 0);
			sharkMove();
			afterMoveCheck();
			countDown();
			if (sharkCount == 1)
				break;

			System.out.println(time + "초 ");
			System.out.println("map");
			for (int i = 0; i < N; i++) {
				System.out.println(Arrays.toString(map[i]));
			}
			System.out.println();
			System.out.println("tempmap");
			for (int i = 0; i < N; i++) {
				System.out.println(Arrays.toString(tempMap[i]));
			}
			System.out.println();
			System.out.println("sharkType");
			for (int i = 0; i < N; i++) {
				System.out.println(Arrays.toString(sharkType[i]));
			}
			System.out.println();
			System.out.println("=========");
		}
		System.out.println(time > 1000 ? "-1" : time);
	}

	private static void afterMoveCheck() {
		for (int i = 1; i <= M; i++) {
			if (sharks[i].x == -1)
				continue;

			if (tempMap[sharks[i].x][sharks[i].y] != i) {
				sharkCount--;
				sharks[i].x = -1;
			} else {
				System.out.print(i + "번 상어 방향 ");
				switch (sharks[i].dir) {
				case 0:
					System.out.println("상 ");
					break;
				case 1:
					System.out.println("우 ");
					break;
				case 2:
					System.out.println("하 ");
					break;
				case 3:
					System.out.println("좌 ");
					break;
				}
			}
		}
	}

	private static void sharkMove() {
		for (int i = 1; i <= M; i++) {
			if (sharks[i].x == -1)
				continue;

//			if (allFullCheck(sharks[i])) {
//				for (int d = 0; d < 4; d++) {
//					int nx = sharks[i].x + dx[pDir[i][sharks[i].dir][d]];
//					int ny = sharks[i].y + dy[pDir[i][sharks[i].dir][d]];
//
//					if (nx < 0 || nx >= N || ny < 0 || ny >= N || sharkType[nx][ny] != i)
//						continue;
//					
//					updateInfo(i, nx, ny, pDir[i][sharks[i].dir][d]);
//					break;
//				}
//			} else {
				boolean moveSuccess = false;
				for (int d = 0; d < 4; d++) {
					int nx = sharks[i].x + dx[pDir[i][sharks[i].dir][d]];
					int ny = sharks[i].y + dy[pDir[i][sharks[i].dir][d]];
	
					if (nx < 0 || nx >= N || ny < 0 || ny >= N)
						continue;
	
					if (map[nx][ny] == 0) {
						moveSuccess = true;
						updateInfo(i, nx, ny, pDir[i][sharks[i].dir][d]);
						break;
					} else {
						if (tempMap[nx][ny] != 0) {
							if (tempMap[nx][ny] > i) {
								updateInfo(i, nx, ny, pDir[i][sharks[i].dir][d]);
							}
							moveSuccess = true;
							break;
						}
						// 맵에는 숫자가 있는데 tempmap은 비어있다 -> 지금 맵에 있는 숫자는 이전 턴에 그려진것, 다음 턴에 그리도록 break는 하지
						// 않는다
					}
				}
				
//			}
		}
	}
	
	private static boolean allFullCheck(Shark shark) {
		boolean allFull = true;
		
		for (int d = 0; d < 4; d++) {
			int nx = shark.x + dx[d];
			int ny = shark.y + dy[d];

			if (nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny] != 0)
				continue;
			
			allFull = false;
		}
		
		return allFull;
	}

	private static void updateInfo(int n, int nx, int ny, int d) {
		sharks[n].px = sharks[n].x;
		sharks[n].py = sharks[n].y;
		sharks[n].x = nx;
		sharks[n].y = ny;
		sharks[n].dir = d;
		map[nx][ny] = K + 1;
		sharkType[nx][ny] = n;
		tempMap[nx][ny] = n;
	}

	private static void countDown() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 0)
					continue;
				map[i][j]--;
				if (map[i][j] == 0)
					sharkType[i][j] = 0;
			}
		}
	}

	private static int stoi(String str) {
		return Integer.parseInt(str);
	}
}
