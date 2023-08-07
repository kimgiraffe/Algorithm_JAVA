package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * SWEA_1218_괄호짝짓기_D4
 * 1.문자열을 순회하면서 여는 괄호가 나오면 스택에 push한다.
 * 2.닫는 괄호가 나오면 스택의 top에 있는 괄호와 쌍을 이루는 괄호인지 확인한다.
 *  2-1.쌍을 이루는 괄호라면 스택의 top을 pop한다.
 * 3.순회 종료 후 스택이 비어있다면 유효한 문자열이고 비어있지 않다면 유효하지 않은 문자열이다.
 * @author semin.kim
 */


public class SWEA_1218_괄호짝짓기_김세민 {

	static BufferedReader br;
	static StringBuilder sb;

	static final int T = 10; //테스트 케이스의 개수는 10으로 고정
	static int tc_cnt; //테스트 케이스의 길이
	static String string;
	static int isValid;

	public static class Stack{
		int top = -1;
		int size;
		int[] list;

		public Stack(int size) {
			list = new int[size];
		}

		public void push(int item) {
			list[++top] = item;
		}

		public int pop() {
			if(!isEmpty()) {
				return list[top--];
			}
			return -1;
		}

		public int peek() {
			if(!isEmpty()) return list[top];
			return -1;

		}

		public boolean isEmpty() {
			return top == -1;
		}
	}


	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		for(int test_case = 1; test_case <= T; ++test_case) { 
			tc_cnt = Integer.parseInt(br.readLine().trim()); //문자열 길이 입력
			Stack stack = new Stack(tc_cnt); //스택 생성

			string = br.readLine().trim(); //문자열 입력

			for(int idx = 0 ; idx < string.length(); ++idx) {
				char ch = string.charAt(idx);

				// 닫는 괄호라면 스택의 top에 있는 괄호와 쌍을 이루는 괄호인지 확인한다.
				if(ch == ')' && stack.peek() == '(') {
					stack.pop();
				}
				else if(ch == '}' && stack.peek() == '{') {
					stack.pop();
				}
				else if(ch == ']' && stack.peek() == '[') {
					stack.pop();
				}
				else if(ch == '>' && stack.peek() == '<') {
					stack.pop();
				}
				else {
					stack.push(ch);
				}

			}
			if(stack.isEmpty()) { //스택이 비어있는 경우..
				isValid = 1; // 유효한 문자열
			}
			else { //스택이 비어있지 않은 경우...
				isValid = 0; //유효하지 않은 문자열
			}
			sb = new StringBuilder();
			sb.append("#").append(test_case).append(" ").append(isValid);
			System.out.println(sb);
		}
	}

}
