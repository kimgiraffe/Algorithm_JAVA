package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

/**
 * BOJ_7785_회사에있는사람
 * 
 * @author semin.kim
 * 
 * 1. 로그에 기록된 출입 기록의 수 입력
 * 2. 출입 기록 입력
 * 3. enter인 경우 set에 추가, leave인 경우 set에서 제거
 * 4. 사전 순의 역순으로 정렬되어있는 set에서 하나씩 출력
 */

public class BOJ_7785_회사에있는사람_김세민 {

	static BufferedReader br;
	static StringBuilder sb;
	
	static int logCount;
	static Set<String> set;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		set = new TreeSet<>(Collections.reverseOrder()); // 사전 순의 역순으로 정렬
		logCount = Integer.parseInt(br.readLine().trim());
		
		while(logCount-- != 0) {
			String log = br.readLine(); // 한 줄 입력
			String[] temp = log.split(" "); // 공백 기준으로 입력 문자열 자르기
			if(log.charAt(log.length() - 1) == 'r') { //enter
				set.add(temp[0]); // set에 추가
			} else if(log.charAt(log.length() - 1) == 'e') { //leave
				set.remove(temp[0]); // set에서 제거
			}
		}
		for(String person : set) {
			sb.append(person).append("\n");
		}
		
		System.out.print(sb);
		
	}
}
