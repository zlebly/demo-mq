package cn.itcast.mq;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @author douwenjie
 * @create 2023-09-18
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringAMQPTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testSendTopicExchange() {
        String exchangeName = "zlebly.topic";
        String message = "就在今天";
        rabbitTemplate.convertAndSend(exchangeName, "lpl.edg.uzi", message);
    }

    @Test
    public void testSendDirectExchange() {
        String exchangeName = "zlebly.direct";
        String message = "狼来了";
        rabbitTemplate.convertAndSend(exchangeName, "blue", message);
    }

    @Test
    public void testFanoutExchange() {
        String exchangeName = "simple.fanout.exchange";
        String message = "hello, simple fanout exchange";
        rabbitTemplate.convertAndSend(exchangeName, "", message);
    }

    @Test
    public void testWorkQueue() throws InterruptedException {
        String queueName = "simple.queue";
        String message = "hello, simple queue ";

        // 发送消息
        for(int i = 0; i < 50; i++) {
            rabbitTemplate.convertAndSend(queueName, message + i);
            System.out.println("发送消息：" + message + i);
            Thread.sleep(20);
        }
    }

    @Test
    public void testAMQP() {
        String queueName = "simple.queue";
//        String message = "SpringAMQPTest";
        Map<String, Object> map = new HashMap<>();
        map.put("name", "jake");
        map.put("age", 13);
        // 发送消息
        rabbitTemplate.convertAndSend(queueName, map);
        System.out.println("发送成功");
    }

    @Test
    public void testAMQP2() {
        String queueName = "simple.queue";
        String message = "SpringAMQPTest_";
        for (int i = 0; i < 50; i++) {
            rabbitTemplate.convertAndSend(queueName, message + i);
        }
    }
}
