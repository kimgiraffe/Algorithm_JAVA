package baekjoon;

import java.util.Scanner;

/**
 * https://www.acmicpc.net/problem/1672
 * @author kimgiraffe
 */
public class BOJ_1672_DNA해독_김세민 {
	
	static int N;
	static String DNA;
	
	private static char decode(char c1, char c2) {		
		if(c1 == 'A') {
			if(c2 == 'A') {
				return 'A';
			}
			else if(c2 == 'G') {
				return 'C';
			}
			else if(c2 == 'C') {
				return 'A';
			}
			else {
				return 'G';
			}
		}
		else if(c1 == 'G') {
			if(c2 == 'A') {
				return 'C';
			}
			else if(c2 == 'G') {
				return 'G';
			}
			else if(c2 == 'C') {
				return 'T';
			}
			else {
				return 'A';
			}
		}
		else if(c1 == 'C') {
			if(c2 == 'A') {
				return 'A';
			}
			else if(c2 == 'G') {
				return 'T';
			}
			else if(c2 == 'C') {
				return 'C';
			}
			else {
				return 'G';
			}
		}
		else {
			if(c2 == 'A') {
				return 'G';
			}
			else if(c2 == 'G') {
				return 'A';
			}
			else if(c2 == 'C') {
				return 'G';
			}
			else {
				return 'T';
			}
		}
		
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		DNA = sc.next();
		char first = ' ', second = ' ';
		second = DNA.charAt(DNA.length() - 1);
		for(int idx = N - 1; idx >= 0; idx--) {
			first = DNA.charAt(idx);
			second = decode(first, second);
		}
		System.out.println(second);
		
		sc.close();
	}

}
