<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<label>
	<select class="form-control input-sm"  id="itemType">
		<option value="0"> [ Type ] </option>
		<c:forEach items="${itemTypes }" var="type">
		<option value="${type.key }">${type.key } - ${type.value }</option>
		</c:forEach>
	</select>
</label>
<c:forEach items="${itemSubTypes }" var="type">
<label name="itemSubType" type="${type.key }">
	<select class="form-control input-sm"  id="itemSubType">
		<option value="0"> [ SubType ] </option>
		<c:forEach items="${type.value }" var="subType">
		<option value="${subType.key }">${subType.key } - ${subType.value }</option>
		</c:forEach>
	</select>
</label>
</c:forEach>