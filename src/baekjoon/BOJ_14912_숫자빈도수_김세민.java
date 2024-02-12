package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14912_숫자빈도수_김세민 {
    static BufferedReader br;
    static StringTokenizer st;

    static int n, d, count;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        for(int num = 1; num <= n; num++) {
            int temp = num;
            while(temp > 0) {
                if(temp % 10 == d){
                    count++;
                }
                temp /= 10;
            }
        }
        System.out.println(count);
    }
}
