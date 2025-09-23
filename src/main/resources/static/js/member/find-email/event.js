// 토스트 오류 메시지
const submitButton = document.querySelector(".btn-submit");
const checkButton = document.querySelector(".btn-check");
const inputCertiContainer = document.getElementById("input-certi-container");

const inputName = document.getElementById("input-name");
const inputNumber = document.getElementById("input-number");
const inputCerti = document.getElementById("input-certi");

const toastName = document.getElementById("toast-name");
const toastNumber = document.getElementById("toast-number");
const toastWrongCerti = document.getElementById("toast-wrong-certi");
const toastCerti = document.getElementById("toast-certi-number");
const toastSendCerti = document.getElementById("toast-send-certi");
const toastFailSend = document.getElementById("toast-fail-send");
const toastFailMember = document.getElementById("toast-fail-member");
inputCertiContainer.style.display = "none";

// 전화번호 포맷팅
function formatPhoneNumber(input) {
    input.value = input.value
        .replace(/[^0-9]/g, '')
        .replace(/(^02|^01[0-9]|[0-9]{3})([0-9]+)?([0-9]{4})$/, '$1 $2 $3');
}
// 인증번호 포맷팅
function formatCertiNumber(input) {
    // 숫자만 남기기
    input.value = input.value.replace(/\D/g, '').slice(0, 6);
}

// 이름 공란 토스트
function showNameErrorToast() {
    toastName.classList.add("show");
    setTimeout(() => {
        toastName.classList.remove("show");
    }, 3000);
}
// 전화번호 공란 오류 토스트
function showNumberErrorToast() {
    toastNumber.classList.add("show");
    setTimeout(() => {
        toastNumber.classList.remove("show");
    }, 3000);
}
// 인증번호 발송 토스트
function showSendToast() {
    toastSendCerti.classList.add("show");
    setTimeout(() => {
        toastSendCerti.classList.remove("show");
    }, 3000);
}
// 인증번호 발송 실패 토스트
function showSendErrorToast() {
    toastFailSend.classList.add("show");
    setTimeout(() => {
        toastFailSend.classList.remove("show");
    }, 3000);
}
// 인증번호 공란 오류 토스트
function showCertiErrorToast() {
    toastCerti.classList.add("show");
    setTimeout(() => {
        toastCerti.classList.remove("show");
    }, 3000);
}
// 인증번호 불일치 오류 토스트
function showWrongCertiErrorToast() {
    toastWrongCerti.classList.add("show");
    setTimeout(() => {
        toastWrongCerti.classList.remove("show");
    }, 3000);
}
// 회원 정보 불일치 토스트
function showMemberErrorToast() {
    toastFailMember.classList.add("show");
    setTimeout(() => {
        toastFailMember.classList.remove("show");
    }, 3000);
}


// 인증번호 받기 버튼 활성화/비활성화
inputName.addEventListener("keyup", (e) => {
    if (inputName.value.length !== 0 && inputNumber.value.length === 13) {
        submitButton.classList.remove('inactive');
    } else if (inputName.value.length === 0 || inputNumber.value.length < 13) {
        submitButton.classList.add('inactive');
    }
});
inputNumber.addEventListener("keyup", (e) => {
    if (inputName.value.length !== 0 && inputNumber.value.length === 13) {
        submitButton.classList.remove('inactive');
    } else if (inputName.value.length === 0 || inputNumber.value.length < 13) {
        submitButton.classList.add('inactive');
    }
});


// 인증번호 받기 전
submitButton.addEventListener("click", () => {
    if (inputName.value.length === 0) {
        showNameErrorToast();  // 이름 공란
    } else if (inputNumber.value.trim().length < 13) {
        showNumberErrorToast();  // 전화번호 공란
    }
    // 회원 정보 불일치 조건 추가 예정(showMemberErrorToast)
});


// 인증번호 받은 후
submitButton.addEventListener("click", (e) => {
    // let text = `<span class="btn-submit-text">확인</span>`;

    if (submitButton.classList.contains('inactive')) {
        return;
    }
    // 이름, 전화번호 확인 조건 추가 예정
    else if (inputCertiContainer.style.display === "none") {
        showSendToast();
        inputCertiContainer.style.display = "block";
        // submitButton.innerHTML = text;
        submitButton.classList.add('none');
        checkButton.classList.remove('none');
    }

    // 인증번호 발송 실패 조건 추가 예정(showSendErrorToast)
    // 인증번호 확인 조건 추가 예정(showWrongCertiErrorToast)
});

checkButton.addEventListener("click", (e) => {
    // 인증번호 공란
    if (inputCertiContainer.style.display === "block" && inputCerti.value.length < 6) {
        showCertiErrorToast();
    }
});

// 확인버튼 활성화
inputCerti.addEventListener("keyup", (e) => {
    if (e.target.value.length === 6) {
        checkButton.classList.remove('inactive')
    } else if (e.target.value.length < 6) {
        checkButton.classList.add('inactive')
    }
});