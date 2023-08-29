package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 2×N 크기의 벽을 2×1, 1×2, 1×1 크기의 타일로 채우는 경우의 수를 구해보자.
 * N(1 ≤ N ≤ 1,000,000)
 * 
 * 2 * 1 크기의 벽을 채우는 경우의 수는 2가지
 * 2 * 2 크기의 벽을 채우는 고유한 경우의 수는 3가지
 * 늘어난만큼의 벽에 타일을 체울 수 있는 고유한 경우의 수는 2가지
 * 
 * dp[n] = 2 * dp[n-1] + 3 * dp[n-2] + 2 * (dp[n-3] + dp[n-4] + ... + dp[0])
 * 
 * 2차원 배열 이용...
 * dp[n][0]은 타일을 채우은 경우의 수
 * dp[n][1]은 dp[n-3][0] + dp[n-4][0] + ... + dp[0][0] 의 값
 * 
 * @author semin.kim
 */

public class BOJ_14852_타일채우기3_김세민 {

	static BufferedReader br;
	
	static int wallSize; // 벽의 크기
	static final int MOD = 1_000_000_007;
	static final int MAX_WALL_SIZE = 1_000_000;
	static long[][] dp;
	
	private static void fill() {
		dp[1][0] = 2;
		dp[2][0] = 7;
		dp[2][1] = 1;
		
		for(int idx = 3; idx <= MAX_WALL_SIZE; idx++) {
			dp[idx][1] = (dp[idx - 3][0] + dp[idx - 1][1]) % MOD;
			dp[idx][0] = (2 * dp[idx - 1][0] + 3 * dp[idx - 2][0] + 2 * dp[idx][1]) % MOD; 
		}
		System.out.println(dp[wallSize][0]);
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		wallSize = Integer.parseInt(br.readLine().trim());
		dp = new long[MAX_WALL_SIZE+1][2];
		fill();
	}
}
