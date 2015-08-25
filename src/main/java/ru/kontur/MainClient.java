package ru.kontur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * @author Dmitry
 * @since 18.08.2015
 */
public class MainClient {
    public static void main(String[] args) {
        String hostname = args[0];
        int port = Integer.parseInt(args[1]);

        try (Client client = new Client(hostname, port)) {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            String line;
            while ((line = in.readLine()) != null) {
                if (line.length() < 1)
                    continue;
                if (line.equals("q")) {
                    break;
                }
                List<String> list = client.sendMessage("get " + line);
                MainClient.printList(list);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Вывод списка строк на экран
     *
     * @param list список строк
     */
    private static void printList(List<String> list) {
        System.out.println("Server response");
        list.stream().forEach(System.out::println);
        System.out.println();
    }
}
