package programmers;

import java.util.*;

public class 주식가격 {
	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(new int[] {1, 2, 3, 2, })));
	}

	public static int[] solution(int[] prices) {
		Stack<Integer> stack = new Stack<Integer>();
        int len = prices.length;
        int[] answer = new int[len];
        
        for (int i = 0; i < len; i++) {
			int temp = i, time = 1;
			while (!stack.isEmpty() && stack.peek() > prices[i]) {
				stack.pop();
				while (temp > 0 && answer[--temp] != 0) {
					time++;
				}
				answer[temp] = time++;
			}
			stack.push(prices[i]);
		}
        
        int time = -1;
        for (int i = len - 1; i >= 0; i--) {
            time++;
            if (answer[i] != 0) {
                continue;
            }
            answer[i] = time;
        }
        return answer;
	}
}
