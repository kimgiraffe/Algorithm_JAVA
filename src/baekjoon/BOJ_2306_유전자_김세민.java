package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * BOJ_2306_유전자
 * KOI 유전자는 다음의 조건을 만족한다.
 * 1.at 와 gc 는 가장 짧은 길이의 KOI 유전자이다.
 * 2.어떤 X가 KOI 유전자라면, aXt와 gXc도 KOI 유전자이다. 
 * 예를 들어, agct 와 gaattc는 KOI 유전자이나, tgca 와 cgattc는 KOI 유전자가 아니다.
 * 3.어떤 X와 Y가 KOI 유전자라면, 이 둘을 연결한 XY도 KOI 유전자이다. 
 * 예를 들면, aattgc 또는 atat는 KOI 유전자이나 atcg 또는 tata는 KOI 유전자가 아니다.
 * KOI 유전자는 DNA 서열 중에서 부분 서열로 구성되어 있다. 
 * 부분 서열이란 주어진 서열에서 임의의 위치에 있는 0개 이상의 문자들을 삭제해서 얻어지는 서열이다. 
 * 예를 들면, DNA 서열 acattgatcg에서 두 번째 문자 c와 마지막 문자 g를 삭제하여 생긴 부분 서열 aattgatc는 길이가 8인 KOI 유전자이다. 
 * 그러나 마지막 문자 g를 삭제하여 만들어진 부분 서열 acattgatc는 KOI 유전자가 아니다.
 * 문제는 주어진 DNA 서열의 부분 서열들 중에서 길이가 최대가 되는 KOI 유전자를 찾아 그 길이를 출력하는 것이다.
 * 
 * memo[start][end] start부터 end 인덱스까지의 KOI 유전자의 최대 길이
 * 
 * @author semin.kim
 */

public class BOJ_2306_유전자_김세민 {

	static BufferedReader br;
	
	static String DNA;
	static int[][] memo;
	
	private static int DP(int start, int end) {
		
		// 메모를 한 적이 있는 경우...
		if(memo[start][end] != -1) {
			return memo[start][end];
		}
		
		// 시작이 끝보다 크거나 같은 경우... 길이는 0
		if(start >= end) return 0;
		
		int length = 0;
		
		// 어떤 X가 KOI 유전자라면, 이 둘을 연결한 XY도 KOI 유전자이다.
		for(int mid = start + 1; mid < end; mid++) {
			length = Math.max(length, DP(start, mid) + DP(mid, end));
		}
		
		// 어떤 X가 KOI 유전자라면, aXt와 gXc도 KOI 유전자이다.
		if((DNA.charAt(start) == 'a' && DNA.charAt(end) == 't') || (DNA.charAt(start) == 'g' && DNA.charAt(end) == 'c')) {
			length = Math.max(length, DP(start + 1, end - 1) + 2);
		}
		
		return memo[start][end] = length;
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		DNA = br.readLine().trim();
		
		memo = new int[DNA.length()][DNA.length()];
		// 메모이제이션 배열을 -1로 초기화
		for(int i = 0; i < DNA.length(); i++) {
			for(int j = 0; j < DNA.length(); j++) {
				memo[i][j] = -1; 
			}
		}
		
		System.out.println(DP(0, DNA.length() - 1));
	}
}
