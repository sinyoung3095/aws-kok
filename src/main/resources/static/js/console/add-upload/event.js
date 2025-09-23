document.addEventListener("DOMContentLoaded", function () {
    const inputMain = document.getElementById("add-main-text");
    const h6 = document.querySelector("h6.pre-h6");
    const inputSub = document.getElementById("add-sub-text");
    const p = document.querySelector("p.pre-p");

    let okcheck = false;

    inputMain.addEventListener("input", function () {
        h6.innerText = inputMain.value;
    });

    inputSub.addEventListener("input", function () {
        p.innerText = inputSub.value;
    });

    inputMain.setAttribute("maxlength", "30");
    inputSub.setAttribute("maxlength", "50");

    const mediaLabel = document.querySelector("label.media-label");
    const backgroundInput = document.getElementById("add-background");

    mediaLabel.addEventListener("click", function () {
        backgroundInput.click();
    });

    backgroundInput.addEventListener("change", function () {
        const file = backgroundInput.files[0];
        if (file) {
            const img = document.getElementById("add-back");
            const reader = new FileReader();
            reader.onload = function (e) {
                img.src = e.target.result;
            };
            reader.readAsDataURL(file);
        }
    });

    const startDateInput = document.getElementById("start-date");
    const endDateInput = document.getElementById("end-date");

    function calculatePeriod() {
        const startDate = startDateInput.value;
        const endDate = endDateInput.value;
        const nowDate = new Date();

        if (startDate && endDate) {
            const start = new Date(startDate);
            const end = new Date(endDate);

            console.log(Math.floor((start - nowDate) / (1000 * 60 * 60 * 24)));
            // 날짜가 올바른지 검증
            if (isNaN(start.getTime()) || isNaN(end.getTime())) {
                alert("올바른 날짜를 입력하세요. (예: 2025-07-15)");
            } else {
                // 기간 계산 (시작일과 종료일 포함)
                const diffDays =
                    Math.floor((end - start) / (1000 * 60 * 60 * 24)) + 1;

                const resultDate = document.querySelector("div.main-date-text");
                const resultPrice = document.querySelector("div.start-price");
                const date2 = document.getElementById("add-date-text");
                if (
                    Math.floor((start - nowDate) / (1000 * 60 * 60 * 24)) <= 0
                ) {
                    alert(
                        "광고 시작일은 신청일 2일 후부터로만 입력 가능합니다."
                    );
                } else {
                    if (diffDays <= 0) {
                        alert("시작일이 종료일 이후입니다.");
                    } else {
                        resultDate.innerText = diffDays + "일";
                        const price = diffDays * 200000;
                        resultPrice.innerText = "￦" + price.toLocaleString();
                        date2.innerText = "광고 기간: " + diffDays + "일";
                        okcheck = true;
                    }
                }
            }
        }
    }

    const btnAddDateText = document.querySelector("button.start-add");

    btnAddDateText.addEventListener("click", function () {
        if (!inputMain.value.trim() || !inputSub.value.trim()) {
            alert("필수 텍스트를 입력해 주세요");
            return;
        }

        if (!okcheck) {
            alert("날짜를 필수로 지정해 주세요");
            return;
        }
    });

    const checkBtn = document.querySelector("button.calcu");
    checkBtn.addEventListener("click", calculatePeriod);
});
