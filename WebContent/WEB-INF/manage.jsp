<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="template-top.jsp" />

<%@ page import="databeans.UserBean" %>
<%@ page import="databeans.FavoriteBean" %>

		<div class="col-md-8 column" id="main">
			
			<c:set var="user" value="${user}" scope="session"/>
        	<div>
        		<h2 style="text-align: center">Favorites for <c:out value="${sessionScope.user.firstName }"/> <c:out value="${sessionScope.user.firstName }"/> </h2>
			</div>
			<div><hr/></div>
			
			<c:forEach var="error" items="${errors}">
				<p style="color:red"> ${error} </p>
			</c:forEach>
			
			<form method="post" action="add.do" class="form-horizontal" role="form">
				<div class="form-group">
					 <label for="inputEmail3" class="col-sm-2 control-label">URL</label>
					<div class="col-sm-10">
						<input class="form-control" id="inputEmail3" type="text" name="link" value="${link}">
					</div>
				</div>
				<div class="form-group">
					 <label for="inputPassword3" class="col-sm-2 control-label">Comment</label>
					<div class="col-sm-10">
						<input class="form-control" id="inputPassword3" type="text" name="comment" value="${comment}">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						 <input type="submit" class="btn btn-primary" name="action" value="Add Favorites">
					</div>
				</div>
			</form>
			
			<div>
				<hr/>
			</div>

			<div>
				<table>
					<c:forEach var="fav" items="${favList}">
					<tr>
			            <td valign="top">
			                <form method="POST" action="delete.do">
			                    <input type="hidden" name="id" value="${fav.favoriteId}"/>
			                    <input type="submit" value="Delete"/>
			                </form>
			            </td>
			            <td valign="top" style="font-size: 20px; font-weight: bold">
			            	
			            	<a href="update.do?id=${fav.favoriteId}" target="_blank">${fav.link}</a>
			            </td>
			        </tr>
			        <tr>
			        	<td valign="top"></td>
			        	<td valign="top">
			        		${fav.comment}
			        	</td>
			        </tr>
			        <tr>
			        	<td valign="top"></td>
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