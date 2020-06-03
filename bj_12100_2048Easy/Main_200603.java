package bj_12100_2048Easy;

import java.io.*;
import java.util.*;

public class Main_200603 {
	
	static int N, map[][], copyMap[][], permu[], max = 0;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		copyMap = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		permu = new int[5];
		permu(5, 0);
		
		System.out.println(max);
	}

	private static void permu(int n, int k) {
		if (n == k) {
			simulation();
			return;
		}
		for (int i = 0; i < 4; i++) {
			permu[k] = i;
			permu(n, k + 1);
			permu[k] = -1;	//해줘도 의미는 없는데, 위아래 맞춰주는 형태 적응하기 위해 
		}
	}

	private static void simulation() {
		copyMapMake();
		for (int i = 0; i < 5; i++) {
			pushAndAdd(permu[i]);
			move(permu[i]);
		}
		int now = searchMax();
		if (max < now) max = now;
	}

	private static int searchMax() {
		int max = 0;
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				if (max < copyMap[i][j])
					max = copyMap[i][j];
		return max;
	}

	private static void move(int d) {
		if (d == 0) {
			for (int j = 0; j < N; j++) {
				int i = 0;
				while (i < N) {
					while (i < N && copyMap[i][j] != 0) i++;
					int next = i + 1;
					while (next < N && copyMap[next][j] == 0) next++;
					
					if (i < N && next < N) {
						copyMap[i][j] = copyMap[next][j];
						copyMap[next][j] = 0;
					}
					i++;
				}
			}
		} else if (d == 1) {
			for (int i = 0; i < N; i++) {
				int j = 0;
				while (j < N) {
					while (j < N && copyMap[i][j] != 0) j++;
					int next = j + 1;
					while (next < N && copyMap[i][next] == 0) next++;
					
					if (j < N && next < N) {
						copyMap[i][j] = copyMap[i][next];
						copyMap[i][next] = 0;
					}
					j++;
				}
			}
		} else if (d == 2) {
			for (int j = 0; j < N; j++) {
				int i = N - 1;
				while (i >= 0) {
					while (i >= 0 && copyMap[i][j] != 0) i--;
					int next = i - 1;
					while (next >= 0 && copyMap[next][j] == 0) next--;
					
					if (i >= 0 && next >= 0) {
						copyMap[i][j] = copyMap[next][j];
						copyMap[next][j] = 0;
					}
					i--;
				}
			}
		} else if (d == 3) {
			for (int i = 0; i < N; i++) {
				int j = N - 1;
				while (j >= 0) {
					while (j >= 0 && copyMap[i][j] != 0) j--;
					int next = j - 1;
					while (next >= 0 && copyMap[i][next] == 0) next--;
					
					if (j >= 0 && next >= 0) {
						copyMap[i][j] = copyMap[i][next];
						copyMap[i][next] = 0;
					}
					j--;
				}
			}
		}
	}

	private static void pushAndAdd(int d) {
		if (d == 0) {
			for (int j = 0; j < N; j++) {
				int i = 0;
				while (i < N) {
					while (i < N && copyMap[i][j] == 0) i++;
					int next = i + 1;
					while (next < N && copyMap[next][j] == 0) next++;
					
					if (i < N && next < N && copyMap[i][j] == copyMap[next][j]) {
						copyMap[i][j] *= 2;
						copyMap[next][j] = 0;
						i = next + 1;
					} else {
						i++;
					}
				}
			}
		} else if (d == 1) {
			for (int i = 0; i < N; i++) {
				int j = 0;
				while (j < N) {
					while (j < N && copyMap[i][j] == 0) j++;
					int next = j + 1;
					while (next < N && copyMap[i][next] == 0) next++;
					
					if (j < N && next < N && copyMap[i][j] == copyMap[i][next]) {
						copyMap[i][j] *= 2;
						copyMap[i][next] = 0;
						j = next + 1;
					} else {
						j++;
					}
				}
			}
		} else if (d == 2) {
			for (int j = 0; j < N; j++) {
				int i = N - 1;
				while (i >= 0) {
					while (i >= 0 && copyMap[i][j] == 0) i--;
					int next = i - 1;
					while (next >= 0 && copyMap[next][j] == 0) next--;
					
					if (i >= 0 && next >= 0 && copyMap[i][j] == copyMap[next][j]) {
						copyMap[i][j] *= 2;
						copyMap[next][j] = 0;
						i = next - 1;
					} else {
						i--;
					}
				}
			}
		} else if (d == 3) {
			for (int i = 0; i < N; i++) {
				int j = N - 1;
				while (j >= 0) {
					while (j >= 0 && copyMap[i][j] == 0) j--;
					int next = j - 1;
					while (next >= 0 && copyMap[i][next] == 0) next--;
					
					if (j >= 0 && next >= 0 && copyMap[i][j] == copyMap[i][next]) {
						copyMap[i][j] *= 2;
						copyMap[i][next] = 0;
						j = next - 1;
					} else {
						j--;
					}
				}
			}
		}
	}

	private static void copyMapMake() {
		for (int i = 0; i < N; i++)
			copyMap[i] = map[i].clone();
	}
}