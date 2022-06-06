console.log("hero module.....");

let heroService = (function () {
    function getList(callback, error) {
        console.log("getList hero....");
        $.ajax({
            url: "/hero/list/",
            type: "get",
            dataType: "json",
            success: function(result){
                if(callback){
                    callback(result);
                }
            },
            error: function(xhr, status, er){
                if(error){
                    error(er);
                }
            }
        })
    }

    function get(hno, callback, error) {
        console.log("getList hero....");
        $.ajax({
            url: "/hero/get/" + hno,
            type: "get",
            dataType: "json",
            success: function(result){
                if(callback){
                    callback(result);
                }
            },
            error: function(xhr, status, er){
                if(error){
                    error(er);
                }
            }
        })
    }
    return {getList: getList, get: get};
})();