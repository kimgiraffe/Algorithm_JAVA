package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * BOJ_6131_완전제곱수
 * A의 제곱은 B의 제곱보다 N만큼 크다 (1 ≤ N ≤ 1,000), (1 ≤ B ≤ A ≤ 500) 
 * 위의 힌트 조건을 만족하는 A와 B 쌍의 개수를 구하자
 * 
 * 
 * @author semin.kim
 */

public class BOJ_6131_완전제곱수_김세민 {

	static BufferedReader br;
	static int N;
	static int count;
	
	private static void solve(int num) {
		for(int B = 1; B <= 500; B++) {
			for(int A = B; A <= 500; A++) {
				if(B*B + num == A*A) {
					count++;
				}
			}
		}
		System.out.println(count);
	}
	
	public static void main(String[] args) throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim());
		solve(N);
	}
}
