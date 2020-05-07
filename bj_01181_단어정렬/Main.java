package bj_01181_단어정렬;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		String[] arr = new String[N];
		
		for (int i = 0; i < N; i++)
			arr[i] = br.readLine();

		Arrays.sort(arr, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				if (o1.length() < o2.length()) return -1;
				else if (o1.length() > o2.length()) return 1;
				
				//이건 왜 오류?
//				int lenCompare = o1.compareTo(o2);
//				if (lenCompare != 0)
//					return lenCompare;
				
				for (int i = 0, len = o1.length(); i < len; i++) {
					if (o1.charAt(i) < o2.charAt(i)) return -1;
					else if (o1.charAt(i) > o2.charAt(i)) return 1;
				}
				return 0;
			}
		});
		
		for (int i = 0; i < N; i++) {
			if (i != 0 && arr[i - 1].equals(arr[i])) continue;
			sb.append(arr[i] + "\n");
		}
		System.out.println(sb);
	}
}