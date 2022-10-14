function start() {
    let y = $('#y_value').val().trim();
    let r = $('#r_value').val().trim();

  cleanErrorMessages();

  if (validateY(y) & validateR(r)) {
      console.log($('form').serialize())
      postRequest($('form').serialize());
  }
}

function validateY(y) {
    if (y === "") {
      setErrorFor("y", "Введена пустая строка!");
      return false;
    }

    if (y.split(' ').length > 1) {
      setErrorFor("y", "Введите число!");
      return false;
    }



    if (isNaN(parseInt(y)) || y.length > 10) {
      setErrorFor("y", "Пожалуйста, вводите число, состоящее не более чем из 10 знаков.");
      return false;
    }

    if (y >= 3 || y <= -5) {
      setErrorFor("y", "Пожалуйста, введите число, соответствующее промежутку.");
      return false;
    }

    return true;
  }

function validateR(r) {

    if (r === "") {
        setErrorFor("r", "Введена пустая строка!");
        return false;
    }

    if (r.split(' ').length > 1) {
        setErrorFor("r", "Введите число!");
        return false;
    }

    if (isNaN(parseInt(r)) || r.length > 10) {
        setErrorFor("r", "Пожалуйста, вводите число, состоящее не более чем из 10 знаков.");
        return false;
    }

    if (parseFloat(r) >= 5 || parseFloat(r) <= 2) {
        setErrorFor("r", "Пожалуйста, введите число, соответствующее промежутку.");
        return false;
    }

    return true;
}

  //return true;

function postRequest(data) {
    console.log(data)
    $.ajax({
        type: "POST",
        url: './controller',
        data: data,
        success: handleResponse,
    });

}

function handleResponse(data) {

    let obj = JSON.parse(data);

    $('#result-table tr:last').after(
        `<tr> " +
        "<td>${obj.x}</td> " +
        "<td>${obj.y}</td> " +
        "<td>${obj.r}</td> " +
        "<td>${obj.currentTime}</td> " +
        "<td>${obj.executionTime}</td> " +
        "<td>${obj.isHit}</td> " +
        "</tr>`
    );

    console.log("сейчас буду рисовать");
    drawPoint(obj.x, obj.y);

}

function cleanErrorMessages() {
  setErrorFor("y", "");
  setErrorFor("r", "")
}

function setErrorFor(input, message) {
  $("." + input + "-error-message").html("<span>" + message + "</span>");
}

function drawPoint(x, y) {
    board.create('point', [x, y], {name: '', size: 2, fixed: true});
}
