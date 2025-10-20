const submitBtn=document.querySelector(".submit-btn");
const radioBtns = document.querySelectorAll(".radio-label");

submitBtn.addEventListener("click", async () => {
    const checkedCount = document.querySelectorAll('input[type="radio"]:checked');
    if (checkedCount.length < 16) {
        alert("모든 항목을 선택해주세요.")
    } else {
        const text = document.getElementById("text-review");
        let sum = 0;
        console.log(text.textContent.length);
        console.log(text.textContent);
        console.log(text.valueOf());
        console.log(text.valueOf().length);
        checkedCount.forEach(radio => {
          sum += Number(radio.value);
        });
        const avg = sum / 16;
        if (text.textContent.length < 1) {
            alert("총평을 반드시 입력해주세요.")
        } else {
            const evaluation = {
                requestExperienceId: 12,
                memberId: memberId,
                evaluationContent: text.textContent,
                evaluationAvgScore: avg
            };
            const review = await fetch(`/enterprise-console/experience/go-review`, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(evaluation)
            })
                .then(response => {
                    if (response.ok) {
                        return response.text();
                    } else {
                        throw new Error("평가 요청 실패");
                    }
                })
                .catch(err => console.error(err));
        }
    }
});