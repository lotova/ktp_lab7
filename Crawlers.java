package ktp_lab7;

import java.io.*;
import java.net.*;
import java.util.HashSet;
import java.util.LinkedList;

/**класс Crawler,
который будет реализовывать основные функциональные возможности
приложения*/

public class Crawlers {

	final static int AnyDepth = 0;
	private LinkedList<URLDepthPair> Processed = new LinkedList<URLDepthPair>(); //список  для всех сайтов, рассмотренных до текущего момента
    private LinkedList<URLDepthPair> NotProcessed = new LinkedList<URLDepthPair>(); //список, который включает только необработанные сайты
    private int Depth; //глубина поиска
    private String StartHost; //начальный URL
    private String Prefix = "http"; //префикс для анализа
    
    /**конструктор */
    public Crawlers(String host, int depth) {
        StartHost = host;
        Depth = depth;
        NotProcessed.add(new URLDepthPair(StartHost, Depth)); //добавляет в список необработанных сайтов новый сайт 
    }

    /** главный метод класса : обработка сайтов, пока они не закончатся из списка необр-ых сайтов*/ 

    public void Scan() throws IOException {

       while (NotProcessed.size() > 0) {
    	   WebScan(NotProcessed.removeFirst());
           }
       }
/**метод, обрабатывающий URL сайта */
    
    public void WebScan(URLDepthPair pair) throws IOException{
    	//установка соединения и перенаправления
        URL url = new URL(pair.getURL());
        URLConnection connection = url.openConnection();
        String redirect = connection.getHeaderField("Location");
        if (redirect != null) {
            connection = new URL(redirect).openConnection();
        }
        Processed.add(pair);
        if (pair.getDepth() == 0) return;

        // чтение литературы
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String input;
        while ((input = reader.readLine()) != null) {
            while (input.contains("a href=\"" + Prefix)) {
                input = input.substring(input.indexOf("a href=\"" + Prefix) + 8);
                String link = input.substring(0, input.indexOf('\"'));
                if(link.contains(" "))
                    link = link.replace(" ", "%20");
                // чтобы избежать многократного посещения одной и той же ссылки
                if (NotProcessed.contains(new URLDepthPair(link, AnyDepth)) ||
                        Processed.contains(new URLDepthPair(link, AnyDepth))) continue;
                NotProcessed.add(new URLDepthPair(link, pair.getDepth() - 1));
            }
        }
        // обязательное закрытие сокета
        reader.close();
    }
    
	/**метод getSites, который будет
возвращать список всех пар URL-глубины, которые были посещены*/
    public void getSites() {
        for (var elements : Processed)
            System.out.println(elements.getURL());
        System.out.println("Links visited: " +Processed.size());
    }
	
	/** */ 
	
}
