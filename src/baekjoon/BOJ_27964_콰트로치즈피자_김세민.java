package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * 토핑의 이름이 Cheese로 끝나는 경우... set에 추가
 * set에 원소가 4개 이상인 경우... yummy 출력
 * 미만인 경우... sad 출력
 * @author semin.kim
 */

public class BOJ_27964_콰트로치즈피자_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	
	static int toppingCount; // 토핑의 개수
	static Set<String> set = new HashSet<>();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		toppingCount = Integer.parseInt(br.readLine().trim()); // 토핑의 개수 입력
		
		st = new StringTokenizer(br.readLine().trim());
		for(int idx = 0; idx < toppingCount; idx++) {
			 String topping = st.nextToken();
			 if(topping.length() < 6) continue; // 문자열의 길이가 6 미만인 경우... 무시
			 // 문자열이 "Cheese"로 끝나는 경우... 토핑 추가
			 if(topping.substring(topping.length() - 6, topping.length()).equals("Cheese")) {
				 set.add(topping);
			 }
		}
		if(set.size() >= 4) { // 4가지 이상의 치즈가 존재하는 경우...
			System.out.println("yummy");
		} else {
			System.out.println("sad");
		}
		
	}
}
