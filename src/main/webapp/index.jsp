<%@ page import="me.tostring.aemdoc.Utils" %>
<%!
    String render(HttpServletRequest request) {
        return Utils.renderResponsiveHtml(request);
    }
%>
<%= render(request) %>

