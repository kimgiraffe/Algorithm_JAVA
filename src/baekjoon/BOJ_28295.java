package baekjoon;

import java.util.Scanner;

/**
 * https://www.acmicpc.net/problem/28295
 * @author kimgiraffe
 * 1. 지시에 대한 정보를 나타내는 정수를 10개 입력받는다.
 * 2. 반복문으로 각 지시에 대해 바라보는 방향을 갱신한다.
 * 3. 최종적으로 바라보는 방향을 출력한다. 
 */

public class BOJ_28295 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		//북동남서(1,2,3,4)
		//초기 방향은 북쪽(1)
		int dir = 1;
		
		for(int idx = 1; idx <= 10; idx++) {
			int order = sc.nextInt();
			if(order == 1) {
				if(dir == 4) {
					dir = 1;
				}
				else {
					dir++;
				}
			}
			else if(order == 2) {
				if(dir == 1 || dir == 3) {
					dir = 4 - dir;
				}
				else {
					dir = 6 - dir;
				}
			}
			else {
				if(dir == 1) {
					dir = 4;
				}
				else {
					dir--;
				}
			}
		}
		
		switch(dir) {
		case 1:
			System.out.println("N");
			break;
		case 2:
			System.out.println("E");
			break;
		case 3:
			System.out.println("S");
			break;
		case 4:
			System.out.println("W");
			break;
		default:
			break;
		}
		
		sc.close();
	}

}
