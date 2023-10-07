package cn.itcast.mq;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
    public void testAMQP() {
        String queueName = "simple.queue";
        String message = "SpringAMQPTest";
        rabbitTemplate.convertAndSend(queueName, message);
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
