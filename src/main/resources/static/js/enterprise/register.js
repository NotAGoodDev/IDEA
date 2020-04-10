$(document).ready(function () {
    //Formulario de registro de empresa
    $("#form-register-enterprise").submit(function (event) {
        event.preventDefault();
        var e = {};

        var form = $(this),
            url = "auth/enterprise/register";
        e.username = form.find("input[name='username']").val();
        e.password = form.find("input[name='password']").val();
        e.name = form.find("input[name='name']").val();
        e.cif = form.find("input[name='cif']").val();
        e.address = form.find("input[name='address']").val();
        e.email = form.find("input[name='email']").val();
        e.telephone = form.find("input[name='telephone']").val();
        e.cardNumber = form.find("input[name='cardNumber']").val();
        e.remaining_ideas = 0;
        e.active = true;

        ApiController.post(url, e, function (response) {

        });
    });
})
