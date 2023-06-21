$(".checkcate, .checkbrand").click(function(){
    var selectedCate= new Array();
    var selectedBrand= new Array();
    var products = new Object();
    $(".checkcate:checked").each(function(){
        selectedCate.push($(this).val());
    });
    $(".checkbrand:checked").each(function(){
        selectedBrand.push($(this).val());
    });
    if(selectedCate.length == 0 && selectedBrand.length ==0){
        location.href = "http://localhost:8080/store/page";
    }
        var categories = selectedCate.toString();
        var brands = selectedBrand.toString();
        $.ajax({
            url: "http://localhost:8080/api/v1/filter",
            type: "GET",
            data: {
                brand: brands,
                categories: categories ,
            },
            dataType: "json",
            success: function(response) {
                products = response;
                console.log(products);
                $(".listp").empty();
                var html = "";
                for (var i = 0; i < products.length; i++) {
                    html += '<div class="list-product col-md-4 col-xs-6">';
                    html += '<div class="product" >';
                    html += '<div class="product-img">';
                    html += '<a><img src="/img/' + products[i].img+ '" alt=""></a>';
                    html += '<div class="product-label"><span class="sale">-30%</span><span class="new">'+products[i].categories_name+'</span></div>';
                    html += '</div>';
                    html += '<div class="product-body">';
                    html += '<p class="product-category">'+products[i].brand_name+'</p>';
                    html += '<h3 class="product-name"><a href="#">'+products[i].name+'</a></h3>';
                    html += '<h4 class="product-price">'+products[i].sell_price+'$'+'</h4>';
                    html += '<div class="product-rating">\n' +
                        '\t\t\t\t\t\t\t\t\t\t\t<i class="fa fa-star"></i>\n' +
                        '\t\t\t\t\t\t\t\t\t\t\t<i class="fa fa-star"></i>\n' +
                        '\t\t\t\t\t\t\t\t\t\t\t<i class="fa fa-star"></i>\n' +
                        '\t\t\t\t\t\t\t\t\t\t\t<i class="fa fa-star"></i>\n' +
                        '\t\t\t\t\t\t\t\t\t\t\t<i class="fa fa-star"></i>\n' +
                        '\t\t\t\t\t\t\t\t\t\t</div>';
                    html += '<div className="product-btns">';
                    html += '<a onclick="function myFunctionw1() {alert(\'Add success! WISHLIST\');}myFunctionw1()"\n' +
                        '\t\t\t\t\t\t\t\t\t\t\t\t\tth:href="@{/wishlist/{username}/{productId}(productId=${'+products[i].id+'}, username=${#authentication.name})}"><i class="fa fa-heart-o"></i></a>';
                    html += '<button style="border: none; background-color: #FFFFFF" class="add-to-compare"><i class="fa fa-exchange"></i><span class="tooltip">add to compare</span></button>';
                    html += '<button style="border: none; background-color: #FFFFFF" class="quick-view"><i class="fa fa-eye"></i><span class="tooltip">quick view</span></button>';
                    html += '</div>';
                    html += '</div>';
                    html += '<div class="add-to-cart">';
                    html += '<a href="/shopping-cart/add/'+products[i].id+'"><button class="add-to-cart-btn"><i class="fa fa-shopping-cart"></i> add to cart</button></a>';
                    html += '</div>';
                    html += '</div>';
                    html += '</div>';
                }
                $(".listp").append(html);
            }
        });
    console.log(brands);
    console.log(categories);
});
