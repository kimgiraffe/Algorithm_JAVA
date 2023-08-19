package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SWEA_1946_간단한압축풀기_D2
 * 1.테스트 케이스의 개수를 입력받는다.
 * 2.알파벳과 숫자 순서쌍의 개수를 입력받는다.
 * 3.알파벳과 숫자 순서쌍을 각각 입력받는다.
 * 4.반복문을 통해 주어진 알파벳을 주어진 숫자만큼 반복하여 출력한다.
 * 	4-1.너비가 10이되면 줄바꿈을 해준다.
 * @author semin.kim
 */

public class SWEA_1946_간단한압축풀기_김세민 {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int T; //테스트 케이스의 개수
	static final int WIDTH = 10; //문서의 너비는 10으로 고정
	static int pair_cnt; //순서쌍의 개수
	static char[] alphabet;
	static int[] numbers;
	
	public static void unZip() {
		int width_idx = 0; //너비의 인덱스
		for(int pairIdx = 0; pairIdx < pair_cnt; ++pairIdx) {
			for(int numberIdx = 0; numberIdx < numbers[pairIdx]; numberIdx++) {
				if(width_idx % WIDTH == 0 && width_idx != 0) { //줄 바꿈
					sb.append("\n");
				}
				sb.append(alphabet[pairIdx]);
				width_idx++;
			}
		}
		System.out.println(sb);
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine().trim()); //테스트 케이스의 개수 입력
		
		for(int test_case = 1; test_case <= T; ++test_case) {
			pair_cnt = Integer.parseInt(br.readLine().trim()); //순서쌍의 개수 입력
			alphabet = new char[pair_cnt];
			numbers = new int[pair_cnt];
			
			for(int pairIdx = 0; pairIdx < pair_cnt; ++pairIdx) {
				st = new StringTokenizer(br.readLine().trim());
				alphabet[pairIdx] = st.nextToken().charAt(0);
				numbers[pairIdx] = Integer.parseInt(st.nextToken());
			}
			sb = new StringBuilder();
			sb.append("#").append(test_case).append("\n");
			unZip();
		}
	}
}
