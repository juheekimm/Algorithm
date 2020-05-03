package bj_01561_놀이공원;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	private static int childNum, M, arr[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		childNum = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[M];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++)
			arr[i] = Integer.parseInt(st.nextToken());

		if (childNum - M <= 0)
			System.out.println(childNum);
		else
			System.out.println(getNum(search()));
	}

	private static int getNum(int time) {
		int count = M;
		for (int i = 0; i < M; i++)
			count += (time - 1) / arr[i];
		
		for (int i = 0; i < M; i++) {
			if ((time) % arr[i] == 0)
				count++;
			if (count == childNum)
				return i + 1;
		}
		return 0;
	}

	private static int search() {
		long left = 0, right = childNum << 4, mid = 0;
		long result = right;
		while (left < right) {
			mid = (left + right) >> 1;

			if (isPossible(mid)) {
				right = mid;
				result = Math.min(result, mid);
			} else {
				left = mid + 1;
			}
		}
		return (int) result;
	}

	private static boolean isPossible(long time) {
		long allSum = 0;

		for (int i = 0; i < M; i++)
			allSum += time / arr[i];

		return allSum >= childNum - M;
	}
}