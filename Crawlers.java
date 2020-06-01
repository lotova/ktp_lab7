package ktp_lab7;

import java.io.*;
import java.net.*;
import java.util.HashSet;
import java.util.LinkedList;

/**����� Crawler,
������� ����� ������������� �������� �������������� �����������
����������*/

public class Crawlers {

	final static int AnyDepth = 0;
	private LinkedList<URLDepthPair> Processed = new LinkedList<URLDepthPair>(); //������  ��� ���� ������, ������������� �� �������� �������
    private LinkedList<URLDepthPair> NotProcessed = new LinkedList<URLDepthPair>(); //������, ������� �������� ������ �������������� �����
    private int Depth; //������� ������
    private String StartHost; //��������� URL
    private String Prefix = "http"; //������� ��� �������
    
    /**����������� */
    public Crawlers(String host, int depth) {
        StartHost = host;
        Depth = depth;
        NotProcessed.add(new URLDepthPair(StartHost, Depth)); //��������� � ������ �������������� ������ ����� ���� 
    }

    /** ������� ����� ������ : ��������� ������, ���� ��� �� ���������� �� ������ �����-�� ������*/ 

    public void Scan() throws IOException {

       while (NotProcessed.size() > 0) {
    	   WebScan(NotProcessed.removeFirst());
           }
       }
/**�����, �������������� URL ����� */
    
    public void WebScan(URLDepthPair pair) throws IOException{
    	//��������� ���������� � ���������������
        URL url = new URL(pair.getURL());
        URLConnection connection = url.openConnection();
        String redirect = connection.getHeaderField("Location");
        if (redirect != null) {
            connection = new URL(redirect).openConnection();
        }
        Processed.add(pair);
        if (pair.getDepth() == 0) return;

        // ������ ����������
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String input;
        while ((input = reader.readLine()) != null) {
            while (input.contains("a href=\"" + Prefix)) {
                input = input.substring(input.indexOf("a href=\"" + Prefix) + 8);
                String link = input.substring(0, input.indexOf('\"'));
                if(link.contains(" "))
                    link = link.replace(" ", "%20");
                // ����� �������� ������������� ��������� ����� � ��� �� ������
                if (NotProcessed.contains(new URLDepthPair(link, AnyDepth)) ||
                        Processed.contains(new URLDepthPair(link, AnyDepth))) continue;
                NotProcessed.add(new URLDepthPair(link, pair.getDepth() - 1));
            }
        }
        // ������������ �������� ������
        reader.close();
    }
    
	/**����� getSites, ������� �����
���������� ������ ���� ��� URL-�������, ������� ���� ��������*/
    public void getSites() {
        for (var elements : Processed)
            System.out.println(elements.getURL());
        System.out.println("Links visited: " +Processed.size());
    }
	
	/** */ 
	
}
