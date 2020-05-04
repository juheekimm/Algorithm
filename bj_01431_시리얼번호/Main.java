package bj_01431_시리얼번호;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
	
	static int N;
	static String[] str;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		str = new String[N];
		for (int i = 0; i < N; i++)
			str[i] = br.readLine();
		
		sort();
		
		for (int i = 0; i < N; i++)
			sb.append(str[i] + "\n");
		System.out.println(sb);
	}

	private static void sort() {
		Arrays.sort(str, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				if (o1.length() < o2.length())
					return -1;
				else if (o1.length() > o2.length())
					return 1;
				else {
					int sum1 = 0, sum2 = 0;
					char[] ch1 = o1.toCharArray();
					for (int i = 0, len = o1.length(); i < len; i++)
						if (ch1[i] >= '1' && ch1[i] <= '9')
							sum1 += (ch1[i] - '0');
					
					char[] ch2 = o2.toCharArray();
					for (int i = 0, len = o2.length(); i < len; i++)
						if (ch2[i] >= '1' && ch2[i] <= '9')
							sum2 += (ch2[i] - '0');
					
					if (sum1 < sum2)
						return -1;
					else if (sum1 > sum2)
						return 1;
					else {
						for (int i = 0, len = o1.length(); i < len; i++) {
							if (ch1[i] < ch2[i]) return -1;
							else if (ch1[i] > ch2[i]) return 1;
						}
						return 0;
					}
				}
			}
		});
	}
}