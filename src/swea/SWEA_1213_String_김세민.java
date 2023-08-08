package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * SWEA_1213_String_D3
 * 1.테스트 케이스 번호를 입력받는다.
 * 2.검색하는 문자열과 문장을 입력받는다.
 * 3.문장을 순회하며 검색하는 문자열의 길이만큼의 부분 문자열과 동일하다면 검색에 성공한다.
 * 4.찾은 문자열의 개수를 출력한다.
 * @author semin.kim
 */

public class SWEA_1213_String_김세민 {
	
	static BufferedReader br;
	static StringBuilder sb;
	
	static final int T = 10; //테스트 케이스의 개수는 10으로 고정
	static String searchString;
	static String sentenceString;
	static int count;
	
	public static void search() {
		int search_len = searchString.length(); //검색할 문자열의 길이
		
		for(int idx = 0; idx+search_len <= sentenceString.length(); idx++) {
			if(sentenceString.substring(idx, idx + search_len).equals(searchString)) { //검색에 성공한 경우...
				count++; //개수 증가
			}
		}
		sb.append(count);
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int test_case = 1; test_case <= T; test_case++) {
			count = 0; //찾은 문자열의 개수를 0으로 초기화
			br.readLine().trim();
			searchString = br.readLine().trim(); //검색할 문자열 입력
			sentenceString = br.readLine().trim(); //문장 입력
			
			sb = new StringBuilder();
			sb.append("#").append(test_case).append(" ");
			search(); //검색
			System.out.println(sb);
		}
	}
}
