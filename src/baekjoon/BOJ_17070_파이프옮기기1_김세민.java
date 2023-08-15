package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ_17070_파이프옮기기1
 * 1.집의 크기와 집의 상태를 입력받는다.
 * 2.깊이 우선 탐색을 통해 파이프를 이동시키는 방법의 수를 구한다.
 * @author semin.kim
 */

public class BOJ_17070_파이프옮기기1_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int house_size; // 집의 크기
	static int[][] house; // 집의 상태를 저장할 2차원 배열
	
	public static void dfs(int col, int row, int dir) { // col: 세로, row: 가로, dir: 방향(1:가로, 2:세로, 3:대각선)
		house[col][row]++;
		if(dir == 1 || dir == 3) { // 가로 방향이거나 대각선 방향인 경우...
			// 벽이 아니고 범위를 넘지 않는 경우...
			if(row < house_size && house[col][row + 1] != -1) {
				dfs(col, row + 1, 1); // 가로 방향으로 이동 
			}
		}
		if(dir == 2 || dir == 3) { // 세로 방향이거나 대각선 방향인 경우...
			// 벽이 아니고 범위를 넘어서지 않는 경우...
			if(col < house_size && house[col + 1][row] != -1) {
				dfs(col + 1, row, 2); // 세로 방향으로 이동
			}
		}
		// 벽이 아니고 범위를 넘어서지 않는 경우...
		if(col < house_size && row < house_size && house[col+1][row] != -1 && house[col][row+1] != -1 && house[col+1][row+1]!=-1) {
			dfs(col + 1, row + 1, 3); // 대각선 방향으로 이동
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		house_size = Integer.parseInt(br.readLine().trim()); // 집의 크기 입력
		
		house = new int[house_size+1][house_size+1]; // 집의 번호는 1,1부터 시작
		
		// 집의 상태 입력
		for(int colIdx = 1; colIdx <= house_size; colIdx++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int rowIdx = 1; rowIdx <= house_size; rowIdx++) {
				house[colIdx][rowIdx] = Integer.parseInt(st.nextToken());
				if(house[colIdx][rowIdx] == 1) house[colIdx][rowIdx] = -1; // 벽
			}
		}
		// 파이프의 초기상태는 (1,1)과 (1,2)를 차지하고 방향은 가로
		dfs(1, 2, 1);

		if(house[house_size][house_size] == -1) house[house_size][house_size] = 0;
		System.out.println(house[house_size][house_size]);
	}
}
