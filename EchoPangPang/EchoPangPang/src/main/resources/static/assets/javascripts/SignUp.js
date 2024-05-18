document.getElementById("emailVerificationButton").addEventListener("click", function() {
    sendEmailVerification();
});

function sendEmailVerification() {
    // 사용자가 입력한 이메일 주소 가져오기
    var emailInput = document.getElementById("email");
    var email = emailInput.value;

    // 이메일 보내기 API 엔드포인트
    var url = "/api/SendEmail";

    // POST 요청으로 보낼 데이터
    var data = { to: email };

    // fetch를 사용하여 POST 요청 보내기
    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('이메일 보내기에 실패했습니다.');
            }
            return response.json();
        })
        .then(data => {
            console.log('이메일이 성공적으로 보내졌습니다.', data);
            // 이메일이 성공적으로 보내진 후에 수행할 작업 추가
        })
        .catch(error => {
            console.error('에러 발생:', error);
            // 에러 처리 로직 추가
        });
}
