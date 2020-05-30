package bj_15649_N과M1;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		int m = s.nextInt();
		int[] arr = new int[m];
		boolean[] check = new boolean[n];
		permu(n, m, 0, arr, check);
	}

	private static void permu(int n, int m, int k, int[] arr, boolean[] check) {
		if (m == k) {
			for (int i = 0; i < m; i++)
				System.out.print(arr[i] + " ");
			System.out.println();
			return;
		}
		
		for (int i = 1; i <= n; i++) {
			if (check[i - 1]) continue;
			
			check[i - 1] = true;
			arr[k] = i;
			permu(n, m, k + 1, arr, check);
			check[i - 1] = false;
			arr[k] = 0;	//이건 사실 안해줘도 되긴 함
		}
	}
}
