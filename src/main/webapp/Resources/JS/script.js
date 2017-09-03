function PageConstants() {
    this.arrayImg = [
        [
            '/Resources/img/img1.jpg',         /* bg picture*/
            'Grand Flowers',        /* text - header */
            'For thousands of years, the area has been continuosly inhabited by Native Americans, who built settlements whitin the canyon.',
                                    /* text - description */
            'KÉVIN VANOVERBERGHE',  /* text - author */
            '1',                    /* logo */
            'left',                 /* align for text */
            'light'                  /* color */
        ],
        [
            '/Resources/img/img2.jpg',
            '2 - Grand Flowers',
            '2 - For thousands of years, the area has been continuosly inhabited by Native Americans, who built settlements whitin the canyon.',
            '2 - KÉVIN VANOVERBERGHE',
            '2',
            'right',
            'dark'
        ],
        [
            '/Resources/img/img3.jpg',
            '3 - Grand Flowers',
            '3 - For thousands of years, the area has been continuosly inhabited by Native Americans, who built settlements whitin the canyon.',
            '3 - KÉVIN VANOVERBERGHE',
            '1',
            'center',
            'light'
        ],
        [
            '/Resources/img/img4.jpg',
            '4 - Grand Flowers',
            '4 - For thousands of years, the area has been continuosly inhabited by Native Americans, who built settlements whitin the canyon.',
            '4 - KÉVIN VANOVERBERGHE',
            '2',
            'right',
            'dark'
        ]
    ];
    this.imgInfoTemplate = '<div class="header">%header%</div><div class="description">%description%</div><div class="author">Photo by %author%</div>';
    this.loader = '<div class="loader">Loading...</div>';


    this.bodyEl     = $('body');
    this.headerEl   = $('header');
    this.headerLogo = $('header .logo');
    this.infoEl     = $('#img-info');
    this.imgBG      = $('.img-bg');
    this.imgBG2     = $('.img-bg-2');
    this.imgEl      = $('.img-bg img');
    this.loaderEl   = '.loader';

    this.classes = {
        loadContent : 'load-content',
        show        : 'show',
        hide        : 'hide'
    };

    this.timerImg   = 15000;
}

function PageController(pageConstants) {
    var self    = this,
        i       = 0,
        tempInterval,
        tempInfoV = '';

    this.init = function() {
        this.blocksSet();

        this.timerInfo();
    };

    this.blocksSet = function() {
        self.imgInfoSet(i);

        return false;
    };

    this.timerInfo = function() {
        tempInterval = setInterval(self.blocksSet, pageConstants.timerImg);
    };

    this.imgInfoSet = function(i) {
        var tempInfo = pageConstants.imgInfoTemplate;

        tempInfo = tempInfo.replace('%header%', pageConstants.arrayImg[i][1]);
        tempInfo = tempInfo.replace('%description%', pageConstants.arrayImg[i][2]);
        tempInfo = tempInfo.replace('%author%', pageConstants.arrayImg[i][3]);

        tempInfoV = tempInfo;

        if ($(pageConstants.loaderEl).length <= 0) {
            pageConstants.bodyEl.append(pageConstants.loader);
        }
        pageConstants.imgEl.attr('src', pageConstants.arrayImg[i][0]);
        pageConstants.imgBG.removeClass(pageConstants.classes.show).addClass(pageConstants.classes.hide);

        pageConstants.bodyEl.addClass(pageConstants.classes.loadContent);

        pageConstants.imgEl.on('load', function(e, data) {
            if (typeof data === "undefined") {
                return false;
            } else {
                var temp = data.count;
            }
            if (i != temp || !pageConstants.bodyEl.hasClass(pageConstants.classes.loadContent)) {
                return false;
            }
            setTimeout(loadContent, 1500);
            pageConstants.bodyEl.removeClass(pageConstants.classes.loadContent);
        });
        pageConstants.imgEl.trigger('load', {
            count: i
        });
    };

    var loadContent = function() {
        pageConstants.imgBG.css({'background-image': 'url('+'/Resources/'+ pageConstants.arrayImg[i][0] +')'});
        pageConstants.infoEl.removeClass(pageConstants.classes.show).addClass(pageConstants.classes.hide);
        pageConstants.headerEl.removeClass(pageConstants.classes.show).addClass(pageConstants.classes.hide);
        pageConstants.imgBG.removeClass(pageConstants.classes.hide).addClass(pageConstants.classes.show);

        setTimeout(function () {
            pageConstants.bodyEl.removeClass('dark light').addClass(pageConstants.arrayImg[i][6]);
        }, 2000);
        setTimeout(hideBgTempContainer, 2500);
        setTimeout(showLogo, 2000);
        setTimeout(showContent, 2500);
    };

    var hideBgTempContainer = function() {
        pageConstants.imgBG2.css({'background-image': 'url('+'/Resources/'+ pageConstants.arrayImg[i][0] +')'});
        pageConstants.imgBG.removeClass(pageConstants.classes.show).addClass(pageConstants.classes.hide);
    };

    var showLogo = function() {
        if (pageConstants.headerEl.hasClass('zi-1'))
            pageConstants.headerEl.removeClass('zi-1');
        pageConstants.headerLogo.removeClass('newtrip-1 newtrip-2 newtrip-1w newtrip-2w opacity2 center right').addClass('newtrip-' + pageConstants.arrayImg[i][4]);
        pageConstants.headerEl.removeClass(pageConstants.classes.hide).addClass(pageConstants.classes.show);
    };

    var showContent = function() {
        pageConstants.infoEl.removeClass('right left center').addClass(pageConstants.arrayImg[i][5]).html(tempInfoV);
        pageConstants.infoEl.removeClass(pageConstants.classes.hide).addClass(pageConstants.classes.show);

        if (i >= pageConstants.arrayImg.length - 1) {
            i = 0;
        } else {
            i++;
        }

        pageConstants.imgEl.attr('src', pageConstants.arrayImg[i][0]);
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