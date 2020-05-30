package bj_03985_롤케이크;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int l = Integer.parseInt(br.readLine());
		int n = Integer.parseInt(br.readLine());
		
		int[] cake = new int[l + 1];
		int[][] arr = new int[n + 1][2];
		int[] cnt = new int[n + 1];
		int expMax = -1, expIdx = -1, realMax = -1, realIdx = -1;
		
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
			if (arr[i][1] - arr[i][0] > expMax) {
				expMax = arr[i][1] - arr[i][0];
				expIdx = i;
			}
		}
		
		for (int i = 1; i <= n; i++) {
			for (int j = arr[i][0]; j <= arr[i][1]; j++) {
				if (cake[j] == 0) {
					cake[j] = i;
					cnt[i]++;
				}
			}
		}
		
		for (int i = 1; i <= n; i++)
			if (cnt[i] > realMax) {
				realMax = cnt[i];
				realIdx = i;
			}
		
		System.out.println(expIdx);
		System.out.println(realIdx);
	}
}
