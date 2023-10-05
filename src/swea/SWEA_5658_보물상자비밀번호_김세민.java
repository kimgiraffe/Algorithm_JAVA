package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;


/**
 * SWEA_5658_보물상자비밀번호
 * 
 * @author semin.kim
 * 
 * 1. 주어진 숫자로 생성할 수 있는 수들을 시계방향으로 회전하며 중복없이 생성한다.
 * 2. 생성한 수들을 내림차순으로 정렬한다.
 * 3. K번째로 큰 수를 출력한다.
 *
 */
public class SWEA_5658_보물상자비밀번호_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static int testcaseCount; // 테스트케이스의 수
	static int numberCount, K; // 숫자의 수
	static char[] numList;

	static Set<Integer> set;

	private static void generateNumbers() {
		for(int turn = 0; turn < numberCount / 4; turn++) {
			// 시계방향 회전 시작
			char[] temp = new char[numberCount];
			
			for(int idx = 1; idx < numberCount; idx++) {
				temp[idx] = numList[idx - 1];
			}
			
			temp[0] = numList[numberCount - 1];
			
			for(int idx = 0; idx < numberCount; idx++) {
				numList[idx] = temp[idx];
			}
			// 시계방향 회전 끝
			
			for(int idx = 0; idx < numberCount; idx+= numberCount / 4) {
				int number = 0;
				for(int from = 0; from < numberCount / 4; from++) {
					char ch = numList[idx + from];
					int num;
					// A ~ F
					if(ch >= 'A' && ch <= 'F') {
						num = ch - 'A' + 10;
					} 
					// 0 ~ 9
					else {
						num = ch - '0';
					}
					// 16진수에서 10진수로 변환하기
					number += num * Math.pow(16, numberCount / 4 - from - 1);
				}
				// 생성한 숫자를 set에 추가하기
				set.add(number);
			}
		}
	}
	
	private static void sortNumbers() {
		// 생성한 수들을 list에 담기
		ArrayList list = new ArrayList(set);
	
		// 생성한 수들을 내림차순으로 정렬
		Collections.sort(list, Comparator.reverseOrder());
		
		// K번째로 큰 수
		sb.append(list.get(K-1)).append("\n");
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		testcaseCount = Integer.parseInt(br.readLine().trim());

		for(int testcase = 1; testcase <= testcaseCount; testcase++) {
			st = new StringTokenizer(br.readLine().trim());
			numberCount = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			numList = new char[numberCount];
			String input = br.readLine();
			for(int idx = 0; idx < numberCount; idx++) {
				numList[idx] = input.charAt(idx);
			}

			set = new HashSet<>();

			// 수 생성
			generateNumbers();

			sb.append("#").append(testcase).append(" ");
			
			sortNumbers();
		}
		
		System.out.println(sb);
	}
}
