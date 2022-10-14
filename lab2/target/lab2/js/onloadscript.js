function fillTable(arrayOfJsonDots) {
    for (let i = 0; i < arrayOfJsonDots.length; i++) {
        let obj = JSON.parse(arrayOfJsonDots[i]);
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
    }
}

function fillGraph(arrayOfJsonDots) {
    for (let i = 0; i < arrayOfJsonDots.length; i++) {
        let obj = JSON.parse(arrayOfJsonDots[i]);
        board.create('point', [obj.x, obj.y], {name: '', size: 2, fixed: true});
    }
}

addEventListener('load', (event) => {
    $.ajax({
        type: "GET",
        url: './controller',
        success: function (data) {
            let arrayOfJsonDots = JSON.parse(data);
            fillGraph(arrayOfJsonDots);
            fillTable(arrayOfJsonDots);
        }
    });
});