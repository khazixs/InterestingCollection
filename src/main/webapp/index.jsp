<%--
  Created by IntelliJ IDEA.
  User: 洗衣机
  Date: 2020/4/9
  Time: 18:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>梦开始的地方</title>
  </head>
  <body>
    <form action="http://localhost:8080/InterestingCollection_war/user/getUserByUserId">
      UserId:<br/>
      <label>
        <input type="text" name="userId" value="">
      </label><br/>

      <input type="submit" value="submit">
    </form>
  </body>
</html>
