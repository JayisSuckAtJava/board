package com.example.board.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class MyScheduler {
	
	// 스케줄러 = 자동화 기능
	// 사용할려면 main 이 있는 boot Aplication 에서설정 해줘야함.
	
	//			초 분 시 일 월 요일
	@Scheduled(cron = "0/30 * * * * *")
	// cron 은 해당 을 지정하는것 이기에 10 ****** 이면 매 10초거 될떄마다 찍음
	// 자동을 위해서는 0/10
	// 이렇게 되면 10초에 1번씩 작동함.
	
	public void test() {
		// 회원 탈퇴 보류 혹은 크롤링 같은 일정 기간, 혹은 자동동작 등을 할수 있는 기능
		// 디버그를 찍는다거나 txt로  만든다 등등을 한다.
		System.out.println("TEST started");
		
	}
}
