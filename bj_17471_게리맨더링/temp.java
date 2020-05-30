package bj_17471_게리맨더링;

import java.io.*;
import java.util.*;

public class temp {
	static int N, M, SR, SC, TIME, CNT, NEXTKILL, PRECNT;
	static int[][] map;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		int max = 0, temp = -1;
		boolean flag = false;

		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; ++j) {
				map[i][j] = Integer.parseInt(st.nextToken());

				if (map[i][j] != 0) {
					if (temp == -1) {
						temp = map[i][j];
					} else {
						if (temp != map[i][j])
							flag = true;
					}
				}

				if (max < map[i][j]) {
					max = map[i][j];
					SR = i;
					SC = j;
				}
				if (map[i][j] != 0) {
					CNT++;
				}
			}
		}
		if (!flag) {
			System.out.println(0);
			System.exit(0);
		}
		while (CNT > 1) {
			NEXTKILL = 0;
			PRECNT = CNT;
			// 가장 높은 위치에서 시작
			if (map[SR][SC] <= 0)
				break;
			bfs(SR, SC);
			if (PRECNT - NEXTKILL != CNT) {
				break;
			}
			TIME++;
			// 다음 죽는 애랑 지금 CNT 다르면
		}
		System.out.println((CNT == 0 || PRECNT == CNT) ? 0 : TIME);
		br.close();
	}

	static boolean isPartitioned;

	private static void bfs(int r, int c) {
		// 다음턴에 없어질애들 숫자를 가지고 있다가
		// 이번에 탐색한 애들의 숫자가 없어지는애들보다 적으면 뜯겨나갔다고 판단할 수 있어
		Queue<int[]> q = new LinkedList<int[]>();
		boolean[][] visited = new boolean[N][M];
		
		visited[r][c] = true;
		q.offer(new int[] { r, c });
		map[r][c]--;
		int cnt = 1;
		while (!q.isEmpty()) {
			int[] temp = q.poll();
			int rr = temp[0];
			int cc = temp[1];
			for (int dir = 0; dir < 4; ++dir) {
				int nr = rr + dr[dir];
				int nc = cc + dc[dir];
				if (nr >= 0 && nr < N && nc >= 0 && nc < M && !visited[nr][nc] && map[nr][nc] > 0) {
					visited[nr][nc] = true;
					if (map[nr][nc] == 1)
						NEXTKILL++;
					else
						cnt++;
					map[nr][nc]--;
					q.add(new int[] { nr, nc });
				}
			}

		}
		CNT = cnt;
		for (int i = 0; i < N; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
		System.out.println("CNT" + CNT);
		System.out.println("PRECNT " + PRECNT);
		System.out.println("NEXTKILL " + NEXTKILL);
		System.out.println();
	}
}