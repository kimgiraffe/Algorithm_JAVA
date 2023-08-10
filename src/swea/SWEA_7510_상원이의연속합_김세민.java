package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * SWEA_7510_상원이의연속합_D3
 * 1.테스트 케이스의 개수를 입력받는다.
 * 2.자연수 하나를 입력받는다.
 * 3. 1부터 시작하여 자연수를 더하고, 만약 더한 값이 주어진 자연수와 같은 경우...
 * 4. 연속된 자연수의 합으로 나태낼 수 있는 경우의 수 1 증가
 * @author semin.kim
 */

public class SWEA_7510_상원이의연속합_김세민 {
	
	static BufferedReader br;
	static StringBuilder sb;
	
	static int T; //테스트 케이스의 개수
	static int num; //자연수
	static int cnt; // 연속된 자연수의 합으로 나타낼 수 있는 경우의 수
	
	public static void solve() {
		int start = 1; // 연속된 자연수의 시작 값
		num--; // 자연수에서 시작 값 1을 뺌
		while(num > 0) {
			start++; // 시작 값을 1증가
			num -= start; // 자연수에서 시작 값을 뺌
			if(num % start == 0) { // 자연수를 현재까지의 연속된 숫자의 합으로 나누어 떨어지는 경우...
				cnt++; // 연속된 자연수의 합으로 나타내는 경우의 수 1 증가
			}
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine().trim()); // 테스트 케이스의 개수 입력
		
		for(int test_case = 1; test_case <= T; ++test_case) {
			num = Integer.parseInt(br.readLine().trim());
			cnt = 1; // 모든 자연수ㄴ는 자기 자신으로 나타낼 수 있음
			sb = new StringBuilder();
			solve();
			sb.append('#').append(test_case).append(' ').append(cnt);
			System.out.println(sb);
		}
	}
}
