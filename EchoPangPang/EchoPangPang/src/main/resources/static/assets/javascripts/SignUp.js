document.getElementById("emailVerificationButton").addEventListener("click", function() {
    sendEmailVerification();
    toggleEmailVerificationCodeInput();
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


function toggleEmailVerificationCodeInput() {
    var codeInputDiv = document.getElementById("emailVerificationCodeInput");
    if (codeInputDiv.style.display === "none") {
        codeInputDiv.style.display = "block";
    } else {
        // codeInputDiv.style.display = "none";
    }
}


//

document.getElementById("submitButton").addEventListener("click", function(event) {
    // event.preventDefault(); // 기본 동작(폼 제출)을 막음
    var codeInputDiv = document.getElementById("emailVerificationCodeInput");
    var code = document.getElementById("emailVerificationCode").value;
    var email = document.getElementById("email").value; // 이메일 주소 입력란의 id를 확인하세요

    if (codeInputDiv.style.display !== "none") {
        // 이메일 인증 코드 입력란이 표시된 경우에만 코드 확인 API 호출
        checkEmailVerificationCode(code, email);
    } else {
        // 이메일 인증 코드 입력란이 숨겨진 경우에는 기본 동작(폼 제출)을 수행
        this.form.submit();
    }
});

function checkEmailVerificationCode(code, email) {
    // 이메일 인증 코드 확인 API 엔드포인트
    var url = "/api/CheckCode";

    // POST 요청으로 보낼 데이터
    var data = {
        code: code,
        email: email
    };

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
                throw new Error('이메일 인증에 실패했습니다.');
            }
            return response.json();
        })
        .then(data => {
            if (data.result !== "success") {
                console.error('이메일 인증이 실패했습니다.', data);
                // 에러 처리 로직 추가
                event.preventDefault(); // 기본 동작(폼 제출)을 막음
            } else {
                console.log('이메일 인증이 성공적으로 완료되었습니다.', data);
                // 이메일 인증이 성공적으로 완료된 후에 수행할 작업 추가
                // 예: 사용자를 다음 페이지로 리다이렉트
            }
        })
        .catch(error => {
            console.error('에러 발생:', error);
            // 에러 처리 로직 추가
            event.preventDefault(); // 기본 동작(폼 제출)을 막음
        });

}
