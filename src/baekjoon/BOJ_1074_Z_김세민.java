package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ_1074_Z
 * 1.배열의 크기(2ⁿ*2ⁿ), 방문 순서를 구할 행과 열을 입력받는다.
 * 2.배열의 크기를 4등분하여 재귀적으로 순서대로 방문한다.
 * 3.배열의 크기가 0이되면 재귀 호출을 종료한다.
 * 4.입력 받는 행과 열의 방문 순서를 출력한다.
 * @author semin.kim
 *
 */

public class BOJ_1074_Z_김세민 {
	
	static BufferedReader br;
	static StringTokenizer st;
	
	static int arr_size, col, row; //배열의 크기, 방문 순서를 구할 행과 열
	
	public static int fill(int size, int c, int r) {
		if(size == 0) return 0;
		else {
			return 2 * (c % 2) + (r % 2) + 4 * fill(size - 1, c / 2, r / 2); // 2ⁿ*2ⁿ 크기의 배열을 4등분하여 재귀적으로 순서대로 방문
		}
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());
		
		arr_size = Integer.parseInt(st.nextToken()); // 배열의 크기 입력
		col = Integer.parseInt(st.nextToken()); // 방문 순서를 구할 행 입력
		row = Integer.parseInt(st.nextToken()); // 방문 순서를 구할 열 입력
		
		System.out.println(fill(arr_size, col, row));
	}
}