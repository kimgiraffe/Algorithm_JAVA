package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * SWEA_9317_석찬이의받아쓰기_D3
 * 1.테스트 케이스의 개수를 입력받는다.
 * 2.문자열의 길이를 입력받는다.
 * 3.석찬이가 따라 적어야 하는 문자열과 따라 적은 문자열을 입력받는다.
 * 4.문자열을 순회하며 따라 적어야 하는 문자열과 따라 적은 문자열을 비교한다. (대소문자 구분)
 * 5.맞게 받아 적은 문자의 개수를 출력한다.
 * @author semin.kim
 */

public class SWEA_9317_석찬이의받아쓰기_김세민 {
	
	static BufferedReader br;
	static StringBuilder sb;
	
	static int T; // 테스트 케이스의 개수
	static int stringLength; // 문자열의 길이
	static char[] correct_string;
	static char[] written_string;
	
	public static int compareString() {
		int correct_chars = 0;
		
		for(int idx = 0; idx < stringLength; idx++) {
			if(correct_string[idx] == written_string[idx]) {
				correct_chars++;
			}
		}
		
		return correct_chars;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine().trim());
		for(int test_case = 1; test_case <= T; ++test_case) {
			stringLength = Integer.parseInt(br.readLine().trim());
			correct_string = new char[stringLength];
			written_string = new char[stringLength];
			
			correct_string = br.readLine().toCharArray();
			written_string = br.readLine().toCharArray();
			sb = new StringBuilder();
			sb.append("#").append(test_case).append(" ").append(compareString());
			System.out.println(sb);
		}
		
	}
}
