package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ_17070_파이프옮기기1
 * 현재 칸이 (i, j)이고 현재 파이프가 가로 방향(0)으로 놓여있는 경우... 직전 칸으로 가능한 경우는 (i, j-1, 0), (i-1, j-1, 2)
 * 현재 칸이 (i, j)이고 현재 파이프가 세로 방향(1)으로 놓여있는 경우... 직전 칸으로 가능한 경우는 (i-1, j, 1), (i-1, j-1, 2)
 * 현재 칸이 (i, j)이고 현재 파이프가 대삭선 방향(2)으로 놓여있는 경우... 직전 칸으로 가능한 경우는 (i, j-1, 0), (i-1, j, 1), (i-1, j-1, 2)
 * 
 * 각 방향에 대해 해당 위치까지 이동시키는 방법의 수를 저장
 * 
 * @author semin.kim
 */

public class BOJ_17070_파이프옮기기1_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static int houseSize; // 집의 크기
	static int[][] house; // 집의 상태를 저장할 2차원 배열
	static int[][][] dp;

	private static void calculateWaysOfMovingPipe() {

		for(int colIdx = 1; colIdx <= houseSize; colIdx++) {
			for(int rowIdx = 1; rowIdx <= houseSize; rowIdx++) {
				if(colIdx == 1 && rowIdx <= 2) continue;
				if(house[colIdx][rowIdx] == 1) continue;

				/// 가로
				if(house[colIdx][rowIdx-1] == 1) {
					dp[colIdx][rowIdx][0] = 0;
				}
				else {
					dp[colIdx][rowIdx][0] = dp[colIdx][rowIdx-1][0] + dp[colIdx][rowIdx-1][2];
				}
				// 세로
				if(house[colIdx-1][rowIdx] == 1) {
					dp[colIdx][rowIdx][1] = 0;
				}	
				else {
					dp[colIdx][rowIdx][1] = dp[colIdx-1][rowIdx][1] + dp[colIdx-1][rowIdx][2];
				}

				// 대각선
				if(house[colIdx-1][rowIdx] == 1 || house[colIdx][rowIdx-1] == 1) {
					dp[colIdx][rowIdx][2] = 0;
				}
				else {
					dp[colIdx][rowIdx][2] = dp[colIdx-1][rowIdx-1][0] + dp[colIdx-1][rowIdx-1][1] + dp[colIdx-1][rowIdx-1][2];
				}
			}
		}
		System.out.println((dp[houseSize][houseSize][0]+dp[houseSize][houseSize][1]+dp[houseSize][houseSize][2]));
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		houseSize = Integer.parseInt(br.readLine().trim()); // 집의 크기 입력

		house = new int[houseSize+1][houseSize+1];
		dp = new int[houseSize+1][houseSize+1][3];

		// 집의 상태 입력
		for(int colIdx = 1; colIdx <= houseSize; colIdx++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int rowIdx = 1; rowIdx <= houseSize; rowIdx++) {
				house[colIdx][rowIdx] = Integer.parseInt(st.nextToken());
			}
		}

		// 가장 처음 파이프는 (1,1), (1,2)를 차지하고 있고, 방향은 가로
		dp[1][2][0] = 1;
		dp[1][2][1] = 0;
		dp[1][2][2] = 0;
		
		calculateWaysOfMovingPipe();

	}
}
