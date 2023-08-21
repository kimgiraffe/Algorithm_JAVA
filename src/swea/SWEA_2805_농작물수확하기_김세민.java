package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 2805_농작물수확하기_D3
 * @author semin.kim
 * 1. 농장의 크기와 농작물의 가치를 입력받는다.
 * 2. 농작물의 가치를 저장한 2차원 배열을 순회하며 수익을 구한다.
 */

public class SWEA_2805_농작물수확하기_김세민 {

	static BufferedReader br;
	static StringBuilder sb;
	
	static int T; // 테스트 케이스의 개수
	static int size; // 농장의 크기
	static int[][] farm; // 농장물의 가치를 저장할 배열
	
	/**
	 * calculateValue method 구현
	 * 1. farm 배열을 순회하며 농장의 규칙에 부합하면
	 * 2. totalValue에 더한다. 
	 * @return totalValue 농장의 규칙에 따라 얻을 수 있는 수익
	 */
	public static int calculateValue() {
		int totalValue = 0;
		for(int col = 0; col < size; col++) {
			for(int row =0; row < size; row++) {
				if(col - row <= size / 2 && col - row >= - size / 2 && col + row >= size / 2 && col + row < size * 3 / 2) {
					totalValue += farm[col][row];
				}
			}
		}
		return totalValue;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		T = Integer.parseInt(br.readLine().trim()); //테스트 케이스 개수 입력
		for(int test_case = 1; test_case <= T; test_case++) {
			size = Integer.parseInt(br.readLine().trim()); // 농장 크기 입력
			farm = new int[size][size];
			
			for(int col = 0; col < size; col++) { // 농장물의 가치 입력
				String tmp = br.readLine().trim();
				for(int row = 0; row < size; row++) {
					farm[col][row] = tmp.charAt(row) - '0';
				}
			}
			
			System.out.println("#" + test_case + " " + calculateValue());
		}
	}

}