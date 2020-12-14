package bj_01003_피보나치함수;


public class test {
	public static void main(String[] args) {
		int num = 14;
		String ans = "";
		while (num > 0) {
			ans = (num % 2) + ans;
			num /= 2;
		}
		System.out.println(ans);
	}
}
