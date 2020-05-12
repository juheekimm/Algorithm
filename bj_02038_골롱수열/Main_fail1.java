package bj_02038_골롱수열;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main_fail1 {
	static HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		System.out.println(golomb(n));
	}

	private static int golomb(int n) {
		int idx = 1, val = 3;
		boolean breakFlag = false;
		
		map.put(idx++, 1);
		map.put(idx++, 2);
		map.put(idx++, 2);
		
		while (idx <= n) {
			if (idx + map.get(val) > n) {
				breakFlag = true;
				break;
			}
			int end = map.remove(val);
			for (int i = 0; i < end; i++)
				map.put(idx + i, val);
			idx += end;
			val++;
		}
		
		if (breakFlag) return val;
		else return map.get(n);
	}
}