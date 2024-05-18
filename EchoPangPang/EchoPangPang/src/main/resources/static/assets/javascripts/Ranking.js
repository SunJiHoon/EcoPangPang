document.addEventListener("DOMContentLoaded", function() {
    renderInitialRankings();
});

function renderInitialRankings() {
    fetch('/api/Ranking/Sort')
        .then(response => response.json())
        .then(data => {
            displayResults(data);
        })
        .catch(error => {
            console.error('Error:', error);
            alert('데이터를 가져오는 중 오류가 발생했습니다.');
        });
}

function search() {
    var query = document.getElementById('searchInput').value;
    if (query) {
        // fetch(`/api/Ranking/search?q=${encodeURIComponent(query)}`)
        fetch(`/api/Ranking/Search/${encodeURIComponent(query)}`)
            .then(response => response.json())
            .then(data => {
                displayResults(data);
            })
            .catch(error => {
                console.error('Error:', error);
                alert('검색 중 오류가 발생했습니다.');
            });
    } else {
        alert('검색어를 입력하세요.');
    }
}

function displayResults(data) {
    var resultsContainer = document.getElementById('results');
    resultsContainer.innerHTML = ''; // 기존 결과를 초기화

    if (data && data.length > 0) {
        data.forEach(result => {
            var resultElement = document.createElement('div');
            resultElement.classList.add('result');
            resultElement.textContent = `이름: ${result.name}, 포인트: ${result.point}, 등급: ${result.puangGrade}`; // 결과의 제목 표시 (적절히 수정 필요)
            resultsContainer.appendChild(resultElement);
        });
    } else {
        resultsContainer.textContent = '검색 결과가 없습니다.';
    }
}
