package bj_17825_주사위윷놀이;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_190226 {
	
	private static class Node {
		int score, blue, red;
		boolean isBlue = false;
		
		Node (int score, int red) {
			this.score = score;
			this.red = red;
		}
//		Node (int blue, boolean isBlue) {
//			this.blue = blue;
//			this.isBlue = isBlue;
//		}
	}
	
	private static int max = 0, permu[], step[], now[];
	private static boolean[] existCheck;
	private static Node[] map;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		map = new Node[43];
		permu = new int[10];
		
		step = new int[10];
		for (int i = 0; i < 10; i++)
			step[i] = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i <= 40; i += 2)
			map[i] = new Node(i, i + 2);
		
		map[10].isBlue = map[20].isBlue = map[30].isBlue = true;
		map[10].blue = 11;
		map[20].blue = 17;
		map[30].blue = 31;
		
//		map[10] = new Node(11 , true);
//		map[20] = new Node(17 , true);
//		map[30] = new Node(31 , true);
		
		map[11] = new Node(13 , 13);
		map[13] = new Node(16 , 15);
		map[15] = new Node(19 , 25);
		map[17] = new Node(22 , 19);
		map[19] = new Node(24 , 25);
		map[25] = new Node(25 , 37);
		map[31] = new Node(28 , 33);
		map[33] = new Node(27 , 35);
		map[35] = new Node(26 , 25);
		map[37] = new Node(30 , 39);
		map[39] = new Node(35 , 40);
		map[42] = new Node(0, 42);
		
		permu[0] = 0;
		permu(9, 0);
		System.out.println(max);
	}

	private static void permu(int n, int k) {
		if (n == k) {
			now = new int[4];
			existCheck = new boolean[43];
			move();
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			permu[k] = i;
			permu(n, k + 1);
			permu[k] = -1;	//굳이 안해줘도 되긴 함 
		}
	}

	private static void move() {
		int score = 0;
		
		for (int i = 0; i < 10; i++) {
			int end = horseMove(permu[i], step[i]);
			if (end == -1) return;
			now[permu[i]] = end;
			score += map[end].score;
		}
		if (max < score) max = score;
	}

	private static int horseMove(int horse, int step) {
		int temp = now[horse];
		
		for (int i = 0; i < step; i++) {
			if (i == 0 && map[temp].isBlue) {
				temp = map[temp].blue;
				continue;
			}
			temp = map[temp].red;
//			if (temp == 42) break;
//			위에서 42번 점의 다음 점도 42번 점으로 돌게 처리해줘서 이 코드는 필요 없다!
		}
		
		if (temp <= 40 && existCheck[temp]) {
			return -1;
		} else {
			existCheck[now[horse]] = false;
			existCheck[temp] = true;
			return temp;
		}
	}
}
