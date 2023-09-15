package cn.itcast.mq;

import com.rabbitmq.client.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@SpringBootTest
class ConsumerApplicationTests {

    @Test
    void consumer() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("121.5.143.96");
        factory.setPort(5673);
        factory.setUsername("itcast");
        factory.setPassword("123321");
        factory.setVirtualHost("/");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        String queueName = "simple.queue";
        channel.queueDeclare(queueName, false, false, false, null);
        // 订阅消息
        channel.basicConsume(queueName, true, new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) {
                // 处理消息
                System.out.println("消费者：" + new String(body));
            }
        });
        System.out.println("消费者等待接收消息...");
    }
}