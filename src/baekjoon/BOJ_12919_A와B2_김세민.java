package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * BOJ_12919_A와B 2
 * 두 문자열 S와 T가 주어졌을 때, S를 T로 바꾸는 게임이다. 문자열을 바꿀 때는 다음과 같은 두 가지 연산만 가능하다.
 * 1.문자열의 뒤에 A를 추가한다.
 * 2.문자열의 뒤에 B를 추가하고 문자열을 뒤집는다.
 * 주어진 조건을 이용해서 S를 T로 만들 수 있는지 없는지 알아내는 프로그램을 작성하시오. 
 * 
 * T를 S로 바꾸기
 * T의 마지막 글자가 A인 경우... 마지막 글자를 제거
 * T의 시작 글자가 B인 경우... 뒤집어서 마지막 글자인 B 제거
 * T가 S와 같아진 경우... 1출력하고 종료
 * T의 길이가 S의 길이보다 짧아진 경우... 만들 수 없음
 * @author semin.kim
 */

public class BOJ_12919_A와B2_김세민 {

	static BufferedReader br;
	static String S, T;
	
	private static void Change(String s, String t) {
		
		if(s.equals(t)) {
			System.out.println(1);
			System.exit(0);
		}
		
		if(s.length() > t.length()) {
			return;
		}
		
		if(t.charAt(t.length()-1) == 'A') {
			String temp = t;
			temp = t.substring(0, t.length() - 1);
			Change(s, temp);
		}
		
		if(t.charAt(0) == 'B') {
			StringBuilder sb = new StringBuilder(t);
			String temp = sb.reverse().toString();
			temp = temp.substring(0, t.length() - 1);

			Change(s, temp);
		}
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		S = br.readLine().trim();
		T = br.readLine().trim();
		
		Change(S, T);
		System.out.println(0);
	}
}
