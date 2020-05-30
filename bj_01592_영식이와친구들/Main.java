package bj_01592_영식이와친구들;

import java.util.Scanner;

public class Main {
	static int n, m, l, arr[];
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		n = s.nextInt();
		m = s.nextInt();
		l = s.nextInt();
		arr = new int[n + 1];
		
		int my = 1, cnt = 0;
		arr[1]++;
		while (check()) {
			++cnt;
			if (arr[my] % 2 == 0) {
				my = my - l + n;
				if (my > n) my -= n;
			} else { 
				my = my + l - n;
				if (my <= 0) my += n;
			}
			arr[my]++;
		}
		System.out.println(cnt);
	}

	private static boolean check() {
		for (int i = 1; i <= n; i++) {
			if (arr[i] == m) return false;
		}
		return true;
	}
}
