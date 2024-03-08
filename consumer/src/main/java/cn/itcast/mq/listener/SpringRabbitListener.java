package cn.itcast.mq.listener;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
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
        System.out.println("消费者1接收到消息：【" + msg + "】" + LocalTime.now());
        Thread.sleep(20);
    }

    @RabbitListener(queues = "simple.queue")
    public void rabbitListener2(String msg) throws InterruptedException{
        System.out.println("消费者2.....接收到消息：【" + msg + "】" + LocalTime.now());
        Thread.sleep(200);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "fanout.queue1"),
            exchange = @Exchange(name = "zlebly.fanout")
    ))
    public void listenFanoutQueue1(String msg) {
        System.out.println("消费者1.....接收到广播消息：【" + msg + "】" + LocalTime.now());
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "fanout.queue2"),
            exchange = @Exchange(name = "zlebly.fanout")
    ))
    public void listenFanoutQueue2(String msg) {
        System.out.println("消费者2.....接收到广播消息：【" + msg + "】" + LocalTime.now());
    }

//    @RabbitListener(queues = "fanout.queue1")
//    public void listenFanoutQueue1(String msg) {
//        System.out.println("消费者1.....接收到广播消息：【" + msg + "】" + LocalTime.now());
//    }
//
//    @RabbitListener(queues = "fanout.queue2")
//    public void listenFanoutQueue2(String msg) {
//        System.out.println("消费者2.....接收到广播消息：【" + msg + "】" + LocalTime.now());
//    }

    @RabbitListener(bindings = @QueueBinding(
        value = @Queue(name = "direct.queue1"),
        exchange = @Exchange(name = "zlebly.direct"),
        key = {"red", "blue"}
    ))
    public void listenDirectQueue1(String msg) {
        System.out.println("消费者接收到direct.queue1的消息:" + msg + LocalTime.now());
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "direct.queue2"),
            exchange = @Exchange(name = "zlebly.direct"),
            key = {"red", "yellow"}
    ))
    public void listenDirectQueue2(String msg) {
        System.out.println("消费者接收到direct.queue2的消息:" + msg + LocalTime.now());
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "topic.queue1"),
            exchange = @Exchange(name = "zlebly.topic", type = ExchangeTypes.TOPIC),
            key = {"rng.*", "lpl.#", "edg.*"}
    ))
    public void listenTopicQueue1(String msg) {
        System.out.println("消费者接收到topic.queue1的消息:" + msg + LocalTime.now());
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "topic.queue2"),
            exchange = @Exchange(name = "zlebly.topic", type = ExchangeTypes.TOPIC),
            key = {"lpl.*", "edg.#", "rng.#"}
    ))
    public void listenTopicQueue2(String msg) {
        System.out.println("消费者接收到topic.queue2的消息:" + msg + LocalTime.now());
    }
}
