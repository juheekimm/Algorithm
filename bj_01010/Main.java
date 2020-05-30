package bj_01010;
import java.util.Scanner;

public class Main {
	static int cnt;
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int taseCaseNum = s.nextInt();
		for (int i = 0; i < taseCaseNum; i++) {
			cnt = 0;
			int n = s.nextInt();
			int m = s.nextInt();
			int[] bucket = new int[n];
			bridgeBuild(m, bucket, n, n);
			System.out.println(cnt);
		}
	}
	
	public static void bridgeBuild(int mSize, int[] bucket, int bucketSize, int pickNum) {
		int lastIndex, smallest;
		if (pickNum == 0) {
			cnt++;
			return;
		}
			
		lastIndex = bucketSize - pickNum - 1; //���� �ֱٿ� ���� ���� ����� index ��ȣ
		
		if (bucketSize == pickNum)
			smallest = 0;
		else
			smallest = bucket[lastIndex] + 1;
		
		for (int i = smallest; i < mSize; i++) {
			bucket[lastIndex + 1] = i;
			bridgeBuild(mSize, bucket, bucketSize, pickNum - 1);
		}
	}
}
