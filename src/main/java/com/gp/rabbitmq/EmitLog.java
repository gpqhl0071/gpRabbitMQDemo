package com.gp.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author gao peng
 * @date 2019/11/18 10:25
 */
public class EmitLog {

  private static final String EXCHANGE_NAME = "logs";

  public static void main(String[] argv) throws Exception {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost(GPHost.ip);
    try (Connection connection = factory.newConnection();
         Channel channel = connection.createChannel()) {
      channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

      String message = argv.length < 1 ? "info: Hello World!" :
          String.join(" ", argv);

      channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes("UTF-8"));
      System.out.println(" [x] Sent '" + message + "'");
    }
  }

}
