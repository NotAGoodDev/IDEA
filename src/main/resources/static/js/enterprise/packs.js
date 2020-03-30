$(document).ready(function() {
    $("#form-packs").submit(function (event) {
        event.preventDefault();

        /* TODO PROBLEMAS:
        *       - Hace la llamada bien a la primera, el segundo click hace 2 peticiones, al 3er click hace 3 y asi...
        *       - No sale el alert cuando da fallo/suceso
        */

        let purchaseData = {};

        $("#pack10ideas").click(function () {
            purchaseData.numIdeasToBuy = 10;
            //purchaseData.discount = 0;
            purchaseData.cardNumber = 123;
            purchaseData.ownerName = "Pack10Ideas";
            purchaseData.validateNumber = 456;
            purchaseData.expirationDate = "2024-04-05";

            ApiController.post("packages/buy", purchaseData).then(function (data) {
                if(data){
                    alert("Pago realizado con exito.");
                    window.location.href = "/home"
                }else{
                    alert("Error al hacer el pago.");
                }
            });
        });

        $("#packXideas").click(function () {
            purchaseData.numIdeasToBuy = document.getElementById("numIdeas").value;
            // Llamada para ver el descuento
            //purchaseData.discount = 100;
            purchaseData.cardNumber = 789;
            purchaseData.ownerName = "PackXIdeas";
            purchaseData.validateNumber = 1011;
            purchaseData.expirationDate = "2024-04-05";

            ApiController.post("packages/buy", purchaseData).then(function (data) {
                if(data){
                    alert("Pago realizado con exito.");
                    window.location.href = "/home"
                }else{
                    alert("Error al hacer el pago.");
                }
            });
        });

        $("#pack20ideas").click(function () {
            purchaseData.numIdeasToBuy = 20;
            //purchaseData.discount = 0;
            purchaseData.cardNumber = 1213;
            purchaseData.ownerName = "Pack20Ideas";
            purchaseData.validateNumber = 1415;
            purchaseData.expirationDate = "2024-04-05";

            ApiController.post("packages/buy", purchaseData).then(function (data) {
                if(data){
                    alert("Pago realizado con exito.");
                    window.location.href = "/home"
                }else{
                    alert("Error al hacer el pago.");
                }
            });
        });
    });
});


/////////// DE PRUEBA ///////////

function getDiscount(){
    // Para que se vea el descuento en pantalla
    let ideasParaDto = {};
    ideasParaDto.numIdeas = document.getElementById("numIdeas").value;

    /*ApiController.get("packages/getDiscount", ideasParaDto, true).then(function (data) {
        document.getElementById("descuento-seleccion").innerHTML = "Descuento: " + data.numIdeas + "%";
        console.log("He entrado en getDisocunt" + data.numIdeas + ".");
    });*/
    if(ideasParaDto.numIdeas >= 1 && ideasParaDto.numIdeas < 50)
        document.getElementById("descuento-seleccion").innerHTML = "Descuento: 5%";
    else if (ideasParaDto.numIdeas >= 50 && ideasParaDto.numIdeas < 250)
        document.getElementById("descuento-seleccion").innerHTML = "Descuento: 15%";
    else
        document.getElementById("descuento-seleccion").innerHTML = "Descuento: 20%";

    getPriceWithDiscount();
}

function getPriceWithDiscount() {
    let precio, dto, total;
    precio = document.getElementById("numIdeas").value;

    // 5% de descuento teniendo en cuenta que 10 ideas cuestan 70€
    if(precio >= 1 && precio < 50)
        document.getElementById("descuento-precio").innerHTML = "266 €";
    else if (precio >= 50 && precio < 250)
        document.getElementById("descuento-precio").innerHTML = "297,5 €";
    else
        document.getElementById("descuento-precio").innerHTML = "410 €";
}
