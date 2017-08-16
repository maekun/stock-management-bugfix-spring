<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../common/common.jsp"  %>
<body>
<div class="container">
	<h3>書籍登録画面</h3>
	<div class="span8">
		<div class="row">
		<form:form modelAttribute="entryBookForm" action="${pageContext.request.contextPath}/book/insert">
			<table class="table table-striped">
			  <tr>
			    <th>
			     	 書籍名
			    </th>
			    <td>
			    	<form:input path="name"  placeholder="書籍名"/>
			    	<form:errors path="name" cssStyle="color:red"/>
			    </td>
			  </tr>
			  <tr>
			    <th>
			     	 著者
			    </th>
			    <td>
			    	<form:input path="author"  placeholder="著者"/>
			    	<form:errors path="author" cssStyle="color:red"/>
			    </td>
			  </tr>
			  <tr>
			    <th>
			     	 出版社
			    </th>
			    <td>
			    	<form:input path="publisher"  placeholder="出版社"/>
			    	<form:errors path="publisher" cssStyle="color:red"/>
			    </td>
			  </tr>
			  <tr>
			    <th>
			     	 価格
			    </th>
			    <td>
			    	<form:input path="price"  placeholder="価格"/>
			    	<form:errors path="price" cssStyle="color:red"/>
			    </td>
			  </tr>
			  <tr>
			    <th>
			     	 ISBNコード
			    </th>
			    <td>
			    	<form:input path="isbncode" placeholder="ISBNコード(#-####-####-#)"/>
			    	<form:errors path="isbncode" cssStyle="color:red"/>
			    </td>
			  </tr>
			  <tr>
			    <th>
			     	 発売日
			    </th>
			    <td>
			   		<form:input type="date" path="saledate" max="2999-12-31" placeholder="発売日"/>
			    	<form:errors path="saledate" cssStyle="color:red"/>
			    </td>
			  </tr>
			  <tr>
			    <th>
			     	 説明
			    </th>
			    <td>
			    	<form:input path="explanation"  placeholder="説明"/>
			    	<form:errors path="explanation" cssStyle="color:red"/>
			    </td>
			  </tr>
			  <tr>
			    <th>
			     	 画像
			    </th>
			    <td>
			    	<form:input path="image"  placeholder="画像"/>
			    	<form:errors path="image" cssStyle="color:red"/>
			    </td>
			  </tr>
			  <tr>
			    <th>
			     	 在庫数
			    </th>
			    <td>
			    	<form:input path="stock"  placeholder="在庫数"/>
			    	<form:errors path="stock" cssStyle="color:red"/>
			    </td>
			  </tr>
			  
			  <tr>
			  	<td></td>
			    <td>
					<input class="btn" type="submit" value="登録">
			    </td>
			  </tr>
			</table>
		  </form:form>
		</div>
	</div>
</div>
</body>
</html>