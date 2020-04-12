$(document).ready(function() {
    let url = window.location.pathname.split('/');
    //[0]   none
    //[1]   enterprise
    //[2]   deal
    //[3]   {idIdea}

    ApiController.get("deal/" + url[3], url[3], false).then(function (deal) {
        console.log(deal);

        /* Los reemplazos de texto son a la fuerza bruta */
        document.getElementById("name").value = deal.title;

        document.getElementById("author").value = deal.creator;

        document.getElementById("enterprise").value = deal.enterprise;

        if(document.getElementById("percentage").value != null
            && document.getElementById("terms").value != null)
        {
            document.getElementById("percentage").value = deal.percentage;
            document.getElementById("terms").value = deal.terms;
        }

        let ideaName = document.getElementById("ideaName").textContent;
        document.getElementById("ideaName").textContent = replaceElements(ideaName);

        let dealHeader = document.getElementById("showDealHeader").textContent;
        document.getElementById("showDealHeader").textContent = replaceElements(dealHeader);

        let dealFooter = document.getElementById("showDealFooter").textContent;
        document.getElementById("showDealFooter").textContent = replaceElements(dealFooter);

        /*Aqui terminan los reemplazos*/



        $( "#sign" ).on('click', function( event ) {

            let params = {};
            params.ideaId = url[3];
            params.percentage = $( "#form" ).find("input[name='percentage']").val();
            params.terms = $( "#form" ).find("textarea[name='terms']").val();

            ApiController.post("deal/enterprise/", params, function (response) {
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

function replaceElements(element)
{
    var date = new Date();
    var day = date.getDate();
    var month = date.getMonth() + 1;    //Enero -> 0, Febrero -> 1
    var year = date.getFullYear();

    var dateString = day + "/" + month + "/" + year;

    return element.toString()
        .replace("\'idea\'", document.getElementById("name").value)
        .replace("\'usuario\'", document.getElementById("author").value)
        .replace("\'empresa\'", document.getElementById("enterprise").value)
        .replace("\'fecha\'", dateString);
}