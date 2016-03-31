package main.me.tostring.aemdoc;

import static main.me.tostring.aemdoc.Constants.RES_DIR;
import static main.me.tostring.aemdoc.Constants.STYLE_CSS;

/**
 * Created by shamalroy on 3/31/16.
 */
public class Utils {

    public static String renderResponsiveHtml(String url, String contextPath) {
        Doc doc = new Doc(url);
        doc.setCss(contextPath + "/public/" + STYLE_CSS);
        return doc.getHtml();
    }

}
