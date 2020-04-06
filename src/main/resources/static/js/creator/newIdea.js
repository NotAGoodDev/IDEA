$(document).ready(function() {

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

    ApiController.get("categories/", "", true).then(function(data){
        data.forEach(function(categoria){
            let elemento = "<option> " + categoria.name + " </option> ";
            $('#search-category').append(elemento);
        })
    });


    // Para mostrar dinamicamente las empresas
    let empresas = [];
    ApiController.get("enterprises/", "", false).then(function (data) {
        let ul = document.getElementById('enterprises-list');

        data.forEach(function (enterprise) {
            empresas.push(enterprise.name);
            let li = document.createElement('li');
            li.appendChild(document.createTextNode(enterprise.name));
            ul.appendChild(li);
        });
    });















});