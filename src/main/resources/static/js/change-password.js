document.addEventListener('DOMContentLoaded', function () {
    let checkbox = document.getElementById('change-pas');
    checkbox.addEventListener('click', function () {
        disableSearch(!checkbox.checked);
    });

    function disableSearch(flag) {
        document.getElementById('password').disabled = flag;
    }
});