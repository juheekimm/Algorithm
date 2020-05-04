package bj_10989_수정렬하기3;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_counting_200504 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int[] ans = new int[10001];
		
		for (int i = 0; i < N; i++)
			ans[Integer.parseInt(br.readLine())]++;
		
		int count = N, idx = 1;
		while (count > 0) {
			int len = ans[idx];
			for (int i = 0; i < len; i++)
				sb.append(idx + "\n");
			count -= len;
			idx++;
		}
		System.out.println(sb);
	}
}