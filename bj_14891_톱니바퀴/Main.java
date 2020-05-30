package bj_14891_톱니바퀴;

import java.io.*;
import java.util.*;

public class Main {

	static int[][] arr = new int[5][8];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for (int i = 1; i <= 4; i++) {
			char[] ch = br.readLine().toCharArray();
			for (int j = 0; j < 8; j++) {
				arr[i][j] = ch[j] - '0';
			}
		}
		
		int tNum = Integer.parseInt(br.readLine());
		int no, dir;
		for (int t = 0; t < tNum; t++) {
			st = new StringTokenizer(br.readLine());
			no = Integer.parseInt(st.nextToken());
			dir = Integer.parseInt(st.nextToken());
			
//			turn(no, dir);			//나자신(turnCheck에서 해주므로 필요x)
			turnCheck(no, dir);		//좌우
		}
		System.out.println(scoreCheck());
	}
	
	private static void turnCheck(int no, int dir) {
		int my = no, left = no - 1, right = no + 1, d = dir;
		int[] change = new int[5];
		change[no] = dir;	//나자신체크
		
		while (left >= 1) {		//왼쪽검사
			if (arr[my][6] != arr[left][2]) {
				d *= -1;
				change[left] = d;
				my = left;
				left = left - 1;
			} else  {
				break;
			}
		}
		
		my = no;
		d = dir;
		while (right <= 4) {	//오른쪽검사
			if (arr[my][2] != arr[right][6]) {
				d *= -1;
				change[right] = d;
				my = right;
				right = right + 1;
			} else  {
				break;
			}
		}
		
		for (int i = 1; i <= 4; i++)
			if (change[i] != 0)
				turn(i, change[i]);
	}
	

	private static void turn(int no, int dir) {
		int temp;
		
		if (dir == 1) {					//시계
			temp = arr[no][7];
			
			for (int i = 6; i >= 0; i--)
				arr[no][i + 1] = arr[no][i];
			
			arr[no][0] = temp;
			
		} else if (dir == -1) {			//반시계
			temp = arr[no][0];
			
			for (int i = 1; i <= 7; i++)
				arr[no][i - 1] = arr[no][i];
			
			arr[no][7] = temp;
		}
	}
	
	private static int scoreCheck() {
		int sum = 0;
		for (int i = 1; i <= 4; i++)
			sum += arr[i][0] * Math.pow(2, (i - 1));
		return sum;
	}
}