package test;

public class 진법변환 {

	public static void main(String[] args) {
//		String 버전 
//		int num = 48;
//		String ans = "";
//		while (num > 0) {
//			ans = (num % 7) + ans;
//			num /= 7;
//		}
//		
//		int 버전 
//		int num = 48;
//		int ans = 0;
//		while (num > 0) {
//			ans = (num % 7) + ans * 10;
//			num /= 7;
//		}
//		
//		int형 숫자 뒤집기 
//		int a = 123456789;
//		int ans = 0;
//		while (a > 0) {
//			ans = (ans * 10) + (a % 10);
//			a /= 10;
//		}
		
//		int num = 100;
//		String n = num + "";
//		System.out.println(Integer.parseInt(n, 7));

		int num = 100;
		int ans = 0;
		while (num > 0) {
			ans = (ans * 10) + (int)Math.pow(7, Math.log10(num));
			System.out.println((int)Math.pow(7, Math.log10(num)));
			num /= 10;
		}
		System.out.println(ans);
	}

}
