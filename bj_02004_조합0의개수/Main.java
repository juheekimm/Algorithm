package bj_02004_조합0의개수;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int n2, n5, r2, r5;
		n2 = n5 = r2 = r5 = 0;
		
		int temp = 0;
		for (int i = 1; i <= 30; i++) {
			temp = (n / (int)Math.pow(2, i)) - ((n - m + 1) / (int)Math.pow(2, i));
			n2 += temp;
			if ((n - m + 1) % (int)Math.pow(2, i) == 0) n2++;
			if (temp == 0 && (n - m + 1) % (int)Math.pow(2, i) != 0) break;
		}
		for (int i = 1; i <= 13; i++) {
			temp = (n / (int)Math.pow(5, i)) - ((n - m + 1) / (int)Math.pow(5, i));
			n5 += temp;
			if ((n - m + 1) % (int)Math.pow(5, i) == 0) n5++;
			if (temp == 0 && (n - m + 1) % (int)Math.pow(5, i) != 0) break;
		}
		for (int i = 1; i <= 30; i++) {
			temp = (m / (int)Math.pow(2, i));
			r2 += temp;
			if (temp == 0) break;
		}
		for (int i = 1; i <= 13; i++) {
			temp = (m / (int)Math.pow(5, i));
			r5 += temp;
			if (temp == 0) break;
		}

		n2 -= r2;
		n5 -= r5;
		
		System.out.println((n2 > n5) ? n5 : n2);
	}
}