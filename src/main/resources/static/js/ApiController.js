
class ApiController {

     
   static basePath(){return "/api/";}

    static get(endpoint, params, async) {
        let url = this.basePath() + endpoint;

        return this.request(url, "GET", params,async);
    }

    static post(endpoint, params) {
        let url = this.basePath() + endpoint;
        return this.request(url, "POST", params,true);
    }

    static put(endpoint, params,async) {
        let url = this.basePath() + endpoint;
        return this.request(url, "PUT", params,async);
    }

    static request(url, method, params, async){

        console.log({
            url         : url,
            method      : method,
            data        : JSON.stringify(params),
            contentType : 'application/json',
        });

        return $.ajax({
            async       : async,
            url         : url,
            type        : method,
            data        : JSON.stringify(params),
            headers: {
                'Content-Type': 'application/json'
            },
            contentType : "application/json; charset=utf-8",
            mimeType: "application/json",
            dataType : 'json'
        });


    }
}

