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
