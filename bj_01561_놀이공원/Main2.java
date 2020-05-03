package bj_01561_놀이공원;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main2 {
	
	private static int N, M, arr[];
	

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[M];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		System.out.println(getNum(search()));
	}


	private static int getNum(int time) {
		int count = 0;
		for (int i = 0; i < M; i++)
			count += (time - 1) / arr[i];
		
		System.out.println("Ccc " + count);
		
		for (int i = 0; i < M; i++) {
			System.out.println(arr[i] + " " + ((time - 2) % arr[i]));
			if ((time - 2) % arr[i] == 0)
				count++;
			if (count == N)
				return i + 1;
			System.out.println(arr[i] + " 초 " + count);
		}
		return 1;
	}


	private static int search() {
		long left = 0, right = Integer.MAX_VALUE, mid = 0, count;
		long result = 0;
		while (left < right) {
			mid = (left + right) >> 1;
			count = getCount(mid);
			
			if (isPossible(mid)) {
				left = mid + 1;
				result = Math.max(result, mid);
			} else
				right = mid;
		}
		return (int) result;
	}
	
	private static boolean isPossible(long time) {
		long allSum = 0;
		
		for(int i = 0; i < M; i++) {
			allSum += time / arr[i];
		}
		return allSum <= N;
	}


	private static long getCount(long mid) {
		long count = 0;
		for (int i = 0; i < M; i++)
			count += mid / arr[i];
		return count;
	}
}

















