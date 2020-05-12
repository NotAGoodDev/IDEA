$(document).ready(function() {

    let discounts;          // Array donde se encuentran los descuentos segun el numero de ideas que se vayan a comprar
    let purchaseData = {};  // Para coger los datos que enviaremos a la pasarela de pago
    let sesionData = {};    // Para coger los datos de la sesion
    let card;
    let paymentIntentObj; //Para almacenar el pago
    var paymentSubmited = false;
    card = document.getElementById("payment");
    card.style.display = "none";


    // Hacemos esta llamada para coger los datos de la sesion para saber el nombre de la empresa, su tarjeta de credito...
    ApiController.get("auth/session", "", false).then(function (data) {
        sesionData = data;
    });

    // Hacemos esta llamada para no tener que hacer una peticion cada vez que se seleccione un numero de ideas
    ApiController.get("packages/getDiscounts", "", false).then(function (discountTable) {
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

    $(".btn-success").click(function () {
        let x;
        x = document.getElementById("payment");
        x.style.display = "block";
    });

    $("#boton-pagar").click(function () {
        // Calculamos el precio final
        console.log("antes de calcular")
        console.log(purchaseData)
        //let finalPrice  = 10000
        ApiController.post("packages/calculateFinalPrice", purchaseData, false).then(function (finalPrice) {
            // Variable para mapear la clase "PaymentModel" de "Stripe/paymentintent" para procesar el pago
            let payment = {};
            console.log("antes de parsear")
            console.log("antes de precio")
            console.log(finalPrice)
            payment.amount = finalPrice.finalPrice * 100;
            console.log("antes de moneda")
            payment.currency = "EUR"; // Mas adelante posibilidad de elegir entre '€' y '$'
            console.log("antes de nombre")
            payment.ownerName = document.getElementById("namecard").value;
            console.log("antes de numeroCard")
            payment.cardNumber =document.getElementById("cardNumber").value;
            console.log("antes de fecha")
            payment.expirationDate = document.getElementById("dateExp").value;
            console.log("antes de cvv")
            payment.validateNumber = document.getElementById("cvv").value;
            console.log("antes de descripcion")
            payment.description =
                "Compra de un pack de " + purchaseData.numIdeasToBuy + " ideas con un valor total de " + payment.amount / 100 +
                payment.currency + " en la pagina web de IDEA.";
            console.log("despues de parsear")
            console.log(payment)

            ApiController.post("Stripe/paymentintent", payment).then(function (result) {
                paymentIntentObj = result;
                console.log(result)
                //Modal que enseña los datos del pago para modificarlo
                document.getElementById('modal_body').innerHTML =
                    '<p>Id: ' +  result.id + '</p>\n' +
                    '<p>Price:' +  result.amount / 100 + '</p>\n';
                $('#modalBuy').modal('show');
            });


        });
        return false;
    });


    //Accion al ocultarse el modal
    $('#modalBuy').on('hidden.bs.modal', function (e) {
        if(!paymentSubmited){
            ApiController.post("Stripe/cancel/" + paymentIntentObj.id, paymentIntentObj.id, false);
        }
        window.location.href = "/";
    });


    //Confirmamos el pago y cerramos el modal
    $("#buttonSavePayment").click(function () {
        console.log("confirm payment");
        console.log(purchaseData);
        ApiController.post("Stripe/confirm/" + paymentIntentObj.id, paymentIntentObj.id, false);
        ApiController.put("packages/updateNumIdeas", purchaseData.numIdeasToBuy, false);
        paymentSubmited = true;
        $('#modalBuy').modal('hide');
    });


    //Cancelamos el pago y cerramos el modal
    $("#buttonCancelPayment").click(function () {
        console.log("cancel payment")
        ApiController.post("Stripe/cancel/" + paymentIntentObj.id, paymentIntentObj.id, false);
        paymentSubmited = true;
        $('#modalBuy').modal('hide');
    });

    // PARA ESTABLECER EN LAS VARIABLES EL NUMERO DE IDEAS Y EL DESCUENTO CORRESPONDIENTE
    $("#pack10ideas").click(function () {
        purchaseData.numIdeasToBuy = 10;
        purchaseData.discount = 0;
    });

    $("#pack20ideas").click(function () {
        purchaseData.numIdeasToBuy = 20;
        purchaseData.discount = 0;
    });

    $("#packXideas").click(function () {
        purchaseData.numIdeasToBuy = parseInt(document.getElementById("numIdeas").value, 10);
        // El descuento ya lo hemos guardado la ultima vez que selecciono el numero de ideas
    });

});
