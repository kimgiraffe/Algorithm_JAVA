package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * SWEA_9997_미니멀리즘시계_D3
 * 1.테스트 케이스의 개수를 입력받는다.
 * 2.시침이 12로부터 몇 도 돌아가 있는지 입력받는다.
 * 3.시침은 1시간에 30도 이동하므로 1도를 이동하기 위해서는 2분의 시간이 지나야 한다.
 * 4.현재 시각을 12시간제로 출력한다.
 * @author semin.kim
 */

public class SWEA_9997_미니멀리즘시계_김세민 {
	
	static BufferedReader br;
	static StringBuilder sb;
	
	static int T; //테스트 케이스의 개수
	static int Angle; //시침이 12로부터 돌아가 있는 각도
	static final int MIN_PER_ANGLE = 2; //시침은 1도에 고정적으로 2분 소요
	static final int MIN_IN_ONE_HOUR = 60; //한시간은 60분
	static final int HALF_DAY_IN_HOUR = 12; //하루는 절반은 12시간
	static int hour, min; //현재 시각(시, 분)
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine().trim()); //테스트 케이스의 개수 입력
		
		for(int test_case = 1; test_case <= T; test_case++) {
			Angle = Integer.parseInt(br.readLine().trim()); //시침이 12로부터 돌아가 있는 각도 입력
			hour = (Angle * MIN_PER_ANGLE) / MIN_IN_ONE_HOUR % HALF_DAY_IN_HOUR; //현재 시각(시) 계산
			min = (Angle * MIN_PER_ANGLE) % MIN_IN_ONE_HOUR; //현재 시각(분) 계산
			sb = new StringBuilder();
			sb.append("#").append(test_case).append(" ").append(hour).append(" ").append(min);
			System.out.println(sb);
		}
	}
}
