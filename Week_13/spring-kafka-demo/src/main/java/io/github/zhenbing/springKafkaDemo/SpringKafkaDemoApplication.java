package io.github.zhenbing.springKafkaDemo;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaProducerException;
import org.springframework.kafka.core.KafkaSendCallback;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@SpringBootApplication
@RestController
@Slf4j
public class SpringKafkaDemoApplication {

    private static final String  TOPIC ="topic1";

    private final KafkaTemplate kafkaTemplate;

    @Autowired
    public SpringKafkaDemoApplication(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringKafkaDemoApplication.class, args);
    }

    @Bean
    public NewTopic topic(){
        return TopicBuilder.name(SpringKafkaDemoApplication.TOPIC).partitions(4).replicas(3).build();
    }

    /**
     * 异步发送
     * @param msg
     * @return
     */
    @GetMapping("/send/{msg}")
    public String sendMsg(@PathVariable("msg") String msg){
        this.kafkaTemplate.send(SpringKafkaDemoApplication.TOPIC,msg).addCallback(new KafkaSendCallback() {
            @Override
            public void onFailure(KafkaProducerException e) {
                log.warn("发送失败");
            }

            @Override
            public void onSuccess(Object o) {
                printSendResult((SendResult) o);
            }
        });
        return "ok";
    }

    /**
     * 同步发送
     * @param msg
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @GetMapping("/sync/send/{msg}")
    public String sendSyncMsg(@PathVariable("msg") String msg) throws ExecutionException, InterruptedException {
        Future future = this.kafkaTemplate.send(SpringKafkaDemoApplication.TOPIC,msg);
        Object obj = future.get();
        printSendResult((SendResult) obj);
        return "ok";
    }

    /**
     * 顺序保证： 保证发送顺序
     * 1,同步发送
     * 2，max.in.flight.requests.per.connection 为1，
     * 该参数指定了生产者在收到服务器响应之前可以发送多少个消息。它的值越高，就会占用越多的内存，不过也会提升吞吐量。
     * 把它设为 1 可以保证消息是按照发送的顺序写入服务器的，即使发生了重试。
     * @param msg
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @GetMapping("/sorted/send/{msg}")
    public String sendSortedMsg(@PathVariable("msg") String msg) throws ExecutionException, InterruptedException {
        Future future = this.kafkaTemplate.send(SpringKafkaDemoApplication.TOPIC,msg);
        Object obj = future.get();
        printSendResult((SendResult) obj);
        return "ok";
    }

    /**
     * 事务
     * @param msg
     * @return
     */
    @GetMapping("/transaction/send/{msg}")
    public String sendTrxMsg(@PathVariable("msg") String msg){
        kafkaTemplate.executeInTransaction(kafkaOperations -> {
            kafkaOperations.send(SpringKafkaDemoApplication.TOPIC,msg);
            // 模拟异常，消息不会发出去
            // throw new RuntimeException("kafka transaction fail");
             return "";
        });
        return "ok";
    }


    @KafkaListener(topics = SpringKafkaDemoApplication.TOPIC)
    public void kafkaConsumer(String content){
        log.info("receive msg:{}",content);
    }


    private void printSendResult(SendResult o) {
        SendResult result = o;
        // 消息发送到的topic
        String topic = result.getRecordMetadata().topic();
        // 消息发送到的分区
        int partition = result.getRecordMetadata().partition();
        // 消息在分区内的offset
        long offset = result.getRecordMetadata().offset();
        log.info("sendResult:{},topic:{},partition:{},offset:{}",result,topic,partition,offset);
    }

}
