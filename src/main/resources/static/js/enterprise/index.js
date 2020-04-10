$( document ).ready(function() {


    ApiController.get("auth/session","",false).then(function(data){
        let listaIdeas = [];
        let id = data.id;
        console.log(data);

        ApiController.get("id/" + id, id, true).then(function (enterprise) {
            console.log(enterprise);

        })
    });


});