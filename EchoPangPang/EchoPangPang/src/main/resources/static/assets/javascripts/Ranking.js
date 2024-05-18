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
        // 순위를 추가하기 위해 데이터를 순회하면서 순위를 할당합니다.
        data.forEach((result, index) => {
            var resultElement = document.createElement('div');
            resultElement.classList.add('result');

            var rankElement = document.createElement('span');
            rankElement.classList.add('rank');
            rankElement.textContent = `순위: ${index + 1}, `;
            resultElement.appendChild(rankElement);

            var nameElement = document.createElement('span');
            nameElement.classList.add('name');
            nameElement.textContent = `이름: ${result.name}, `;
            resultElement.appendChild(nameElement);

            var pointElement = document.createElement('span');
            pointElement.classList.add('point');
            pointElement.textContent = `포인트: ${result.point}, `;
            resultElement.appendChild(pointElement);

            var gradeElement = document.createElement('span');
            gradeElement.classList.add('grade');
            gradeElement.textContent = `등급: ${result.puangGrade}`;
            resultElement.appendChild(gradeElement);

            resultsContainer.appendChild(resultElement);
        });
    } else {
        resultsContainer.textContent = '검색 결과가 없습니다.';
    }
}
