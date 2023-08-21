package swea;

import java.util.Scanner;

/**
 * 1933_간단한N의약수
 * @author semin.kim
 * 1. 정수 N이 입력으로 주어진다. (N은 1이상 1000이하)
 * 2. 1부터 N까지 N으로 나누었을 때의 나머지가 0인지 확인한다.
 * 3. N의 모든 약수를 오름차순으로 출력한다.
 */

public class SWEA_1933_간단한N의약수_김세민 {

	public static int N;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		for(int mod = 1; mod <= N; mod++) { //1부터 N까지 N으로 나머지가 0이면 N의 약수이다.
			if(N % mod == 0) {
				System.out.print(mod + " ");
			}
		}
		
		sc.close();
	}

}
