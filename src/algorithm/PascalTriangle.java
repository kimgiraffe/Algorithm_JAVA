package algorithm;

import java.io.IOException;

/**
 * 파스칼의 삼각형
 * 
 * 1. nCn = nC0 = 1 이므로 row와 col이 같거나 col이 0인경우 1
 * 2. 이항계수의 성질 nCr = n-1Cr-1 + n-1Cr을 활용하여 나머지 파스칼 삼각형을 채운다.
 * 3. n >= r인 경우에 대하여 파스칼 삼각형 출력
 * 
 * @author semin.kim
 *
 */

public class PascalTriangle {

	static StringBuilder sb;
	
	static final int MAX = 31;
	static int[][] pascalTriangle = new int[MAX][MAX]; // row에는 n, col에는 r이 들어감
	
	private static void makeTriangle() {
		for(int row = 0; row < MAX; row++) {
			for(int col = 0; col < MAX; col++) {
				if(row == col || col == 0) { // nCn = nC0 = 1
					pascalTriangle[row][col] = 1;
				}
				else if(row >= 1){ // nCr = n-1Cr-1 + n-1Cr
					pascalTriangle[row][col] = pascalTriangle[row-1][col-1] + pascalTriangle[row-1][col];
				}
			}
		}
	}
	
	private static void printTriangle() {
		for(int row = 0; row < MAX; row++) {
			for(int col = 0; col < MAX; col++) {
				if(row < col) continue; // n < r인 경우... nCr을 정의할 수 없음
				sb.append(pascalTriangle[row][col]).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.print(sb);
	}
	
	public static void main(String[] args) throws IOException {
		sb = new StringBuilder();
		
		makeTriangle();
		
		printTriangle();
	}
}
