package bj_02004_조합0의개수;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int n2, n5, r2, r5;
		n2 = n5 = r2 = r5 = 0;
		
		int num2 = 0, num5 = 0;
		for (int i = 30; i >= 0; i--) {
			if (Math.pow(2, i) <= n) {
				num2 = i;
				break;
			}
		}
		
		for (int i = 13; i >= 0; i--) {
			if (Math.pow(5, i) <= n) {
				num5 = i;
				break;
			}
		}
		
		for (int i = 1; i <= num2; i++) {
//			System.out.println("i가 " + i + "일때 " + (n / (int)Math.pow(2, i) + " " + ((n - m + 1) / (int)Math.pow(2, i))));
			n2 += (n / (int)Math.pow(2, i)) - ((n - m + 1) / (int)Math.pow(2, i));
			if ((n - m + 1) % (int)Math.pow(2, i) == 0) n2++;
		}
		for (int i = 1; i <= num5; i++) {
//			System.out.println("i가 " + i + "일때 " + (n / (int)Math.pow(2, i) + " " + ((n - m + 1) / (int)Math.pow(2, i))));
			n5 += (n / (int)Math.pow(5, i)) - ((n - m + 1) / (int)Math.pow(5, i));
			if ((n - m + 1) % (int)Math.pow(5, i) == 0) n5++;
		}
		for (int i = 1; i <= num2; i++) {
//			System.out.println("i가 " + i + "일때 " + (n / (int)Math.pow(2, i) + " " + ((n - m + 1) / (int)Math.pow(2, i))));
			r2 += (m / (int)Math.pow(2, i));
		}
		
		for (int i = 1; i <= num5; i++) {
//			System.out.println("i가 " + i + "일때 " + (n / (int)Math.pow(2, i) + " " + ((n - m + 1) / (int)Math.pow(2, i))));
			r5 += (m / (int)Math.pow(5, i));
		}
		
		
		
		
		System.out.println(n2 + " " + n5);
		System.out.println(r2 + " " + r5);
		n2 -= r2;
		n5 -= r5;
		
		System.out.println((n2 > n5) ? n5 : n2);
	}
}