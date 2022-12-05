package com.ssafy.recur.swea.sol2117;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class House{
	int x;
	int y;
	public House(int x, int y) {
		this.x=x;
		this.y=y;
	}
}
public class Solution {
	// 운영 비용은 서비스 영역의 면적고 ㅏ동일
	// 운영 비용 -> K*K + (K-1) * (K-1)
	// 한 지점으로부터 거리 안에 있는지 확인하기 위해서는 |x1-x2| + |y1-y2| 가 K이하면 포함 되는것
	// 집 좌표들을 찍은 다음에 배열에 저장시키고
	// K를 계속 증가시키면서
	// 현재 k의 크기에 가장 큰 걸 구함
	// N이짝수일 땐 정사각형을 포함할 수 있는 k는 N+1
	static int N, M,K;
	static int max_value;
	static ArrayList<House> house;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			house = new ArrayList<>();
			for (int i = 0; i<N ; i++) {
				st =new StringTokenizer(br.readLine());
				for (int j = 0 ; j<N ; j++) {
					if(st.nextToken().equals("1"))  house.add(new House(i,j));
				}
			}
			max_value=0;
			K=1;
			while(K!=N+2) {
				check();
				K++;
			}
			System.out.println("#" + tc +  " " + max_value);
		}
	}
	static void check() {
		for (int i =0 ; i <N ; i++) {
			for (int j = 0 ; j<N ; j++) {
				check_house(i,j);
			}
		}
	}
	
	static void check_house(int i, int j) {
		int cnt=0;
		for (int l =0 ; l <house.size() ; l++) {
			if (Math.abs(i-house.get(l).x) + Math.abs(j-house.get(l).y)<K) {
				cnt++;
			}
		}
		if (K*K+(K-1)*(K-1)<=cnt*M){
			max_value=Math.max(max_value, cnt);
		}
	}
}
