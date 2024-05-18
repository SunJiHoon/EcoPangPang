function search() {
    var query = document.getElementById('searchInput').value;
    if (query) {
        fetch(`/api/Ranking/search?q=${encodeURIComponent(query)}`)
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

    if (data.results && data.results.length > 0) {
        data.results.forEach(result => {
            var resultElement = document.createElement('div');
            resultElement.classList.add('result');
            resultElement.textContent = result.title; // 결과의 제목 표시 (적절히 수정 필요)
            resultsContainer.appendChild(resultElement);
        });
    } else {
        resultsContainer.textContent = '검색 결과가 없습니다.';
    }
}
