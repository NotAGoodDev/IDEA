$(document).ready(function() {
    let url = window.location.pathname.split('/');
    //[0]   none
    //[1]   creator
    //[2]   viewIdea
    //[3]   {id}

    ApiController.get("ideas/" + url[3], url[3], false).then(function (idea) {
        document.getElementById("titleHTML").innerHTML += idea.title;

        document.getElementById("titulo").value = idea.title;
        document.getElementById("empresa").value = idea.enterprise;
        document.getElementById("categoria").value = idea.category;
        document.getElementById("descripcion-corta").value = idea.summary;
        document.getElementById("descripcion-larga").value = idea.description;
    });
});