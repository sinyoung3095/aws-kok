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
    const previewImg = document.getElementById("add-back");

    // 라벨 클릭 시 input 클릭
    mediaLabel.addEventListener("click", function () {
        backgroundInput.click();
    });

    // 파일 선택 시 미리보기 이미지 변경
    backgroundInput.addEventListener("change", function () {
        const file = backgroundInput.files[0];

        if (file) {
            const reader = new FileReader();
            reader.onload = function (e) {
                // 기존 이미지 교체
                previewImg.src = e.target.result;
                // 선택한 새 파일을 시각적으로 표시 (optional)
                previewImg.style.opacity = "1";
            };
            reader.readAsDataURL(file);
        } else {
            // 파일이 없을 경우 (선택 취소 시) 기본 이미지로 되돌리기
            previewImg.src = "/images/experience/ad_bg_img.jpg";
        }
    });

    const startDateInput = document.getElementById("start-date");
    const endDateInput = document.getElementById("end-date");

    if (page === "create") {
        const startInput = document.querySelector("#start-date");
        const endInput = document.querySelector("#end-date");

        if (!startInput || !endInput) return;
        if (startInput.value && endInput.value) return;

        const today = new Date();
        const twoDaysLater = new Date(today);
        twoDaysLater.setDate(today.getDate() + 2);

        const formatted = (d) => {
            const y = d.getFullYear();
            const m = String(d.getMonth() + 1).padStart(2, "0");
            const day = String(d.getDate()).padStart(2, "0");
            return `${y}-${m}-${day}`;
        };

        startInput.value = formatted(twoDaysLater);
        endInput.value = formatted(twoDaysLater);
    }

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
                const resultPrice = document.querySelector("div.start-price .price");
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
                        resultPrice.innerText = price.toLocaleString();
                        date2.innerText = "광고 기간: " + diffDays + "일";
                        okcheck = true;
                    }
                }
            }
        }
    }

    // 달력 날짜 확인 버튼
    const checkBtn = document.querySelector(".confirm-btn");
    checkBtn.addEventListener("click", (e)=>{
        e.preventDefault();

        calculatePeriod()
    });

    // 광고 등록하기 버튼
    const btnRegisterAd = document.querySelector("#btn-register-ad");
    if(btnRegisterAd){
        btnRegisterAd.addEventListener("click", async () => {
            const isMainValid = validateInput(inputMain, "메인 텍스트를 입력해주세요.");
            const isSubValid = validateInput(inputSub, "서브 텍스트를 입력해주세요.");
            const isDateValid = validateDate(okcheck);

            // 하나라도 false면 return
            if (!isMainValid || !isSubValid || !isDateValid) return;

            const payInfo = {
                price: 100,
                duration: 2
            }

            await pay(payInfo);

            const priceText = document.querySelector(".start-price .price").textContent;
            const paymentPrice = Number(priceText.replace(/,/g, "").trim());

            const data = {
                advertisementMainText: document.querySelector("#ad-main-text").value,
                advertisementSubText: document.querySelector("#ad-sub-text").value,
                advertiseStartDatetime: document.querySelector("#start-date").value,
                advertiseEndDatetime: document.querySelector("#end-date").value,
                companyId: 1,
                paymentPrice: paymentPrice,
                files: document.querySelector("#add-background").files
            }

            try {
                const result = await adService.register(data);
                console.log("광고 등록 성공이당", result);
                alert("광고가 등록되었습니다.");
            } catch (err) {
                console.error(err);
                alert("광고 등록 중 오류가 발생했습니다.");
            }
        });
    }

});




