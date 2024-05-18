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
