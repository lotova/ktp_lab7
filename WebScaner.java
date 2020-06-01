package ktp_lab7;

import java.io.IOException;

public class WebScaner {

	public static void main(String[] args) throws IOException {
		     
       Crawlers crawler = new Crawlers(args[0], Integer.parseInt(args[1]));
        crawler.Scan();
        System.out.println("Depth: " + Integer.parseInt(args[1]));
        crawler.getSites();
	}

}
