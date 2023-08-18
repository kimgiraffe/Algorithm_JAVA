package baekjoon;
import java.util.*;

/** https://www.acmicpc.net/problem/2485 가로수
 * 
 * @author semin.kim
 * 1. 기존의 나무 사이의 간격 계산
 * 2. 간격들의 최대공약수 구하기
 * 3. 필요한 가로수 개수 추가하기
 */

public class BOJ_2485_가로수_김세민 {
	public static int GCD(int num1, int num2) {
		while(true) {
			int tmp = num2;
			num2 = num1 % num2;
			num1 = tmp;
			if(num2 == 0) return num1;
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt(); // 가로수의 수
		int[] arr = new int[N]; // 가로수의 위치
		int[] diff = new int[N - 1]; // 가로수 사이의 간격
		int cnt = 0;	// 필요한 최소 가로수의 개수
		int MIN = Integer.MAX_VALUE; // 간격 사이의 최대공약수
		
		for(int idx = 0; idx < N; idx++) {
			arr[idx] = sc.nextInt();
		}
		
		Arrays.sort(arr);
		
		for(int idx = 0; idx < N - 1; idx++) {
			diff[idx] = arr[idx + 1] - arr[idx];  
		}
			
		
		MIN = diff[0];
		for(int idx = 1; idx < N - 1; idx++) {
			MIN = GCD(MIN, diff[idx]);
		}
			
					
		for(int idx = 0; idx < N - 1; idx++) {
			cnt += diff[idx] / MIN - 1;
		}
		
		System.out.println(cnt);
		
		
		sc.close();
	}
}
