package bj_17413_단어뒤집기2;

import java.io.*;
import java.util.Stack;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		char[] ch = br.readLine().toCharArray();
		Stack<Character> st = new Stack<>();
		
		int i = 0;
		String temp;
		while (i < ch.length) {
			temp = "";
			
			if (ch[i] == '<') {
				do {
					bw.append(ch[i]);
				} while (ch[i++] != '>' && i < ch.length);
				
			} else if (ch[i] == ' ') {
				bw.append(ch[i++]);
			
			} else {				//나머지 알파벳, 숫자
				do {
					st.add(ch[i++]);
				} while (i < ch.length && ch[i] != ' ' && ch[i] != '<' && ch[i] != '>');
				
				while (!st.isEmpty())
					bw.append(st.pop());
			}
		}
		bw.flush();
		bw.close();
	}
}