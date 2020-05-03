package bj_10815_숫자카드;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		HashSet<Integer> set = new HashSet<Integer>();
		
		int N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			set.add(Integer.parseInt(st.nextToken()));

		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++)
			if (set.contains(Integer.parseInt(st.nextToken())))
				sb.append("1 ");
			else
				sb.append("0 ");
		
		System.out.println(sb);	
	}
}