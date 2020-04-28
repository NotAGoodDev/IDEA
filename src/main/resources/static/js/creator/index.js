$( document ).ready(function() {

    ApiController.get("auth/session", "", false).then(function (data) {
        let id = data.id;
        let tamIdeas = data.idea.length;
        console.log(data);


        //Para listar todas las ideas
        for (let i = 0; i < tamIdeas; i++) {
            document.getElementById("myIdeas").innerHTML +=
            '<div class="container lista-idea">\n'+
            '\t<div class="col-12">\n'+
            '\t\t<div class="row">\n'+
            '\t\t\t<div class="col-6 text-left">'+
            '\t\t\t\t<h3>'+data.idea[i].title+'</h3>'+
            '\t\t\t</div>\n'+
            '\t\t\t<div class="col-6 col-lg-6 data text-right">\n'+
            '\t\t\t\t<div class="row">\n'+
            '\t\t\t\t\t<div class="col-10 data text-right">\n'+
            '\t\t\t\t\t\t<span class="btn-edit">\n'+
            '\t\t\t\t\t\t\t<a href="/creator/viewIdea/' + data.idea[i].id + '"> <i class="fa fa-pencil-alt"style="font-size: 25px"></i></a>\n'+
            '\t\t\t\t\t\t</span>\n'+
            '\t\t\t\t\t</div>\n'+
            '\t\t\t\t\t<div class="col-2 data text-right">\n'+
            '\t\t\t\t\t\t<span class="btn-edit" onclick=confirm()>\n'+
            '\t\t\t\t\t\t\t<a><i class="far fa-trash-alt" style="font-size: 25px"></i></a>\n'+
            '\t\t\t\t\t\t</span>\n'+
            '\t\t\t\t\t</div>\n'+
            '\t\t\t\t</div>\n'+
            '\t\t\t</div>\n'+
            '\t\t</div>\n'+
            '\t\t<div class="row">\n'+
            '\t\t\t<div class="col-lg-12 text-right">\n'+
            '\t\t\t\t<h5>'+data.idea[i].createdAt+'</h5>'+
            '\t\t\t</div>\n'+
            '\t\t</div>\n'+
            '\t\t<div class="row">\n'+
            '\t\t\t<a href="/creator/viewIdea/' + data.idea[i].id + '">' + data.idea[i].summary + '</a>' + '\n'+
            '\t\t</div>\n'+
            '\t</div>\n'+
            '</div>\n ' +
            '<div class="sep"></div>'
        }

        // Ideas aceptadas: FALTA POR HACER
        for (let i = 0; i < tamIdeas; i++) {
            ApiController.get("ideas/" + data.idea[i].id, null, false).then(function (idea) {
                //console.log(idea);
                if(idea.dealDone){
                    document.getElementById("ideas-aceptadas-html").innerHTML +=
                    '<div class="container lista-idea mt-5">\n'+
                    '\t<div class="col-12">\n' +
                    '\t\t<div class=" row">\n' +
                    '\t\t\t<div class="col-12 text-left">\n'+
                    '\t\t\t\t<h3>'+ data.idea[i].title + '</h3>\n'+
                    '\t\t\t</div>\n'+
                    '\t\t</div>\n'+
                    '\t\t<div class=" row">\n'+
                    '\t\t\t<div class="col-12 text-right">\n'+
                    '\t\t\t\t<h5>' + data.idea[i].createdAt + '</h5>\n'+
                    '\t\t\t</div>\n'+
                    '\t\t</div>\n'+
                    '\t\t<div class="row">\n'+
                    '\t\t\t\t<a href="/enterprise/deal/' + idea.deal + '">'+ data.idea[i].summary + '</a>\n'+
                    '\t\t</div>\n'+
                    '\t</div>\n'+
                    '</div>\n';
                }
            });
        }
    });
});


