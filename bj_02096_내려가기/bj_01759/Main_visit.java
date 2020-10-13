package bj_01759;

import java.io.*;
import java.util.*;

//190806. 1h. 백트래킹 + dfs 문제. 정통 백트래킹 방식으로 풀었음.
public class Main_visit {
	private static int codeLen, charLen, jaCnt = 0, moCnt = 0;
	private static char[] c;
	private static boolean[] isVisit;
	private static String str = "";
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		codeLen = Integer.parseInt(st.nextToken());
		charLen = Integer.parseInt(st.nextToken());

		c = new char[charLen];
		isVisit = new boolean[charLen];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < charLen; i++)
			c[i] = st.nextToken().charAt(0);
		Arrays.sort(c);
		
		codeMaker(0);
	}
	
	private static void codeMaker(int cnt) {
		if (cnt >= codeLen) {
			moCnt = 0;
			jaCnt = 0;
			char[] temp = str.toCharArray();
		
			for (int i = 0; i < temp.length; i++) {
				if (chCheck(temp[i])) moCnt++;
				else jaCnt++;
			}
			if (moCnt >= 1 && jaCnt >= 2)
				System.out.println(str);
			
			return;
		}
		
		for (int i = 0; i < charLen; i++) {
			if (isVisit[i]) continue;
			
			if (cnt == 0 || str.charAt(str.length() - 1) < c[i]) {
				str += c[i];
				isVisit[i] = true;
				codeMaker(cnt + 1);

				//백트래킹부분. str은 마지막 한글자 제거
				str = str.substring(0, cnt);
				isVisit[i] = false;
			}
		}
	}

	private static boolean chCheck(char ch) {
		if (ch == 97 || ch == 101 || ch == 105 || ch == 111 || ch == 117)
			return true;
		return false;
	}
}