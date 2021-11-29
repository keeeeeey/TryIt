
    function insertCart(productId){
        var id = productId;
        $.ajax({
            url : "/insertCart",
            type : "GET",
            async:true,
            datatype:"text",
            data: {productId:id},
            error:function(data){
                alert(data.msg);
            },
            success: function(data) {
                alert(data.msg);
            }
        });

    }

    function upCart(cartId){

        var id = cartId;
        $.ajax({
            url : "/upCart",
            type : "GET",
            async:true,
            datatype:"text",
            data: {cartId:id},
            error:function(data){

            },
            success: function(data) {
                if(data.msg!=null){
                }else{
                    var changeId = "qty"+cartId;
                    var num = Number($('#' + changeId).val()) + 1;
                    $('#' + changeId).val(num);
                    var changePriceId = "price"+cartId;
                    var changeTotal = "total"+cartId;
                    var changeprice = Number($('#' + changePriceId).text()) * num;
                    $('#' + changeTotal).text(changeprice);
                    var orderTotal = (data.ordertotal).toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
                    $('#orderTotal').text(orderTotal+"원");
                }
            }
        });
    }


    function downCart(cartId){
        var changeId = "qty"+cartId;
        var value = $('#' + changeId).val();
        if(value>=2){
            $(changeId).val(value + 1);
            var id = cartId;
            $.ajax({
                url : "/downCart",
                type : "GET",
                async:true,
                datatype:"text",
                data: {cartId:id},
                error:function(data){

                },
                success: function(data) {
                    if(data.msg!=null){
                        alert(data.msg);
                    }else{
                        var num = Number($('#' + changeId).val()) - 1;
                        $('#' + changeId).val(num);
                        var changePriceId = "price"+cartId;
                        var changeTotal = "total"+cartId;
                        var changeprice = Number($('#' + changePriceId).text()) * num;
                        $('#' + changeTotal).text(changeprice);
                        var orderTotal = (data.ordertotal).toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
                        $('#orderTotal').text(orderTotal+"원");
                    }
                }
            });
        }
    }

    function emptycart(){
        var result = confirm("장바구니를 비우시겠습니까?");
        if(result == true){
            location.href = "/emptyCart";
        }
    }



