package bj_02096_내려가기;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int N, map[][];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][3];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		if (N == 1) {
			Arrays.parallelSort(map[0]);
			System.out.println(map[0][2] + " " + map[0][0]);
			return;
		}

		dp();
	}

	private static void dp() {
		int[] temp = new int[3];
		int[] arr = map[0].clone();
		
		for (int i = 1; i < N; i++) {
			//max
			temp[0] = map[i][0] + Math.max(arr[0], arr[1]);
			temp[1] = map[i][1] + Math.max(Math.max(arr[0], arr[1]), arr[2]);
			temp[2] = map[i][2] + Math.max(arr[1], arr[2]);
			arr = temp.clone();
		}
		
		Arrays.sort(temp);
		System.out.print(temp[2]);
		
		
		temp = new int[3];
		arr = map[0].clone();
		for (int i = 1; i < N; i++) {
			//min
			temp[0] = map[i][0] + Math.min(arr[0], arr[1]);
			temp[1] = map[i][1] + Math.min(Math.min(arr[0], arr[1]), arr[2]);
			temp[2] = map[i][2] + Math.min(arr[1], arr[2]);
			arr = temp.clone();
		}
		
		Arrays.sort(temp);
		System.out.print(" " + temp[0]);
	}
}