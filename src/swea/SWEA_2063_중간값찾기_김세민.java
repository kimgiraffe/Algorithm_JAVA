package swea;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 2063_중간값찾기
 * @author semin.kim
 * 1. 점수의 개수 N을 입력받는다.(N은 홀수)
 * 2. N개의 점수를 입력받고 list에 저장한다.
 * 3. list를 오름차순으로 정렬한다.
 * 4. list의 중간 index값을 출력한다.
 * 
 */

public class SWEA_2063_중간값찾기_김세민 {

	public static int N;
	public static int[] list;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		list = new int[N];
		
		for(int idx = 0; idx < N; idx++) { // N개의 수를 입력받아 list 배열에 저장
			list[idx] = sc.nextInt();
		}
		
		Arrays.sort(list); // list 배열을 오름차순으로 정렬
		System.out.println(list[N / 2]); // 중간값 출력
		
		sc.close();
	}

}
