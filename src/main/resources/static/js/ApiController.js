
class ApiController {

     
   static basePath(){return "/api/";}

    static get(endpoint, params, async) {
        let url = this.basePath() + endpoint;

        return this.request(url, "GET", '',async);
    }

    static post(endpoint, params) {
        let url = this.basePath() + endpoint;
        return this.request(url, "POST", params);
    }

    static put(endpoint, params) {
        let url = this.basePath + endpoint;
        return this.request(url, "PUT", params);
    }

    //deberia ser private
    static request(url, method, params, async){

        console.log({
            url         : url,
            method      : method,
            data        : JSON.stringify(params),
            contentType : 'application/json',
        });

        $.ajax({
            async       : async,
            url         : url,
            type        : method,
            data        : JSON.stringify(params),
            headers: {
                'Content-Type': 'application/json'
            },
            contentType : "application/json; charset=utf-8",
            mimeType: "application/json",
            dataType : 'json',
            success: function(data){return data; },
            error: function(){return null;}
        });


    }
}

