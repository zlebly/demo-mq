package cn.itcast.mq.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

/**
 * @author douwenjie
 * @create 2023-09-18
 */
@Component
public class SpringRabbitListener {
    @RabbitListener(queues = "simple.queue")
    public void rabbitListener(String msg) throws InterruptedException {
        System.out.println("spring 消费者1接收到消息：【" + msg + "】" + LocalTime.now());
        Thread.sleep(20);
    }

    @RabbitListener(queues = "simple.queue")
    public void rabbitListener2(String msg) throws InterruptedException{
        System.out.println("spring 消费者2.....接收到消息：【" + msg + "】" + LocalTime.now());
        Thread.sleep(200);
    }

}
