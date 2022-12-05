package com.ssafy.recur.JOL.sol1828;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
class Marterial{
	int low;
	int high;
	public Marterial(int low,int high) {
		this.low =low;
		this.high=high;
	}
}
public class Main {
	// 보관되어야 할 온도가 각기 다름, 각 Ci마다 최저 보관온도하고 최고보관온도가 정해져있음
	// 적은 수의 냉장고를 사용하고 싶다.
	// 최저점이 낮을 경우 최고점이 높은 순서대로 정렬
	// 최저점이 낮을 경우 
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ArrayList<Marterial> marterial = new ArrayList<>();
		StringTokenizer st;
		for (int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			marterial.add(new Marterial(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
		}
		Collections.sort(marterial,(o1,o2) -> o1.high== o2.high ? o1.low - o2.low : o1.high-o2.high);
		int cnt=1;
		int idx=0;
		for (int i = 1; i <marterial.size() ;i ++) {
			if (marterial.get(idx).high<marterial.get(i).low) {
				idx=i;
				cnt++;
			}
		}
		System.out.println(cnt);
	}
}
