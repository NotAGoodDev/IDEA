$(document).ready(function() {
    document.getElementById("name").value = "Best AI you ever thought";

    document.getElementById("author").value = "Hermenegildo";

    document.getElementById("enterprise").value = "Gulugulu";

    let dealHeader = document.getElementById("showDealHeader").textContent;
    document.getElementById("showDealHeader").textContent = replaceElements(dealHeader);

    let dealFooter = document.getElementById("showDealFooter").textContent;
    document.getElementById("showDealFooter").textContent = replaceElements(dealFooter);

    $()

    $( "#form" ).submit(function( event ) {
        event.preventDefault();
        let params = {};
        let form = $(this);
        params.idea_id = 0;             //Tengo que usar cookies?
        params.enterprise_id = 0;       //Tengo que usar cookies?
        params.percentage = form.find("input[name='percentaje']").val();
        params.text = document.getElementById("terms").textContent;

        ApiController.post("deal/", params, function (response) {
        });
    });
});

function replaceElements(element)
{
    //Se puede simplificar cuando sepa como conseguir los parametros

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