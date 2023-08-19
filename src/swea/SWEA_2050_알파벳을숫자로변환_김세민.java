package swea;

import java.util.Scanner;

/**
 * 2050_알파벳을숫자로변환
 * @author semin.kim
 * 1. 알파벳으로 이루어진 문자열을 입력받는다.
 * 2. 문자열을 순회하며
 * 3. 각 알파벳을 숫자로 변환한 결과값을 빈 칸을 두고 출력
 */

public class SWEA_2050_알파벳을숫자로변환_김세민 {
	public static String string;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		string = sc.next();
		for(int idx = 0; idx < string.length(); idx++) {
			System.out.print(string.charAt(idx) - 'A' + 1 + " ");
		}
		
		sc.close();
	}

}
