<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="template-top.jsp" />

<%@ page import="databeans.FavoriteBean" %>
<%@ page import="databeans.UserBean" %>

		<div class="col-md-8 column" id="main">
		
			<c:forEach var="error" items="${errors}">
				<p style="color:red"> ${error} </p>
			</c:forEach>
			
			
			<div>
		
				<table>
				
				<c:forEach var="fav" items="${favList}">

					<tr>
			            <td valign="top" style="font-size: 20px; font-weight: bold">
			            	<form method="post" action="update.do">
			            		<input type="hidden" name="id" value="${fav.favoriteId}">
			            		<a href="update.do?id=${fav.favoriteId}" target="_blank">${fav.link}</a>
			            	</form>
			            	
			            </td>
			        </tr>
			        <tr>
			        	<td valign="top">
			        		${fav.comment}
			        	</td>
			        </tr>
			        <tr>
			        	<td valign="top">
			        		${fav.clickCount} Clicks.
			        	</td>
			        </tr>
			        <tr><td><br/></td></tr>
				</c:forEach>
				</table>
			</div>
		</div>
	</div>
</div>

<jsp:include page="template-bottom.jsp" />