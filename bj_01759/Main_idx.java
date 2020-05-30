package bj_01759;

import java.io.*;
import java.util.*;

//190806. 1h. 백트래킹 + dfs 문제. visit 대신 idx를 사용해서 visit효과를 줘봄.
public class Main_idx {
	private static int codeLen, charLen, jaCnt = 0, moCnt = 0;
	private static char[] c;
	private static String str = "";
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		codeLen = Integer.parseInt(st.nextToken());
		charLen = Integer.parseInt(st.nextToken());
		c = new char[charLen];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < charLen; i++)
			c[i] = st.nextToken().charAt(0);
		Arrays.sort(c);
		
		codeMaker(0, 0);
	}
	
	private static void codeMaker(int cnt, int idx) {
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
		
		for (int i = idx; i < charLen; i++) {
			if (cnt == 0 || str.charAt(str.length() - 1) < c[i]) {
				str += c[i];
				codeMaker(cnt + 1, i + 1);
				//백트래킹부분. str은 마지막 한글자 제거.
				//매개변수에 idx를 줘서 visit 효과를 줬기때문에 visit은 필요 없다.
				str = str.substring(0, cnt);
			}
		}
	}

	private static boolean chCheck(char ch) {
		if (ch == 97 || ch == 101 || ch == 105 || ch == 111 || ch == 117)
			return true;
		return false;
	}
}