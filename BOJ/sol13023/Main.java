package com.ssafy.recur.BOJ.sol13023;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static ArrayList<Integer>[] p;
	static boolean checked = false;

	// 아니면 입력받으면서 배열의 카운트롤 세고 그 카운트가 4가 넘어가는게 있으면 1출력 아니면 0출력?
	// 관계를 입력받아서 양방향으로 친구 등록을 해줌
	// 순차적으로 반복문 돌리면서 dfs 탐색 해서 depth 가 4 이상이면 끝내고 1
	// 없으면 0 인데 2000 * 2000번 하는데 안터지나?
	// 리스트를 써서 해결해보는건 어떨까?
	// int 배열에 ArrayList 추가해서 시도해보기?
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		p = new ArrayList[N];
		for (int i = 0; i<N ; i ++) {
			p[i]= new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			st=new StringTokenizer(br.readLine());
			int num =Integer.parseInt(st.nextToken());
			int num2 =Integer.parseInt(st.nextToken());
			p[num].add(num2);
			p[num2].add(num);
		}
		for (int i = 0; i < N; i++) {
			dfs(i,0,new boolean[N]);
			if (checked) break;
		}
		System.out.println(checked ? 1 : 0);
	}

	static void dfs(int num,int cnt,boolean[] visited) {
		if (cnt==4) {
			checked=true;
			return;
		}
		visited[num]=true;
		for (int i = 0 ; i <p[num].size() ; i++) {
			if (visited[p[num].get(i)]) continue;
			dfs(p[num].get(i),cnt+1,visited.clone());
		}
	}
}
