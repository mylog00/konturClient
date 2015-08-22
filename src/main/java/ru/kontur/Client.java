package ru.kontur;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dmitry
 * @since 18.08.2015
 */
public class Client implements Closeable {
    private final Socket client;

    /**
     * Создает клиента для указанного адреса и порта
     *
     * @param hostname адрес хоста
     * @param port     номер порта
     * @throws IOException если возникли ошибки при подключении к серверу
     */
    public Client(String hostname, int port) throws IOException {
        this.client = new Socket(hostname, port);
        System.out.println("Client started. hostname:" + hostname + "; port:" + port);
    }

    /**
     * Послать запрос к серверу
     * @param message строка с запросом
     * @return ответ от сервера
     * @throws IOException при ошибках отправки или получения данных
     */
    List<String> sendMessage(String message) throws IOException {
        //Поток для получения входных данных от клиента
        BufferedReader in = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
        //Поток для отправки ответа клиенту
        PrintWriter out = new PrintWriter(new OutputStreamWriter(this.client.getOutputStream()));
        //Посылаем запрос серверу
        out.println(message);
        out.println();
        out.flush();

        List<String> answer = new ArrayList<>(12);
        String line;
        //получаем ответ
        while ((line = in.readLine()) != null) {
            if (line.length() == 0)
                break;
            answer.add(line);
        }
        //Закрываем все соединения
        in.close();
        out.close();
        //возвращаем полученный результат
        return answer;
    }

    @Override
    public void close() throws IOException {
        //Закрываем все соединения
        if (!this.client.isClosed())
            this.client.close();
        System.out.println("Client stopped");
    }
}
