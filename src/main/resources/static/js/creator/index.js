$( document ).ready(function() {


    ApiController.get("auth/session", "", false).then(function (data) {
        let listaIdeas = [];
        let id = data.id;
        let tamIdeas = data.idea.length;
        console.log(data);


        //Para listar todas las ideas
        for (let i = 0; i < tamIdeas; i++) {
            document.getElementById("myIdeas").innerHTML +=
        '<div class="container lista-idea">\n'+
                '<div class="col-12">\n'+
                '<div class="row">\n'+
                '<div class="col-6 text-left"><h3>'+data.idea[i].title+'</h3></div>\n'+
            '<div class="col-6 col-lg-6 data text-right">\n'+
                '<div class="row">\n'+

                '<div class="col-10 data text-right">\n'+
                '<span class="btn-edit">\n'+
                '<a href="/creator/viewIdea"> <i class="fa fa-pencil-alt"style="font-size: 25px"></i></a>\n'+
            '</span>\n'+
            '</div>\n'+

            '<div class="col-2 data text-right">\n'+
                '<span class="btn-edit" onclick=confirm()>\n'+
                '<a><i class="far fa-trash-alt" style="font-size: 25px"></i></a>\n'+
            '</span>\n'+
            '</div>\n'+
            '</div>\n'+
            '</div>\n'+
            '</div>\n'+
            '<div class="row">\n'+
                '<div class="col-lg-12 text-right">\n'+
                '<h5>'+data.idea[i].createdAt+'</h5></div>\n'+
            '</div>\n'+
            '<div class="row">\n'+
                '<a href="/creator/viewIdea">' + data.idea[i].summary + '\n'+
                '</a>\n'+
            '</div>\n'+
            '</div>\n'+
            '</div>\n ' +
                '<div class="sep"></div>'

        }

        //Falta hacer las compradas (BACKEND)
        for (let i = 0; i < tamIdeas; i++) {
            document.getElementById("ideasCompradas").innerHTML +=
                '<div class="container lista-idea">\n' +
                '\t<div class="row">\n' +
                '\t\t<div class="col-12 data">\n' +
                '\t\t\t<a href="/enterprise/viewIdea/' + data.idea[i].id + '">\n' +
                '\t\t\t\t<div class="row">\n' +
                '\t\t\t\t\t<div class="col-8 col-lg-8 data">\n' +
                '\t\t\t\t\t\t<strong>' + data.idea[i].title + '</strong>\n' +
                '\t\t\t\t\t</div>\n' +
                '\t\t\t\t\t<div class="col-4 col-lg-4 data text-right">\n' +
                '\t\t\t\t\t\t<p>' + data.idea[i].createdAt + '\n' +
                '\t\t\t\t\t\t\t<br>\n' +
                '\t\t\t\t\t\t</p>\n' +
                '\t\t\t\t\t\t<div class="row">\n' +
                '\t\t\t\t\t\t\t<div class="col-3 data"></div>\n' +
                '\t\t\t\t\t\t\t<div class="col-3 data"></div>\n' +
                '\t\t\t\t\t\t</div>\n' +
                '\t\t\t\t\t</div>\n' +
                '\t\t\t\t</div>\n' +
                '\n' +
                '\n' +
                '\t\t\t\t<div class="col-12 col-lg-12 data">\n' +
                '\t\t\t\t\t<p>' + data.idea[i].summary + '</p>\n' +
                '\t\t\t\t</div>\n' +
                '\t\t\t</a>\n' +
                '\t\t</div>\n' +
                '\t</div>\n' +
                '</div>\n'
        }



        //Categorias disponibles
        ApiController.get("categories/", "", false).then(function (category) {
            //console.log(category);
            for(let i = 0; i < category.length; i++)
            {
                document.getElementById("selectType").innerHTML +=
                    '                <option value="Categoria '+ i +'">' + category[i].name + '</option>\n';
            }
        });
    });
});


