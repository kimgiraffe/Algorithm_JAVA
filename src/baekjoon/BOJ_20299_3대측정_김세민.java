package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ_20299_3대측정
 * 1.팀의 수, 팀원 3명의 레이팅 합에 대한 가입 조건, 개인 레이팅에 대한 가입 조건을 입력받는다.
 * 2.가입신청한 팀의 팀원들의 레이팅 정보를 입력받는다.
 * 3.각 팀에 대해 팀원 3명의 레이팅 합에 대한 가입 조건 및 개인 레이팅을 모두 만족하는 경우...
 * 4.가입 가능한 팀의 수를 1 증가한다.
 * 5.가입 가능한 팀의 수와 가입 가능한 팀의 개인 레이팅을 출력한다.
 *
 * @author semin.kim
 */

public class BOJ_20299_3대측정_김세민 {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int team_cnt, team_limit, individual_limit; //팀의 수, 팀원 3명의 레이팅 합 가입 조건, 개인 레이팅 가입 조건
	static int mem1_rating, mem2_rating, mem3_rating; // 각 팀의 1번, 2번, 3번 팀원의 개인 레이팅
	static int rating_sum; //각 팀의 팀원 3명의 레이팅 합
	static int joinable_team_cnt; // 가입 가능한 팀의 수
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		st = new StringTokenizer(br.readLine().trim());
		team_cnt = Integer.parseInt(st.nextToken());
		team_limit = Integer.parseInt(st.nextToken());
		individual_limit = Integer.parseInt(st.nextToken());
		
		while(team_cnt--!=0) {
			st = new StringTokenizer(br.readLine().trim());
			mem1_rating = Integer.parseInt(st.nextToken());
			mem2_rating = Integer.parseInt(st.nextToken());
			mem3_rating = Integer.parseInt(st.nextToken());
			rating_sum = mem1_rating + mem2_rating + mem3_rating;
			if(mem1_rating >= individual_limit && mem2_rating >= individual_limit && mem3_rating >= individual_limit && rating_sum >= team_limit) {
				joinable_team_cnt++;
				sb.append(mem1_rating).append(' ').append(mem2_rating).append(' ').append(mem3_rating).append(' ');
			}
		}
		sb.insert(0, joinable_team_cnt + "\n");
		
		System.out.println(sb);
		
	}
}
