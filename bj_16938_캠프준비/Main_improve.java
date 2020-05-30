package bj_16938_캠프준비;

import java.util.Scanner;

public class Main_improve {
	static int n, l, r, x, count = 0;
	static int[] level;
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		n = s.nextInt();
		l = s.nextInt();
		r = s.nextInt();
		x = s.nextInt();
		level = new int[n];
		
		for (int i = 0; i < n; i++)
			level[i] = s.nextInt();

		dfs(0, Integer.MAX_VALUE, -1, 0, 0);
		System.out.println(count);
	}

	private static void dfs(int idx, int min, int max, int sum, int cnt) {
		if (cnt >= 2) {
			if (r < sum) { //합이 최대보다 커져버리면 return;
				return;
			} else { //합이 최대보다 작거나 같고
				if (l <= sum && max - min >= x) { //최소보다 크면서 차이 조건도 만족하면
					count++;
				}
			}
		}
		
		for (int i = idx; i < level.length; i++)
			dfs(i + 1, Math.min(min, level[i]), Math.max(max, level[i]), sum + level[i], cnt + 1);
	}
}
