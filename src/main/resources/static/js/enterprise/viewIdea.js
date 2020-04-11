$(document).ready(function () {
    let url = window.location.pathname.split('/');
                                                                //[0]   none
                                                                //[1]   enterprise
                                                                //[2]   {idEnterprise}
                                                                //[3]   idea
                                                                //[4]   {idIdea}
    let idIdea = url[4]

    ApiController.get("ideas/" + idIdea, idIdea, false).then(function (idea) {
        console.log(idea);
        document.getElementById("titulo").setAttribute("value", idea.title);
        document.getElementById("autor").setAttribute("value", idea.creator);
        document.getElementById("fecha").setAttribute("value", idea.createdAt);
        document.getElementById("descriptionS").setAttribute("value", idea.summary);        //Summary es una palabra reservada, por eso el error

        document.getElementById("textArea").innerHTML =
            '                    <textarea name="description" id="description" type="readonly" readonly >' + idea.description + '</textarea>\n';

        document.getElementById("dealIdea").innerHTML =
            '                <a href="/enterprise/deal/' + url[4] + '">\n' +
            '                    <button type="button" class="btn-success">Contratar</button>\n' +
            '                </a>'
    });
});