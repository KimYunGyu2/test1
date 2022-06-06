/*
*   reply module
* */

console.log("Reply Module......");
let replyService = (function(){

    function add(reply, callback, error){
        console.log("add reply..........");
        $.ajax({
            url: "/reply/new",
            type: "post",
            data: JSON.stringify(reply), // 오브젝트 구조를 제이슨 구조로 바꿔줌
            contentType: "application/json",
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
        });
    }

    function get(rno, callback, error) {
        console.log("read reply.......");
        $.ajax({
            url: "/reply/" + rno,
            type: "get",
            dataType: "json",
            success: function (result) {
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

    function remove(rno, callback, error) {
        console.log("remove reply.....");
        $.ajax({
            url: "/reply/" + rno,
            type: "delete",
            success: function (result) {
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

    function modify(rno, reply, callback, error) {
        console.log("modify reply....");
        $.ajax({
            url: "/reply/" + rno,
            type: "patch",
            data: JSON.stringify(reply),
            contentType: "application/json",
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
        });
    }

    function getList(criteria, callback, error) {
        console.log("getList reply....");
        $.ajax({
            url: "/reply/list/" + criteria.bno + "/" + criteria.page,
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

    return {add: add, get: get, remove: remove, modify: modify, getList: getList};

})();