package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ_15683_감시
 * 1.사무실의 세로, 가로 크기를 입력받는다.
 * 2.사무실 각 칸의 정보를 입력받는다.
 * 3.
 * @author semin.kim
 *
 */

public class BOJ_15683_감시_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	
	static int col_size, row_size;
	static int[][] office;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());
		
		col_size = Integer.parseInt(st.nextToken());
		row_size = Integer.parseInt(st.nextToken());
		
		office = new int[col_size][row_size];
		
		for(int colIdx = 0; colIdx < col_size; colIdx++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int rowIdx = 0; rowIdx < row_size; rowIdx++) {
				office[colIdx][rowIdx] = Integer.parseInt(st.nextToken());
				
			}
		}
		
		
	}
}
