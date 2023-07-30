package baekjoon;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * https://www.acmicpc.net/problem/24039
 * @author semin.kim
 */

public class BOJ_24039 {
	public static int N, i, j, result = 0;
	public static ArrayList<Integer> list = new ArrayList<Integer>();

	public static void Prime() {
		for(i = 2; i <= 10000; i++) {
			for(j = 2; j < i; j++) {
				if(i % j == 0) {
					break;
				}
			}
			if(i == j) {
				list.add(i);
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
	
		Prime();
		
		for(int idx = 0; idx < list.size(); idx++) {
			result = list.get(idx) * list.get(idx + 1);
			if(result > N) {
				System.out.println(result);
				break;
			}
		}

		sc.close();
	}

}
