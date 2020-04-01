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
    var elemento = "<option> " + categoria.name + " </option> "
    $('#select-type').append(elemento);
    })

    });

})