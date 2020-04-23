$( document ).ready(function() {


    ApiController.get("auth/session", "", false).then(function (data) {
        let listaIdeas = [];
        let id = data.id;
        let tamIdeas = data.idea.length;
        console.log(data);

        //Ideas restantes
        document.getElementById("remainingIdeas").innerHTML +=
            '<p>Ideas restantes por recibir: ' + data.remainingIdeas + '</p>'

        //Para listar todas las ideas
        for (let i = 0; i < tamIdeas; i++) {
            document.getElementById("ideasRecibidas").innerHTML +=
                '<div class="container lista-idea">\n' +
                '\t<div class="row">\n' +
                '\t\t<div class="col-12 data">\n' +
                '\t\t\t<a href="/enterprise/' + data.id + '/viewIdea/' + data.idea[i].id + '">\n' +         /* /enterprise/idEnterprise/idea/idIdea */
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

        //Falta hacer las compradas (BACKEND)
      /*  for (let i = 0; i < tamIdeas; i++) {
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
        }*/

       //Categorias disponibles
       ApiController.get("categories/", "", false).then(function (category) {
        //console.log(category);
        for(let i = 0; i < category.length; i++)
        {
            let elemento = "<option> " + category[i].name + " </option> ";
            $('#selectType').append(elemento);
        }
    });


    });

    document.getElementById("selectType").onchange = function(){
        ApiController.get("auth/session", "", false).then(function (data) {
            let listaIdeas = [];
            let id = data.id;
            let tamIdeas = data.idea.length;
          //  console.log(data);
    
            document.getElementById("ideasRecibidas").innerHTML = "";
            for (let i = 0; i < tamIdeas; i++){
                ApiController.get("ideas/"+data.idea[i].id,"",false).then(function(ideaApi){
                    if(ideaApi.category == document.getElementById("selectType").value)
                    document.getElementById("ideasRecibidas").innerHTML +=
                        '<div class="container lista-idea">\n' +
                        '\t<div class="row">\n' +
                        '\t\t<div class="col-12 data">\n' +
                        '\t\t\t<a href="/enterprise/' + data.id + '/viewIdea/' + data.idea[i].id + '">\n' +         /* /enterprise/idEnterprise/idea/idIdea */
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
                })
            }
    
            //Falta hacer las compradas (BACKEND)
           /* document.getElementById("ideasCompradas").innerHTML = "";
            for (let i = 0; i < tamIdeas; i++){
                ApiController.get("ideas/"+data.idea[i].id,"",false).then(function(ideaApi){
                    if(ideaApi.category == document.getElementById("selectType").value)
                    document.getElementById("ideasCompradas").innerHTML +=
                        '<div class="container lista-idea">\n' +
                        '\t<div class="row">\n' +
                        '\t\t<div class="col-12 data">\n' +
                        '\t\t\t<a href="/enterprise/' + data.id + '/viewIdea/' + data.idea[i].id + '">\n' +         
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
                })
            }*/
         
        });
    }
});


