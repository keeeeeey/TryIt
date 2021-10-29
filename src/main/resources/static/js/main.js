/*-----------------------------------------------------------------------------------

    Theme Name: Smartshop - Multipurpose eCommerce Template
    Description: Multipurpose eCommerce Template
    Author: Chitrakoot Web
    Version: 1.0

    /* ----------------------------------

    JS Active Code Index
            
        01. Preloader
        02. Sticky Header
        03. Scroll To Top
        04. Parallax
        05. MagnificPopup
        06. Search Category
        07. Category Toggle
        08. Copy to clipboard
        09. Resize function
        10. FullScreenHeight function
        11. ScreenFixedHeight function
        12. FullScreenHeight and screenHeight with resize function
        13. URL selector
        14. Sliders
        15. Tabs
        16. CountUp
        17. Countdown
        18. Price Ranger
        
    ---------------------------------- */    

(function($) {

    "use strict";

    var $window = $(window);


        /*------------------------------------
            01. Preloader
        --------------------------------------*/

        $('#preloader').fadeOut('normall', function () {
            $(this).remove();
        });


        /*------------------------------------
            02. Sticky Header
        --------------------------------------*/

        $window.on('scroll', function() {
            var scroll = $window.scrollTop();
            if (scroll <= 50) {
                $("header").removeClass("scrollHeader").addClass("fixedHeader");
            } 
            else {
                $("header").removeClass("fixedHeader").addClass("scrollHeader");
            }
        });

         if ($(".mp-menu").length !== 0) {
             new mlPushMenu(document.getElementById('mp-menu'), document.getElementById('trigger'), {
                type: 'cover'
            });
        };


        /*------------------------------------
            03. Scroll To Top
        --------------------------------------*/

        $window.on('scroll', function() {
            if ($(this).scrollTop() > 500) {
                $(".scroll-to-top").fadeIn(400);

            } else {
                $(".scroll-to-top").fadeOut(400);
            }
        });

        $(".scroll-to-top").on('click', function(event) {
            event.preventDefault();
            $("html, body").animate({
                scrollTop: 0
            }, 600);
        });


        /*------------------------------------
            04. Parallax
        --------------------------------------*/

        // sections background image from data background
        var pageSection = $(".parallax,.bg-img");
        pageSection.each(function(indx) {

            if ($(this).attr("data-background")) {
                $(this).css("background-image", "url(" + $(this).data("background") + ")");
            }
        });

        
        /*------------------------------------
            05. MagnificPopup
        --------------------------------------*/

        $('.story-video').magnificPopup({
            delegate: '.video',
            type: 'iframe'
        });

        $('.popup-video').magnificPopup({
                disableOn: 700,
                type: 'iframe',
                mainClass: 'mfp-fade',
                removalDelay: 160,
                preloader: false,
                fixedContentPos: false
        }); 

        $('.source-modal').magnificPopup({
            type: 'inline',
            mainClass: 'mfp-fade',
            removalDelay: 160
        });

        $('.newsletter-popup').magnificPopup({
            type: 'inline',
            mainClass: 'mfp-fade',
            removalDelay: 160
        });        


        /*------------------------------------
            06. Search Category
        --------------------------------------*/

        $('.search-category').on('click', function (){
            $('.categories-search').slideToggle();
        });


        /*------------------------------------
            07. Category Toggle
        --------------------------------------*/

        var resizeCategories;
        $window.on('resize', function (e) {
            clearTimeout(resizeCategories);
            resizeCategories = setTimeout(function () {

                if ($window.width() > 991) {
                    $('.categories-search').show();
                } else {
                    $('.categories-search').hide();
                }
            }, 250);

        });


        /*------------------------------------
            08. Copy to clipboard
        --------------------------------------*/

        if ($(".copy-clipboard").length !== 0) {
            new ClipboardJS('.copy-clipboard');
            $('.copy-clipboard').on('click', function() {
                var $this = $(this);
                var originalText = $this.text();
                $this.text('Copied');
                setTimeout(function() {
                    $this.text('Copy')
                    }, 2000);
            });
        };


        /*------------------------------------
            09. Resize function
        --------------------------------------*/

        $window.resize(function(event) {
            setTimeout(function() {
                SetResizeContent();
            }, 500);
            event.preventDefault();
        });


        /*------------------------------------
            10. FullScreenHeight function
        --------------------------------------*/

        function fullScreenHeight() {
            var element = $(".full-screen");
            var $minheight = $window.height();
            element.css('min-height', $minheight);
        }


        /*------------------------------------
            11. ScreenFixedHeight function
        --------------------------------------*/

        function ScreenFixedHeight() {
            var $headerHeight = $("header").height();
            var element = $(".screen-height");
            var $screenheight = $window.height() - $headerHeight;
            element.css('height', $screenheight);
        }


        /*------------------------------------
            12. FullScreenHeight and screenHeight with resize function
        --------------------------------------*/        

        function SetResizeContent() {
            fullScreenHeight();
            ScreenFixedHeight();
        }

        SetResizeContent();


    // === when document ready === //
    $(document).ready(function() {

        /*------------------------------------
            13. URL selector
        --------------------------------------*/

        var urlparam = window.location.pathname.split('/');
        var menuselctor = window.location.pathname;
        if (urlparam[urlparam.length - 1].length > 0) menuselctor = urlparam[urlparam.length - 1];
        else menuselctor = urlparam[urlparam.length - 2];
        $('.element-listing li').find('a[href="' + menuselctor + '"]').closest('li').addClass('active');
       

        /*------------------------------------
            14. Sliders
        --------------------------------------*/

        // Testmonials carousel1
        $('#testmonials-carousel').owlCarousel({
            loop: true,
            responsiveClass: true,
            autoplay: true,
            smartSpeed: 1000,            
            nav: false,
            dots: true,
            center:true,
            margin: 0,
            responsive: {
                0: {
                    items: 1
                },
                768: {
                    items: 1
                },
                992: {
                    items: 1
                }
            }
        });

        // Product grid slider
        $('.product-grid-slide').owlCarousel({
            loop: true,
            nav: false,
            dots: true,
            autoplay: true,
            autoplayTimeout: 5000,
            responsiveClass: true,
            autoplayHoverPause: false,
            smartSpeed:1000,
            margin: 25,
            navText: ["<i class='fa fa-angle-left'></i>", "<i class='fa fa-angle-right'></i>"],
            responsive: {
                0: {
                    items: 1
                },
                576: {
                    items: 2
                },
                992: {
                    items: 3
                },
                1200: {
                    items: 4
                }
            }
        });   

        // Offer slider
        $('.offer-slider').owlCarousel({
            loop: true,
            dots: false,
            nav: false,
            margin: 0,
            autoplay: true,
            autoplayTimeout: 5000,
            responsiveClass: true,
            autoplayHoverPause: false,
            smartSpeed: 1000,
            responsive: {
                0: {
                    items: 1
                },
                768: {
                    items: 1
                   
                },
                992: {
                    items: 1
                    
                }
            }
        });

        // featured-productr
        $('.featured-product').owlCarousel({
            loop: true,
            responsiveClass: true,
            dots: true,
            nav: false,
            autoplay: true,
            autoplayTimeout: 5000,
            responsiveClass: true,
            autoplayHoverPause: false,
            smartSpeed:1000,
            margin: 20,
            responsive: {
                0: {
                    items: 1
                },
                576: {
                    items: 2
                },
                768: {
                    items: 3
                },
                992: {
                    items: 3,
                    margin: 30,
                },
                1200: {
                    items: 4,
                    margin: 30
                }
            }
        });

        // Clients carousel
        $('.clients').owlCarousel({
            loop: true,
            nav: false,
            dots: false,
            autoplay: true,
            smartSpeed:500,
            autoplayTimeout: 3000,
            responsiveClass: true,
            autoplayHoverPause: false,
            responsive: {
                0: {
                    items: 2,
                    margin: 20
                },
                768: {
                    items: 3,
                    margin: 40,
                },
                992: {
                    items: 4,
                    margin: 60,
                    },
                    1200: {
                    items: 5,
                    margin: 80,
                }
            }
        });

        // Clients carousel
        $('.client-section .clients').owlCarousel({
            loop: true,
            nav: false,
            responsiveClass: true,
            dots: false,
            autoplay:true,
            smartSpeed:500,
            autoplayTimeout: 3000,
            responsiveClass: true,
            autoplayHoverPause: false,
            responsive: {
                0: {
                    items: 3,
                    margin: 20
                },
                768: {
                    items: 4,
                    margin: 40,
                    },
                992: {
                    items: 5,
                    margin: 60,
                }
            }
        });
        
        // Default owlCarousel
        $('.owl-carousel').owlCarousel({
            items: 1,
            loop:true,
            dots: false,
            margin: 0,
            autoplay:true,
            smartSpeed:500
        });   

        // Revolution slider1
        if ($("#rev_slider_1").length !== 0) {
            var tpj = jQuery;
            var revapi5;
            tpj(document).ready(function() {
                if (tpj("#rev_slider_1").revolution == undefined) {
                    revslider_showDoubleJqueryError("#rev_slider_1");
                } else {
                    revapi5 = tpj("#rev_slider_1").show().revolution({
                        sliderType: "standard",
                        sliderLayout:"fullwidth",
                        delay:12000,
                        responsiveLevels: [1240, 1024, 767, 480],
                        gridwidth: [1170, 1170, 767, 480],
                        gridheight: [700, 700, 600, 600],
                        hideThumbs:10,
                        fullScreenAutoWidth: "on",
                        fullScreenOffsetContainer: "header",

                        navigation: {
                            onHoverStop: "off",
                            touch: {
                                touchenabled: "on",
                                swipe_threshold: 75,
                                swipe_min_touches: 1,
                                swipe_direction: "horizontal",
                                drag_block_vertical: false
                            },
                            arrows: {
                                enable: true,
                                style: 'metis',
                                tmp: '',
                                rtl: false,
                                hide_onleave: true,
                                hide_onmobile: true,
                                hide_under: 0,
                                hide_over: 9999,
                                hide_delay: 200,
                                hide_delay_mobile: 1200,
                                left: {
                                    container: 'slider',
                                    h_align: 'left',
                                    v_align: 'center',
                                    h_offset: 20,
                                    v_offset: 0
                                },
                                right: {
                                    container: 'slider',
                                    h_align: 'right',
                                    v_align: 'center',
                                    h_offset: 20,
                                    v_offset: 0
                                }
                            },
                            bullets: {
                                enable: false,
                            },
                        },
                        spinner:"spinner4"
                    });
                }
            });
        }

        // Revolution slider2
        if ($("#rev_slider_2").length !== 0) {
            var tpj = jQuery;
            var revapi6;
            tpj(document).ready(function() {
                if (tpj("#rev_slider_2").revolution == undefined) {
                    revslider_showDoubleJqueryError("#rev_slider_2");
                } else {
                    revapi6 = tpj("#rev_slider_2").show().revolution({
                        sliderType: "standard",
                        sliderLayout:"fullwidth",
                        delay:12000,
                        responsiveLevels: [1240, 1024, 767, 480],
                        gridwidth: [1170, 1170, 767, 480],
                        gridheight: [700, 700, 600, 600],
                        hideThumbs:10,
                        fullScreenAutoWidth: "on",
                        fullScreenOffsetContainer: "header",

                        navigation: {
                            onHoverStop: "off",
                            touch: {
                                touchenabled: "on",
                                swipe_threshold: 75,
                                swipe_min_touches: 1,
                                swipe_direction: "horizontal",
                                drag_block_vertical: false
                            },
                            arrows: {
                                enable: true,
                                style: 'metis',
                                tmp: '',
                                rtl: false,
                                hide_onleave: true,
                                hide_onmobile: true,
                                hide_under: 0,
                                hide_over: 9999,
                                hide_delay: 200,
                                hide_delay_mobile: 1200,
                                left: {
                                    container: 'slider',
                                    h_align: 'left',
                                    v_align: 'center',
                                    h_offset: 20,
                                    v_offset: 0
                                },
                                right: {
                                    container: 'slider',
                                    h_align: 'right',
                                    v_align: 'center',
                                    h_offset: 20,
                                    v_offset: 0
                                }
                            },
                            bullets: {
                                enable: false,
                            },
                        },
                        spinner:"spinner4"
                    });
                }
            });
        }

        /*------------------------------------
            15. Tabs
        --------------------------------------*/

        //Horizontal Tab
        if ($(".horizontaltab").length !== 0) {
            $('.horizontaltab').easyResponsiveTabs({
                type: 'default', //Types: default, vertical, accordion
                width: 'auto', //auto or any width like 600px
                fit: true, // 100% fit in a container
                tabidentify: 'hor_1', // The tab groups identifier
                activate: function(event) { // Callback function if tab is switched
                    var $tab = $(this);
                    var $info = $('#nested-tabInfo');
                    var $name = $('span', $info);
                    $name.text($tab.text());
                    $info.show();
                }
            });
        }

        // Child Tab
        if ($(".childverticaltab").length !== 0) {
            $('.childverticaltab').easyResponsiveTabs({
                type: 'vertical',
                width: 'auto',
                fit: true,
                tabidentify: 'ver_1', // The tab groups identifier
                activetab_bg: '#fff', // background color for active tabs in this group
                inactive_bg: '#F5F5F5', // background color for inactive tabs in this group
                active_border_color: '#c1c1c1', // border color for active tabs heads in this group
                active_content_border_color: '#c1c1c1' // border color for active tabs contect in this group so that it matches the tab head border
            });
        }

        //Vertical Tab
        if ($(".verticaltab").length !== 0) {
            $('.verticaltab').easyResponsiveTabs({
                type: 'vertical', //Types: default, vertical, accordion
                width: 'auto', //auto or any width like 600px
                fit: true, // 100% fit in a container
                closed: 'accordion', // Start closed if in accordion view
                tabidentify: 'hor_1', // The tab groups identifier
                activate: function(event) { // Callback function if tab is switched
                    var $tab = $(this);
                    var $info = $('#nested-tabInfo2');
                    var $name = $('span', $info);
                    $name.text($tab.text());
                    $info.show();
                }
            });
        }

        /*------------------------------------
            16. CountUp
        --------------------------------------*/

        $('.countup').counterUp({
            delay: 25,
            time: 2000
        });

        /*------------------------------------
            17. Countdown
        --------------------------------------*/

        if ($(".countdown").length !== 0) {
            var tpj = jQuery;
            var countdown;
            tpj(document).ready(function() {
                if (tpj(".countdown").countdown == undefined) {
                    revslider_showDoubleJqueryError(".countdown");
                } else {
                    countdown = tpj(".countdown").show().countdown({
                        date: "01 Jan 2021 00:01:00", //set your date and time. EX: 15 May 2014 12:00:00
                        format: "on"
                    });
                }
            });
        }

        
    });

    // === when window loading === //
    $window.on("load", function() {

        // stellar
        $window.stellar();

        /*------------------------------------
            18. Price Ranger
        --------------------------------------*/

        // price range
        if ($(".price-range").length !== 0) {
            $(".price-range").ionRangeSlider({
                type: "double",
                grid: true,
                min: 0,
                max: 1000,
                from: 200,
                to: 800,
                prefix: "$"
            });
        }

    });

})(jQuery);