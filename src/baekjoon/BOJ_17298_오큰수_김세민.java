package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_17298_오큰수_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int N;
	static int[] A;
	static int[] answer;
	static Stack<Integer> stack = new Stack<>();
	
	private static void solve() {
		for(int idx = N; idx > 0; idx--) {
			while(!stack.isEmpty() && stack.peek() <= A[idx]) {
				stack.pop();
			}
			if(stack.isEmpty()) answer[idx] = -1;
			else answer[idx] = stack.peek();
			
			stack.push(A[idx]);
		}
		
		for(int idx = 1; idx <= N; idx++) {
			sb.append(answer[idx]).append(" ");
		}
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		N = Integer.parseInt(br.readLine().trim());
		
		A = new int[N+1];
		answer = new int[N+1];
		
		st = new StringTokenizer(br.readLine().trim());
		
		for(int idx = 1; idx <= N; idx++) {
			A[idx] = Integer.parseInt(st.nextToken());
		}
		
		solve();
		
		System.out.println(sb);
	}
}
