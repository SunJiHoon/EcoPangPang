const calendarBody = document.getElementById('calendarBody');
const monthYear = document.getElementById('monthYear');
let currentDate = new Date();

// 특정 날짜에 도장 찍기
const stampedDates = [5, 10, 15]; // 도장을 찍을 날짜들 (예: 5일, 10일, 15일)

function renderCalendar(date) {
    calendarBody.innerHTML = '';
    const year = date.getFullYear();
    const month = date.getMonth();
    monthYear.innerText = `${year}년 ${month + 1}월`;

    const firstDayOfMonth = new Date(year, month, 1).getDay();
    const lastDateOfMonth = new Date(year, month + 1, 0).getDate();

    let row = document.createElement('tr');
    for (let i = 0; i < firstDayOfMonth; i++) {
        const emptyCell = document.createElement('td');
        row.appendChild(emptyCell);
    }

    for (let day = 1; day <= lastDateOfMonth; day++) {
        if (row.children.length === 7) {
            calendarBody.appendChild(row);
            row = document.createElement('tr');
        }

        const cell = document.createElement('td');
        cell.innerText = day;

        // // 특정 날짜에 도장 추가
        // if (stampedDates.includes(day)) {
        //     const stamp = document.createElement('div');
        //     stamp.classList.add('stamp');
        //     cell.appendChild(stamp);
        // }
        if (stampedDates.includes(day)) {
            const stamp = document.createElement('div');
            stamp.classList.add('stamp');
            const img = document.createElement('img');
            img.src = '/assets/images/stamp_outline.png'; // 이미지 경로 설정
            img.classList.add('stamp-image'); // 이미지 요소에 클래스 추가
            stamp.appendChild(img);
            cell.appendChild(stamp);
        }

        if (year === new Date().getFullYear() && month === new Date().getMonth() && day === new Date().getDate()) {
            cell.classList.add('today');
        }

        row.appendChild(cell);
    }

    calendarBody.appendChild(row);
}

document.getElementById('prevMonth').addEventListener('click', () => {
    currentDate.setMonth(currentDate.getMonth() - 1);
    renderCalendar(currentDate);
});

document.getElementById('nextMonth').addEventListener('click', () => {
    currentDate.setMonth(currentDate.getMonth() + 1);
    renderCalendar(currentDate);
});

renderCalendar(currentDate);

// 날짜 셀 클릭 이벤트 핸들러
calendarBody.addEventListener('click', (event) => {
    const clickedCell = event.target;
    if (clickedCell.tagName === 'TD') {
        // const clickedDate = clickedCell.innerText;
        // const year = currentDate.getFullYear();
        // const month = currentDate.getMonth() + 1; // getMonth()는 0부터 시작하므로 1을 더해줍니다.

        const clickedDate = clickedCell.innerText;
        const year = currentDate.getFullYear();
        const month = currentDate.getMonth() + 1; // getMonth()는 0부터 시작하므로 1을 더해줍니다.

        // 클릭한 날짜로 쿼리 생성
        const formattedDate = `${String(year).slice(-2)}-${month.toString().padStart(2, '0')}-${clickedDate.padStart(2, '0')}`;
        const url = `/api/Calender/day/${formattedDate}`;

        // 생성된 쿼리를 사용하여 GET 요청 보내기
        fetch(url)
            .then(response => response.json())
            .then(data => {
                // 처리할 작업 수행
                console.log(data);
            })
            .catch(error => {
                console.error('Error:', error);
                // 오류 처리
            });

        // 클릭한 날짜를 출력
        // alert(`${year}년 ${month}월 ${clickedDate}일`);
    }
});
