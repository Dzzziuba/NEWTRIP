function PageConstants() {
    this.imgInfoTemplate = '<div id="img-block-%imgId%" class="img-bg hide" style="background-image:url(\'%imgUrl%\')"></div>';
    this.loader = '<div class="loader">Loading...</div>';


    this.bodyEl     = $('body');
    this.headerEl   = $('header');
    this.headerLogo = $('header .logo');
    this.infoEl     = $('#img-info');

    this.classes = {
        loadContent : 'load-content',
        show        : 'show',
        hide        : 'hide'
    };

    this.timerImg   = 30; //seconds
}

function PageController(pageConstants) {
    var self        = this,
        i           = 0,
        ImgBlocks   = '',
        tempInterval,
        arrayContent;

    this.init = function() {
        this.getContent();
    };

    this.getContent = function() {
        pageConstants.bodyEl.append(pageConstants.loader);

        $.ajax({
            url: "/rest/home"
        })
            .done(function(data) {
                arrayContent = data;
                arrayContent.images = arrayContent.images.split(',');

                pageConstants.infoEl.find('.header').html(arrayContent.name);
                pageConstants.infoEl.find('.description').html(arrayContent.description);
                pageConstants.infoEl.find('.author').html('Photo by ' + arrayContent.authors);

                for (var y = 0; y < arrayContent.images.length; y++) {
                    ImgBlocks = ImgBlocks + pageConstants.imgInfoTemplate.replace('%imgId%', y).replace('%imgUrl%', arrayContent.images[y]);
                }
                pageConstants.infoEl.after(ImgBlocks);

                self.blocksSet();
                self.timerInfo();
            });
    };

    this.blocksSet = function() {
        self.imgInfoSet(i);

        return false;
    };

    this.timerInfo = function() {
        if (arrayContent.images.length > 1)
            tempInterval = setInterval(self.blocksSet, pageConstants.timerImg*1000);
    };

    this.imgInfoSet = function(i) {
        pageConstants.infoEl.removeClass(pageConstants.classes.show).addClass(pageConstants.classes.hide);
        pageConstants.headerEl.removeClass(pageConstants.classes.show).addClass(pageConstants.classes.hide);

        setTimeout(function () {
            pageConstants.bodyEl.removeClass('dark light').addClass('light');
            $('#img-block-'+i).removeClass(pageConstants.classes.hide).addClass(pageConstants.classes.show);
        }, 1000);
        setTimeout(showLogo, 2000);
        setTimeout(showContent, 2500);

        setTimeout(function () {
            for (var y = 0; y < arrayContent.images.length; y++) {
                if (y !== i) {
                    $('#img-block-' + y).removeClass(pageConstants.classes.show).addClass(pageConstants.classes.hide);
                }
            }
        }, 1500);
    };

    var showLogo = function() {
        if (pageConstants.headerEl.hasClass('zi-1'))
            pageConstants.headerEl.removeClass('zi-1');
        pageConstants.headerLogo.removeClass('newtrip-1 newtrip-2 newtrip-1w newtrip-2w opacity2 center right').addClass('newtrip-' + "1");
        pageConstants.headerEl.removeClass(pageConstants.classes.hide).addClass(pageConstants.classes.show);
    };

    var showContent = function() {
        pageConstants.infoEl.removeClass(pageConstants.classes.hide).addClass(pageConstants.classes.show);

        if (i >= arrayContent.images.length - 1) {
            i = 0;
        } else {
            i++;
        }
    };

    this.randomInteger = function(min, max) {
        var rand = min - 0.5 + Math.random() * (max - min + 1);
        rand = Math.round(rand);
        return rand;
    };
}

$(function() {
    var pageConstants  = new PageConstants();
    var pageController = new PageController(pageConstants);

    pageController.init();
});