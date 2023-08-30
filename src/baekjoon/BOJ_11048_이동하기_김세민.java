package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ_11048_이동하기
 * N * M크기의 미로, (1, 1)에서 시작하여 오른쪽, 아래, 대각선 오른쪽 아래 3방향으로 이동 가능
 * 가져올 수 있는 사탕의 개수의 최댓값
 * 
 * (i, j) 위치 전에 올 수 있는 위치는 (i, j-1), (i-1, j), (i-1, j-1)
 * 사탕을 최대한 많이 가져오기 위에서는 직전에 올 수 있는 위치에서 최대로 사탕을 가져온 후 현재 칸에 있는 사탕읖 가져온다.
 * @author semin.kim
 *
 */

public class BOJ_11048_이동하기_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	
	static int colSize, rowSize;
	static int[][] map, candy;
	
	private static void calculateMaxCandyCount() {
		for(int colIdx = 1; colIdx <= colSize; colIdx++) {
			for(int rowIdx = 1; rowIdx <= rowSize; rowIdx++) {
				// 현재 위치 직전의 위치에서 올 수 있는 3가지 중 가장 많은 사탕을 가져오고 현재 있는 칸에서 사탕을 가져온다.
				candy[colIdx][rowIdx] = Math.max(candy[colIdx][rowIdx-1], Math.max(candy[colIdx-1][rowIdx], candy[colIdx-1][rowIdx-1])) + map[colIdx-1][rowIdx-1];
			}
		}
		
		System.out.println(candy[colSize][rowSize]);
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());
		
		colSize = Integer.parseInt(st.nextToken());
		rowSize = Integer.parseInt(st.nextToken());
		
		map = new int[colSize][rowSize];
		candy = new int[colSize+1][rowSize+1]; // 현재 칸에서 가져올 수 있는 사탕의 최댓값 저장
		
		for(int colIdx = 0; colIdx < colSize; colIdx++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
				map[colIdx][rowIdx] = Integer.parseInt(st.nextToken());
			}
		}
		calculateMaxCandyCount();
		
	}
}
