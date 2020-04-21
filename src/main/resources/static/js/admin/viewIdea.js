$(document).ready(function() {
    let url = window.location.pathname.split('/');
    //[0]   none
    //[1]   admin
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

    $("#btn-revision").click(function(event){
        event.preventDefault();

        let id = url[3];
        ApiController.get("ideas/"+id, "", false).then(function (idea) {
            console.log(idea);
            $.confirm({
                title: idea.title,
                content: '¿Confirmar idea?',
                closeIcon: true,
                buttons: {
                    confirmar: function () {
                        ApiController.post("admin/ideas/" + id + "/confirmar", "", false).then(function (data) {
                            console.log(data);
                            $.alert('Idea confirmada');
                            $(location).attr('href',"/");
                        });
                    },
                    borrar: function () {
                        ApiController.post("admin/ideas/" + id + "/borrar", "", false).then(function (data) {
                            console.log(data);
                            $.alert('Idea borrada');
                            $(location).attr('href',"/");
                        });
                    },
                }
            });
        });
    });

});

