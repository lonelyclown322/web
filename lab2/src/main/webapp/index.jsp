<%--
  Created by IntelliJ IDEA.
  User: smyts
  Date: 26.09.2022
  Time: 23:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" buffer="8kb" language="java" %>
<html lang="ru">
<meta charset="utf-8">



<head>
    <title> WEB LAB 2 </title>
    <link rel="stylesheet" type="text/css" href="resources/styles/jsx.css" />
    <link rel="stylesheet" type="text/css" href="resources/styles/mainstyle.css" />


</head>

<body>

<div id="header">
    <h1 id="name">Мыц Степан</h1>
    <h2>P32141, var: 15130 | ITMO, Saint-Petersburg</h2>
</div>

<div id="container">

    <div id="jxgbox" class="graph" style="height: 300px; width: 300px"></div>

<%--    <canvas id="myCanvas" width="300" height="300"></canvas>--%>
<%--        <svg width="300" height="300">--%>

<%--            <line x1="0" x2="300" y1="150" y2="150"></line>--%>
<%--            <line x1="150" x2="150" y1="0" y2="300"></line>--%>

<%--            <polygon points="150,0 145,15 155,15" stroke="yellow"></polygon>--%>
<%--            <polygon points="300,150 285,145 285,155" stroke="yellow"></polygon>--%>

<%--            <polygon points="50,100 150,100 150,150 50,150"></polygon>--%>

<%--            <path d="M200,150--%>
<%--            A50,50 0 0,1 150,200--%>
<%--            L 150,150--%>
<%--            Z"></path>--%>

<%--            <polygon points="150,150 250,150 150,50"></polygon>--%>

<%--            <text x="285" y="135">X</text>--%>
<%--            <text x="160" y="15">Y</text>--%>

<%--            <line x1="100" x2="100" y1="140" y2="160"></line>--%>
<%--            <line x1="200" x2="200" y1="140" y2="160"></line>--%>

<%--            <line x1="140" x2="160" y1="100" y2="100"></line>--%>
<%--            <line x1="140" x2="160" y1="200" y2="200"></line>--%>

<%--            <text x="40" y="130">-R</text>--%>
<%--            <text x="85" y="130">-R/2</text>--%>
<%--            <text x="190" y="130">R/2</text>--%>
<%--            <text x="245" y="130">R</text>--%>

<%--            <text x="170" y="52.5">R</text>--%>
<%--            <text x="170" y="102.5">R/2</text>--%>
<%--            <text x="170" y="202.5">-R/2</text>--%>
<%--            <text x="170" y="252.5">-R</text>--%>
<%--        </svg>--%>

    <form class="form" id="form" onsubmit="start();
                                      return false;"
          method="post">

        <div class="x-value">
            <%--@declare id="x-value"--%><label for="x-value" class="input-label">Значение X:</label><br><br>
                <input type="radio" id="radio-option-1" name="x_value" value="-5" checked="checked">
                <label for="radio-option-1">-5</label>
                <input type="radio" id="radio-option-2" name="x_value" value="-4">
                <label for="radio-option-2">-4</label>
                <input type="radio" id="radio-option-3" name="x_value" value="-3">
                <label for="radio-option-3">-3</label><br>
                <input type="radio" id="radio-option-4" name="x_value" value="-2">
                <label for="radio-option-4">-2</label>
                <input type="radio" id="radio-option-5" name="x_value" value="-1">
                <label for="radio-option-5">-1</label>
                <input type="radio" id="radio-option-6" name="x_value" value="0">
                <label for="radio-option-6">&nbsp0</label><br>
                <input type="radio" id="radio-option-7" name="x_value" value="1">
                <label for="radio-option-7">&nbsp1</label>
                <input type="radio" id="radio-option-8" name="x_value" value="2">
                <label for="radio-option-8">&nbsp2</label>
                <input type="radio" id="radio-option-9" name="x_value" value="3">
                <label for="radio-option-9">&nbsp3</label>
        </div>

        <div class="y-value">
            <%--@declare id="y-value"--%><label for="y-value" class="input-label">Значение Y:</label><br><br>
            <input type="text" id="y_value" name="y_value" placeholder="Введите координату Y">
            <div class="y-error-message" style="height: 18px; margin-top: 23px;"></div>
        </div>

        <div class="r-value">
            <%--@declare id="r-value"--%><label for="r-value" class="input-label">Значение R:</label><br><br>
                <input type="text" id="r_value" name="r_value" placeholder="Введите значение R">
                <div class="r-error-message" style="height: 18px; margin-top: 23px;">
                </div>
        </div>

        <input type="submit" value="Submit">

    </form>

    <div class="table-section">
        <table id="result-table">
            <thead>
                <tr>
                    <th>X</th>
                    <th>Y</th>
                    <th>R</th>
                    <th>Текущее время</th>
                    <th>Время выполнения</th>
                    <th>Результат</th>
                </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </div>

</div>

<script type="text/javascript" src="js/jquery-3.6.0.js"></script>
<script type="text/javascript" src="js/onloadscript.js"></script>
<script type="text/javascript" src="js/validation.js"></script>
<script type="text/javascript" src="js/http_jsxgraph.org_distrib_jsxgraphcore.js"></script>
<script type="text/javascript" src="js/graph.js"></script>

</body>
</html>
