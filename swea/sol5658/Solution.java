package com.ssafy.recur.swea.sol5658;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution {
	// 한 번 돌릴 때 마다 숫자가 시계방향으로 한 칸씩 회전
	// 진수 변환이 필요
	// 보물상자의 비밀번호가 만들어 지는 경우의 수는 최대 N/4번
	// 즉, N/4번을 회전 시키면서 모든 경우의 수를 TreeSet에 저장 (꺼낼때 수가 필요하고 중복이 있어선 안되니)
	// 회전을 시킨다는건 끝에 있는것을 앞쪽으로 이동시킨다는 말이니 Dequeue를 써도 괜찮을듯
	// 꺼낸 애들을 전부 10진수로 변환해서 정렬 한 후 결과값 도출
	// 요약
	// 1. N/4번 반복문 설정
	// 2. 반복문 내에서  N/4 의 갯수로 자른 값을 저장
	// 3. 저장이 끝났다면 회전
	// 4. 반복문이 끝나면 2진수 변환 후 정렬
	// 5. K번쨰 값을 찾아서 출력
	static int N,K;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc =1;tc <= T ; tc++) {
			st = new StringTokenizer(br.readLine());
			N= Integer.parseInt(st.nextToken());
			K= Integer.parseInt(st.nextToken());
			int i =1;
			char[] numList = br.readLine().toCharArray();
			int numLength= numList.length;
			int start=0;
			ArrayList<String> result = new ArrayList<>();
			while (i<=N/4) {
				int cnt=0;
				int j=0;
				String str1= "";
				while(j<numLength) {
					if (cnt!=0 && cnt%(N/4) ==0 && str1 != "" && !result.contains(str1)) {
						result.add(str1);
						str1 = "";
					}
					str1+= numList[start+cnt];
					cnt++;
					if ((start+cnt)!=0 && (start+cnt)%numLength==0) cnt = (start*-1);
					j++;
					if (j==numLength) result.add(str1);
				}
				start++;
				i++;
			}
			Collections.sort(result,(o1,o2) -> Integer.parseInt(o2,16) - Integer.parseInt(o1,16));
			System.out.println("#" + tc + " " + Integer.parseInt(result.get(K-1),16));
		}
	}
}
