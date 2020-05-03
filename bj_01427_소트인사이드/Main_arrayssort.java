package bj_01427_소트인사이드;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_arrayssort {

	static char[] data;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		data = br.readLine().toCharArray();
		Arrays.sort(data);
		for (int i = data.length - 1; i >= 0; i--)
			System.out.print(data[i]);
	}
}