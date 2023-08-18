package baekjoon;

import java.util.Scanner;

public class BOJ_2153_소수단어_김세민 {

	private static String str;
	private static int N = 0;
	
	private static void isPrime(int num) {
		for(int mod = 2; mod <= Math.sqrt(num); mod++) {
			if(num % mod == 0) {
				System.out.println("It is not a prime word.");
				return;
			}
		}
		System.out.println("It is a prime word.");
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		str = sc.next();
		for(int idx = 0; idx < str.length(); idx++) {
			if(str.charAt(idx) >= 'a' && str.charAt(idx) <= 'z')
				N += str.charAt(idx) -'a' + 1;
			else {
				N += str.charAt(idx) - 'A' + 27;
			}
		}
		
		isPrime(N);
		
		sc.close();
	}

}
