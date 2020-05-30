package bj_17144_미세먼지안녕;

import java.util.Arrays;

public class test {

	public static void main(String[] args) {
		int[] a1 = new int[] {33, 62, 46, 34, 6, 22, 76, 25, 77, 68, 71, 47, 19, 1, 41, 93, 80, 36, 92, 91};
		int[] a2 = new int[] {23, 26, 38, 3, 88, 56, 99, 14, 81, 18, 63, 32, 16, 8, 28, 85, 95, 11, 74, 7};
		int size = a1.length;
		
		Arrays.sort(a1);
		Arrays.sort(a2);
		
		System.out.println(Arrays.toString(a1));
		System.out.println(Arrays.toString(a2));
		
		int cnt = 0;
		for (int i = 1; i <= size; i++) {
			for (int j = 1; j <= size; j++) {
				if (a2[size-j] != -1)
				if (a1[size - i] > a2[size - j]) {
					a2[size-j] = -1;
					cnt++;
					
					break;
				}
				
			}
		}
		System.out.println(cnt);
	}

}
