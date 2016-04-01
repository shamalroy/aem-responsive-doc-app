package me.tostring.aemdoc.model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

import static me.tostring.aemdoc.Constants.*;

/**
 * Created by shamalroy on 3/31/16.
 */
public class Doc {
    Document doc;

    public Doc(Document doc) {
        this.doc = doc;
    }

    public Doc(String url) {
        getDocPage(url);
    }

    private void getDocPage(String url) {
        try {
            System.out.println("URL: " + url);
            File file = new File(RES_DIR + JKS);
            String absolutePath = file.getAbsolutePath();
            System.out.println("JKS Path: " + absolutePath);
            System.setProperty("javax.net.ssl.trustStore", absolutePath);
            doc = Jsoup.connect(url).followRedirects(true).get();

            // change the css,js files to absolute path https://docs.adobe.com
            updateStaticResPath("link", "href");
            updateStaticResPath("script", "src");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateStaticResPath(String tag, String att) {
        Element head = doc.head();
        Elements tagEl = head.getElementsByTag(tag);
        for (Element cssLink : tagEl) {
            if (cssLink.hasAttr(att)) {
                String attVal = cssLink.attr(att);
                if (attVal.startsWith("/etc")) {
                    attVal = ADOBE_DOCS_ANONYMOUS_HOST + attVal;
                }
                cssLink.attr(att, attVal);
            }
        }
    }

    public void setMetadata(String meta) {

    }

    public void setCss(String cssPath) {
        Element head = doc.head();
        head.append("<link rel=\"stylesheet\" href=\"" + cssPath + "\">");
    }

    public String getHtml() {
        return doc.html();
    }
}
