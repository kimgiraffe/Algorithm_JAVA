package baekjoon;

import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;


/**
 * 업무 평가
 * 업무는 가장 최근에 주어진 순서대로
 * 업무를 하던 도중 새로운 업무가 추가되면 하던 업무를 중단하고 새로운 업무 시작
 * 새로운 업무가 끝났다면, 이전에 하던 업무를 이전에 하던 부분부터 이어서 진행\
 * 
 * 새로운 업무를 스택에 추가하자
 * 만약 과제 해결 시간이 1분이라면 즉시 점수에 추가하자
 * 
 * 
 * @author semin.kim
 *
 */


public class BOJ_17952_과제가끝나지않아_김세민 {

	static Scanner sc;

	static class work{
		int score;
		int time_to_take;
	}

	static Stack<work> stack = new Stack<>();

	static int time_cnt; // 분기의 총 시간
	static int score, time_to_take; // 각 업무의 점수와 걸리는 시간
	static int total_score; // 총 점수

	public static void main(String[] args) throws NumberFormatException, IOException {

		sc = new Scanner(System.in);

		time_cnt = sc.nextInt();

		for(int curr_time = 0; curr_time < time_cnt; curr_time++) {
			int flag = sc.nextInt(); // 1인 경우 업무의 만점과 과제 해결 시간 입력
			if(flag == 1) {
				work W = new work();
				score = sc.nextInt();
				time_to_take = sc.nextInt();
				W.score = score;
				W.time_to_take = time_to_take;

				stack.add(W); // 스택에 새로운 업무를 축가
			}

			if(!stack.isEmpty()){
				// 가장 최근 받은 업무의 남은 시간을 1 감소
				stack.peek().time_to_take--;
				if(stack.peek().time_to_take == 0) { // 업무를 완료하는 경우...
					total_score += stack.peek().score; // 총 점수에 추가
					stack.pop();
				}
			}
		}

		System.out.println(total_score);
	}
}