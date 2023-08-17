package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/**
 * BOJ_3109_빵집
 * 1.각 column마다 우상, 우, 우하 순으로 파이프를 놓는다.
 * 2.빵집에 도달한 경우... 놓을 수 있는 파이프라인의 최대 개수를 1 증가한다.
 * 3.놓을 수 없는 경우... 지도 정보를 갱신한다.
 * @author semin.kim
 *
 */

public class BOJ_3109_빵집_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	
	static int row_size, col_size; // 지도의 가로, 세로 크기
	static char[][] map; // 지도 정보를 저장하는 2차원 배열
	static int ways; // 놓을 수 있는 파이프라인의 최대 개수
	
	private static boolean placePipe(int col, int row) {
		if(map[col][row] != '.') { // 파이프를 놓을 수 없는 경우...
			return false;
		}
		
		if(row == row_size - 1) { // 빵집에 도달한 경우...
			ways++; // 가능한 파이프라인 개수 1 증가
			return true;
		}
		map[col][row] = 'x';
		// 우상, 우, 우하 순으로 파이프를 놓아야 최대한 많이 파이프라인을 설치할 수 있다.
		return (placePipe(col - 1, row + 1) || placePipe(col, row + 1) || placePipe(col + 1, row + 1));
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());
		
		col_size = Integer.parseInt(st.nextToken()); // 세로 크기 입력
		row_size = Integer.parseInt(st.nextToken()); // 가로 크기 입력
		
		map = new char[col_size + 3][row_size + 3];
		
		// 빵집 근처의 모습 입력
		for(int colIdx = 1; colIdx <= col_size; colIdx++) {
			String tmp = br.readLine().trim();
			for(int rowIdx = 0; rowIdx < row_size; rowIdx++) {
				map[colIdx][rowIdx] = tmp.charAt(rowIdx);
			}
		}
		
		// 각 column마다 파이프라인을 설치할 수 있는지 확인
		for(int colIdx = 1; colIdx <= col_size; colIdx++) {
			placePipe(colIdx, 0);
		}
		System.out.println(ways);
	}
}
