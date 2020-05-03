package bj_01484_다이어트;

import java.util.Scanner;

public class Main {
	
	private static int G;

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		G = s.nextInt();
		twoPointer();
	}

	private static void twoPointer() {
		StringBuilder sb = new StringBuilder();
		int s = 1, e = 1;
		
		while (true) {
			if (Math.pow(e, 2) - Math.pow(s, 2) >= G) s++;
			else if (Math.pow(e, 2) - Math.pow(e - 1, 2) > G) break;
			else e++;
			
			if (Math.pow(e, 2) - Math.pow(s, 2) == G)
				sb.append(e + "\n");
		}
		
		if (sb.length() == 0) 
			System.out.println(-1);
		else 
			System.out.println(sb);
	}
}