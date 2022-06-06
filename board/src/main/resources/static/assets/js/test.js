console.log("Reply Module......");
let testService = (function() {

    function test01(callback, error) {
        console.log("test01.....");
        $.ajax({
            url: "/reply/test01",
            type: "get",
            success: function(result) {
                if (callback) {
                    callback(result);
                }
            },
            error: function (xhr, status, er) {
                if (error) {
                    error(er);
                }
            }
        })
    }

    function test02(name, callback, error) {
        console.log("test02.....");
        $.ajax({
            url: "/reply/test02/" + name,
            type: "get",
            data: name,
            success: function(result) {
                if (callback) {
                    callback(result);
                }
            },
            error: function (xhr, status, er) {
                if (error) {
                    error(er);
                }
            }
        })
    }

    function test03(callback, error) {
        console.log("test03.....");
        $.ajax({
            url: "/reply/test03",
            type: "get",
            dataType: "json",
            success: function(result) {
                if (callback) {
                    callback(result);
                }
            },
            error: function (xhr, status, er) {
                if (error) {
                    error(er);
                }
            }
        })
    }

    function test04(test,callback, error) {
        console.log("test04.....");
        $.ajax({
            url: "/reply/test04",
            type: "post",
            data: JSON.stringify(test),
            contentType: "application/json",
            dataType: "json",
            success: function(result) {
                if (callback) {
                    callback(result);
                }
            },
            error: function (xhr, status, er) {
                if (error) {
                    error(er);
                }
            }
        })
    }

    return {test01: test01, test02: test02, test03: test03, test04: test04};
})();