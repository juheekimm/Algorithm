package bj_11004_K번째수;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ArrayList구현 {

	private static int n, k;
	
	private static class ArrayList {
		int[] data;
		int size;
		int capacity;
		
		ArrayList(int num) {
			capacity = num;
			data = new int[capacity];
			size = 0;
		}
		
		void add(int num) {
//			if (size == capacity) reallocate();
			data[size++] = num;
		}

		void reallocate() {
			capacity <<= 1;
			int[] temp = new int[capacity];
			for (int i = 0; i < size; i++)
				temp[i] = data[i];
			
			data = temp;	//어차피 참조형이니까 이렇게 해주면 됨 
		}
		
		int pop() {
			return data[--size];
		}
		
		int get (int idx) {
			return data[idx];
		}
		
		void sort() {
			Arrays.sort(data);
		}

		void print() {
			for (int i = 0; i < size; i++)
				System.out.print(data[i] + " ");
			System.out.println();
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		ArrayList list = new ArrayList(n);
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++)
			list.add(Integer.parseInt(st.nextToken()));
		
		list.sort();
		System.out.println(list.get(k - 1));
	}
}