$(document).ready(function() {
    let url = window.location.pathname.split('/');
    //[0]   none
    //[1]   enterprise
    //[2]   deal
    //[3]   {idIdea}
    var date = new Date();
    var day = date.getDate();
    var month = date.getMonth() + 1;    //Enero -> 0, Febrero -> 1
    var year = date.getFullYear();

    var dateString = day + "/" + month + "/" + year;

    ApiController.get("deal/" + url[3], url[3], false).then(function (deal) {
        console.log(deal);

        /* Los reemplazos de texto son a la fuerza bruta */
        document.getElementById("name").value = deal.title;

        document.getElementById("author").value = deal.creator;

        document.getElementById("enterprise").value = deal.enterprise;

        //sustituimos en la parte del texto del contrato toodo lo que sea clase user_sub

        let user_sub = document.getElementsByClassName("user_sub");

        [].slice.call( user_sub ).forEach(function(part){
            part.innerHTML = deal.creator;
        });

        //sustituimos en la parte del texto del contrato toodo lo que sea clase ent_sub

        let ent_sub = document.getElementsByClassName("ent_sub");

        [].slice.call( ent_sub ).forEach(function(part){
            part.innerHTML = deal.enterprise;
        });

        //sustituimos en la parte del texto del contrato toodo lo que sea clase idea_sub
        let date_sub = document.getElementsByClassName("date_sub");

        [].slice.call( date_sub ).forEach(function(part){
            part.innerHTML = dateString;
        });

        //sustituimos en la parte del texto del contrato toodo lo que sea clase idea_sub

        let idea_sub = document.getElementsByClassName("idea_sub");

        [].slice.call( idea_sub ).forEach(function(part){
            part.innerHTML = deal.title;
        });



        if(document.getElementById("percentage").value != null
            && document.getElementById("terms").value != null)
        {
            document.getElementById("percentage").value = deal.percentage;
            document.getElementById("terms").value = deal.terms;
        }

        /*Aqui terminan los reemplazos*/



        $( "#sign" ).on('click', function( event ) {

            let params = {};
            params.ideaId = url[3];
            params.percentage = $( "#form" ).find("input[name='percentage']").val();
            params.terms = $( "#form" ).find("textarea[name='terms']").val();

            ApiController.post("deal/creator/", params, function (response) {
                console.log(response);
            });

            window.location.href = "/home";
        });

        $( "#cancel" ).on('click', function( event ) {
            window.location.href = "/home";
        });

    })

    /*  Porcentaje dinamico
    $("input#percentage").on('change', function () {
        document.getElementById("percentageFooter").innerText = document.getElementById("percentage").value;
    })*/
});

