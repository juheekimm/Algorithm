package bj_01094;

import java.util.Scanner;

public class Main2 {
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
//			System.out.printf("sum은%d 그래서 %d를 반으로 잘라요!\n", sum, min);
			min /= 2; //가장 길이가 짧은걸 반으로 자르기
			num++; //자르면 막대개수 증가
			if (sum - min >= x) { //자른거 버리고 남아있는 합
//				System.out.printf("sum은 %d min은 %d 그래서 빼줬더니 %d!\n", sum, min, sum - min);
				sum -= min;
				num--; //하나 버렸으니까 막대개수 감소
			}
			slide();
		}
	}
}
