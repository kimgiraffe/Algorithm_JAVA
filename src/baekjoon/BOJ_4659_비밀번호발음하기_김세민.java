package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ_4659_비밀번호발음하기
 * 1.모음(a,e,i,o,u) 하나를 반드시 포함하여야 한다.
 * 2.모음이 3개 혹은 자음이 3개 연속으로 오면 안 된다.
 * 3.같은 글자가 연속적으로 두번 오면 안되나, ee 와 oo는 허용한다.
 * 마지막 테스트 케이스는 end이며, 패스워드는 한글자 이상 20글자 이하의 문자열이다. 또한 패스워드는 대문자를 포함하지 않는다.
 * 
 * 입력으로 주어지는 각 비밀번호에 대해 1~3의 조건을 차례대로 확인한다.
 * 조건을 만족하지 않는 경우 다음 조건을 확인할 필요 없이 바로 "not acceptable" 출력
 * 
 * @author semin.kim
 */

public class BOJ_4659_비밀번호발음하기_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static String password;
	static final char[] VOWELS = {'a', 'e', 'i', 'o', 'u'};
	static final String IS_ACCEPTABLE = " is acceptable.";
	static final String IS_NOT_ACCEPTABLE = " is not acceptable.";

	/**
	 * 모음인지 확인하는 메서드
	 * 모음인 경우... true 반환, 자음인 경우... false 반환
	 * @param idx
	 * @return
	 */
	private static boolean isVowel(int idx) {
		for(int vowelIdx = 0; vowelIdx < 5; vowelIdx++) {
			if(password.charAt(idx) == VOWELS[vowelIdx]) {
				return true;
			}
		}
		return false;
	}

	private static void CheckPassword() {
		sb.append("<").append(password).append(">");
		int vowelCount = 0; // 모음의 개수
		for(int idx = 0; idx < password.length(); idx++) {
			for(int vowelIdx = 0; vowelIdx < 5; vowelIdx++) {
				if(password.charAt(idx) == VOWELS[vowelIdx]) {
					vowelCount++;
				}
			}
		}
		// 모음이 없는 경우...
		if(vowelCount == 0) {
			sb.append(IS_NOT_ACCEPTABLE);
			System.out.println(sb);
			return;
		}
		// 모음이 3개 혹은 자음이 3개 연속으로 오는 경우...
		for(int idx = 2; idx < password.length(); idx++) {
			if(isVowel(idx) && isVowel(idx - 1) && isVowel(idx - 2)) { // 모음이 3개 연속으로 오는 경우...
				sb.append(IS_NOT_ACCEPTABLE);
				System.out.println(sb);
				return;
			}
			else if(!isVowel(idx) && !isVowel(idx - 1) && !isVowel(idx - 2)) { // 자음이 3개 연속으로 오는 경우...
				sb.append(IS_NOT_ACCEPTABLE);
				System.out.println(sb);
				return;
			}
		}

		// 같은 글자가 연속적으로 두번 오는 경우(ee, oo는 허용)
		for(int idx = 1; idx < password.length(); idx++) {
			if(password.charAt(idx) != 'e' && password.charAt(idx) != 'o') {
				if(password.charAt(idx) == password.charAt(idx - 1)){
					sb.append(IS_NOT_ACCEPTABLE);
					System.out.println(sb);
					return;
				}
			}
		}
		// 1~3의 모든 조건을 만족하는 경우...
		sb.append(IS_ACCEPTABLE);
		System.out.println(sb);
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		while(true) {
			sb = new StringBuilder();
			password = br.readLine().trim();
			if(password.equals("end")) {
				break;
			}
			CheckPassword();
		}
	}
}
