package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ_1149_RGB거리
 * RGB거리에는 집이 N개 있다. 거리는 선분으로 나타낼 수 있고, 1번 집부터 N번 집이 순서대로 있다.
 * 집은 빨강, 초록, 파랑 중 하나의 색으로 칠해야 한다. 각각의 집을 빨강, 초록, 파랑으로 칠하는 비용이 주어졌을 때, 아래 규칙을 만족하면서 모든 집을 칠하는 비용의 최솟값을 구해보자.
 * 1.1번 집의 색은 2번 집의 색과 같지 않아야 한다.
 * 2.N번 집의 색은 N-1번 집의 색과 같지 않아야 한다.
 * 3.i(2 ≤ i ≤ N-1)번 집의 색은 i-1번, i+1번 집의 색과 같지 않아야 한다.
 * 
 * 첫째 줄에 집의 수 N(2 ≤ N ≤ 1,000)이 주어진다. 
 * 둘째 줄부터 N개의 줄에는 각 집을 빨강, 초록, 파랑으로 칠하는 비용이 1번 집부터 한 줄에 하나씩 주어진다. 
 * 집을 칠하는 비용은 1,000보다 작거나 같은 자연수이다.
 * 
 * 1번째 집을 칠하는 최소비용은 3색 중 비용이 가장 작은 값이다.
 * i번째 집까지 칠하는 최소비용은 i - 1번째까지 집을 칠하는 최소비용에 i-1번째 집과 다른 색 중 최소 비용을 더한 값이다.
 * dp[idx][0] : idx번째 집을 빨강으로 칠한 경우... idx - 1번째 집을 초록과 파랑으로 칠한 비용 중 최솟값 에 idx번째 집을 빨강으로 칠하는 비용을 더함
 * dp[idx][1] : idx번째 집을 초록으로 칠한 경우... idx - 1번째 집을 빨강과 파랑으로 칠한 비용 중 최솟값 에 idx번째 집을 초록으로 칠하는 비용을 더함
 * dp[idx][2] : idx번째 집을 파랑으로 칠한 경우... idx - 1번째 집을 빨강과 초록으로 칠한 비용 중 최솟값 에 idx번째 집을 파랑으로 칠하는 비용을 더함
 * @author semin.kim
 *
 */

public class BOJ_1149_RGB거리_김세민 {

	static BufferedReader br;
	static StringTokenizer st;

	static int houseCount; // 집의 개수

	static int[][] houses;
	static int[][] dp;
	static int minCost = Integer.MAX_VALUE;
	
	private static void calculateCost() {
		// dp[idx][0] : idx번째 집을 빨강으로 칠한 경우... idx - 1번째 집을 초록과 파랑으로 칠한 비용 중 최솟값 에 idx번째 집을 빨강으로 칠하는 비용을 더함
		// dp[idx][1] : idx번째 집을 초록으로 칠한 경우... idx - 1번째 집을 빨강과 파랑으로 칠한 비용 중 최솟값 에 idx번째 집을 초록으로 칠하는 비용을 더함
		// dp[idx][2] : idx번째 집을 파랑으로 칠한 경우... idx - 1번째 집을 빨강과 초록으로 칠한 비용 중 최솟값 에 idx번째 집을 파랑으로 칠하는 비용을 더함
		for(int idx = 1; idx <= houseCount; idx++) {
			dp[idx][0] = Math.min(dp[idx-1][1], dp[idx-1][2]) + houses[idx][0];
			dp[idx][1] = Math.min(dp[idx-1][0], dp[idx-1][2]) + houses[idx][1];
			dp[idx][2] = Math.min(dp[idx-1][0], dp[idx-1][1]) + houses[idx][2];
		}
		
		// 모든 집을 칠한 후... 최소비용 출력
		minCost = Math.min(dp[houseCount][0], Math.min(dp[houseCount][1], dp[houseCount][2]));
		System.out.println(minCost);
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		houseCount = Integer.parseInt(br.readLine().trim());
		houses = new int[houseCount+1][3];
		dp = new int[houseCount+1][3];
		
		// 각각의 집마다 빨강, 초록, 파랑으로 칠하는 비용 입력
		for(int houseIdx = 1; houseIdx <= houseCount; houseIdx++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int colorIdx = 0; colorIdx < 3; colorIdx++) {
				houses[houseIdx][colorIdx] = Integer.parseInt(st.nextToken());
			}
		}
		
		calculateCost();
	}
}
