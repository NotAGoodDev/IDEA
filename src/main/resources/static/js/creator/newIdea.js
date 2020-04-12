$(document).ready(function() {
    /*
    $('#search-empresa').keyup(function(){
        var value = $(this).val().toLowerCase();
        $('.list-empresas-result li').each(function(){
            if($(this).text().toLowerCase().indexOf(value) >= 0){
                $(this).show();
            }else{
                $(this).hide();
            }
        });

    });

    $('#search-empresa').focusin(function(){
        $('.container-empresas-result').show();
    });

     $('#search-empresa').focusout(function(){
         $('.container-empresas-result').hide();
    });


    $('.list-empresas-result li').click(function(){
        var val = $(this).text();
        console.log(val);
        $('#search-empresa').val(val);
    });
    */

    // Para coger el ID del creador para poder pasarlo a la base de datos
    let currentUsername;
    ApiController.get("auth/session", "", false).then(function (user) {
        currentUsername = user.username;
    });

    // Para mostrar las categorias dispinibles
    ApiController.get("categories/", "", true).then(function(data){
        data.forEach(function(categoria){
            let elemento = "<option> " + categoria.name + " </option> ";
            $('#search-category').append(elemento);
        })
    });


    // Para mostrar dinamicamente las empresas
    let empresas = [];
    ApiController.get("enterprises/", "", false).then(function (data) {
        data.forEach(function (enterprise) {
            empresas.push(enterprise.name);
        });
    });

    $("#search-empresa").autocomplete({
        source: empresas
    });

    // Creamos la idea y luego hay que enviarsela al administrador para que la acepte o la rechace
    $("#crear-idea").click(function () {
        let idea = {};
        idea.title = document.getElementById("title").value;
        idea.description = document.getElementById("large-description").value; // large description
        idea.summary = document.getElementById("short-description").value; // short description
        idea.active = true;
        idea.enterprise = document.getElementById("search-empresa").value;
        idea.creator = currentUsername;
        idea.category = document.getElementById("search-category").value;

        ApiController.post("ideas/", idea).then(function (createdIdea) {

        });

        window.location.href = "/home";
    });

    $( "#cancel" ).on('click', function( event ) {
        window.location.href = "/home";
    });

});
