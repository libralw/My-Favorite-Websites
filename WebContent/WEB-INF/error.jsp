<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="template-top.jsp" />

		<div class="col-md-8 column" id="main">
			
			<c:forEach var="error" items="${errors}">
			<p style="color:red"> ${error} </p>
			</c:forEach>
			
			<p>
			    <a href="manage.do">Back to manage your favorites</a>
			</p>
		</div>
	</div>
</div>

<jsp:include page="template-bottom.jsp" />
