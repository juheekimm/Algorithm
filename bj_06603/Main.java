package bj_06603;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int k, LOTTO_NUM = 6;;
	static int[] arr, result;
	static boolean[] isVisit;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			k = Integer.parseInt(st.nextToken());
			if (k == 0) return;
			arr = new int[k];
			result = new int[LOTTO_NUM];
			isVisit = new boolean[k];
			for (int i = 0; i < k; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			lotto(0);
		}
	}
	private static void lotto(int index) {
		if (index == LOTTO_NUM) {
			for (int i = 0; i < LOTTO_NUM; i++) {
				System.out.print(result[i] + " ");
			}
			System.out.println();
			return;
		}
		
		//이건 arr의 길이(6보다 큰)만큼 돌아야해! 그래야 다 넣어주지
		for (int i = 0; i < arr.length; i++) {
			//이미 true면 건너뛴다.
			if (isVisit[i]) continue;
			isVisit[i] = true;
			
			if ((index > 0 && result[index] < arr[i]) || index == 0) {
				result[index] = arr[i];
				lotto(index + 1);
			}
			isVisit[i] = false;
		}
	}
}
