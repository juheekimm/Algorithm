package bj_17822_원판돌리기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int n, m, t, arr[][], sum, count;
	static boolean[][] adjoin;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());
		
		arr = new int[n][m];
		adjoin = new boolean[n][m];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}
		
		int cnt = 0;
		while (cnt++ < t) {
			st = new StringTokenizer(br.readLine());

			rotation(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			getSum();
			
			if (adjoinCheck())
				remove();
			else
				adjust();
			
			sum = count = 0;
		}
		
		System.out.println(getSum());
	}

	private static void rotation(int x, int d, int k) {
		for (int i = x - 1; i < n; i += x)
			oneRotation(i, d, k);
	}

	private static void oneRotation(int x, int d, int k) {
		for (int move = 0; move < k; move++) {
			if (d == 0) {
				int temp = arr[x][m - 1];
				for (int j = m - 1; j > 0; j--)
					arr[x][j] = arr[x][j - 1];
				arr[x][0] = temp;
				
			} else {	//반시계방향이면
				int temp = arr[x][0];
				for (int j = 0; j < m - 1; j++)
					arr[x][j] = arr[x][j + 1];
				arr[x][m - 1] = temp;
			}
		}
	}
	
	private static boolean adjoinCheck() {
		boolean flag = false;
		
		for (int i = 0; i < n; i++) {	//양옆 지우기
			for (int j = 0; j < m; j++) {
				if (arr[i][j] != 0 && arr[i][j] == arr[i][(j + 1) % m]) {
					flag = true;
					adjoin[i][j] = true;
					adjoin[i][(j + 1) % m] = true;
				}
			}
		}
		
		for (int j = 0; j < m; j++) {	//위아래 지우기
			for (int i = 0; i < n - 1; i++) {
				if (arr[i][j] != 0 && arr[i][j] == arr[i + 1][j]) {
					flag = true;
					adjoin[i][j] = true;
					adjoin[i + 1][j] = true;
				}
			}
		}
		return flag;
	}
	
	private static void remove() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (adjoin[i][j]) {
					arr[i][j] = 0;
					adjoin[i][j] = false;
				}
			}
		}
	}
	
	private static void adjust() {
		double avg = (double)sum / count;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (arr[i][j] != 0) {
					if (arr[i][j] > avg)
						arr[i][j]--;
					else if (arr[i][j] < avg)
						arr[i][j]++;
				}
			}
		}
	}
	
	private static int getSum() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (arr[i][j] != 0) {
					sum += arr[i][j];
					count++;
				}
			}
		}
		
		if (sum == 0) {
			System.out.println(0);
			System.exit(0);
		}
		return sum;
	}
}