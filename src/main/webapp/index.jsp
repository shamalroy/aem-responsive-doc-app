<%@ page import="me.tostring.aemdoc.*" %>
<%!
    String render(String contextPath) {
        return Utils.renderResponsiveHtml("https://docs.adobe.com/content/docs/en/aem/6-1.html", contextPath);
    }
%>
<%= render(request.getContextPath()) %>

