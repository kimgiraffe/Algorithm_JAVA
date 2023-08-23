package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;


/**
 * BOJ_17471_게리맨더링
 * 시는 N(2이상 10이하)개의 구역으로 나누어져 있고, 구역은 1번부터 N번까지 번호가 매겨져 있다. 
 * 구역을 두 개의 선거구로 나눠야 하고, 각 구역은 두 선거구 중 하나에 포함되어야 한다. 
 * 선거구는 구역을 적어도 하나 포함해야 하고, 한 선거구에 포함되어 있는 구역은 모두 연결되어 있어야 한다. 
 * 구역 A에서 인접한 구역을 통해서 구역 B로 갈 수 있을 때, 두 구역은 연결되어 있다고 한다. 
 * 중간에 통하는 인접한 구역은 0개 이상이어야 하고, 모두 같은 선거구에 포함된 구역이어야 한다.
 * 공평하게 선거구를 나누기 위해 두 선거구에 포함된 인구의 차이를 최소로 하려고 한다. 시의 정보가 주어졌을 때, 인구 차이의 최솟값을 구해보자.
 * 
 * 각 구역은 두 선거구 중 하나에만 포함되므로, 두 선거구는 서로소 집합이다.
 * 구역의 총 개수에서 두 개의 서로소 집합을 생성한다.
 * @author semin.kim
 *
 */

public class BOJ_17471_게리맨더링_김세민 {

	static BufferedReader br;
	static StringTokenizer st;

	static int districtCnt; // 구역의 개수
	static final int MAX_DISTRICT_CNT = 10;

	static int minDiff = Integer.MAX_VALUE; // 두 선거구의 인구 차이의 최솟값

	static ArrayList<Integer>[] adjList;

	static ArrayList<Integer> left, right;

	static int[] population;
	static int[] check1;
	static int[] check2;

	private static int calculate(ArrayList<Integer> list, int num) {
		int count = 1;

		Queue<Integer> queue = new ArrayDeque<Integer>();

		check2 = new int[districtCnt + 1];

		queue.offer(list.get(0));
		check2[list.get(0)] = 1;

		while(!queue.isEmpty()) {
			int curr = queue.poll();
			for(int idx = 0; idx < adjList[curr].size(); idx++) {
				if(check2[adjList[curr].get(idx)] == 0 && check1[adjList[curr].get(idx)] == num) {
					check2[adjList[curr].get(idx)] = 1;
					count++;
					queue.offer(adjList[curr].get(idx));
				}
			}
		}

		return count;
	}

	private static void dfs(int L, int cnt) {
		if(cnt >= 1) {
			for(int idx = 1; idx <= districtCnt; idx++) {
				if(check1[idx] == 1) {
					left.add(idx); // 첫번째 선거구에 넣기
				}
				else {
					right.add(idx); // 두번째 선거구에 넣기
				}
			}


			// 두 선거구 중 어느 하나라도 구역의 개수가 0인 경우...
			if(left.size() == 0 || right.size() == 0) return;


			int selectedDistrict = 0; // 선택한 구역의 수
			selectedDistrict += calculate(left, 1);
			selectedDistrict += calculate(right, 0);

			if(selectedDistrict == districtCnt) { // 주어진 구역을 모두 선택한 경우...
				int populationLeft = 0, populationRight = 0;
				// 첫 번째 선거구의 인구 수 구하기
				for(int idx = 0; idx < left.size(); idx++) {
					populationLeft += population[left.get(idx)];
				}

				// 두 번쨰 선거구의 인구 수 구하기
				for(int idx = 0; idx < right.size(); idx++) {
					populationRight += population[right.get(idx)];
				}

				int diff = Math.abs(populationLeft - populationRight); // 두 선거구의 인구 수 차이

				// 두 선거구의 인구 수 차이의 최솟값 갱신
				if(minDiff > diff) {
					minDiff = diff;
				}

			}
			// 두 선거구 초기화
			left.clear();
			right.clear();
		}

		for(int idx = L; idx <= districtCnt; idx++) {
			if(check1[idx] == 0) {
				check1[idx] = 1;
				dfs(idx + 1, cnt + 1);
				check1[idx] = 0;
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		districtCnt = Integer.parseInt(br.readLine().trim());
		left = new ArrayList<>(); // 첫 번째 선거구
		right = new ArrayList<>(); // 두 번째 선거구

		population = new int[districtCnt + 1];
		check1 = new int[districtCnt + 1];
		check2 = new int[districtCnt + 1];
		
		adjList = new ArrayList[districtCnt + 1];
		for(int idx = 0; idx <= districtCnt; idx++) {
			adjList[idx] = new ArrayList<>();
		}

		st = new StringTokenizer(br.readLine().trim());

		// 각 구역의 인구 수 입력
		for(int idx = 1; idx <= districtCnt; idx++) {
			population[idx] = Integer.parseInt(st.nextToken());
		}

		// 각 구역의 인접한 구역의 정보 입력
		for(int idx = 1; idx <= districtCnt; idx++) {
			st = new StringTokenizer(br.readLine().trim());

			int adjacent_cnt = Integer.parseInt(st.nextToken()); // 인접합 구역의 수
			// 인접한 구역의 번호 입력
			for(int cnt = 1; cnt <= adjacent_cnt; cnt++) {
				adjList[idx].add(Integer.parseInt(st.nextToken()));
			}
		}

		dfs(1, 0);

		if(minDiff == Integer.MAX_VALUE) {
			System.out.println(-1);
		}
		else {
			System.out.println(minDiff);
		}
	}
}
