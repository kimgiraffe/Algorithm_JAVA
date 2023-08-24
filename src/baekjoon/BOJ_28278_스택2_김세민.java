package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * BOJ_28278_스택2
 * 정수를 저장하는 스택을 구현한 다음, 입력으로 주어진 명령을 처리하는 프로그램 작성
 * 
 * @author semin.kim
 */

public class BOJ_28278_스택2_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int orderCount; // 명령의 개수
	static Stack<Integer> stack = new Stack<>();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		orderCount = Integer.parseInt(br.readLine().trim()); // 명령의 개수 입력
		for(int idx = 1; idx <= orderCount; idx++) {
			st = new StringTokenizer(br.readLine().trim());
			int order = Integer.parseInt(st.nextToken());
			if(order == 1) { // 정수를 스택에 넣는다
				int num = Integer.parseInt(st.nextToken());
				stack.push(num);
			} else if(order == 2) { // 스택에 정수가 있다면 맨 위의 정수를 빼고 출력
				if(!stack.isEmpty()) {
					sb.append(stack.pop()).append("\n");
				} else { // 없다면 -1 출력
					sb.append(-1).append("\n");
				}
			} else if(order == 3) { // 스택에 들어있는 정수의 개수 출력
				sb.append(stack.size()).append("\n");
			} else if(order == 4) { // 스택이 비어있으면 1, 아니면 0 출력
				if(stack.isEmpty()) {
					sb.append(1).append("\n");
				} else {
					sb.append(0).append("\n");
				}
			} else if(order == 5) { // 스택에 정수가 있다면 맨 위의 정수를 출력
				if(!stack.isEmpty()) {
					sb.append(stack.peek()).append("\n");
				}
				else { // 없다면 -1 출력
					sb.append(-1).append("\n");
				}
			}
		}
		System.out.println(sb);
	}
}
