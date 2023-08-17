package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * BOJ_9663_NQueen
 * 1.체스판의 크기 N을 입력받는다.
 * 2.퀸을 1행부터 N행까지 배치한다.
 * 	2-1.다응 행에 배치할 수 없는 경우... 직전까지 놓아진 상태로 복귀한다.
 * 	2-2.모든 퀸을 배치한 경우... 퀸을 놓는 방법의 수를 1 증가한다.
 * 	2-3.1열부터 N열까지 시도하여 다음 행에 퀸을 배치한다.
 * 3.퀸을 놓는 방법의 수를 출력한다.
 * @author semin.kim
 *
 */

public class BOJ_9663_NQueen_김세민 {

	static BufferedReader br;
	static int N, answer; // 체스판의 크기, 퀸을 놓는 방법의 수
	static int[] column;
	
	private static void placeQueen(int row) {
		// 가지치기 : 직전까지 놓아진 상태로
		if(!isAvailable(row - 1)) {
			return;
		}
		// 기저조건
		if(row > N) {
			answer++;
			return;
		}
		// 유도파트
		for(int col = 1; col <= N; col++) {  // 1열부터 N열까지 시도
			column[row] = col;
			placeQueen(row + 1); // 다음 행에 퀸 배치
		}
	}
	
	private static boolean isAvailable(int row) { // row : 마지막에 놓아진 퀸의 행
		for(int idx = 1; idx < row; idx++) {
			if(column[idx] == column[row] || row - idx == Math.abs(column[row] - column[idx])) {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine().trim()); // 체스판의 크기 입력
		column = new int[N+1];
		
		placeQueen(1);
		System.out.println(answer);
	}
}
