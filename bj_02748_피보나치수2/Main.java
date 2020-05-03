package bj_02748_피보나치수2;

import java.util.Scanner;

public class Main {

	static long[] fibo = new long[91];
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		
		fibo[0] = 0;
		fibo[1] = 1;
		
		for (int i = 2; i <= n; i++)
			fibo[i] = fibo[i - 2] + fibo[i - 1];
		
		System.out.println(fibo[n]);
	}
}