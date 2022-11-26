package ru.peshekhonov;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Publisher {

    static final String EXCHANGE_TITLE = "articles";
    private static final Scanner scanner = new Scanner(System.in);
    private static String topic;
    private static String message;

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            channel.exchangeDeclare(EXCHANGE_TITLE, BuiltinExchangeType.DIRECT);
            System.out.println("Вводите сообщения вида:\nтема сообщение");

            while (true) {
                readArticle();
                if (topic.equals("q")) {
                    return;
                }
                channel.basicPublish(EXCHANGE_TITLE, topic, null, message.getBytes(StandardCharsets.UTF_8));
            }
        }
    }

    private static void readArticle() {
        String[] array = scanner.nextLine().strip().split("\\h+", 2);
        topic = message = "";
        if (array.length >= 1) {
            topic = array[0];
        }
        if (array.length == 2) {
            message = array[1];
        }
    }
}
