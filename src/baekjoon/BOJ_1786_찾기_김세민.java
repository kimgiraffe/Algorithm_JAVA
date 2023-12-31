package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * BOJ_1786_찾기
 * 
 * @author semin.kim
 *
 * 1. 패턴을 이용하여 부분일치 테이블 배열을 작성한다.
 * 2. KMP 알고리즘을 이용하여 패턴이 등장하는 횟수와 text에서 패턴이 등장하는 위치를 저장한다.
 *
 */

public class BOJ_1786_찾기_김세민 {
	static BufferedReader br;
	static StringBuilder sb;
	
	static String text, pattern;
	static int textLength, patternLength; // 텍스트의 길이, 패턴의 길이
	static int[] failure; // 맨 앞부터 해당 인덱스까지 길이가 2이상인 부분문자열 중 접두사이면서 접미사인 최대 문자열의 길이
	static int matchCount; // 패턴 매칭 횟수
	static int[] match;
	
	/**
	 * 부분일치 테이블 생성 메서드
	 */
	private static void generateFailFunc() {
		int prefixIdx, suffixIdx;
		
		failure[0] = -1;
		for(suffixIdx = 1; suffixIdx < patternLength; suffixIdx++) {
			prefixIdx = failure[suffixIdx - 1];
			
			// 일치할 때까지 접두부 이동
			while((pattern.charAt(suffixIdx) != pattern.charAt(prefixIdx + 1)) && (prefixIdx >= 0)) {
				prefixIdx = failure[prefixIdx];
			}
			if(pattern.charAt(suffixIdx) == pattern.charAt(prefixIdx + 1)) { // 접두부와 접미부가 일치하는 경우...
				failure[suffixIdx] = prefixIdx + 1; 
			} else { // 접두부와 접미부가 일치하지 않는 경우...
				failure[suffixIdx]= -1; 
			}
		}
	}
	
	private static void KMP() {
		int textPointer = 0, patternPointer = 0; // text포인터, pattern포인터
		
		while(textPointer < textLength && patternPointer < patternLength) {
			if(text.charAt(textPointer) == pattern.charAt(patternPointer)) { // 문자가 일치하는 경우...
				textPointer++; patternPointer++; // 텍스트 포인터와 패턴 포인터를 오른쪽으로 1 이동
			}
			else if(patternPointer == 0) {
				textPointer++;
			}
			else {
				patternPointer = failure[patternPointer - 1] + 1; // 패턴 포인터 이동
			}
			if(patternPointer == patternLength) { // 패턴 매칭 성공
				matchCount++; // 패턴이 나타나는 횟수 1 증가
				match[matchCount-1] = textPointer - patternLength + 1;
				//sb.append(textPointer - patternLength + 1).append(" "); // 패턴이 나타나는 위치
				patternPointer = failure[patternPointer - 1] + 1; // 패턴 포인터 이동
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		text = br.readLine();
		pattern = br.readLine();
		textLength = text.length();
		patternLength = pattern.length();
		
		failure = new int[patternLength];
		match = new int[textLength];
		
		generateFailFunc(); // 부분일치 테이블 생성
		KMP(); 
		
		sb.append(matchCount).append("\n");
		for(int idx = 0; idx < matchCount; idx++) {
			sb.append(match[idx]).append(" ");
		}
		
		System.out.println(sb);
	}
}
