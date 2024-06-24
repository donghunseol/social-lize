$(function () {
    const currentURL = window.location.pathname; // 현재 페이지의 URL을 가져옵니다.
    const sidemenu = $(".side-menu .head-menu");
    const sidemenuURL = $(".side-menu .sub a");

    console.log(currentURL);

    // 페이지 로딩될 때 해당 페이지에 대응하는 사이드 메뉴를 활성화합니다.
    sidemenuURL.each(function () {
        const menuURL = $(this).attr('href');
        console.log("헤헤");
        if (currentURL === menuURL) {
            $(this).closest('.side-menu').find('.head-menu').addClass('active');
            $(this).closest('.side-menu').find('.sub').addClass('active');
            $(this).parent().addClass('on');
        }
    });

    // 사이드 메뉴 클릭 시 동작하는 함수입니다.
    sidemenu.click(function () {
        sidemenu.removeClass('active');
        $(this).addClass('active');
        $(this).parent().siblings().find('.sub').removeClass('active');
        $(this).parent().find('.sub').toggleClass('active');
    });
});