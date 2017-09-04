/**
 * Created by dev on 08.08.17.
 */
init = function () {
    var ws = new WebSocket("ws://localhost:8095/getCacheInfo");
    ws.onopen = function (event) {

    }
    ws.onmessage = function (event) {
        var cachInfo = JSON.parse(event.data);
        document.getElementById("hitCount").innerHTML = cache.hitCount;
        document.getElementById("missCount").innerHTML = cache.missCount;
    }
    ws.onclose = function (event) {
    }
};