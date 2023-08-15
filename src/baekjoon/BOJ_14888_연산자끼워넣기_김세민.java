package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ_14888_연산자끼워넣기
 * 1.수의 개수와 수를 입력받는다.
 * 2.각 연산자의 개수를 입력받는다.
 * 3.깊이우선탐색을 통해 식의 결과의 최대,최솟값을 갱신한다.
 *
 * @author semin.kim
 */

public class BOJ_14888_연산자끼워넣기_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	
	static int num_cnt; // 숫자의 개수
	static int[] numList; // 숫자를 저장할 1차원 배열
	static int[] operatorList; // 연산자의 개수를 저장할 1차원 배열
	static int MAX = Integer.MIN_VALUE; // 식의 결과의 최댓값
	static int MIN = Integer.MAX_VALUE; // 식의 결과의 최솟값
	
	public static void dfs(int depth, int value) {
		if(depth == num_cnt) { // 식의 계산이 끝난 경우...
			MAX = Math.max(MAX, value); // 최댓값 갱신
			MIN = Math.min(MIN, value); // 최솟값 갱신
		}
		
		for(int idx = 0; idx < 4; idx++) {
			if(operatorList[idx] != 0) { // 연산자의 개수가 0이 아닌 경우...
				operatorList[idx]--; // 연산자를 하나 사용
				
				if(idx == 0) { // '+'
					dfs(depth + 1, value + numList[depth]);
				} else if(idx == 1) { // '-'
					dfs(depth + 1, value - numList[depth]);
				} else if(idx == 2) { // '*'
					dfs(depth + 1, value * numList[depth]);
				} else if(idx == 3) { // '/'
					dfs(depth + 1, value / numList[depth]);
				}
				operatorList[idx]++; // 연산자 미사용 처리
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		br = new BufferedReader(new InputStreamReader(System.in));
		
		num_cnt = Integer.parseInt(br.readLine().trim()); // 숫자의 개수 입력
		numList = new int[num_cnt];
		operatorList = new int[4];
		st = new StringTokenizer(br.readLine().trim());
		// 숫자 n개 입력
		for(int numIdx = 0; numIdx < num_cnt; numIdx++) {
			numList[numIdx] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine().trim());
		// 각 연산자의 개수 입력
		for(int opIdx = 0; opIdx < 4; opIdx++) {
			operatorList[opIdx] = Integer.parseInt(st.nextToken());
		}
		
		dfs(1, numList[0]);
		System.out.println(MAX+"\n"+MIN);
	}
}
