var idea_global;
$(document).ready(function() {
    let url = window.location.pathname.split('/');
    //[0]   none
    //[1]   creator
    //[2]   viewIdea
    //[3]   {id}

    let currentUsername;
    ApiController.get("auth/session", "", false).then(function (user) {
        currentUsername = user.username;
    });




    // Para mostrar dinamicamente las empresas
    let empresas = [];
    ApiController.get("enterprises/", "", false).then(function (data) {
        data.forEach(function (enterprise) {
            empresas.push(enterprise.name);
        });
    });

    $("#search-empresa").autocomplete({
        source: empresas,
        // focus: necesario para ocultar un div que lo pone JQuery
        focus: function (event, ui) {
            $(".ui-helper-hidden-accessible").hide();
            event.preventDefault();
        }
    });


    ApiController.get("ideas/" + url[3],url[3], false).then(function (idea) {
        idea_global = idea;
        idea_global.id = url[3];
        console.log(idea_global);
        $("titleHTML").innerHTML += idea.title;

        $("[name = title]").val(idea.title);
        $("[name = search-empresa]").val(idea.enterprise);
        let options = $('#search-category option');
        console.log(options);
        $("[name =descripcion-corta]").val(idea.summary);
        $("[name =descripcion-larga]").val(idea.description);
    });

    // Para mostrar las categorias dispinibles
    ApiController.get("categories/", "", true).then(function(data){
        data.forEach(function(categoria){
            let selected = "";
            if(idea_global.category == categoria.name) selected = "selected";
            let elemento = "<option value='" + categoria.name + "' " + selected + "> " + categoria.name + " </option> ";
            $('#search-category').append(elemento);
        })
    });


    $("#set-idea").click(function () {
        var idea = {};

        let options = $('#search-category option');
        console.log(options);

        idea.title = $("[name = title]").val();
        idea.enterprise = $("[name = search-empresa]").val();
        idea.category = $("[name = search-category]").val();
        idea.summary = $("[name = descripcion-corta]").val();
        idea.description = $("[name = descripcion-larga]").val();
        idea.active = true;
        idea.state = "Revision";
        idea.id = idea_global.id;
        idea.deal = idea_global.deal;
        idea.creator = idea_global.creatorUser;
        idea.createdAt = idea_global.createdAt;

        console.log("Idea:");
        console.log(idea);

        ApiController.put("ideas/", idea).then(function (modifyIdea) {
            console.log(modifyIdea);
        });

        window.location.href = "/home";
    });

});