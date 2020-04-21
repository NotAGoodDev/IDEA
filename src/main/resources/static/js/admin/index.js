$( document ).ready(function() {




    ApiController.get("ideas", "", false).then(function (data) {

        console.log(data);

        for (let i = 0; i < data.length; i++) {
            document.getElementById("lista-todas-ideas").innerHTML +=
            '<a href="/admin/viewIdea/' + data[i].id + '"><div class="container lista-idea">\n'+
            '\t<div class="col-12">\n'+
            '\t\t<div class="row">\n'+
            '\t\t\t<div class="col-6 text-left">'+
            '\t\t\t\t<h3>' + data[i].title + '</h3>'+
            '\t\t\t</div>\n'+
            '\t\t\t<div class="col-6 col-lg-6 data text-right">\n'+
            '\t\t\t<div>\n'+
            '\t\t\t\t<h5>' + data[i].createdAt + '</h5>'+
            '\t\t\t</div>\n'+
            '\t\t\t<div>\n'+
            '\t\t\t\t<span>Estado: ' + data[i].state + '</span>'+
            '\t\t\t</div>\n'+
            '\t\t\t</div>\n'+
            '\t\t</div>\n'+
            '\t\t<div class="row">\n'+
            '\t\t\t<div>' + data[i].summary + '</div>' +
            '\t\t\t<button class="btn-right btn-ver" onclick=adminConfirmIdea(event,' + data[i].id + ')>Revisión</button>'+
            '\t\t</div>\n'+
            '\t</div>\n'+
            '</div>\n ' +
            '<div class="sep"></div></a>'
        }




    });


    ApiController.get("ideas/state/revision", "", false).then(function (data) {

        console.log(data);

        for (let i = 0; i < data.length; i++) {
            document.getElementById("lista-ideas-confirmar").innerHTML +=
            '<a href="/admin/viewIdea/' + data[i].id + '"><div class="container lista-idea">\n'+
            '\t<div class="col-12">\n'+
            '\t\t<div class="row">\n'+
            '\t\t\t<div class="col-6 text-left">'+
            '\t\t\t\t<h3>' + data[i].title + '</h3>'+
            '\t\t\t</div>\n'+
            '\t\t\t<div class="col-6 col-lg-6 data text-right">\n'+
            '\t\t\t<div>\n'+
            '\t\t\t\t<h5>' + data[i].createdAt + '</h5>'+
            '\t\t\t</div>\n'+
            '\t\t\t<div>\n'+
            '\t\t\t\t<span>Estado: ' + data[i].state + '</span>'+
            '\t\t\t</div>\n'+
            '\t\t\t</div>\n'+
            '\t\t</div>\n'+
            '\t\t<div class="row">\n'+
            '\t\t\t<div>' + data[i].summary + '</div>' +
            '\t\t\t<button class="btn-right btn-ver confirmar" onclick=adminConfirmIdea(event,' + data[i].id + ')>Revisión</button>'+
            '\t\t</div>\n'+
            '\t</div>\n'+
            '</div>\n ' +
            '<div class="sep"></div></a>'
        }

    });




});


function adminConfirmIdea( event, id) {
    event.preventDefault();
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
                        location.reload();
                    });
                },
                borrar: function () {
                    ApiController.post("admin/ideas/" + id + "/borrar", "", false).then(function (data) {
                        console.log(data);
                        $.alert('Idea borrada');
                        location.reload();
                    });
                },
            }
        });
    });

}



