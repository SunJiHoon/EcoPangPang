package hackathon.EchoPangPang.component;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {
    @Scheduled(fixedDelay = 10000)
    public void performTask() {
        System.out.println("10초마다 실행되는 작업: " + System.currentTimeMillis() / 1000);
    }
}
