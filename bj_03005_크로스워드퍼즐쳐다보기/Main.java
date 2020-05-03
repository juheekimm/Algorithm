package bj_03005_크로스워드퍼즐쳐다보기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
	private static int r, c, ans;
	private static char[][] map;
	private static ArrayList<char[]> list;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		map = new char[r][c];
		list = new ArrayList<>();
		
		for (int i = 0; i < r; i++)
			map[i] = br.readLine().toCharArray();
		
		findStart();
		findFirst();
		
		String rslt = "";
		for (char c : list.get(ans)) {
			if (c == '\0') break;
			rslt += c;
		}
		System.out.println(rslt);
	}

	private static void findFirst() {
		for (int i = 1; i < list.size(); i++) {
			if (list.get(i)[0] < list.get(ans)[0])
				ans = i;
			else if (list.get(i)[0] == list.get(ans)[0]) {
				for (int j = 1; j < 20; j++) {
					if (list.get(i)[j] < list.get(ans)[j]) {
						ans = i;
						break;
					} else if (list.get(i)[j] == list.get(ans)[j]) {
						continue;
					} else {
						break;
					}
				}
			}
		}
	}

	private static void findStart() {
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (map[i][j] == '#') continue;
				
				if (i == 0)
					vertiWord(i, j);
				else if (map[i - 1][j] == '#')
					vertiWord(i, j);
				
				if (j == 0)
					horizonWord(i, j);
				else if (map[i][j - 1] == '#')
					horizonWord(i, j);
			}
		}
	}

	private static void horizonWord(int x, int y) {
		char[] arr = new char[20];
		int idx = 0;
		
		for (int j = y; j < c; j++) {
			if (map[x][j] == '#') break;
			arr[idx++] = map[x][j];
		}
		if (idx >= 2) list.add(arr);
	}
	
	private static void vertiWord(int x, int y) {
		char[] arr = new char[20];
		int idx = 0;
		
		for (int i = x; i < r; i++) {
			if (map[i][y] == '#') break;
			arr[idx++] = map[i][y];
		}
		if (idx >= 2) list.add(arr);
	}
}