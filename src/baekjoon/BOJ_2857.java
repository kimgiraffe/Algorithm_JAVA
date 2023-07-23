package baekjoon;

import java.util.Scanner;
import java.util.Vector;

/**
 * https://www.acmicpc.net/problem/2857
 * @author semin.kim
 * 1. 5명의 요원을 문자열로 입력받는다.
 * 2. 반복문을 통해 문자열이 FBI를 포함하면 cnt 값을 1증가, vector에 추가
 * 3. cnt 값이 0이라면 "HE GOT AWAY!" 출력
 * 4. 0이 아니라면 해당하는 vector를 순회하며 index 값을 출력
 */

public class BOJ_2857 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		Vector<Integer> v = new Vector<Integer>();
		String[] agent = new String[5];
		int cnt = 0;
		
		for(int index = 0; index < 5; index++) {
			agent[index] = sc.next();
			if(agent[index].contains("FBI")) {
				cnt++;
				v.add(index);
			}	
		}
		
		if(cnt == 0) System.out.println("HE GOT AWAY!");
		else {
			for(int idx = 0; idx < v.size(); idx++) {
				System.out.print(v.get(idx) + 1 + " ");
			}
		}
		
		sc.close();

	}

}
