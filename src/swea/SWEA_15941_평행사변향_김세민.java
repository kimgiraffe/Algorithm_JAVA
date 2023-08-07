package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * SWEA_15941_평행사변형
 * 1. 테스트 케이스의 개수를 입력받는다.
 * 2. 변의 길이를 입력받는다.
 * 3. 변의 길이가 고정일 때, 넓이가 가장 큰 평행사변형은 정사각형이다.
 * 4. 정사각형의 넓이(변의 길이 * 변의 길이)를 출력한다.
 * @author semin.kim
 */

public class SWEA_15941_평행사변향_김세민 {
	
	static BufferedReader br; // 입력
	static StringBuilder sb; // 출력
	
	static int T; //테스트 케이스의 개수
	static int length; //변의 길이
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine().trim()); //테스트 케이스의 개수 입력
		for(int test_case = 1; test_case <= T; ++test_case) {
			length = Integer.parseInt(br.readLine().trim()); //평행사변형의 한 변의 길이 입력
			sb = new StringBuilder();
			sb.append("#").append(test_case).append(" ").append(length * length); // 정사각형의 넓이 출력
			System.out.println(sb);
		}
	}
}
