package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ_16926_배열돌리기1
 * N * M크기의 배열을 R번 반시계 방향으로 돌리자
 * 
 * 1. 가장 바깥 행과 열에 대하여 배열을 4부분으로 나누어 반시계 방향으로 돌린다.
 * 	1-1. 2 <= i <= N, j = 1 -> (i, j) = (i - 1, j)
 * 	1-2. 2 <= j <= M; i = N -> (i, j) = (i, j - 1)
 * 	1-3. N-1 >= i >= 1; j = M -> (i, j) = (i + 1, j)
 * 	1-4. M-1 >= j >= 1; i = 1 -> (i, j) = (i, j + 1)
 * 2. 행과 열의 시작 끝 범위가 각각 1 증가, 감소한다.
 * 3. 더 이상 회전을 할 수 없을 때까지 반복
 * 4. 다음 회전을 위해 한 번 회전한 배열을 원본 배열로 복사
 * 5. 회전 횟수만큼 1~4 반복
 * 
 * @author semin.kim
 *
 */

public class BOJ_16926_배열돌리기1_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int colSize, rowSize; // 배열의 세로, 가로 크기
	static int rotateCount; // 회전 횟수
	static int[][] arr, temp; // 입력으로 주어진 2차원 배열, 회전을 위해 사용할 임시 배열
	
	private static void rotateArray(int colCount, int rowCount) {
		if(colCount == colSize / 2 || rowCount == rowSize / 2) { // 모든 행과 열에 대하여 회전을 완료한 경우...
			return; // 중단
		}
		// 2 <= i <= N, j = 1 -> (i, j) = (i - 1, j)
		for(int colIdx = 2 + colCount; colIdx <= colSize - colCount; colIdx++) {
			temp[colIdx][1+rowCount] = arr[colIdx-1][1+rowCount];
		}
		// 2 <= j <= M; i = N -> (i, j) = (i, j - 1)
		for(int rowIdx = 2 + rowCount; rowIdx <= rowSize - rowCount; rowIdx++) {
			temp[colSize-colCount][rowIdx] = arr[colSize-colCount][rowIdx-1];
		}
		// N-1 >= i >= 1; j = M -> (i, j) = (i + 1, j)
		for(int colIdx = colSize - 1 - colCount; colIdx >= 1 + colCount; colIdx--) {
			temp[colIdx][rowSize-rowCount] = arr[colIdx+1][rowSize-rowCount];
		}
		// M-1 >= j >= 1; i = 1 -> (i, j) = (i, j + 1)
		for(int rowIdx = rowSize - 1 - rowCount; rowIdx >= 1 + rowCount; rowIdx--) {
			temp[1+colCount][rowIdx] = arr[1+colCount][rowIdx+1];
		}
		// 다음 행, 열로 이동
		rotateArray(colCount+1, rowCount+1);
	}
	
	/**
	 * 다음 회전을 위해 원본 배열을 temp배열로 갱신
	 */
	private static void copyTempToArray() {
		for(int col = 1; col <= colSize; col++) {
			for(int row = 1; row <= rowSize; row++) {
				arr[col][row] = temp[col][row];
			}
		}
	}
	
	/**
	 * 배열을 출력
	 */
	private static void printArray() {
		for(int col = 1; col <= colSize; col++) {
			for(int row = 1; row <= rowSize; row++) {
				sb.append(temp[col][row]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());
		sb = new StringBuilder();
		
		colSize = Integer.parseInt(st.nextToken());
		rowSize = Integer.parseInt(st.nextToken());
		rotateCount = Integer.parseInt(st.nextToken());
		
		arr = new int[colSize + 1][rowSize + 1];
		temp = new int[colSize + 1][rowSize + 1];
		
		for(int col = 1; col <= colSize; col++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int row = 1; row <= rowSize; row++) {
				arr[col][row] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int iter = 1; iter < rotateCount; iter++) {
			rotateArray(0, 0);
			copyTempToArray();
		}
		rotateArray(0, 0);
		
		printArray();
	}
}
