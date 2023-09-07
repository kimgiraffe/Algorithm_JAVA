package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_16496_큰수만들기_김세민 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stringBuilder = new StringBuilder();
        StringTokenizer stringTokenizer;

        int num = Integer.parseInt(br.readLine());
        stringTokenizer = new StringTokenizer(br.readLine());

        List<Number> numberList = new ArrayList<>();

        for(int i=0; i<num; i++)
            numberList.add(new Number(stringTokenizer.nextToken()));

        Collections.sort(numberList);

        for(int i=num-1; i>-1; i--)
            stringBuilder.append(numberList.get(i).originalNumber);

        System.out.println(new BigInteger(stringBuilder.toString()).toString());
    }

    public static class Number implements Comparable<Number>{
        long originalNumber;
        StringBuilder numberString = new StringBuilder();

        public Number(String num){
            this.originalNumber = Long.parseLong(num);

            for(int i=0; i<10; i++){
                numberString.append(num.charAt(i%num.length()));
            }
        }

        @Override
        public int compareTo(Number o) {
            if(Long.parseLong(this.numberString.toString()) - Long.parseLong(o.numberString.toString()) == 0)
                return 0;
            else if(Long.parseLong(this.numberString.toString()) - Long.parseLong(o.numberString.toString()) > 0)
                return 1;
            else return -1;
        }
    }
}