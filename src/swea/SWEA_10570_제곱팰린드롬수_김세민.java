package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SWEA_10570_제곱팰린드롬수_김세민
 * 1.테스트 케이스의 개수를 입력받는다.
 * 2.제곱팰린드롬수 판별 범위의 시작과 끝을 입력받는다.
 * 3.범위를 순회하면서 팰린드롬이고 제곱수인지 확인한다.
 * 4.제곱수라면 개수를 1 증가한다.
 * 5.순회 종료 후 범위 내 제곱 팰린드롬 수의 개수를 출력한다.
 * @author semin.kim
 */

public class SWEA_10570_제곱팰린드롬수_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static int T; //테스트 케이스의 개수
	static int start, end; //범위의 시작과 끝
	static int cnt; //제곱 팰린드롬의 개수

	static boolean isPalindrome(int num) {
		String tmpString = String.valueOf(num);

		for(int idx = 0; idx < tmpString.length() / 2; idx++) {
			if(tmpString.charAt(idx) != tmpString.charAt(tmpString.length() - idx - 1)) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine().trim());

		for(int test_case = 1; test_case <= T; ++test_case) {
			cnt = 0; // 제곱 팰린드롬 수의 개수
			st = new StringTokenizer(br.readLine().trim());
			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());

			for(int num = start; num <= end; ++num) {
				// 제곱 수이면서 팰린드롬 수인 경우...
				if(isPalindrome(num) && isPalindrome((int) (Math.sqrt(num))) && (int) Math.sqrt(num) * (int) Math.sqrt(num) == num) {
					cnt++; // 제곱 팰린드롬 수 1 증가
				}
			}

			sb = new StringBuilder();
			sb.append("#").append(test_case).append(" ").append(cnt);
			System.out.println(sb);
		}
	}
}
