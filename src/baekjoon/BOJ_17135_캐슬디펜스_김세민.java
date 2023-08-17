package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_17135_캐슬디펜스_김세민 {
    static int col_size, row_size, distance_limit;
    static int[][] map;
    static int[][] copyMap;
    static int MAX_ENEMIES;

    static BufferedReader br;
    static StringTokenizer st;

    public static void main(String[] args) throws NumberFormatException, IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine().trim());
        col_size = Integer.parseInt(st.nextToken()); // 행의 수 입력
        row_size = Integer.parseInt(st.nextToken()); // 열의 수 입력
        distance_limit = Integer.parseInt(st.nextToken());  // 궁수의 공격 거리 제한 입력

        map = new int[col_size + 1][row_size + 1];
        copyMap = new int[col_size + 1][row_size + 1];
        //  map을 복사하는 이유는, 궁수가 적을 공격한 후, 적을 처지하는 과정에서
        //  map을 바로 바로 바꾸면, 궁수가 같은 적을 쏠 수도 있기 때문이다.
        for (int colIdx = 1; colIdx <= col_size; colIdx++) {
            st = new StringTokenizer(br.readLine());
            for (int rowIdx = 1; rowIdx <= row_size; rowIdx++) {
                map[colIdx][rowIdx] = Integer.parseInt(st.nextToken());
                copyMap[colIdx][rowIdx] = map[colIdx][rowIdx];
            }
        }

        // 궁수가 서 있는 x좌표를 저장할 ArrayList
        ArrayList<Integer> archer = new ArrayList<>();
        MAX_ENEMIES = 0; // 처지할 수 있는 최대 적의 수
        comb(1, row_size, 3, archer);   // 궁수가 서 있는 x좌표를 조합으로 구한다.

        System.out.println(MAX_ENEMIES);    // 결과 출력
    }

    // map을 원래대로 변경.
    public static void init() {
        for (int i = 1; i <= col_size; i++) {
            for (int j = 1; j <= row_size; j++) {
                map[i][j] = copyMap[i][j];
            }
        }
    }

    // 거리를 구하는 함수.
    public static int distance(int r1, int r2, int c1, int c2) {
        return Math.abs(r1 - r2) + Math.abs(c1 - c2);
    }

    // 궁수가 서 있는 x좌표를 조합으로 구하는 함수.
    public static void comb(int start, int n, int r, ArrayList<Integer> archer) {
        if (r == 0) {
            init();
            attack(archer);
            return;
        }

        for (int i = start; i <= n; i++) {
            archer.add(i);
            comb(i + 1, n, r - 1, archer);
            archer.remove(archer.size() - 1);
        }
    }

    // 궁수가 적을 공격하는 함수.
    public static void attack(ArrayList<Integer> archer) {
        int killed = 0;

        // 최대 N턴까지 진행할 수 있음.
        for (int n = 1; n <= col_size; n++) {
            boolean[][] visited = new boolean[col_size + 1][row_size + 1];
            for (int k = 0; k < archer.size(); k++) {
                int temp = archer.get(k); // 궁수가 서 있는 x좌표
                int min_distance = Integer.MAX_VALUE; // 최소 거리
                int min_colIdx = Integer.MAX_VALUE; // 최소 거리의 y좌표
                int min_rowIdx = Integer.MAX_VALUE; // 최소 거리의 x좌표

                // 맵 전체를 탐색해서 최단거리를 찾아내는 것이 목적.
                for (int col = 1; col <= col_size; col++) {
                    for (int row = 1; row <= row_size; row++) {
                        if (map[col][row] == 1) { // 적이 있을 경우
                            if (min_distance >= distance(col, col_size + 1, row, temp)) {
                                // 현재 구한 최소 거리보다 더 짧은 거리가 발생할 경우
                                // 최단거리와 좌표들을 다시 초기화.
                                if (min_distance > distance(col, col_size + 1, row, temp)) {
                                    min_distance = distance(col, col_size + 1, row, temp);
                                    min_colIdx = col;
                                    min_rowIdx = row;
                                } else {
                                    // 현재 구한 최소 거리와 지금 구한 최소 거리가 같을 경우,
                                    // 가장 왼쪽 적부터 처지해야하므로 x좌표가 더 작은지 검사해야 함.
                                    if (min_rowIdx > row) {
                                        min_colIdx = col;
                                        min_rowIdx = row;
                                    }
                                }
                            }
                        }
                    }
                }

                // 위에 과정을 모두 거친 후, 최소 거리가 D보다 작으면,
                // 그 좌표에 visited 처리를 해 준다.
                if (min_distance <= distance_limit) {
                    visited[min_colIdx][min_rowIdx] = true;
                }
            }

            // visited가 true인 좌표만 적을 처지한다.
            // 궁수가 같은 적을 쏠 수도 있기때문에 바로 바로 map[i][j] = 0하면 안 된다.
            for (int colIdx = 1; colIdx <= col_size; colIdx++) {
                for (int rowIdx = 1; rowIdx <= row_size; rowIdx++) {
                    if (visited[colIdx][rowIdx]) {
                        map[colIdx][rowIdx] = 0;
                        killed++;
                    }
                }
            }

            // 성 바로 위 줄을 모두 0으로 초기화.
            for (int rowIdx = 1; rowIdx <= row_size; rowIdx++) {
                map[col_size][rowIdx] = 0;
            }

            // i번째 줄을 i-1번째 줄로 초기화.
            for (int colIdx = col_size; colIdx >= 1; colIdx--) {
                for (int rowIdx = 1; rowIdx <= row_size; rowIdx++) {
                    map[colIdx][rowIdx] = map[colIdx - 1][rowIdx];
                }
            }
        }

        MAX_ENEMIES = Math.max(MAX_ENEMIES, killed);
    }

}