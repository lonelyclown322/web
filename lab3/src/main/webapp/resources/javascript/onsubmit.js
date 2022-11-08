function onsubmit() {
    if ($('input[type="text"]')[0].value === "") {
        alert("Значение Y не может быть пустой стркой!");
    }
    return $('input[type="text"]')[0].value !== "";

}

function fillGraph() {
    let rows = $('table')[0].rows;

    for (let i = 1; i < rows.length; i++) {
        let x = rows[i].cells[0].textContent;
        let y = rows[i].cells[1].textContent;
        let result = rows[i].cells[3].textContent;

        if (x !== "" && y!== "" && result !== "") {
            drawPoint(x, y, result);
        }
    }
}




// function onsubmit() {
//     return validateX() && validateY() && validateR();
// }
//
//
// function validateX() {
//     let counter = 0;
//     for (const checkbox of $(".x-value input[type=checkbox]")) {
//         if (checkbox.checked) {
//             counter++;
//         }
//     }
//
//     if (counter !== 1) {
//         alert("Должно быть выбрано ровно 1 значение Х");
//         return false;
//     }
//
//     return true;
// }
//
// function validateY() {
//     let y = $('input[type="text"]')[0].value;
//
//     if (y === "") {
//         alert("Значение Y обязательно должно быть введено!");
//         return false;
//     }
//
//     if (isNaN(y) || y.split(' ').length > 1) {
//         alert("Значение Y должно быть одним числом!");
//         return false;
//     }
//
//
//
//     if (y.length > 10) {
//         alert("Значение Y не может состоять из более чем 10 знаков.");
//         return false;
//     }
//
//     if (y >= 5 || y <= -3) {
//         alert("Значение Y должно соответствовать промежутку.");
//         return false;
//     }
//
//     return true;
// }
//
// function validateR() {
//     let counter = 0;
//     for (const checkbox of $(".r-value input[type=checkbox]")) {
//         if (checkbox.checked) {
//             counter++;
//         }
//     }
//
//     if (counter !== 1) {
//         alert("Должно быть выбрано ровно 1 значение R");
//         return false;
//     }
//
//     return true;
// }
