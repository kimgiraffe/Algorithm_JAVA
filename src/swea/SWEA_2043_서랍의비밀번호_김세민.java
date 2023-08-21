package swea;

import java.util.Scanner;

/**
 * 2043_서랍의비밀번호
 * @author semin.kim
 * 1. 비밀번호 P와 번호 K가 입력으로 주어진다.
 * 2. 확인해야 하는 횟수를 출력한다. (K부터 시작하여 1씩 증가)
 */

public class SWEA_2043_서랍의비밀번호_김세민 {
	public static int P, K;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		P = sc.nextInt();
		K = sc.nextInt();
		System.out.println(P - K + 1);
		
		sc.close();
	}

}
