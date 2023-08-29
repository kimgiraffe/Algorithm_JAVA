package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * BOJ_1463_1로만들기
 * 
 * 정수 X에 사용할 수 있는 연산은 다음과 같이 세 가지 이다.
 * 1.X가 3으로 나누어 떨어지면, 3으로 나눈다.
 * 2.X가 2로 나누어 떨어지면, 2로 나눈다.
 * 3.1을 뺀다.
 * 정수 N(1이상 1,000,000이하)이 주어졌을 때, 위와 같은 연산 세 개를 적절히 사용해서 1을 만들려고 한다. 연산을 사용하는 횟수의 최솟값을 출력하시오.
 * 
 * 우선, 모든 정수에 대하여 N - 1에서 1로 만드는 연산횟수 + 1 로 설정
 * 3으로 나누어 떨어지는 경우... N / 3에서 1로 만드는 연산횟수 + 1
 * 2로 나누어 떨어지는 경우... N / 2에서 1로 만드는 연산횟수 + 1
 * @author semin.kim
 *
 */

public class BOJ_1463_1로만들기_김세민 {

	static BufferedReader br;
	
	static int num;
	static int[] dp;
	
	private static void solve() {
		dp[1] = 0;
		
		for(int idx = 2; idx <= num; idx++) {
			// 우선, 모든 정수에 대하여 N - 1에서 1로 만드는 연산횟수 + 1 로 설정

			dp[idx] = dp[idx-1] + 1;
			
			// 3으로 나누어 떨어지는 경우... N / 3에서 1로 만드는 연산횟수 + 1
			if(idx % 3 == 0) {
				dp[idx] = Math.min(dp[idx/3] + 1, dp[idx]);
 			}
			
			// 2로 나누어 떨어지는 경우... N / 2에서 1로 만드는 연산횟수 + 1
			if(idx % 2 == 0) {
				dp[idx] = Math.min(dp[idx/2] + 1, dp[idx]);
			}
		}
		System.out.println(dp[num]);
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		num = Integer.parseInt(br.readLine().trim());
		dp = new int[num+1];
		solve();
	}
}
