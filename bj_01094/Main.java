package bj_01094;

import java.util.Scanner;

public class Main {
	static int x;
	static int min = 64;	//가장짧은것의 길이
	static int sum = 64;
	static int num = 1;		//막대의 개수
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		x = s.nextInt();
		slide();
	}

	private static void slide() {
		if (sum == x) {
			System.out.println(num);
		} else if (sum > x) {
			min /= 2;
			num++;
			if (sum - min >= x) {
				sum -= min;
				num--;
			}
			slide();
		}
	}
}
