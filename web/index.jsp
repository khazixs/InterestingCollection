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
    <title>INTERESTING_COLLECTIONS</title>
  </head>
  <body>
  <div id="context">
    <table>
      <tr style="align-content: center">
        <td>Now</td>
        <td>let</td>
        <td>us</td>
        <td>begin</td>
        <td>a</td>
        <td>new</td>
        <td>life</td>
      </tr>
    </table>

  </div>
  </body>
  <script>

    let td = document.getElementsByTagName("td");
    let r,g,b,rgb;
    for (let i = 0;i<td.length;i++) {
      r = Math.floor(Math.random() * 255);
      g = Math.floor(Math.random() * 255);
      b = Math.floor(Math.random() * 255);
      rgb = `rgb(${r},${g},${b}`;
      td[i].style.color = rgb;
      td[i].style.fontSize = '100px';
    }

  </script>
</html>
