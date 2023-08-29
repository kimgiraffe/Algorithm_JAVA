package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * SWEA_1228_암호문1
 * 
 * Arraylist에 원본 암호문을 삽입한다.
 * x의 위치 다음에  y개의 숫자를 삽입한다.
 * 다음 삽입되는 위치는 x + 1이다.
 * 
 * @author semin.kim
 *
 */

public class SWEA_1228_암호문1_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static final int T = 10; // 테스트 케이스의 개수는 10으로 고정
	static int originalPasswordLength; //원본 암호문의 길이
	static ArrayList<Integer> password;
	static int commandCount; // 명령어의 개수

	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		for(int testCase = 1; testCase <= T; testCase++) {
			originalPasswordLength = Integer.parseInt(br.readLine().trim()); // 원본 암호문의 길이 입력
			sb = new StringBuilder();
			sb.append('#').append(testCase).append(' ');

			password = new ArrayList<>();

			st = new StringTokenizer(br.readLine().trim());

			for(int idx = 0; idx < originalPasswordLength; idx++) {
				password.add(Integer.parseInt(st.nextToken()));
			}

			commandCount = Integer.parseInt(br.readLine().trim()); // 명령어의 개수 입력

			st = new StringTokenizer(br.readLine().trim());
			for(int commandIdx = 0; commandIdx < commandCount; commandIdx++) {
				char command = st.nextToken().charAt(0);
				int x = Integer.parseInt(st.nextToken());
				int numCount = Integer.parseInt(st.nextToken());
				// 입력받은 숫자 삽입
				for(int numIdx = 0; numIdx < numCount; numIdx++) {
					int num = Integer.parseInt(st.nextToken());
					password.add(x++, num);
				}
			}

			// 수정된 암호문의 처음 10개 출력
			for(int idx = 0; idx < 10; idx++) {
				sb.append(password.get(idx)).append(" ");
			}

			System.out.println(sb);
		}
	}
}
