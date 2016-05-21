function posMouse(e){
    var mouX = 0, mouY = 0;
    if (!e) e = window.event;
    if (e.pageX || e.pageY) {
        mouX = e.pageX;
        mouY = e.pageY;
    } else if (e.clientX || e.clientY) {
        mouX = e.clientX + (document.documentElement.scrollLeft || document.body.scrollLeft) - document.documentElement.clientLeft;
        mouY = e.clientY + (document.documentElement.scrollTop || document.body.scrollTop) - document.documentElement.clientTop;
    }
    return {"x":mouX, "y":mouY}
}

$(document).ready(function(){
    $('#txt').mouseover(function(){
        $(this).next().slideDown('slow');
    })
        .mouseout(function(e){
            var elem = $(this).next(), mou = posMouse(e);
            if ((mou.x < elem.offset().left) || (mou.x > elem.offset().left + elem.width()) || (mou.y < elem.offset().top))
            elem.slideUp('slow');
        });
    $('#box').mouseout(function(){
        $(this).slideUp('slow');
    });
});
