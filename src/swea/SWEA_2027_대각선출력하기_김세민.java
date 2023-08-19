package swea;

import java.util.Scanner;

/*
#++++
+#+++
++#++
+++#+
++++#
 */

/**
 * 2027_대각선출력하기
 * @author semin.kim
 * 1. 주어진 조건에 따라 출력한다.
 */
public class SWEA_2027_대각선출력하기_김세민 {
	public static final int N = 5;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		for(int col = 1; col <= N; col++) {
			for(int row = 1; row <= N; row++) {
				if(col == row) { // 행과 열이 같은 경우 "#" 출력
					System.out.print("#");
				} else { // 다른 경우 "+' 출력
					System.out.print("+");
				}
			}
			System.out.println();
		}
		
		sc.close();
	}

}
