package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * BOJ_1834_나머지와몫이같은수
 *
 * [문제]
 * N으로 나누었을 때 나머지와 몫이 같은 모든 자연수의 합을 구하는 프로그램을 작성하시오. 
 * 예를 들어 N=3일 때, 나머지와 몫이 모두 같은 자연수는 4와 8 두 개가 있으므로, 그 합은 12이다.
 *
 * @author semin.kim
 *
 */

public class BOJ_1834_나머지와몫이같은수_김세민 {

	static BufferedReader br;
	
	static long N;
	static long sum;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Long.parseLong(br.readLine().trim());
		
		for(int num = 1; num < N; num++) {
			sum += num * N + num;
		}
		
		System.out.println(sum);
	}
}
