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
            // alert('데이터를 가져오는 중 오류가 발생했습니다.');
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
                // alert('검색 중 오류가 발생했습니다.');
            });
    } else {
        alert('검색어를 입력하세요.');
    }
}

function displayResults(data) {
    var resultsContainer = document.getElementById('results');
    resultsContainer.innerHTML = ''; // 기존 결과를 초기화

    // 헤더 추가
    var headerElement = document.createElement('div');
    headerElement.classList.add('header');
    headerElement.innerHTML = `<span class="rank">순위&nbsp;&nbsp;</span> <span></span> <span class="name">이름&nbsp;&nbsp;</span> <span></span> <span class="point">포인트</span>`;
    resultsContainer.appendChild(headerElement);

    if (data && data.length > 0) {
        data.forEach((result, index) => {
            var resultElement = document.createElement('div');
            resultElement.classList.add('result');

            var contentElement = document.createElement('div'); // 새로운 div 생성
            contentElement.classList.add('content'); // 필요한 클래스 추가

            // 순위에 따라 다른 클래스 적용
            var rankClass = '';
            if (index === 0) {
                rankClass = 'rank-1';
            } else if (index === 1) {
                rankClass = 'rank-2';
            } else if (index === 2) {
                rankClass = 'rank-3';
            }

            var rankElement = document.createElement('span');
            rankElement.classList.add('rank', rankClass);
            rankElement.textContent = `  ${index + 1}`;
            contentElement.appendChild(rankElement);

            var nameElement = document.createElement('span');
            nameElement.classList.add('name');
            nameElement.textContent = `  ${result.name}`;
            contentElement.appendChild(nameElement);

            var pointElement = document.createElement('span');
            pointElement.classList.add('point');
            pointElement.textContent = `  ${result.point}  `;
            contentElement.appendChild(pointElement);

            resultElement.appendChild(contentElement);
            resultsContainer.appendChild(resultElement);
        });
    } else {
        resultsContainer.textContent = '검색 결과가 없습니다.';
    }
}
