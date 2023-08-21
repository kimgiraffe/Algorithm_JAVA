package swea;

import java.util.Scanner;

/**
 * 2047_신문헤드라인
 * @author semin.kim
 * 1. 문자열을 입력받는다.
 * 2. 문자열을 순회하며 소문자 알파벳을 대문자로 변환한다.
 * 3. 변환된 문자열을 출력한다.
 */

public class SWEA_2047_신문헤드라인_김세민 {

	public static String string;
	public static char[] chars;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		string = sc.next();
		
		chars = string.toCharArray();
		for(int idx = 0; idx < chars.length; idx++) {
			if(chars[idx] >= 'a' && chars[idx] <= 'z') { //소문자 알파벳인 경우 대문자로 변환
				chars[idx] += 'A' - 'a';
			}
		}
		System.out.println(chars);
		
		//System.out.println(string.toUpperCase());
		
		sc.close();
	}

}
