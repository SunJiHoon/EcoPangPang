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
