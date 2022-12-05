package com.ssafy.recur.swea.imitation3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Solution {
	// 카드 가 섞이기 시작하는 시점을 설정 가능
	// 1~N/2 까지 왼쪽 N/2+1 ~ N 까지 오른족
	// 0은 한번도 안섞임
	// 1은 N/2와 N/2+1이 한번 섞임
	// idx의 값은 5
	// x는 0~N-1까지
	// 즉 N-1 까지 x만큼 셔플을 돌린 경우의 수를 * x *x*x*x 까지 하고 불가능하면 탈출 하라는 말인듯?
	// 1이면 N-1과 N+1 만큼 섞임 2이면 N-2 N+2만큼 섞임?
	// N-1의 1/2이 넘어가면 3,2,1 순으로 섞임
	// 그러니까 두개의 카드덱이 있을 경우 1일 경우 size-1-1의 위치에 들어가고
	// N일경우 size-1-N의 위치부터 +1씩 해주면서 카드를 넣어줌
	// 그걸 0~N-1까지 해주고 정렬 확인
	// 안됬으면 재귀로 다시 섞어줌 -> 안터짐 그러면 ?

	// N 길이의 결과 배열 생성
	// 반복문을 돌리면서 i의 값이 되면 null값을 하나 집어넣고 그 다음 숫자를 넣어줌
	// 반복문 끝나고 카드리스트2 반복 돌리면서 null값인 곳에 카드를 넣어줌
	// 그리고 i값이 음수가 되면 0으로 바꿔주고 그곳에 null 값을 넣음
	// 그거 반복하면 풀 수
	static int N;
	static int[] cardList;
	static ArrayList<Integer> cardList4;
	static String adc;
	static String desc;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		outer: for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			int[] cardList1 = new int[N / 2];
			int[] cardList2 = new int[N / 2];
			st = new StringTokenizer(br.readLine());
			int[] cardList = new int[N];
			ArrayList<Integer> cardList4 = new ArrayList<>();
			for (int i = 0; i < N / 2; i++) {
				int num = Integer.parseInt(st.nextToken());
				cardList1[i] = num;
				cardList[i] = num;
				cardList4.add(num);
			}
			for (int i = 0; i < N / 2; i++) {
				int num = Integer.parseInt(st.nextToken());
				cardList2[i] = num;
				cardList[N / 2 + i] = num;
				cardList4.add(num);
			}
			result1 = Integer.MAX_VALUE;
			Collections.sort(cardList4, (o1, o2) -> o1 - o2); // 오름차순 정렬
			adc = cardList4.toString();
			Collections.sort(cardList4, (o1, o2) -> o2 - o1); // 내림차순 정렬
			desc = cardList4.toString();
			if (Arrays.toString(cardList).equals(adc) || Arrays.toString(cardList).equals(desc)) {
				System.out.println("#" + tc + " " + 0);
				continue;
			}

			for (int i = 1; i < N; i++) {
				int[] cl1 = cardList1.clone();
				int[] cl2 = cardList2.clone();
				dfs(i, cl1, cl2, 1);
			}
			System.out.println("#" + tc + " " + (result1 != Integer.MAX_VALUE ? result1 : -1));
		}
	}
	static int result1;
	private static void dfs(int idx, int[] cardList1, int[] cardList2, int depth) {
		if (depth > 5) { // 5번 초과 셔플 하면 탈출
			return;
		}
		int[] result = new int[N];
		int idx_2 = 0;
		int idx_3 = 0;
		int cnt = 0; // 오른쪽 배열의 카운카운팅
		int cnt2 = 0;
		// idx => 분기점 (뒤에서부터) 즉, 정확한 분기점은 N/2-idx
		// 분기점보다 커지는 순간부터 0 num 0 num 의 순서
		// 분기점보다 적으면 num만
		int quarter2 = N/2 -idx; // 오른쪽 카드리스트가 더 앞에올 경우
		int j = 0;
		
		while(idx_2<N) { // 카드 위치와 0 값을 세는 cnt1,cnt2 변수를 만들어 셔풀 93라인 ~ 120라인
			int quarter = N / 2 - idx;
			if (quarter > j) { // 분기점 
				result[idx_2++] = cardList1[idx_3++];
				cnt2++;
			} else {
				if (++quarter2 <= 0 && cnt < N/2) {
					result[idx_2++] = 0;
					cnt++;
					continue;
				}
				if(cnt<N/2) {
					result[idx_2++]=0;
					cnt++;
				}
				if (cnt2<N/2) {
					result[idx_2++] = cardList1[idx_3++];
					cnt2++;
				}
			}
			j++;
		}

		idx_2 = 0;
		for (int i = 0; i < N; i++) { 
			if (result[i] == 0) {
				result[i] = cardList2[idx_2++];
			}
		}
		int[] cl1 = new int[N / 2]; // 왼쪽 카드리스트 갱신
		int[] cl2 = new int[N / 2]; // 오른쪽 카드리스트 갱신
		for (int i = 0; i < N / 2; i++) {
			cl1[i] = result[i];
			cl2[i] = result[N / 2 + i];
		}
		
		String temp = Arrays.toString(result); // 배열 문자열로 변환
		if (temp.equals(adc) || temp.equals(desc)) { // 오름차순정렬 or 내림차순한 배열과 같으면 최소값 갱신하고 dfs 종료
			result1=Math.min(depth, result1);
			return;
		}
		for (int i = 1;i <N ; i ++) {
			dfs(i, cl1,cl2,depth+1);
		}
	}
}
