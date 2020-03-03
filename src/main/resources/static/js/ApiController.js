

$.ajax({
    url: 'http://localhost:8080/api/ideas/',
    success: function(response) {
        response.forEach(function (val, key) {
            list.append(`<li> ${val.title} -  ${val.active} </li>`);
        });
    },
    error: function() {
        console.log("No se ha podido obtener la información");
    }
});