package bj_01644_소수의연속합;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

	static int N, rslt = 0, arr[], arrSize;
	static boolean[] check;
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		N = s.nextInt();
		
		arr = new int[N + 1];
		check = new boolean[N + 1];
//		if (check[N]) rslt++;		
		//만약 자기자신도 소수라면 rslt를 하나 추가해준다 로 하려했는데
		//어차피 이것까지 슬라이딩 윈도우가 다 해

		checkNum();
		makeArr();
		slide();
		
		System.out.println(rslt);
	}

	private static void slide() {
		int s, e, sum;
		s = e = sum = 0;
		
		while (true) {
			if (sum >= N) sum -= arr[s++];
			//끝 인덱스는 포함하지 않으므로, arrSize - 1로 하면 안된다 
			else if (e == arrSize) break;
			else sum += arr[e++];
			if (sum == N) rslt++;
		}
	}

	private static void checkNum() {
		//만약 N = 120이면 end = 10인데, +1은 굳이 안해줘도 된다 
		//왜냐하면 11 * 11 > 120이므로. 11* 2, 3, 4,... 이런건 이미 이전에 지워졌기 때문에 상관 없다 
		int end = (int)Math.sqrt(N);
		
		int num = 2;
		while (num <= end) {
			//자기 자신은 지우면 안된다!
			for (int i = num * 2; i <= N; i += num)
				//소수가 아닌 수들을 true로 바꿔준다 
				check[i] = true;

			num = searchNum(num);
		}
	}

	private static int searchNum(int num) {
		while (num < N)
			// 그 다음으로 지워줄 소수를 찾는다 
			if (!check[++num])
				return num;
		
		//지워줄 소수가 없다면, 이렇게 넘겨주면 while의 조건에 걸려서 자동으로 종료된다 
		return num + 1;
	}
	
	private static void makeArr() {
		int idx = 0;
		for (int i = 2; i <= N; i++)
			if (!check[i])
				arr[idx++] = i;
		arrSize = idx;	//size니까 1 큰 상태로 저장 
	}
}