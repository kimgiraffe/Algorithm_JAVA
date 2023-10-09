package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * BOJ_1769_3의배수
 * 
 * 1. 문자열을 입력받는다.
 * 2. 자릿수를 모두 더한다.
 * 3. 한 자리수일 때까지 반복한다.
 * 4. 변환 횟수와 3의 배수인 경우 YES, 아닌 경우 NO 출력
 * 
 * @author semin.kim
 * 
 */

public class BOJ_1769_3의배수_김세민 {

	static BufferedReader br;
	static StringBuilder sb;
	static int convertCount; // 변환 횟수
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		String X = br.readLine().trim();
		
		while(true) {
			if(X.length() == 1) break; // 한 자리인 경우 중단
			
			int num = 0; // 변환한 숫자
			
			for(int idx = 0; idx < X.length(); idx++) {
				num += X.charAt(idx) - '0';
			}
			convertCount++; // 변환 횟수 1 증가
			X = Integer.toString(num); // 변환
			
		}
		
		sb.append(convertCount).append("\n");
		
		if(X.equals("3") || X.equals("6") || X.equals("9")) { // 3의 배수인 경우...
			sb.append("YES");
		} else { // 3의 배수가 아닌 경우...
			sb.append("NO");
		}
		
		System.out.println(sb);
	}
}
