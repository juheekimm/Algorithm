package bj_01427_소트인사이드;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_quick {

	static char[] data;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		data = br.readLine().toCharArray();
		int len = data.length;

		quick(0, len - 1);
		for (int i = len - 1; i >= 0; i--)
			System.out.print(data[i]);
	}

	private static void quick(int start, int end) {
		if (start == end)
			return;

		int mid = partition(start, end);
		quick(start, mid - 1);	//R로 바꾸고싶으면 mid로 
		quick(mid, end);
	}

	private static int partition(int L, int R) {
		int pivot = data[(L + R) >> 1];	//pivot을 어디로 잡든 상관 없음 
		//형태를 외워서 똑같이 접목한다 이미 있는 코드를 재활용하는 방식 
		//최선은 pivot이 딱 중앙에 있을때 5/ 5 -> , 최악은 9개 1개 쪼개질때 8 / 1 -> 7/ 1
		//nlogn이최선, 최악은 n^2
		
		//시작 중간 끝점 세개중에 중간에 있는 값을 pivot으로 쓰기 
//		pivot = data[L] data[R] data[(L + R) >> 1];

		while (L <= R) {
			while (data[L] < pivot)
				L++;
			while (pivot < data[R])
				R--;
			if (L <= R) {
				swap(L++, R--);
			}
		}
		return L;
	}

	private static void swap(int a, int b) {
		char temp = data[a];
		data[a] = data[b];
		data[b] = temp;
	}
}