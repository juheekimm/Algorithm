package bj_02869_달팽이는올라가고싶다;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	private static int A, B, V;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
	
		if (A >= V) {
			System.out.println(1);
			return;
		}
		int temp = V - A;
		if (temp % (A - B) == 0)
			System.out.println(temp / (A - B) + 1);
		else
			System.out.println(temp / (A - B) + 2);
	}
}
