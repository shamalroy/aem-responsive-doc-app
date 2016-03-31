<%@ page import="main.me.tostring.aemdoc.Utils" %><%!
    String render(String contextPath) {
        return Utils.renderResponsiveHtml("https://docs.adobe.com/content/docs/en/aem/6-1.html", contextPath);
    }
%>
<%= render(request.getContextPath()) %>

