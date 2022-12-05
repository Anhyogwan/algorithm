package com.ssafy.recur.swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 자리가 1~N까지 일렬로 있음 3개의 출입구가 존재
// 대기하는 낚시꾼 존제
// 한사람당 하나으 ㅣ낚시터
// 하나으 ㅣ출입구씩 선택하여 순차적 입장
// 맨 마지막 사람이 아니면 아무대나 가도 상관없고
// 마지막은 가까운곳이 두곳이라면 하나선택해야함
// 하나 출입구 끝나면 다음 출입구 선택해서 다시 반복
// 불리언 배열 , 인덱스끼리의 거리차이
// 고려해야할것 -> 입구를 여는 순서
// 마지막 사람이 위치하는곳 2^n -> 문이 3개 고정이니까 최대 8가지의 경우의 수가 생길 수 있다

// 순열로 3개의 게이트 순서 정해서 순서가 정해지면
// 짝수 일 경우 각자 배분하면서 거리 계산 해주고
// 홀수 일 경우 왼쪽에 넣는 경우로 한번 돌리고
// 오른쪽에 넣는 경우로 한번 돌리고
// 이걸 2번째 게이트까지 반복하고 마지막은 양쪽 다 넣어봐서 결과값 작은거  반환
public class Solution {
	static int N;
	static int[] fishing;
	static int[][] gate;
	static int result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int tc = 1; tc <= T; tc++) {
			result = Integer.MAX_VALUE;
			N = Integer.parseInt(br.readLine());
			fishing = new int[N];
			st = new StringTokenizer(br.readLine());
			gate = new int[3][];
			gate[0] = new int[] { Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) };
			st = new StringTokenizer(br.readLine());
			gate[1] = new int[] { Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) };
			st = new StringTokenizer(br.readLine());
			gate[2] = new int[] { Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) };

			perm(0, new int[3], new boolean[3]);
			System.out.println("#" + tc + " " + result);
		}

	}

	// start, end 값은 반드시 게이트의 위치에서 시작
	static void perm(int idx, int[] numbers, boolean[] isSelected) {
		if (idx == 3) {
			// System.out.println(Arrays.toString(numbers));
			selected(numbers);
			return;
		}
		for (int i = 0; i < 3; i++) {
			if (isSelected[i])
				continue;
			isSelected[i] = true;
			numbers[idx] = i;
			perm(idx + 1, numbers, isSelected);
			isSelected[i] = false;
		}
	}

	private static void selected(int[] numbers) {
		// 홀수 일 경우 알아서 한명 두명 ~ 해서 끝까지 넣어줌
		// 짝수 일 경우 한명 두명~ 해서 끝까지 넣고 1명이 남으면 그 한명을 오른족에 넣은 경우의 수와 왼쪽에 넣은 경우의 수를 생각
		
		boolean[] visited = new boolean[N];
		int start = gate[numbers[0]][0];
		int end = gate[numbers[0]][0];
		int gatePeople = gate[numbers[0]][1];
		int remain = 0;
		if (gatePeople % 2 == 0) {
			gatePeople--;
			remain = 1;
		}
		//System.out.println(gatePeople);
		int i = 0;
		int distance = 0;
		while (gatePeople >= 0) {
			if (start - i >= 0) {
				visited[start - i] = true;
				distance += (i + 1);
				gatePeople--;
			}
			if (gatePeople < 0) {
				i++;
				break;
			}
			if (end + i < N) {
				visited[end + i] = true;
				distance += (i + 1);
				gatePeople--;
			}
			i++;
		}
		if (remain == 1) {
			if (start - i >= 0) {
				visited[start - i] = true;
				subset(numbers, 1, visited.clone(), distance + i + 1);
				visited[start - i] = false;
			}
			if (end + i < N) {
				visited[end + i] = true;
				subset(numbers, 1, visited.clone(), distance + i + 1);
				visited[end + i] = false;
			}
		} else {
			subset(numbers, 1, visited.clone(), distance);
		}
	}

	private static void subset(int[] numbers, int idx, boolean[] visited, int distance) {
		//System.out.println(Arrays.toString(visited));
		if (idx == 3) {
			//System.out.println(distance--);
			result = Math.min(result, --distance);
			return;
		}
		int start = gate[numbers[idx]][0];
		int end = gate[numbers[idx]][0];
		int gatePeople = gate[numbers[idx]][1];
		int remain = 0;
		if (gatePeople % 2 == 0) {
			gatePeople--;
			remain = 1;
		}
		int i = 0;
		while (gatePeople > 0) {
			if (start - i >= 0 && !visited[start - i]) {
				visited[start - i] = true;
				distance += (i + 1);
				gatePeople--;
			}
			if (gatePeople <= 0) {
				break;
			}
			if (end + i < N && !visited[end + i]) {
				visited[end + i] = true;
				distance += (i + 1);
				gatePeople--;
			}
			i++;
		}
		if (remain == 1) {
			while (true) {
				if (start - i >= 0 && !visited[start - i]) {
					visited[start - i] = true;
					subset(numbers, idx + 1, visited.clone(), distance + i + 1);
					visited[start - i] = false;
					if (end+i <N && !visited[end+i]) {
						visited[end + i] = true;
						subset(numbers, idx + 1, visited.clone(), distance + i + 1);
						visited[end + i] = false;
					}
					break;
				}
				if (end + i < N && !visited[end + i]) {
					visited[end + i] = true;
					subset(numbers, idx + 1, visited.clone(), distance + i + 1);
					visited[end + i] = false;
					break;
				}
				i++;
			}
		} else {
			subset(numbers, idx + 1, visited.clone(), distance);
		}
	}
}
