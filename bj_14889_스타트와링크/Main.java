package bj_14889_스타트와링크;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	private static int N, minDif = Integer.MAX_VALUE, score[], number[], map[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		number = new int[N];
		Arrays.fill(number, N / 2, N, 1);
		
		score = new int[2];
		do {
			scoreCalc();
			if (minDif == 0) break;
		} while (nextCombination());
		
		System.out.println(minDif);
	}

	private static void scoreCalc() {
		Arrays.fill(score, 0);
		
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				if (i != j)
					if (number[i] == number[j])
						score[number[i]] += map[i][j];
		
		if (minDif > Math.abs(score[0] - score[1]))
			minDif = Math.abs(score[0] - score[1]);
	}

	private static boolean nextCombination() {
		int i = N - 2;
		while (i > 0 && number[i - 1] >= number[i]) i--;
		if (i == 0) return false;
		
		int j = N - 2;
		while (number[i - 1] >= number[j]) j--;
		swap(i - 1, j);
		
		j = N - 2;
		while (i < j) swap(i++, j--);
		
		return true;
	}

	private static void swap(int i, int j) {
		int temp = number[i];
		number[i] = number[j];
		number[j] = temp;
	}
}