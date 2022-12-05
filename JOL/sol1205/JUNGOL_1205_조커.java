package com.ssafy.recur.JOL.sol1205;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class JUNGOL_1205_조커 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int zero_cnt = 0;
		int max_value =Integer.MIN_VALUE;
		int N = Integer.parseInt(br.readLine().trim());
		ArrayList<Integer> list = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			if (num == 0) {
				zero_cnt++;
			} else {
				list.add(num);
			}
		}
		if (list.size()==0) {
			System.out.println(zero_cnt);
			return;
		}
		
		Collections.sort(list);
		ArrayList<Integer> result;
		for (int j = 0; j < list.size(); j++) {
			result= new ArrayList<>();
			int cnt=zero_cnt;
			for (int i = j; i < list.size(); i++) {
				if (result.size()==0) {
					result.add(list.get(i));
					continue;
				}
				if( result.get(result.size()-1) +1 ==list.get(i)) {
					result.add(list.get(i));
					continue;
				}
				if (list.get(i)-result.get(result.size()-1)-1 > 0 && list.get(i)-result.get(result.size()-1)-1<=cnt) {
					cnt-=(list.get(i)-result.get(result.size()-1)-1);
					result.add(list.get(i));
				}
			}
			if (max_value<result.size()+zero_cnt) {
				max_value=result.size()+zero_cnt;
			}
		}
		
		System.out.println(max_value);
	}
}
