
class ApiController {

    static basePath = "/api/";

    static get(endpoint, params) {
        let url = this.basePath + endpoint;

        return this.request(url, "GET", '');
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
    static request(url, method, params){

        console.log({
            url         : url,
            method      : method,
            params      : JSON.stringify(params),
            contentType : 'application/json',
        });

        $.ajax({
            url         : url,
            type        : method,
            data        : JSON.stringify(params),
            headers: {
                'Content-Type': 'application/json'
            },
            contentType : "application/json; charset=utf-8",
            mimeType: "application/json",
            dataType : 'json',
        });


    }
}

