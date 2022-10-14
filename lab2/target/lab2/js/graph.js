let board = JXG.JSXGraph.initBoard('jxgbox', {boundingbox: [-4, 4, 4, -4], axis:true});

function setVerticesInvisible(figure) {
    for (let i = 0; i < figure.vertices.length - 1; i++) {
        figure.vertices[i].setAttribute({visible: false});
    }
}

function wrapData(x, y) {
    return `x_value=${Math.round(x*100)/100}&y_value=${Math.round(y*100)/100}&r_value=${$('#r_value').val().trim()}`
}

function createTriangle(R) {
    let p1 = board.create('point', [0, 0], {name: '', size: 2, fixed: true});
    let p2 = board.create('point', [0, R], {name: '', size: 2, fixed: true});
    let p3 = board.create('point', [R, 0], {name: '', size: 2, fixed: true});
    let triangle = board.create('polygon', [p1, p2, p3], {borders: {strokeColor: 'black'}, fixed: true});
    setVerticesInvisible(triangle);
    return triangle;
}

function createRectangle(R) {
    let p1 = board.create('point', [0, 0], {name: '', size: 2, fixed: true});
    let p2 = board.create('point', [-R, 0], {name: '', size: 2, fixed: true});
    let p3 = board.create('point', [-R, R/2], {name: '', size: 2, fixed: true});
    let p4 = board.create('point', [0, R/2], {name: '', size: 2, fixed: true});
    let rectangle = board.create('polygon', [p1, p2, p3, p4], {borders: {strokeColor: 'black'}, fixed: true});
    setVerticesInvisible(rectangle);
    return rectangle;
}

function createCircle(R) {
    let p1 = board.create('point', [0, 0], {name: '', size: 2, fixed: true, visible: false})
    let p2 = board.create('point', [0, -R/2], {name: '', size: 2, fixed: true, visible: false})
    let p3 = board.create('point', [R/2, 0], {name: '', size: 2, fixed: true, visible: false})
    return board.create('sector', [p1, p2, p3], {strokeColor: 'black'});
}

let getMouseCoords = function(e, i) {
        let cPos = board.getCoordsTopLeftCorner(e, i),
            absPos = JXG.getPosition(e, i),
            dx = absPos[0]-cPos[0],
            dy = absPos[1]-cPos[1];
        return new JXG.Coords(JXG.COORDS_BY_SCREEN, [dx, dy], board);
    },
    down = function(e) {
        if (e.button === 2 || e.target.className === 'JXG_navigation_button') {
            return;
        }
        let canCreate = true, i, coords, el;

        if (e[JXG.touchProperty]) {
            // index of the finger that is used to extract the coordinates
            i = 0;
        }
        coords = getMouseCoords(e, i);
        let R = $('#r_value').val().trim();
        if (canCreate && validateR(R)) {
            postRequest(wrapData(coords.usrCoords[1], coords.usrCoords[2]));
            board.create('point', [coords.usrCoords[1], coords.usrCoords[2]], {name:'',size:1, fixed: true});
        }
    }

board.on("down", down);

let previousObjects = null;

$('#r_value').change(function () {
    let R = $('#r_value').val().trim();
    if (validateR(R)) {
        cleanErrorMessages();
        let o1 = createTriangle(R),
        o2 = createRectangle(R),
        o3 = createCircle(R);
        if (previousObjects) {
            board.removeObject(previousObjects);
        }
        previousObjects = [o1, o2, o3];
    }
})
