package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Base64;

/**
 * SWEA_1928_Base64Decoder_D2
 * 1.테스트 케이스의 개수를 입력받는다.
 * 2.인코딩된 문자열을 입력받는다.
 * 3.Base64 라이브러리를 이용하여 디코딩한다.
 * @author semin.kim
 */

public class SWEA_1928_Base64Decoder_김세민 {
	
	static BufferedReader br;
	static StringBuilder sb;
	
	static int T; //테스트 케이스의 개수
	static String encodedString;
	static String decodedString;
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine().trim()); //테스트 케이스의 개수 입력
		for(int test_case = 1; test_case <= T; ++test_case) {
			encodedString = br.readLine().trim();
			decodedString = new String(Base64.getDecoder().decode(encodedString));
			sb = new StringBuilder();
			sb.append("#").append(test_case).append(" ").append(decodedString);
			System.out.println(sb);
		}
	}
}
