package bj_03190_뱀;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main2 {
	private static class Node {
		int x, y;
		Node (int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	private static int n, k, l, time, nowHead, nowTail, nowRotation, map[][], dir[][];
	private static Node head, tail;
	private static int[] dx = {0, 1, 0, -1};	//우하좌상 순
	private static int[] dy = {1, 0, -1, 0};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		
		k = Integer.parseInt(br.readLine());
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			map[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = 5;
		}
		
		l = Integer.parseInt(br.readLine());
		dir = new int[l][2];

		int t;	char d;
		for (int i = 0; i < l; i++) {
			st = new StringTokenizer(br.readLine());
			t = Integer.parseInt(st.nextToken());
			d = st.nextToken().charAt(0);
			dir[i][0] = t;
			dir[i][1] = (d == 'L') ? -1 : 1;	//-1이면 왼쪽, 1이면 오른쪽 
		}
		
		head = new Node(0, 0);
		tail = new Node(0, 0);
		map[0][0] = 1;
		while (true) {
			time++;
			
			map[head.x][head.y] = 0;
			head.x += dx[nowHead];
			head.y += dy[nowHead];
			
			System.out.println("머리 " + head.x + " " + head.y);
			
			if (head.x < 0 || head.x >= n || head.y < 0 || head.y >= n || map[head.x][head.y] == 1) {
				break;
			} else {
				
				if (map[head.x][head.y] != 5) {	//이동한 칸에 사과가 없다면 꼬리도 이동 
					tail.x += dx[nowHead];
					tail.y += dy[nowHead];
				}
				map[head.x][head.y] = 1; 		//새로 이동한 칸에 뱀 그리기
			}
			System.out.println("꼬리 " + tail.x + " " + tail.y);
			System.out.println("nowdir" + nowHead);
			print();
			
			if (dir[nowRotation][0] == time) {
				nowHead = (nowHead + dir[nowRotation][1] + 4) % 4;
				nowRotation = (nowRotation + 1) % 4;
			}
		}
		System.out.println(time);
	}

	private static void print() {
		for (int i = 0; i < n; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
		System.out.println();
	}
}















