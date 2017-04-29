<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:template>

	<jsp:attribute name="header">
        <%@include file="../components/navbar.jsp"%>
    </jsp:attribute>
	<jsp:attribute name="footer">
        <div id="pagefooter" class="row">
            <%@include file="../components/footer.jsp"%>
        </div>
    </jsp:attribute>

	<jsp:body>
	    <div style="padding-top: 50px;">
		    <h1>Home page</h1>
            <p>Welcome to the Bus Line website!</p>
        </div>
        <div>
			<h1>${test}</h1>
		</div>
		<h5>${greeting}</h5>
	    
	    <br> <br> <br> <br>
    </jsp:body>

</t:template>