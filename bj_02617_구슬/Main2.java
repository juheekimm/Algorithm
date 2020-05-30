package bj_02617_구슬;

import java.io.*;
import java.util.*;

public class Main2 {
	static int n, m, mid, cnt;
	static ArrayList<Integer>[] list;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		mid = (n + 1) / 2;
		
		list = new ArrayList[n + 1];
		for (int i = 0; i <= n; i++)
			list[i] = new ArrayList<>();

		int easy, hard;
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			
			hard = Integer.parseInt(st.nextToken());
			easy = Integer.parseInt(st.nextToken());
			
			list[easy].add(hard);
		}
		calcWeight();
		System.out.println(cnt);
	}

	private static void calcWeight() {
		/*
		 * 작은 동전 구하기
		 * list[1] = 2, 5
		 * list[2] = 4 일때
		 * 1이 2보다 작은데, 2가 4보다 작으므로 1도 4보다 작다.
		 * list[1]에 4를 추가해서 2, 5, 4로 만들어 주는 과정
		 */
		for (int i = 1; i <= n; i++) {
			//list[1]이면 2, 5가 들어있으니까 2번 돈다
			for (int j = 0; j < list[i].size(); j++) {
				//list[1]의 첫번째 원소인 2, 2를 인덱스로 하는 리스트(list[list[1].get(0)])의 크기인 1만큼 돈다
				//즉, 여기서는 4가 나올 것. 걔를 list[1]에 넣어준다
				for (int k = 0; k < list[list[i].get(j)].size(); k++) {
					int temp = list[list[i].get(j)].get(k);
					boolean equalValExist = false;	//중복체크
					
					for (int j2 = 0; j2 < list[i].size(); j2++)
						if (list[i].get(j2) == temp)
							equalValExist = true;
					
					if (!equalValExist) {
						list[i].add(temp);
					}
				}
			}
		}

		for (int i = 1; i <= n; i++)
			if (list[i].size() >= mid)
				cnt++;
		
		/*
		 * 큰 동전 구하기
		 * list 안에 내가 몇번 들어가있는지 count한다.
		 * 나보다 작은 애의 리스트에 내가 몇번 들어있는지 세면 나보다 작은 애의 개수를 구할 수 있다.
		 */
		int[] cntArr = new int[n + 1];
		
		for (int i = 0; i <= n; i++)
			for (int j = 0; j < list[i].size(); j++)
				cntArr[list[i].get(j)]++;

		for (int i = 0; i < cntArr.length; i++) {
			if (cntArr[i] >= mid)
				cnt++;
		}
	}
}