package cn.itcast.mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@SpringBootTest
class PublisherApplicationTests {
    @Test
    void publishTest() throws IOException, TimeoutException {
        // 1.建立连接
        ConnectionFactory factory = new ConnectionFactory();
        // 1.1 设置连接参数
        factory.setHost("121.5.143.96");
        factory.setUsername("itcast");
        factory.setPassword("123321");
        factory.setPort(5673);
        factory.setVirtualHost("/");
        // 1.2 建立连接
        Connection connection = factory.newConnection();
        // 2.创建通道
        Channel channel = connection.createChannel();
        // 3.创建队列
        String queueName = "simple.queue";
        channel.queueDeclare(queueName, false, false, false, null);
        // 4.发送消息
        String msg = "hello";
        channel.basicPublish("", queueName, null, msg.getBytes());
        // 5.关闭连接资源
        channel.close();
        connection.close();
    }
}
