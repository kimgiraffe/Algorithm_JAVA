package swea;

import java.util.Scanner;

/**
 * 1936_1대1가위바위보
 * @author semin.kim
 * 1. A와 B가 무엇을 냈는지 입력으로 주어진다. (비기는 경우는 없음)
 * 2. 조건문을 이용하여 A와 B 중 누가 이겼는지 판별한다.
 * 3. A가 이겼다면 A 출력, B가 이겼다면 B 출력
 */

public class SWEA_1936_1대1가위바위보_김세민 {

	public static int A, B;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		A = sc.nextInt();
		B = sc.nextInt();
		
		if(A == 1) { //A: 가위
			if(B == 2) { //B: 바위
				System.out.println("B");
			}
			else { //B: 보(비기는 경우는 없음)
				System.out.println("A");
			}
		} else if(A == 2) { //A: 바위
			if(B == 1) { //B: 가위
				System.out.println("A");
			}
			else { //B: 보(비기는 경우는 없음)
				System.out.println("B");
			}
		} else { //A: 보
			if(B == 1) { //B: 가위
				System.out.println("B");
			}
			else { //B: 바위(비기는 경우는 없음)
				System.out.println("A");
			}
		}
		
		sc.close();
	}

}
