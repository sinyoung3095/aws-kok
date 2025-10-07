// 결제 모듈
const pay = async ({price, duration}) => {
    try {
        const response = await Bootpay.requestPayment({
            application_id: "687efac486cd66f61255b55c", //앱키
            price: price,
            order_name: `배너 광고(${duration})`,
            order_id: "TEST_ORDER_ID",
            pg: "토스",
            method: "토스",
            tax_free: 0,
            user: {
                id: "회원아이디",
                username: "회원이름",
                phone: "01000000000",
                email: "test@test.com",
            },
            items: [
                {
                    id: "item_id",
                    name: `배너 광고(${duration})`,
                    qty: 1,
                    price: price,
                },
            ],
            extra: {
                open_type: "iframe",
                card_quota: "0,2,3",
                escrow: false,
            },
        });
        switch (response.event) {
            case "done":
                console.log(response);
                console.log("여기 맞는가")
                // service.save();
                // 결제 완료 처리
                break;
        }
    } catch (e) {
        // 결제 진행중 오류 발생
        // e.error_code - 부트페이 오류 코드
        // e.pg_error_code - PG 오류 코드
        // e.message - 오류 내용
        console.log(e.message);
        switch (e.event) {
            case "cancel":
                // 사용자가 결제창을 닫을때 호출
                console.log(e.message);
                break;
            case "error":
                // 결제 승인 중 오류 발생시 호출
                console.log(e.error_code);
                break;
        }
    }
};

document.addEventListener("DOMContentLoaded", function () {
    const inputMain = document.getElementById("ad-main-text");
    const h6 = document.querySelector("h6.pre-h6");
    const inputSub = document.getElementById("ad-sub-text");
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
    btnAddDateText.addEventListener("click", async () => {
        const isMainValid = validateInput(inputMain, "메인 텍스트를 입력해주세요.");
        const isSubValid = validateInput(inputSub, "서브 텍스트를 입력해주세요.");
        const isDateValid = validateDate(okcheck);

        // 하나라도 false면 return
        if (!isMainValid || !isSubValid || !isDateValid) return;

        // 모든 유효성 통과 시 실행
        console.log("등록 처리 실행!");

        // 결제 완료
        const payInfo = {
            price: 3000,
            duration: 2
        }
        // await pay(payInfo);

        // 데이터 보내기
        const data = {
            adMainText: document.querySelector("#ad-main-text").value,
            adSubText: document.querySelector("#ad-sub-text").value,
            adStartDatetime: document.querySelector("#start-date").value,
            adEndDatetime: document.querySelector("#end-date").value
        }

        console.log(data)
    });

    function validateInput(inputElement, message) {
        const parent = inputElement.closest(".yoso");
        let errorMsg = parent.querySelector(".error-msg");
        const value = inputElement.value.trim();

        if (!value) {
            inputElement.style.border = "2px solid red";

            if (!errorMsg) {
                errorMsg = document.createElement("p");
                errorMsg.classList.add("error-msg");
                errorMsg.innerText = message;
                parent.appendChild(errorMsg);
            }
            return false;
        } else {
            inputElement.style.border = "";
            if (errorMsg) errorMsg.remove();
            return true;
        }
    }

    function validateDate(okcheck) {
        // okcheck가 false면 날짜 미지정
        if (!okcheck) {
            const dateWrap = document.querySelector(".date-wrap");
            let errorMsg = dateWrap.querySelector(".error-msg");

            if (!errorMsg) {
                errorMsg = document.createElement("p");
                errorMsg.classList.add("error-msg");
                errorMsg.innerText = "날짜를 필수로 지정해 주세요.";
                dateWrap.appendChild(errorMsg);
            }
            return false;
        } else {
            const dateWrap = document.querySelector(".date-wrap");
            const errorMsg = dateWrap.querySelector(".error-msg");
            if (errorMsg) errorMsg.remove();
            return true;
        }
    }

    // const btnAddDateText = document.querySelector("button.start-add");
    // btnAddDateText.addEventListener("click", async () => {
    //     if (!inputMain.value.trim()) {
    //         inputMain.style.border = "2px solid red";
    //         return;
    //     }
    //
    //     if (!inputSub.value.trim()) {
    //         alert("필수 텍스트를 입력해 주세요");
    //         inputMain.style.border = "2px solid red";
    //         return;
    //     }
    //
    //     if (!okcheck) {
    //         alert("날짜를 필수로 지정해 주세요");
    //         return;
    //     }
    //
    //     // 결제 완료
    //     const payInfo = {
    //         price: 3000,
    //         duration: 2
    //     }
    //     // await pay(payInfo);
    //
    //     // 데이터 보내기
    //     const data = {
    //         adMainText: document.querySelector("#ad-main-text").value,
    //         adSubText: document.querySelector("#ad-sub-text").value,
    //         adStartDatetime: document.querySelector("#start-date").value,
    //         adEndDatetime: document.querySelector("#end-date").value
    //     }
    //
    //     console.log(data)
    // });

    const checkBtn = document.querySelector("button.confirm-btn");
    checkBtn.addEventListener("click", ()=>{
        calculatePeriod()
    });
});

// 달력 input
const dateInputs = document.querySelectorAll(".date-input");
dateInputs.forEach(input => {
    input.addEventListener("input", (e) => {
        let value = e.target.value.replace(/[^0-9]/g, ""); // 숫자만 허용
        if (value.length > 8) value = value.substring(0, 8);

        if (value.length >= 5) {
            value = value.substring(0,4) + "-" + value.substring(4,6) +
                (value.length > 6 ? "-" + value.substring(6) : "");
        } else if (value.length >= 4) {
            value = value.substring(0,4) + "-" + value.substring(4);
        }

        e.target.value = value;
    });
});

document.addEventListener("DOMContentLoaded", () => {
    const today = new Date();
    const twoDaysLater = new Date(today);
    twoDaysLater.setDate(today.getDate() + 2);

    const formatted = (d) => {
        const y = d.getFullYear();
        const m = String(d.getMonth() + 1).padStart(2, "0");
        const day = String(d.getDate()).padStart(2, "0");
        return `${y}-${m}-${day}`;
    };

    document.getElementById("start-date").value = formatted(twoDaysLater);
    document.getElementById("end-date").value = formatted(twoDaysLater);
});





