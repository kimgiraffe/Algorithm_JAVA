package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SWEA_10032_과자분배
 * 1.테스트 케이스의 개수를 입력받는다.
 * 2.과자의 개수와 과자를 나눠줄 인원 수를 입력받는다.
 * 3.과자의 개수가 인원 수로 나누어떨어질 경우 0을 출력
 * 4.나누어떨어지지 않을 경우 1 출력
 * @author semin.kim
 */

public class SWEA_10032_과자분배_김세민 {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int T; // 테스트 케이스의 개수
	static int snack_cnt, people_cnt; // 과자의 개수, 인원 수
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine().trim()); // 테스트 케이스의 개수
		
		for(int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine().trim());
			snack_cnt = Integer.parseInt(st.nextToken()); // 나눠줄 과자의 개수 입력
			people_cnt = Integer.parseInt(st.nextToken()); // 과자를 나눠줄 인원 수 입력
			
			sb = new StringBuilder();
			sb.append('#').append(test_case).append(' ');
			if(snack_cnt % people_cnt == 0) { // 나누어 떨어질 경우...
				sb.append(0); // 0 출력
			} else { // 나누어 떨어지지 않을 경우...
				sb.append(1); // 1 출력
			}
			System.out.println(sb);
		}
	}
}
