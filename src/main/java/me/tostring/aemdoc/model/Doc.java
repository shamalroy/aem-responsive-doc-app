package me.tostring.aemdoc.model;

import me.tostring.aemdoc.Utils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static me.tostring.aemdoc.Constants.JKS;
import static me.tostring.aemdoc.Constants.RES_DIR;

/**
 * Created by shamalroy on 3/31/16.
 */
public class Doc {
    Logger logger = Logger.getLogger(this.getClass().getName());
    Document doc;

    public Doc(Document doc) {
        this.doc = doc;
    }

    public Doc(String url) {
        loadDocPage(url);
    }

    private void loadDocPage(String url) {
        try {
            File file = new File(RES_DIR + JKS);
            String absolutePath = file.getAbsolutePath();

            System.setProperty("javax.net.ssl.trustStore", absolutePath);
            doc = Jsoup.connect(url).followRedirects(true).get();

            // change the css,js files to absolute path https://docs.adobe.com
            Utils.updateStaticResPath(doc.head(), "link", "href", "/etc");
            Utils.updateStaticResPath(doc.head(), "script", "src", "/etc");
            Utils.updateStaticResPath(doc.body(), "img", "src", "/content");

        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error:", e);
        }
    }


    public void setMetaTag(String meta) {
        Element head = doc.head();
        head.prepend(meta);
    }

    public void setCss(String cssPath) {
        Element head = doc.head();
        head.append("<link rel=\"stylesheet\" href=\"" + cssPath + "\">");
    }

    public String getHtml() {
        return doc.html();
    }
}
