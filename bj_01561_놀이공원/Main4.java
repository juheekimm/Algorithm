package bj_01561_놀이공원;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main4 {

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

	private static int getNum(long time) {
		long count = M;
//		loop: while (true) {
//			for (int i = 0; i < M; i++) {
//				count += time / arr[i];
//				if (count >= childNum) {
//					rslt = i + 1;
//					break loop;
//				}
////				System.out.println("count" + count);
//			}
//		}

		count = M;
		for (int i = 0; i < M; i++) {
			count += (time - 1) / arr[i];
			System.out.println("countttt" + count);
		}
		
		for (int i = 0; i < M; i++) {
			if ((time) % arr[i] == 0)
				count++;
			if (count == childNum)
				return i + 1;
			System.out.println("i " + i + " count " + count);
		}
		System.out.println("김준호바보 ");
		return 0;
	}

	private static long search() {
		long left = 0, right = Long.MAX_VALUE>>1, mid = 0;
		long result = right;
		while (left < right) {
			mid = (left + right) >> 1;

			if (isPossible(mid)) {
				System.out.println("쉽가");
				right = mid;
				result = Math.min(result, mid);
			} else {
				left = mid + 1;
				System.out.println("ㄴㄴ;;");
			}
		}
		System.out.println(">>>>>>" + result);
		return result;
	}

	private static boolean isPossible(long time) {
		System.out.print(time + " 일때");
		long allSum = 0;

		for (int i = 0; i < M; i++) {
			allSum += time / arr[i];
		}
		System.out.println("allSum = " + allSum + " 가능?");
		return allSum >= childNum - M;
	}
}