package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * SWEA_1234_비밀번호_D3
 * 1.문자열이 포함하는 문자의 총수 (문자열의 길이)를 입력받는다.
 * 2.문자열을 입력받는다.
 * 3.문자열의 첫번째 원소를 스택에 push한다.
 * 4.문자열을 순회하며 문자열의 다음 문자와 스택의 top을 비교한다.
 * 	4-1. 문자열의 다음 원소가 스택의 top과 다르다면 push한다.
 * 	4-2. 같다면 스택의 top원소를 꺼낸다.
 * 5.순회를 종료하면 스택에 저장되어 있는 원소를 하나씩 꺼내 stringbuilder 에 append한다.
 * 6.stringbuilder를 이용하여 역순으로 출력한다.
 * @author semin.kim
 */

public class SWEA_1234_비밀번호_김세민 {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static final int T = 10; //테스트 케이스의 개수는 10으로 고정
	static int string_len; //문자열의 길이
	static String string; //문자열
	static Stack<Character> stack; //비밀번호를 저장할 스택
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int test_case = 1; test_case <= T; test_case++) {
			stack = new Stack<>();
			st = new StringTokenizer(br.readLine().trim());
			
			string_len = Integer.parseInt(st.nextToken()); //문자열의 길이 입력
			
			string = st.nextToken(); //문자열 입력
			
			stack.push(string.charAt(0)); //문자열의 첫 번째 문자를 스택에 push
			for(int idx = 1; idx < string_len; idx++) {
				if(!stack.isEmpty() && stack.peek() == string.charAt(idx)) { //스택의 top과 동일하다면 pop
					stack.pop();
				} else { //다르다면 push
					stack.push(string.charAt(idx));
				}
			}
			
			sb = new StringBuilder();
			sb.append("#").append(test_case).append(" ");
			System.out.print(sb);
			sb = new StringBuilder();
			
			while(!stack.isEmpty()) { //스택의 원소를 하나씩 꺼내 stringbuilder 에 append
				sb.append(stack.peek());
				stack.pop();
			}
			System.out.println(sb.reverse());
		}
	}
}
