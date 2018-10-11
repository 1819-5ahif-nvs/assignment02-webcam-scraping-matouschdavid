import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.sql.Timestamp;
import java.util.logging.Logger;

public class Main {

    static Logger logger = Logger.getLogger(Main.class.getName());
    private static String lastLink = "";
    public static void main(String[] args) throws InterruptedException {
        for(;;)
        {
            Scap();
            Thread.sleep(1000*60);
        }
    }

    static void Scap(){
        Document doc = null;
        Element video = null;
        String source = "";
        try{
            //Get whole document
            doc = Jsoup.connect("https://webtv.feratel.com/webtv/?cam=5132&design=v3&c0=0&c2=1&lg=en&s=0").userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
                    .referrer("http://www.google.com").get();
            //get video element
            video = doc.getElementById("fer_video");
            //get src attribute of fer_video
            source = video.select("source").first().attr("src");
            //compare link to last one
            if(source != lastLink){
                //Log if changed
                logger.info(new Timestamp(System.currentTimeMillis()) + source);
            }
            //save current link as last one
            lastLink = source;
        }catch (Exception e){
            System.out.println(e.getStackTrace());
        }
    }
}
