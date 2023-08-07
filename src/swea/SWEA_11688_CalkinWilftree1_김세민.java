package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * SWEA_11688_CalkinWilftree1_D3
 * 1.테스트 케이스의 개수를 입력받는다.
 * 2.길이 1이상 30이하의 문자열을 입력받는다.
 * 3.문자열을 순회하며 분자, 분모 값을 갱신한다.
 * 	3-1. L인 경우... 분모가 분자만큼 증가한다.
 * 	3-2. R인 경우... 분자가 분모만큼 증가한다.
 * 4.분자와 분모를 출력한다.
 * @author semin.kim
 */

public class SWEA_11688_CalkinWilftree1_김세민 {
	
	static BufferedReader br; //입력
	static StringBuilder sb; //출력
	
	static int T; // 테스트 케이스의 개수
	static String string; //문자열
	static int numerator, denominator; //분자, 분모
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine().trim()); //테스트 케이스의 개수 입력
		for(int test_case = 1; test_case <= T; ++test_case) {
			string = br.readLine().trim(); // 문자열 입력
			numerator = 1; denominator = 1; //테스트 케이스마다 분자, 분모를 1로 초기화(root 노드는 1/1)
			for(int idx = 0; idx < string.length(); idx++) {
				if(string.charAt(idx) == 'L') { //L인 경우...
					denominator += numerator; //분모가 분자만큼 증가
				} else { //R인 경우...
					numerator += denominator; //분자가 분모만큼 증가
				}
			}
			sb = new StringBuilder();
			sb.append("#").append(test_case).append(" ").append(numerator).append(" ").append(denominator);
			System.out.println(sb);
		}
	}
}
