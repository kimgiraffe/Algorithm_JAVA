package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ_2239_스도쿠
 * [문제]
 * 9 * 9 크기의 보드가 있을 때, 각 행과 각 열, 9개의 3 * 3 크기의 보드에 1부터 9까지의 숫자가 중복 없이 나타나도록 보드를 채우자.
 * 답이 여러 개 있다면 그 중 사전식으로 앞서는 것을 출력한다.
 * 
 * [입력]
 * 9개의 줄에 9개의 숫자로 보드가 입력된다. 아직 숫자가 채워지지 않은 칸에는 0이 주어진다.
 * 
 * [출력]
 * 9개의 줄에 9개의 숫자로 답을 출력한다. 답이 여러 개 있다면 그 중 사전식으로 앞서는 것을 출력한다. 즉, 81자리의 수가 제일 작은 경우를 출력한다.
 * 
 * [풀이]
 * (0,0)부터 스도쿠를 숫자 1부터 9까지 채워본다.(사전순으로 앞서야 하므로 1부터 채우기)
 * 유효한 스도쿠인 경우 오른쪽으로 한 칸 이동하여 채운다.
 * 가로로 다 채운 경우 다음 줄 채우기 시작한다.
 * 모든 칸을 채운 경우 완성된 스도쿠를 출력한다.
 * 
 * @author semin.kim
 *
 */

public class BOJ_2239_스도쿠_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static final int SUDOKU_SIZE = 9;
	static final int GRID_SIZE = 3;
	static int[][] sudoku;
	static boolean[] rows, cols, grids;
	static boolean ok;
	
	private static boolean isValid(int col, int row, int num) {
		// 가로 세로에서 이미 존재 숫자가 존재하는 경우...
		for(int idx = 0; idx < SUDOKU_SIZE; idx++) {
			if(sudoku[col][idx] == num || sudoku[idx][row] == num) {
				return false;
			}
		}
		
		// 3 * 3 크기의 보드 내에서 중복되는 숫자가 존재하는 경우...
		int gridCol = (col / 3) * 3;
		int gridRow = (row / 3) * 3;
		
		for(int colIdx = gridCol; colIdx < gridCol + 3; colIdx++) {
			for(int rowIdx = gridRow; rowIdx < gridRow + 3; rowIdx++) {
				if(sudoku[colIdx][rowIdx] == num) {
					return false;
				}
			}
		}
		
        return true;
    }
    
	private static void fill(int col, int row) {
		// 유효한 스도쿠인 경우...
		if(ok) {
			return;
		}
		
		// 스도쿠 생성 완료
		if(col == SUDOKU_SIZE) {
			printSudoku();
			ok = true;
			return;
		}
		
		if(sudoku[col][row] > 0) { // 이미 채운 칸인 경우...
			if(row == 8) { // 가로로 숫자를 다 채운 경우...
				fill(col + 1, 0); // 다음 줄로 이동하여 채우기
			}
			else { // 오른쪽으로 한 칸 이동하여 채우기
				fill(col, row + 1);
			}
		} else { // 아직 채워지지 않은 칸인 경우...
			for(int num = 1; num <= SUDOKU_SIZE; num++) {
				if(isValid(col, row, num)) { // 유효한 스도쿠인 경우...
					sudoku[col][row] = num; // 숫자를 채우기
					if(row == 8) { // 가로로 숫자를 다 채운 경우...
						fill(col + 1, 0); // 다음 줄로 이동하여 채우기
					}
					else { // 오른쪽으로 한 칸 이동하여 채우기
						fill(col, row + 1);
					}
					sudoku[col][row] = 0; // 다시 돌려놓기
				}
			}
		}
		
	}
	
	private static void printSudoku() {
		for(int col = 0; col < SUDOKU_SIZE; col++) {
			for(int row = 0; row < SUDOKU_SIZE; row++) {
				sb.append(sudoku[col][row]);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		sudoku = new int[SUDOKU_SIZE][SUDOKU_SIZE];
		
		for(int col = 0; col < SUDOKU_SIZE; col++) {
			String input = br.readLine().trim();
			for(int row = 0; row < SUDOKU_SIZE; row++) {
				sudoku[col][row] = input.charAt(row) - '0';
			}
		}
		fill(0, 0);
	}
}
