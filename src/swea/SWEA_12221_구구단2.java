package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SWEA_12221_구구단2_D3
 * 1.테스트 케이스의 개수를 입력받는다.
 * 2.자연수 두 개를 입력받는다.
 * 3.두 자연수가 모두 10이하라면 곱을 출력한다.
 * 4.아니라면 -1을 출력한다.
 * @author semin.kim
 */

public class SWEA_12221_구구단2 {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int T; //테스트 케이스의 개수
	static int num1, num2; //자연수 두 개
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine().trim()); //테스트 케이스의 개수 입력
		
		for(int test_case = 1; test_case <= T; ++test_case) {
			st = new StringTokenizer(br.readLine().trim());
			num1 = Integer.parseInt(st.nextToken()); //첫번째 자연수 입력
			num2 = Integer.parseInt(st.nextToken()); //두번째 자연수 입력
			
			sb = new StringBuilder();
			sb.append("#").append(test_case).append(" ");
			if(num1 < 10 && num2 < 10) { //두 자연수가 모두 10 미만인 경우...
				sb.append(num1 * num2); //두 자연수의 곱 출력
			} else { //두 자연수 중 하나라도 10 이상인 경우...
				sb.append(-1); //-1 출력
			}
			System.out.println(sb);
		}
	}
}
