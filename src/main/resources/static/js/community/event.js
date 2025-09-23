HTMLCollection.prototype.forEach = Array.prototype.forEach;

const report = document.getElementsByClassName("report-1");
const btn = document.getElementsByClassName("btn");
const modals = document.getElementsByClassName("report-7")[0];
const cancle = document.getElementsByClassName("report-19")[0];
// 글쓰기
const hide = document.getElementsByClassName("hide-1")[0];
const popup = document.getElementById("post-write-popup");
if (popup.style.display === "none") {
    hide.style.display = "flex";
}

// 각 버튼에 클릭 이벤트 추가
btn.forEach((bt, i) => {
    bt.addEventListener("click", (e) => {
        if (
            report[i].style.display === "none" ||
            report[i].style.display === ""
        ) {
            if (bt === e.target) {
                report[i].style.display = "flex";
            } else {
                report[i].style.display = "none";
            }
        } else {
            report[i].style.display = "none";
        }
    });
});

// 신고하기
report.forEach((but) => {
    but.addEventListener("click", (e) => {
        report.forEach((rep) => {
            rep.style.display = "none";
        });
        modals.style.display = "flex";
    });
});

// 신고하기 끄기
cancle.addEventListener("click", (e) => {
    modals.style.display = "none";
});

// 댓글창 모달
const down = document.getElementById("down");
const detail = document.getElementsByClassName("reply")[0];
const replys = document.getElementsByClassName("replys");

replys.forEach((reply) => {
    reply.addEventListener("click", (e) => {
        detail.style.display = "flex";
    });
});
// 댓글창 모달 끄기
const replytext = document.getElementsByClassName("replytext");
const change = document.getElementsByClassName("change")[0];
down.addEventListener("click", (e) => {
    replytext.forEach((text) => {
        if (text.value !== "") {
            change.style.display = "flex";
        }
    });
    if (change.style.display !== "flex") {
        detail.style.display = "none";
    }
});
// 작성중 경고창 끄기
const changeCancle = document.getElementsByClassName("del-12")[1];
const changeExit = document.getElementsByClassName("del-10")[1];
changeCancle.addEventListener("click", (e) => {
    change.style.display = "none";
});
changeExit.addEventListener("click", (e) => {
    replytext.forEach((text) => {
        text.value = "";
    });
    change.style.display = "none";
    detail.style.display = "none";
});

// 댓글 삭제
const delet = document.getElementsByClassName("report-1");
const del = document.getElementsByClassName("del")[0];
const delbtn = document.getElementsByClassName("delbtn");
const delreport = document.getElementsByClassName("delbtn-1");

// 댓글 샌드위치 버튼 작동
delbtn.forEach((bt, i) => {
    bt.addEventListener("click", (e) => {
        if (
            delreport[i].style.display === "none" ||
            delreport[i].style.display === ""
        ) {
            if (bt === e.target) {
                delreport[i].style.display = "flex";
            } else {
                delreport[i].style.display = "none";
            }
        } else {
            delreport[i].style.display = "none";
        }
    });
});

// 댓글 삭제 버튼 클릭시 경고창
delreport.forEach((but) => {
    but.addEventListener("click", (e) => {
        delreport.forEach((rep) => {
            rep.style.display = "none";
        });
        del.style.display = "flex";
    });
});

const dlecancle = document.getElementsByClassName("del-12")[0];
dlecancle.addEventListener("click", (e) => {
    del.style.display = "none";
});
// 답글
const comments = document.getElementsByClassName("comments");
const commentbtn = document.getElementsByClassName("comment");
commentbtn.forEach((comment, i) => {
    comment.addEventListener("click", (e) => {
        if (
            comments[i].style.display === "none" ||
            comments[i].style.display === ""
        ) {
            comments[i].style.display = "flex";
        } else {
            comments[i].style.display = "none";
        }
    });
});

// 답글 삭제 샌드위치
const delbtn1 = document.getElementsByClassName("delbtn-0");
const delreport1 = document.getElementsByClassName("delbtn-2");
const delComment = document.getElementsByClassName("del-comment")[0];
delbtn1.forEach((bt, i) => {
    bt.addEventListener("click", (e) => {
        if (
            delreport1[i].style.display === "none" ||
            delreport1[i].style.display === ""
        ) {
            if (bt === e.target) {
                delreport1[i].style.display = "flex";
            } else {
                delreport1[i].style.display = "none";
            }
        } else {
            delreport1[i].style.display = "none";
        }
    });
});
delreport1.forEach((but) => {
    but.addEventListener("click", (e) => {
        delreport1.forEach((rep) => {
            rep.style.display = "none";
        });

        delComment.style.display = "flex";
    });
});
const commentDelete = document.getElementsByClassName("del-10")[2];
const commentExit = document.getElementsByClassName("del-12")[2];
// 답글 삭제 취소
commentExit.addEventListener("click", (e) => {
    delComment.style.display = "none";
});
// 답글 삭제 버튼
commentDelete.addEventListener("click", (e) => {
    delComment.style.display = "none";
});

// 좋아요
const hearts = document.getElementsByClassName("heart");
hearts.forEach((heart, i) => {
    heart.addEventListener("click", (e) => {
        if (heart.style.fill === "red" || heart.style.fill === "") {
            console.log("들어옴");
            heart.style.fill = "white";
            heart.style.stroke = "black";
        } else {
            heart.style.fill = "red";
            heart.style.stroke = "red";
        }
    });
});
