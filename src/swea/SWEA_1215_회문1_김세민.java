package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * SWEA_1215_회문1_D3
 * 1.찾아야 하는 회문의 길이를 입혁받는다.
 * 2.8*8 크기의 글자판을 입력받는다.
 * 3.글자판을 가로, 세로로 각각 순회하며 회문의 개수를 구한다.
 * @author semin.kim
 */

public class SWEA_1215_회문1_김세민 {
	
	static BufferedReader br;
	static StringBuilder sb;
	
	static final int T = 10; //테스트 케이스의 개수는 10으로 고정
	static int palindrome_length; //찾아야 하는 회문의 길이
	static final int GRID_SIZE = 8; //글자판의 크기는 8 * 8으로 고정
	static char[][] GRID;
	
	/**
	 * isPalindrome method 구현
	 * 1.문자열을 순회하며 회문인 경우... true 반환
	 * 2.회문이 아닌 경우... false 반환
	 * @param str
	 * @return
	 */
	public static boolean isPalindrome(String str) {
		int len = str.length(); //문자열의 길이
		for(int idx = 0; idx < len / 2; idx++) {
			if(str.charAt(idx) != str.charAt(len - idx - 1)) { //서로 다른 경우...
				return false;
			}
		}
		// 회문인 경우 true 반환
		return true;
	}
	
	public static int findPalindrome() {
		int count = 0; //회문의 개수
		
		// 세로 방향 탐색
		for(int col = 0; col < GRID_SIZE; col++) {
			for(int row = 0; row <= GRID_SIZE - palindrome_length; row++) {
				 String str = "";
				 for(int idx = 0; idx < palindrome_length; idx++) {
					 str += GRID[row+idx][col];
				 }
				 if(isPalindrome(str)) {
					 count++;
				 }
			}
		}
		
		// 가로 방향 탐색
		for(int row = 0; row < GRID_SIZE; row++) {
			for(int col = 0; col <= GRID_SIZE - palindrome_length; col++) {
				 String str = "";
				 for(int idx = 0; idx < palindrome_length; idx++) {
					 str += GRID[row][col+idx];
				 }
				 if(isPalindrome(str)) {
					 count++;
				 }
			}
		}
		
		return count;
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int test_case = 1; test_case <= T; ++test_case) {
			GRID = new char[GRID_SIZE][GRID_SIZE];
			palindrome_length = Integer.parseInt(br.readLine().trim()); //회문의 길이 입력
			for(int col = 0; col < GRID_SIZE; col++) { //글자판 입력
				String[] tmpStrings = br.readLine().split("");
				for(int row = 0; row < GRID_SIZE; row++) {
					GRID[col][row] = tmpStrings[row].charAt(0);
				}
			}
			sb = new StringBuilder();
			sb.append("#").append(test_case).append(" ").append(findPalindrome()); //회문의 개수 출력
			
			System.out.println(sb);
		}
		
		
	}
}
