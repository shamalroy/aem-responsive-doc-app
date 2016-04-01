package me.tostring.aemdoc;

import me.tostring.aemdoc.model.Doc;

import javax.servlet.http.HttpServletRequest;

import static me.tostring.aemdoc.Constants.ADOBE_DOCS_HOST;
import static me.tostring.aemdoc.Constants.STYLE_CSS;

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
        doc.setCss(request.getContextPath() + "/public/" + STYLE_CSS);
        return doc.getHtml();
    }

}
