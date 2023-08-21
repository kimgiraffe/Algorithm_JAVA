package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ_28323_불안정한수열
 * N개의 자연수(인덱스는 1부터 시작)
 * K개의 자연수를 선택하여 인접한 수의 합이 홀수인 수열이 불안정한 수열
 * K의 최댓값 찾기
 * K = 1인 경우도 불안정한 수열
 * 
 * 짝수 + 홀수 = 홀수
 * 홀수 + 짝수 = 짝수
 * 
 * 홀, 짝이 번갈아 나와야함
 * 
 * @author semin.kim
 */

public class BOJ_28323_불안정한수열_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	
	static int num_length; // 입력 자연수 수열의 길이
	static int[] numList; // 자연수를 저장하는 1차원 배열
	static int K = 1;
	
	public static void solve() {
		for(int idx = 1; idx < num_length; idx++) {
			if(numList[idx] % 2 != numList[idx-1] % 2) { // 인접한 수가 홀, 짝 또는 짝, 홀인 경우...
				K++;
			}
		}
		System.out.println(K);
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		num_length = Integer.parseInt(br.readLine().trim());
		numList = new int[num_length];
		
		st = new StringTokenizer(br.readLine().trim());
		
		for(int idx = 0; idx < num_length; idx++) {
			numList[idx] = Integer.parseInt(st.nextToken());
		}
		solve();
		
		
	}
}
