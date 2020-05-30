package bj_02999_비밀이메일;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		char[] ch = br.readLine().toCharArray();
		int r = 0, c = 0, n = ch.length, idx = 0;
		
		for (int i = (int) Math.sqrt(n); i >= 1; i--) {
			if (n % i == 0) {
				r = i;
				c = n / r;
				break;
			}
		}
		
		char[][] msg = new char[r][c];
		for (int j = 0; j < c; j++)
			for (int i = 0; i < r; i++)
				msg[i][j] = ch[idx++];
		
		for (int i = 0; i < r; i++)
			for (int j = 0; j < c; j++)
				bw.append(msg[i][j]);
		
		bw.flush();
		bw.close();
	}
}