function fixRequiredLabels() {
    var labels = document.getElementsByTagName('label');
    for (i=0; i<labels.length; i++) {
        if (labels[i].className == 'required') {
            labels[i].innerHTML = labels[i].innerHTML + "*";
        }
    }
}

function onLoad() {
    fixRequiredLabels();
}

window.onload = onLoad;