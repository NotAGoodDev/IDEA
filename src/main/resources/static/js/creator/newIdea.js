$(document).ready(function() {

    $('#search-empresa').keyup(function(){
        var value = $(this).val().toLowerCase();
        if(value != ""){
            var result = false;
            $('.list-empresas-result li').each(function(){
                if($(this).text().toLowerCase().indexOf(value) >= 0){
                    $(this).show();
                    result = true;
                }else{
                    $(this).hide();
                }
            });
            console.log(result);
            if(!result){
                console.log("Entra");
                $('.container-empresas-result').hide();
            }else{
                $('.container-empresas-result').show();
            }

        }else{
            $('.container-empresas-result').hide();
        }
    });
})