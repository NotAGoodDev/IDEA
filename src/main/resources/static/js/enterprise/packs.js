$(document).ready(function() {

    let discounts;          // Array donde se encuentran los descuentos segun el numero de ideas que se vayan a comprar
    let purchaseData = {};  // Para coger los datos de los packs de ideas
    let sesionData = {};    // Para coger los datos de la sesion
    let payment = {};       // Para llamar a la pasarela de pago
    let card;

    card = document.getElementById("payment");
    card.style.display = "none";


    // Hacemos esta llamada para coger los datos de la sesion para saber el nombre de la empresa, su tarjeta de credito...
    ApiController.get("auth/session", "", false).then(function (data) {
        sesionData = data;
        purchaseData.cardNumber = data.creditCard;
        purchaseData.ownerName = data.name;
    });

    // Hacemos esta llamada para no tener que hacer una peticion cada vez que se seleccione un numero de ideas
    ApiController.get("packages/getDiscount", "", false).then(function (discountTable) {
        discounts = Object.entries(discountTable); // Array con cada posicion: [PAIR INTEGER], el PAIR en string.
    });

    // Esto es solo para mostrarselo a front
    $("#numIdeas").on('input',function () {
        if(discounts != null){
            let numIdeasToBuy = document.getElementById("numIdeas").value;
            let eachItem, disc, pricePerIdea = 7, total, i, str;

            for (i = 0; i < discounts.length; ++i){
                // Hacemos esto para quedarnos solo con los numero del string ==> ["PAIR", INTEGER] = [ideasRango, dto]
                eachItem = discounts[i];
                str = eachItem[0];
                str = str.replace('Pair(first=', '');
                str = str.replace('second=', '');
                str = str.replace(')', '');
                str = str.split(',');
                disc = eachItem[1];

                if(parseInt(numIdeasToBuy, 10) >= parseInt(str[0], 10) &&
                    parseInt(numIdeasToBuy, 10) < parseInt(str[1], 10)){

                    purchaseData.discount = disc; // Descuento para aplicarlo despues a la compra
                    total = pricePerIdea * numIdeasToBuy;
                    total -= total * (disc / 100);
                    document.getElementById("descuento-seleccion").innerHTML = "Descuento: " + disc + "%";
                    document.getElementById("descuento-precio").innerHTML = total.toFixed(2) + " €";
                }
            }
        }
    });
/*
    $("#pack10ideas").click(function () {
        purchaseData.numIdeasToBuy = 10;
        purchaseData.discount = 0;
        purchaseData.validateNumber = 456;
        purchaseData.expirationDate = "2024-04-05";

        ApiController.put("packages/buy", purchaseData, false).then(function (data) {
            payment.description = "Compra de un pack de 10 ideas";
            payment.amount = data;
            payment.currency = "EUR"; // En back es un enumerado, daria error si lo paso como string
            ApiController.post("Stripe/paymentintent", payment).then(function (data) {
                console.log(data);
            });
        });
    });


    // Este y el de abajo hacerlo igual que el de 10 ideas cuando este finalizada la pasarela de pago
    $("#packXideas").click(function () {
        purchaseData.numIdeasToBuy = parseInt(document.getElementById("numIdeas").value, 10);
        //purchaseData.discount = 100; // ya elegido arriba cuando se mostraba
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
*/
    $(".btn-success").click(function () {
        let x;
        x = document.getElementById("payment");
        x.style.display = "block";
    });


});
