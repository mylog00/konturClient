package ru.kontur;

import java.io.IOException;
import java.util.List;

/**
 * @author Dmitry
 * @since 18.08.2015
 */
public class Main {
    public static void main(String[] args) {
        String hostname = args[0];
        int port = Integer.parseInt(args[1]);

        try (Client client = new Client(hostname, port)) {
            List<String> list = client.sendMessage("get k");
            printList(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printList(List<String> list){
        list.stream().forEach(System.out::println);
    }
}
