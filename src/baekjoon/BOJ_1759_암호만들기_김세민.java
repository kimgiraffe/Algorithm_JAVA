package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BOJ_1759_암호만들기
 * 
 * 서로 다른 L개의 알파벳 소문자로 구성되며 최소 한 개의 모음(a,e,i,o,u)과 최소 두 개의 자음으로 구성
 * 알파벳이 암호에서 증가하는 순서로 배열
 *
 * C개의 알파벳 소문자 중에서 L개를 조합, 모음은 최소 한 개, 자음은 최소 두개
 * 사전순으로 출력을 해야하기 때문에 정렬 필요
 * @author semin.kim
 */

public class BOJ_1759_암호만들기_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static int length, alphabet_cnt; //암호의 길이, 문자의 종류

	static char[] charList; 
	static char[] password;

	public static void combination(int currentIdx, int selectIdx, StringBuilder sb) {
		if(selectIdx == length) { // 비밀번호 생성 완료
			int vowel_cnt = 0, consonant_cnt = 0; // 모음의 개수, 자음의 개수를 0으로 초기화
			// 생성된 비밀번호의 모음의 개수와 자음의 개수를 세기
			for(int idx = 0; idx < length; idx++) {
				if(password[idx] == 'a' || password[idx] == 'e' || password[idx] == 'i' ||
						password[idx] == 'o' || password[idx] == 'u') {
					vowel_cnt++;
				}
				else {
					consonant_cnt++;
				}
			}
			// 모음의 개수가 1미만이거나 자음의 개수가 2 미만인 경우...출력하지 않는다.
			if(vowel_cnt < 1 || consonant_cnt < 2) return;
			// 생성된 비밀번호 출력
			for(int idx = 0; idx < length; idx++) {
				sb.append(password[idx]);
			}
			sb.append("\n");
			return;
		}

		if(currentIdx == alphabet_cnt) {
			return;
		}

		// 선택하고 넘어가기
		password[selectIdx] = charList[currentIdx];
		combination(currentIdx + 1, selectIdx + 1, sb);

		// 선택하지 않고 넘어가기
		combination(currentIdx + 1, selectIdx, sb);

	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());
		sb = new StringBuilder();

		length = Integer.parseInt(st.nextToken());
		alphabet_cnt = Integer.parseInt(st.nextToken());

		charList = new char[alphabet_cnt];
		password = new char[length];

		st = new StringTokenizer(br.readLine().trim());
		for(int idx = 0; idx < alphabet_cnt; idx++) {
			charList[idx] = st.nextToken().charAt(0);
		}
		// 입력받은 문자들을 사전 순으로 정렬
		Arrays.sort(charList);

		combination(0, 0, sb);
		System.out.print(sb);
	}
}
