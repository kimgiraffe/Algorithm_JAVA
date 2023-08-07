package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * SWEA_5601_쥬스나누기_D3
 * 1. 테스트 케이스의 개수를 입력받는다.
 * 2. 사람의 수를 입력받는다.
 * 3. 모든 사람이 최대한의 쥬스를 마신다면 1/N 리터의 쥬스를 마신다.
 * @author semin.kim
 */

public class SWEA_5601_쥬스나누기_김세민 {
	
	static BufferedReader br; // 입력
	static StringBuilder sb; // 출력

	static int T; //테스트 케이스의 개수
	static int people_cnt; //사람의 수
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine().trim()); // 테스트 케이스의 개수 입력
		
		for(int test_case = 1; test_case <= T; ++test_case) {
			people_cnt = Integer.parseInt(br.readLine().trim()); //사람의 수 입력
			sb = new StringBuilder();
			sb.append("#").append(test_case).append(" ");
			for(int people_idx = 1; people_idx <= people_cnt; people_idx++) { //모든 사람이 1/N만큼 쥬스를 마신다.
				sb.append("1/").append(people_cnt).append(" ");
			}
			System.out.println(sb);
		}
	}
}
