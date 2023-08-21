package baekjoon;

import java.io.IOException;
import java.util.Scanner;

/**
 * BOJ_1244_스위치켜고끄기
 * 1. 스위치의 상태를 1차원 배열에 저장하고
 * 2. 남학생인 경우, 배열을 순회하면서 받은 수의 배수인 스위치의 상태를 바꾼다.
 * 3. 여학생인 경우, 좌우로 하나씩 배열을 순회하면서 대칭이면서 가장 많은 스위치를 포함하는 구간을 찾아 상태를 바꾼다.
 * 4. 스위치의 마지막 상태를 한 줄에 20개씩 출력한다.
 * @author semin.kim
 *
 */

public class BOJ_1244_스위치켜고끄기_김세민 {
	
	static int numOfSwitches, numOfStudents;
	static int[] Switches;
	static int gender, num;
	
	/**
	 * BoyUpdate method 구현
	 * 1. 스위치 배열을 순회하며 인자로 받은 su로 나누어떨어지면 상태를 바꾼다.
	 * @param su
	 */
	public static void BoyUpdate(int su) {
		for(int switchIdx = 1; switchIdx <= numOfSwitches; switchIdx++) {
			if(switchIdx % su == 0) {
				Switches[switchIdx] = 1 - Switches[switchIdx];
			}
		}
	}
	
	/**
	 * GirlUpdate method 구현
	 * 1. 인자로 받은 su를 기준으로 양옆으로 배열을 순회하면서 서로 다를 때까지 cnt 값을 증가한다.
	 * 2. su와 cnt값을 기준으로 스위치 상태를 바꾼다.
	 * @param su
	 */
	public static void GirlUpdate(int su) {
		int cnt;
		for(cnt = 1; cnt + su <= numOfSwitches && su - cnt >= 1; cnt++) {
			if(Switches[su + cnt] != Switches[su - cnt]) {
				break;
			}
		}
		cnt--;
		for(int switchIdx = su - cnt; switchIdx <= su + cnt; switchIdx++) {
			Switches[switchIdx] = 1 - Switches[switchIdx];
		
		}
		
	}
	
	/**
	 * PrintSwitches method 구현
	 * 1. 한 줄에 20개씩 스위치 상태를 출력한다. (시작 idx는 1이다.)
	 */
	public static void PrintSwitches() {
		for(int idx = 1; idx <= numOfSwitches; idx++) {
			if(idx % 20 == 1 && idx != 1) {
				System.out.println();
			}
			System.out.print(Switches[idx] + " ");
		}
	}
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		numOfSwitches = sc.nextInt(); // 스위치의 개수 입력
		Switches = new int[numOfSwitches + 1];
				
		for(int switchIdx = 1; switchIdx <= numOfSwitches; switchIdx++) {
			Switches[switchIdx] = sc.nextInt();
		}
		
		numOfStudents = sc.nextInt(); // 학생 수 입력
		
		for(int studentIdx = 0; studentIdx < numOfStudents; studentIdx++) {
			gender = sc.nextInt(); //성별 입력
			num = sc.nextInt(); //받은 수 입력
			if(gender == 1) { // 남학생인 경우
				BoyUpdate(num);
			} else { // 여학생인 경우
				GirlUpdate(num);
			}
		}
		// 스위치 상태 출력
		PrintSwitches();
		
		sc.close();
	}

}

