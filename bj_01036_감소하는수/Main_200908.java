package bj_01036_감소하는수;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main_200908 {
	
	static int count, now, save;
	static Map<Integer, Integer> map = new HashMap<Integer, Integer>();
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		count = s.nextInt();
		
		int numLen = 1;
		while (true) {
			for (int i = 1; i <= 9; i++) {
				save = 0;
				backTracking(numLen, 1, i, i * (int)Math.pow(10, numLen - 1));
				map.put(i * (int)Math.pow(10, numLen - 1), save);
				System.out.println(i * (int)Math.pow(10, numLen - 1) + " " + save);
			}
			numLen++;
		}
		
	}

	private static void backTracking(int numLen, int nowLen, int lastNum, int num) {
		if (numLen == nowLen) {
			now++;
			if (count == now) {
				System.out.println(num);
				System.exit(0);
			}
			return;
		}
		
		for (int addNum = 0; addNum < lastNum; addNum++) {
//			backTracking(numLen, nowLen + 1, addNum, num + addNum * (int)Math.pow(numLen,a));
			System.out.println(num * 10 + addNum);
			save++;
		}
	}
}
