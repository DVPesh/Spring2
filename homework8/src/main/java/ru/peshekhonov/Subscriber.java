package ru.peshekhonov;

import com.rabbitmq.client.*;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Subscriber {

    private static final Scanner scanner = new Scanner(System.in);
    private static String topic;
    private static String command;

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            channel.exchangeDeclare(Publisher.EXCHANGE_TITLE, BuiltinExchangeType.DIRECT);

            String queueTitle = channel.queueDeclare().getQueue();
            System.out.println("Queue title: " + queueTitle);

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.printf("тема: %s, сообщение: %s%n", delivery.getEnvelope().getRoutingKey(), message);
            };

            channel.basicConsume(queueTitle, true, deliverCallback, consumerTag -> {
            });

            System.out.println("Для задания или отмены темы подписки используйте команду set или del, пробел и название темы");

            while (true) {
                readCommand();
                switch (command) {
                    case "set" -> channel.queueBind(queueTitle, Publisher.EXCHANGE_TITLE, topic);
                    case "del" -> channel.queueUnbind(queueTitle, Publisher.EXCHANGE_TITLE, topic);
                }
            }
        }
    }

    private static void readCommand() {
        String[] array = scanner.nextLine().strip().split("\\h+", 2);
        topic = command = "";
        if (array.length > 0 && array[0].equals("q")) {
            System.exit(0);
        }
        if (array.length == 2) {
            command = array[0];
            topic = array[1];
        }
    }
}
