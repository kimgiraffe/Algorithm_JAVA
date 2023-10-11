package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 에라토스테네스의 체
 * 
 * 1. 배열의 값을 인덱스 값으로 초기화한다.
 * 2. 2번 인덱스부터 배열을 순회하면서  소수의 배수인 경우 제외
 * 3. 사용자로부터 입력받은 숫자 이하의 체로 걸러진 숫자가 아닌 수 (소수)를 모두 출력한다. 
 * @author semin.kim
 *
 */

public class SieveOfEratosthenes {

	static BufferedReader br;
	static StringBuilder sb;
	
	static int number; // 사용자로부터 입력 받을 숫자
	static int[] sieve = new int[100001];
	
	private static void init() {
		// 에라토스테네스 체 초기화
		for(int idx = 2; idx <= number; idx++) {
			sieve[idx] = idx;
		}
	}
	
	private static void eratosthenes() {
		for(int sieveIdx = 2; sieveIdx <= number; sieveIdx++) {
			if(sieve[sieveIdx] == 0) { // 이미 걸러진 수라면 무시
				continue;
			}
			
			for(int num = 2 * sieveIdx; num <= number; num += sieveIdx) {
				sieve[num] = 0; // 해당 수의 배수라면 소수가 아님
			}
		}
	}
	
	private static void print() {
		for(int idx = 2; idx <= number; idx++) {
			if(sieve[idx] != 0) { // 걸러진 적이 없다면 소수이다.
				sb.append(sieve[idx]).append(" ");
			}
		}
		System.out.println(sb);
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		number = Integer.parseInt(br.readLine().trim());
		
		init(); // 에라토스테네스 체 초기화
		eratosthenes(); // 소수가 아닌 수 거르기
		print(); // 소수 출력
	}
}
