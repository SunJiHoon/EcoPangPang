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


function toggleCheckBox(checkbox, itemId) {
    var isChecked = checkbox.checked;
    var endpoint = isChecked ? "/api/v1/mission/check" : "/api/v1/mission/uncheck";

    // PATCH 요청 보내기
    fetch(endpoint, {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ itemId: itemId })
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('체크 상태 변경에 실패했습니다.');
            }
            return response.json();
        })
        .then(data => {
            console.log('체크 상태가 성공적으로 변경되었습니다.', data);
            // 변경이 성공하면 추가적인 작업을 수행할 수 있습니다.
        })
        .catch(error => {
            console.error('에러 발생:', error);
            // 에러 처리 로직 추가
        });
}
