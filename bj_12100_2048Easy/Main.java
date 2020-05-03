package bj_12100_2048Easy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	private static int N, map[][], copyMap[][], maxVal = 0, permu[];
	
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
		System.out.println(maxVal);
	}

	private static void permu(int n, int k) {
		if (n == k) {
			copyMapMake();
			allMove();
			maxCheck();
			if (maxVal == (int)Math.pow(2, 15)) {
				System.out.println(maxVal);
				System.exit(0);
			}
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			permu[k] = i;
			permu(n, k + 1);
			permu[k] = 0;	//안해줘도 되지만 의미 상 
		}
	}

	private static void allMove() {
		for (int i = 0; i < 5; i++)
			move(permu[i]);
	}

	private static void move(int d) {
		if (d == 0) {
			for (int j = 0; j < N; j++) {
				for (int i = 0; i < N; i++) {
					if (copyMap[i][j] != 0) {
						int idx = i;
						while (++i < N && copyMap[i][j] == 0);
						
						if (i < N) {		//값을 합친다면 
							if (copyMap[idx][j] == copyMap[i][j]) {
								copyMap[idx][j] *= 2;
								copyMap[i][j] = 0;
							} else {	//값을 합치지 않는다
								i--;
							}
						}
					}
				}
				push(d, j);
			}
		} else if (d == 1) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (copyMap[i][j] != 0) {
						int idx = j;
						while (++j < N && copyMap[i][j] == 0);
						
						if (j < N) {		//값을 합친다면 
							if (copyMap[i][idx] == copyMap[i][j]) {
								copyMap[i][idx] *= 2;
								copyMap[i][j] = 0;
							} else {	//값을 합치지 않는다
								j--;
							}
						}
					}
				}
				push(d, i);
			}
		} else if (d == 2) {
			for (int j = 0; j < N; j++) {
				for (int i = N - 1; i >= 0; i--) {
					if (copyMap[i][j] != 0) {
						int idx = i;
						while (--i >= 0 && copyMap[i][j] == 0);
						
						if (i >= 0) {		//값을 합친다면 
							if (copyMap[idx][j] == copyMap[i][j]) {
								copyMap[idx][j] *= 2;
								copyMap[i][j] = 0;
							} else {	//값을 합치지 않는다
								i++;
							}
						}
					}
				}
				push(d, j);
			}
		} else if (d == 3) {
			for (int i = 0; i < N; i++) {
				for (int j = N - 1; j >= 0; j--) {
					if (copyMap[i][j] != 0) {
						int idx = j;
						while (--j >= 0 && copyMap[i][j] == 0);
						
						if (j >= 0) {		//값을 합친다면 
							if (copyMap[i][idx] == copyMap[i][j]) {
								copyMap[i][idx] *= 2;
								copyMap[i][j] = 0;
							} else {	//값을 합치지 않는다
								j++;
							}
						}
					}
				}
				push(d, i);
			}
		}
	}

	private static void push(int d, int xy) {
		if (d == 0) {
			for (int i = 0; i < N; i++) {
				if (copyMap[i][xy] == 0) {
					int idx = i;
					while (++i < N && copyMap[i][xy] == 0);
					
					if (i < N) {
						copyMap[idx][xy] = copyMap[i][xy];
						copyMap[i][xy] = 0;
						i = idx;
					}
				}
			}
		} else if (d == 1) {
			for (int j = 0; j < N; j++) {
				if (copyMap[xy][j] == 0) {
					int idx = j;
					while (++j < N && copyMap[xy][j] == 0);
					
					if (j < N) {
						copyMap[xy][idx] = copyMap[xy][j];
						copyMap[xy][j] = 0;
						j = idx;
					}
				}
			}
		} else if (d == 2) {
			for (int i = N - 1; i >= 0; i--) {
				if (copyMap[i][xy] == 0) {
					int idx = i;
					while (--i >= 0 && copyMap[i][xy] == 0);
					
					if (i >= 0) {
						copyMap[idx][xy] = copyMap[i][xy];
						copyMap[i][xy] = 0;
						i = idx;
					}
				}
			}
		} else if (d == 3) {
			for (int j = N - 1; j >= 0; j--) {
				if (copyMap[xy][j] == 0) {
					int idx = j;
					while (--j >= 0 && copyMap[xy][j] == 0);
					
					if (j >= 0) {
						copyMap[xy][idx] = copyMap[xy][j];
						copyMap[xy][j] = 0;
						j = idx;
					}
				}
			}
		}
	}

	private static void maxCheck() {
		int max = 0;
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				if (max < copyMap[i][j])
					max = copyMap[i][j];
		
		if (maxVal < max) maxVal = max;
	}

	private static void copyMapMake() {
		for (int i = 0; i < N; i++)
			copyMap[i] = map[i].clone();
	}
}