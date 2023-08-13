package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BOJ_1755_숫자놀이
 * 1.읽을 숫자의 시작 숫자와 끝 숫자를 입력받는다.
 * 2.반복문을 통해 각 숫자를 읽은 숫자 하나씩 읽은 문자열을 저장한다.
 * 3.읽은 문자열을 기준을 정렬한다.
 * 4.숫자를 한 줄에 10개씩 출력한다.
 * @author semin.kim
 */

public class BOJ_1755_숫자놀이_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int start, end, length; // 읽을 숫자의 시작과 끝
	static int idx; // 숫자와 읽은 문자열의 시작 인덱스
	static final String[] READ_NUM_STRINGS= {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
	
	static class pair implements Comparable<pair>{
		int number;
		String string;

		public pair(int number, String string) {
			this.number = number;
			this.string = string;
		}

		// 숫자를 하나씩 읽은 문자열을 기준으로 재정의
		@Override
		public int compareTo(pair o) {
			return string.compareTo(o.string);
		}
	}
	
	static pair[] pairs;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		st = new StringTokenizer(br.readLine().trim());
		
		start = Integer.parseInt(st.nextToken()); //시작 숫자 입력
		end = Integer.parseInt(st.nextToken()); //끝 숫자 입력
		length = end - start + 1;
		
		pairs = new pair[length];
		
		for(int num = start; num <= end; num++) {
			if(num < 10) { // 읽을 숫자가 한 자리 숫자인 경우...
				pairs[idx++] = new pair(num, READ_NUM_STRINGS[num]);
			} else { // 읽을 숫자가 두 자리인 경우...
				pairs[idx++] = new pair(num, READ_NUM_STRINGS[num/10]+" "+READ_NUM_STRINGS[num%10]);
			}
		}
		
		// 사전순으로 정렬
		Arrays.sort(pairs);
		
		// 숫자를 한 줄에 10개씩 출력
		for(int iter = 0; iter < length; iter++) {
			if(iter % 10 == 0 && iter != 0) {
				sb.append('\n');
			}
			sb.append(pairs[iter].number).append(' ');
		}
		System.out.println(sb);
	}
}
