package bj_13335_트럭;

public class test {
	
	int a;
	static int b;
	
	public test() {}
	
	public test(int a, int b) {
		this.a = a;
		this.b = b;
	}
	
	public static int add(int a, int b) {
		return a + b;
	}
	
	public int subtract(int a, int b) {
		test tt1 = new test();
		a = tt1.add(a, b);
		return a - b + add(a, b);
	}
	
	public static void print(String...strings) {
		for (int i = 0; i < strings.length; i++) {
			System.out.println(strings[i]);
		}
	}
	
	public static void main(String[] args) {
		test t1 = new test();
		test t2 = new test();
		
		System.out.println(t1.a);
		System.out.println(t1.b);
		System.out.println(t2.a);
		System.out.println(t2.b);
		
		t1.a = 3;
		t1.b = 4;
		
		System.out.println(t1.a);
		System.out.println(t1.b);
		System.out.println(t2.a);
		System.out.println(t2.b);
		
		print(new String[]{"aa", "bb", "cc"});
	}
}
