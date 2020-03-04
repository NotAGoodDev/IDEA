
class ApiController {

    static basePath = "http://localhost:8080/api/";

    static get(endpoint, params) {
        let url = this.basePath + endpoint;
        return this.request(url, "GET", params);
    }

    static post(endpoint, params) {
        let url = this.basePath + endpoint;
        return this.request(url, "POST", params);
    }

    static put(endpoint, params) {
        let url = this.basePath + endpoint;
        return this.request(url, "PUT", params);
    }

    //deberia ser private
    static request(url, type, params){
        $.ajax({
            url: url,
            type: type,
            params : params,
            function(response) {
                return response;
            },
        });
    }
}

