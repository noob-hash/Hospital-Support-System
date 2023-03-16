<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.time.LocalDateTime,java.time.format.DateTimeFormatter,java.net.URLDecoder" %>


<%  DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDateTime now=LocalDateTime.now();
%>
<%String today=dtf.format(now);
%>
<link rel="stylesheet" href="Base.css"> 
<div id="header">
    <ul class="list row">
        <li>
            <div id="logo">

            </div>
        </li>
        <li>
            <div class="greeting">
                <%Cookie[] cookies = request.getCookies();
                if(cookies != null){
                    for(Cookie c: cookies){
                        if(c.getName().equals("Username")){

                %>
                Welcome <%= URLDecoder.decode(c.getValue(), "UTF-8")%> 
                    <%}
                    }
                }%>
            </div>
        </li>
        <li>
            <div class="date">
                Date: <%=today%>
            </div>
        </li>

    </ul>
</div>
