package bj_01010;
import java.util.Scanner;

public class Main_dp_version {
	static int[][] sum = new int[100][100];
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int taseCaseNum = s.nextInt();
		for (int i = 0; i < taseCaseNum; i++) {
			int n = s.nextInt();
			int m = s.nextInt();
			System.out.println(combination(m, n));
		}
	}
	
	public static int combination(int n, int r) {
		if (sum[n][r] != 0)
			return sum[n][r];
		
		if (n == r || r == 0) {
			return 1;
		} else {
			sum[n][r] = combination(n - 1, r) + combination(n - 1, r - 1);
			return sum[n][r];
		}	
	}
}