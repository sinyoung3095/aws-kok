const fileInput = document.getElementById("flie-input");
const previewInner = document.querySelector(".popup-preview-inner");

fileInput.addEventListener("change", () => {
    // 선택된 파일들 가져오기
    const files = fileInput.files;

    Array.from(files).forEach((file) => {
        // 이미지 파일만 처리
        if (!file.type.startsWith("image/")) return;

        const reader = new FileReader();
        reader.onload = (e) => {
            // preview-item DOM 만들기
            const item = document.createElement("div");
            item.classList.add("preview-item");

            // 이미지 태그
            const img = document.createElement("img");
            img.src = e.target.result;
            img.alt = file.name;

            // 삭제 버튼
            const btn = document.createElement("button");
            btn.classList.add("preview-remove-btn");
            btn.innerHTML = `
        <svg viewBox="0 0 24 24" aria-label="icon" fill="currentColor" height="16" role="img" width="16">
          <path clip-rule="evenodd"
            d="M6.434 6.435a.8.8 0 0 1 1.132 0L12 10.869l4.434-4.434a.8.8 0 1 1 1.132 1.13L13.13 12l4.435 4.435a.8.8 0 1 1-1.132 1.13L12 13.133l-4.434 4.434a.8.8 0 0 1-1.132-1.131L10.87 12 6.434 7.566a.8.8 0 0 1 0-1.131"
            fill-rule="evenodd"></path>
        </svg>
      `;

            // 삭제 기능 연결
            btn.addEventListener("click", () => {
                item.remove();
            });

            // 조립
            item.appendChild(img);
            item.appendChild(btn);
            previewInner.appendChild(item);
        };
        reader.readAsDataURL(file);
    });
});
