$(document).ready(function() {
    $("#form-packs").submit(function (event) {
        event.preventDefault();

        /* TODO PROBLEMAS:
        *       - Hace la llamada bien a la primera, el segundo click hace 2 peticiones, al 3er click hace 3 y asi...
        *       - No sale el alert cuando da fallo/suceso
        */

        let purchaseData = {};

        $("#pack10ideas").click(function () {
            let txt = document.getElementById("descuento-seleccion").value; // coger valor de la linea 73 del html
            let numb = txt.match(/\d/g);
            numb = numb.join("");
            alert(numb);

            /*purchaseData.numIdeasToBuy = 10;
            purchaseData.discount = 0;
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
            });*/
        });

        $("#packXideas").click(function () {
            purchaseData.numIdeasToBuy = document.getElementById("numIdeas").value;
            purchaseData.discount = 100;
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
            purchaseData.discount = 0;
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

// SOLO PARA QE EL USUARIO VEA EL DESCUENTO Y EL PRECIO FINAL, EL PRECIO NO SE PASA A BACK SINO QUE SE CALCULA ALLI POR SEGURIDAD
function getDiscount(){
    let dto, total, pricePerIdea = 7, numIdeasToBuy;

    numIdeasToBuy = document.getElementById("numIdeas").value;

    // PARA EL RANGO DE IDEAS MEJOR HACER UNA PETICION GET QUE NOS DEVUELVA LOS DESCUENTOS
    if(numIdeasToBuy >= 40 && numIdeasToBuy < 250) {
        dto = 0.05;
        document.getElementById("descuento-seleccion").innerHTML = "Descuento: 5%";
    }
    else if (numIdeasToBuy >= 250 && numIdeasToBuy < 2000) {
        dto = 0.1;
        document.getElementById("descuento-seleccion").innerHTML = "Descuento: 10%";
    }
    else if (numIdeasToBuy >= 2000 && numIdeasToBuy < 3500) {
        dto = 0.15;
        document.getElementById("descuento-seleccion").innerHTML = "Descuento: 15%";
    }
    else{
        dto = 0.2;
        document.getElementById("descuento-seleccion").innerHTML = "Descuento: 20%";
    }

    total = pricePerIdea * numIdeasToBuy;
    total -= total * dto;

    document.getElementById("descuento-precio").innerHTML = total.toFixed(2) + " €";

    console.log(total + " " + numIdeasToBuy);
}
