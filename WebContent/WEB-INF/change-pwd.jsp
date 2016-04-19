<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="template-top.jsp" />

		<div class="col-md-8 column" id="main">
			
			<div id="newpassword">
				<p style="font-size: medium; color: #428bca; font-weight: bold" >Enter your new password</p>
			</div><br>
			
			<c:forEach var="error" items="${errors}">
				<p style="color:red"> ${error} </p>
			</c:forEach>
			
			<form method="POST" action="change-pwd.do" class="form-horizontal" role="form">
				<div class="form-group">
					<label for="inputPassword3" class="col-sm-2 control-label">New Password</label>
					<div class="col-sm-10">
						<input class="form-control" id="inputPassword3" type="password" name="newPassword" value="">
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-sm-2 control-label">Confirm Password</label>
					<div class="col-sm-10">
						<input class="form-control" id="inputPassword3" type="password" name="confirmPassword" value="">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						 <input type="submit" class="btn btn-primary" name="action" value="Change Password">
					</div>
				</div>
			</form>
		</div>
	</div>
</div>

<jsp:include page="template-bottom.jsp" />