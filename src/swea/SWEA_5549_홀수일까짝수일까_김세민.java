package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * SWEA_5549_홀수일까짝수일까_D3
 * 1.테스트 케이스의 개수를 입력받는다.
 * 2.100자리 이하의 양의 정수를 문자열로 입력받는다.
 * 3.문자열의 마지막 인덱스 값이 짝수인지 판별한다.
 * @author semin.kim
 */

public class SWEA_5549_홀수일까짝수일까_김세민 {
	
	static BufferedReader br;
	static StringBuilder sb;
	
	static int T; //테스트 케이스의 개수
	static String num;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine().trim()); //테스트 케이스의 개수 입력
		
		for(int test_case = 1; test_case <= T; test_case++) {
			num = br.readLine().trim(); //100자리 이하의 양의 정수 입력
			sb = new StringBuilder();
			sb.append("#").append(test_case).append(" ");
			if(((num.charAt(num.length() - 1) - '0') & 1) == 1) { //문자열의 마지막 인덱스 값이 홀수인 경우...
				sb.append("Odd");
			} else { //문자열의 마지막 인덱스 값이 짝수인 경우...
				sb.append("Even");
			}
			System.out.println(sb);
		}
	}
}
