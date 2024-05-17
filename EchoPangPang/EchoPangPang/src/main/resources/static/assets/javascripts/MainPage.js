var percentageElement = document.getElementById('percentageValue');

// 요소에서 텍스트 값 가져오기
var percent = parseInt(percentageElement.textContent);

// 원하는 퍼센트 값
// var percent = 75;

// 프로그레스 바 요소 가져오기
var progressBar = document.querySelector('.progress-bar');

// 프로그레스 바의 너비를 퍼센트에 맞게 조정
progressBar.style.width = percent + '%';
progressBar.setAttribute('aria-valuenow', percent);

document.getElementById('menu-button').addEventListener('click', function() {
    var elements = document.querySelectorAll('.floating-element');
    elements.forEach(function(element) {
        if (element.style.display === 'none' || element.style.display === '') {
            element.style.display = 'block'; // 보이게 하기
        } else {
            element.style.display = 'none'; // 숨기기
        }
    });
});
