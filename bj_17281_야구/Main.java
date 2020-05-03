package bj_17281_야구;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	private static int N, num[][], np[], temp[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		num = new int[N][9];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++)
				num[i][j] = Integer.parseInt(st.nextToken());
		}

		int maxSum = Integer.MIN_VALUE;
		//np는 1번인덱스-8번인덱스까지만 돌리고, 0번인덱스를 3번인덱스 자리에 넣어준
		np = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8};
		do {
			temp = np.clone();
			int t = temp[0];
			temp[0] = temp[1];
			temp[1] = temp[2];
			temp[2] = temp[3];
			temp[3] = t;
			int sum = calc();
			if (maxSum < sum) maxSum = sum;
		} while (nextPermutation());

		System.out.println(maxSum);
	}

	private static int calc() {
		int sum = 0, i = 0;
		for (int in = 0; in < N; in++) {
			
			int out = 0;
			int[] field = new int[4];
			while (out < 3) {
				if (num[in][temp[i]] == 0) {
					out++;
				} else {
					field[0]++;
					sum += move(field, num[in][temp[i]]);
				}
				i = (i + 1) % 9;
			}
		}
		return sum;
	}

	private static int move(int[] field, int num) {
		int sum = 0;
		if (num == 1) {
			sum += field[3];
			for (int i = 2; i >= 0; i--)
				field[i + 1] = field[i];
			field[0] = 0;
		} else if (num == 2) {
			sum += field[2] + field[3];
			for (int i = 1; i >= 0; i--)
				field[i + 2] = field[i];
			field[1] = field[0] = 0;
		} else if (num == 3) {
			sum += field[1] + field[2] + field[3];
			field[3] = field[0];
			field[2] = field[1] = field[0] = 0;
		} else {
			sum += field[0] + field[1] + field[2] + field[3];
			field[3] = field[2] = field[1] = field[0] = 0;
		}
		return sum;
	}

	private static boolean nextPermutation() {
		int i = 9 - 1;
		while (i > 1 && np[i - 1] >= np[i]) i--;
		if (i == 1) return false;
		
		int j = 9 - 1;
		while (np[i - 1] >= np[j]) j--;
		swap(i - 1, j);
		
		j = 9 - 1;
		while (i < j) swap(i++, j--);
		return true;
	}

	private static void swap(int i, int j) {
		int temp = np[i];
		np[i] = np[j];
		np[j] = temp;
	}
}