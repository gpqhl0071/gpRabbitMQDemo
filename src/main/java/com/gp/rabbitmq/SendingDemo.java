package com.gp.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.nio.charset.StandardCharsets;

/**
 * @author gao peng
 * @date 2019/11/15 14:49
 */
public class SendingDemo {

  private final static String QUEUE_NAME = "task_queue";

  public static void main(String[] argv) throws Exception {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("192.168.9.214");

    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();

    channel.queueDeclare(QUEUE_NAME, true, false, false, null);

    String message = "Hello World BEijing!1";
    channel.basicPublish("", QUEUE_NAME, null, message.getBytes(StandardCharsets.UTF_8));
    String message1 = "Hello World BEijing!2";
    channel.basicPublish("", QUEUE_NAME, null, message1.getBytes(StandardCharsets.UTF_8));
    String message2 = "Hello World BEijing!3";
    channel.basicPublish("", QUEUE_NAME, null, message2.getBytes(StandardCharsets.UTF_8));

    channel.close();
    connection.close();
  }
}
