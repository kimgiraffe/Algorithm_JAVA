package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * BOJ_16637_괄호추가하기
 * 수식의 길이는 1이상 19이하의 홀수
 * 연산자의 우선순위는 모두 동일. 연산자는 왼쪽부터 순서대로 계산
 * 괄호 안에 들어있는 식은 먼저 계산. 단, 괄호 안에 연산자는 하나만 가능
 * 추가하는 괄호 개수의 제한 없음. 괄호를 추가하지 않아도 됨.
 * 수식의 결과값이 최대가 되려면...
 * 1.연산자는 괄호 안에 한개만 들어갈 수있으므로 문자열을 3개 단위로 묶는다.
 * 2.연산자의 인덱스는 항상 홀수이다.
 * 3.앞에서부터 괄호로 묵은 경우와 괄호로 묶지 않고 진행하였을 때의 값을 비교하여 최댓값을 갱신한다.
 * 4.인덱스가 범위를 넘어가는 경우 종료
 * 
 * @author semin.kim
 */

public class BOJ_16637_괄호추가하기_김세민 {

	static BufferedReader br;
	static int formula_length; // 수식의 길이
	static char[] formula; // 수식
	
	static int MAX_value = Integer.MIN_VALUE; // 수식의 최댓값
	
	public static int calculate(int n1, char op, int n2) {
		int answer = n1;
		switch (op) {
		case '+':
			answer += n2;
			break;
		case '-':
			answer -= n2;
			break;
		case '*':
			answer *= n2;
			break;

		default:
			break;
		}
		
		return answer;
		
	}
	
	public static void dfs(int idx, int value) {
		// 기저 조건
		if(idx > formula_length - 1) {
			MAX_value = Math.max(MAX_value, value);
			return;
		}
		char op = (idx == 0) ? '+' : formula[idx-1];
		// 괄호로 묶는 경우...
		// 이전의 계산 값과 괄호 안의 계산 값을 더함
		if(idx + 2 < formula_length) {
			int bracket = calculate(formula[idx]-'0', formula[idx+1], formula[idx+2]-'0');
			dfs(idx + 4, calculate(value, op, bracket));
		}
		// 괄호로 묶지 않는 경우...
		// 이전의 계산 값과 다음의 계산 값을 더함
		dfs(idx + 2, calculate(value, op, formula[idx]-'0'));
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		formula_length = Integer.parseInt(br.readLine().trim()); // 수식의 길이 입력
		formula = new char[formula_length];
		formula = br.readLine().trim().toCharArray(); // 수식 입력
		dfs(0, 0);
		System.out.println(MAX_value);
	}
}
