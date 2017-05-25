$(function() {
    $('.destroy-button').on('click', function(e) {
        if(!confirm('本当に削除しますか？')) e.preventDefault();
    });
});