package me.tostring.aemdoc;

import me.tostring.aemdoc.model.Doc;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.servlet.http.HttpServletRequest;

import static me.tostring.aemdoc.Constants.*;

/**
 * Created by shamalroy on 3/31/16.
 */
public class Utils {

    public static String renderResponsiveHtml(HttpServletRequest request) {
        String docUri = request.getParameter("d");
        String docUrl;
        if (docUri != null && docUri.length() > 0) {
            docUrl = ADOBE_DOCS_HOST + docUri;
        } else {
            docUrl = ADOBE_DOCS_HOST;
        }
        Doc doc = new Doc(docUrl);
        doc.setMetaTag("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
        doc.setCss(request.getContextPath() + "/public/" + STYLE_CSS);
        return doc.getHtml();
    }

    public static void updateStaticResPath(Element element, String tag, String att, String filter) {
        Elements tagEls = element.getElementsByTag(tag);
        for (Element tagEl : tagEls) {
            if (tagEl.hasAttr(att)) {
                String attVal = tagEl.attr(att);
                if (attVal.startsWith(filter)) {
                    attVal = ADOBE_DOCS_ANONYMOUS_HOST + attVal;
                }
                tagEl.attr(att, attVal);
            }
        }
    }

}
