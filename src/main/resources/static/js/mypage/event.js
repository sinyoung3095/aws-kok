HTMLCollection.prototype.forEach = Array.prototype.forEach;
const first = document.getElementsByClassName("first");
const second = document.getElementsByClassName("second");
const third = document.getElementsByClassName("third");
const third1=document.getElementsByClassName("third-1")
const fourth = document.getElementsByClassName("fourth");
const list = document.getElementsByClassName("post-1");
const savedList=document.getElementById("saved-list-wrap");
const postsList=document.getElementById("my-posts-wrap");
const requestList=document.getElementById("request-list-wrap");
const internList=document.getElementById("intern-list-wrap");
const paymentList=document.getElementById("payment-list-wrap");
const close = document.getElementById("close");
const setting = document.getElementsByClassName("setting")[0];
const set = document.getElementsByClassName("set")[0];
const profileWrapCircle=document.querySelector(".setting-15.profileWrapCircle");
const profileImgPop=document.querySelector(".profile-0");
const submit = document.querySelector("setting-40");
const profile = document.getElementsByClassName("profile")[0];
const profileset = document.getElementsByClassName("setting-16")[0];
const label = document.getElementsByClassName("label")[0];
const background = document.getElementById("background");
const lists = document.getElementsByClassName("list");
const remove = document.getElementsByClassName("remove");
const del = document.getElementsByClassName("del")[0];
const cancle = document.getElementsByClassName("del-12")[0];
const job = document.getElementsByClassName("setting-28")[0];
const joblist = document.getElementsByClassName("job")[0];
const listbtn = document.getElementById("list");
const profileUpdate=document.querySelector(".profile-update");
const profileDelete=document.querySelector(".profile-delete");
const profileInput=document.querySelector(".profile-input");
const profileImgRound=document.querySelector(".profile-img-round");
const nameinput=document.querySelector(".name-input");
const jobInput=document.querySelector(".job-input");
const infoInput=document.querySelector(".info-input");

label.addEventListener("click", (e) => {
    background.click();
});
// console.log(set);
// 프로필 편집 열기
set.addEventListener("click", (e) => {
    setting.style.display = "flex";
});
// 프로필 편집 닫기
close.addEventListener("click", (e) => {
    setting.style.display = "none";
});
// 프로필 편집 완료
submit.addEventListener("click", (e) => {
    const profileImgInput=profileInput.value;
    const name=nameinput.value;
    const job=jobInput.value;
    const info=infoInput.value;
    setting.style.display = "none";
});
profileWrapCircle.addEventListener("click", ()=>{
    profileImgPop.style.display="flex";
})
// 프로필 사진 변경 창 띄우기
profileset.addEventListener("click", (e) => {
    profile.style.display = "flex";
});
// 프로필 이미지 변경
profileUpdate.addEventListener("click", ()=>{
    profileImgPop.style.display="none";
    profileInput.click();
});
profileInput.onchange=function (e){
    const file=e.target.files[0];
    if(!file) return;
    const fileRead=new FileReader();
    fileRead.onload=function (e2){
        profileImgRound.src=e2.target.result;
    }
    fileRead.readAsDataURL(file);
}
// 프로필 삭제
profileDelete.addEventListener("click", ()=>{
    profileImgPop.style.display="none";
    profileImgRound.src='/images/main-page/image.png';
})
// 직군 변경 모달
job.addEventListener("click", (e) => {
    if (joblist.style.display === "none" || joblist.style.display === "") {
        joblist.style.display = "flex";
        listbtn.innerHTML = `<path clip-rule="evenodd" d="M17.566 14.566a.8.8 0 0 0 0-1.132l-5-5a.8.8 0 0 0-1.132 0l-5 5a.8.8 0 0 0 1.132 1.132L12 10.13l4.434 4.435a.8.8 0 0 0 1.132 0" fill-rule="evenodd"></path>`;
    } else {
        joblist.style.display = "none";
        listbtn.innerHTML = `<path clip-rule="evenodd" d="M6.434 9.435a.8.8 0 0 1 1.132 0L12 13.869l4.434-4.434a.8.8 0 1 1 1.132 1.13l-5 5a.8.8 0 0 1-1.132 0l-5-5a.8.8 0 0 1 0-1.13" fill-rule="evenodd"></path>`;
    }
});
// 직군 변경하기
const jobType = document.getElementsByClassName("job-3");
const choice = document.getElementsByClassName("choice");
const select = document.getElementsByClassName("select")[0];
const jobName = document.getElementsByClassName("job-6");

jobType.forEach((job, i) => {
    job.addEventListener("click", (e) => {
        choice.forEach((ch) => {
            ch.classList.remove("active");
        });
        choice[i].classList.add("active");
        select.value = jobName[i].innerText;
        joblist.style.display = "none";
        listbtn.innerHTML = `<path clip-rule="evenodd" d="M6.434 9.435a.8.8 0 0 1 1.132 0L12 13.869l4.434-4.434a.8.8 0 1 1 1.132 1.13l-5 5a.8.8 0 0 1-1.132 0l-5-5a.8.8 0 0 1 0-1.13" fill-rule="evenodd"></path>`;
    });
});
// 내 게시물
first.forEach((item) => {
    item.addEventListener("click", (e) => {
        item.classList.add("active");
        second.forEach((sec) => {
            sec.classList.remove("active");
        });
        third.forEach((fir) => {
            fir.classList.remove("active");
        });
        fourth.forEach((fir) => {
            fir.classList.remove("active");
        });
        third1.forEach((th1)=>{
            th1.classList.remove("active");
        })
        postsList.classList.add("active");
        savedList.classList.remove("active");
        requestList.classList.remove("active");
        paymentList.classList.remove("active");
        internList.classList.remove("active");
    });
});

// 저장한 공고 목록
second.forEach((item) => {
    item.addEventListener("click", (e) => {
        item.classList.add("active");
        first.forEach((fir) => {
            fir.classList.remove("active");
        });
        third.forEach((fir) => {
            fir.classList.remove("active");
        });
        fourth.forEach((fir) => {
            fir.classList.remove("active");
        });
        third1.forEach((th1)=>{
            th1.classList.remove("active");
        })
        savedList.classList.add("active");
        postsList.classList.remove("active");
        requestList.classList.remove("active");
        paymentList.classList.remove("active");
        internList.classList.remove("active");
    });
});
// 지원 내역
third.forEach((item) => {
    item.addEventListener("click", (e) => {
        item.classList.add("active");
        first.forEach((fir) => {
            fir.classList.remove("active");
        });
        second.forEach((fir) => {
            fir.classList.remove("active");
        });
        fourth.forEach((fir) => {
            fir.classList.remove("active");
        });
        third1.forEach((th1)=>{
            th1.classList.remove("active");
        })
        postsList.classList.remove("active");
        savedList.classList.remove("active");
        requestList.classList.add("active");
        paymentList.classList.remove("active");
        internList.classList.remove("active");
        showExperienceRequest();
    });
});
third1.forEach((item) => {
    item.addEventListener("click", (e) => {
        item.classList.add("active");
        first.forEach((fir) => {
            fir.classList.remove("active");
        });
        second.forEach((fir) => {
            fir.classList.remove("active");
        });
        fourth.forEach((fir) => {
            fir.classList.remove("active");
        });
        third.forEach((th)=>{
            th.classList.remove("active");
        })
        postsList.classList.remove("active");
        savedList.classList.remove("active");
        internList.classList.add("active");
        requestList.classList.remove("active");
        paymentList.classList.remove("active");

        showInternRequest();
    });
});
// 결제 내역
fourth.forEach((item) => {
    item.addEventListener("click", (e) => {
        item.classList.add("active");
        first.forEach((fir) => {
            fir.classList.remove("active");
        });
        second.forEach((fir) => {
            fir.classList.remove("active");
        });
        third.forEach((fir) => {
            fir.classList.remove("active");
        });
        third1.forEach((th)=>{
            th.classList.remove("active");
        })
        postsList.classList.remove("active");
        savedList.classList.remove("active");
        requestList.classList.remove("active");
        paymentList.classList.add("active");
        internList.classList.remove("active");
    });
});

// 드롭다운
function dropdownFn() {
    const triggers = document.querySelectorAll(".dropdown-trigger");
    const menus = document.querySelectorAll(".option-menu");

    if (!triggers) return;

    // 드롭다운 열기
    triggers.forEach((trigger) => {
        trigger.addEventListener("click", (e) => {
            console.log("들어옴");
            e.stopPropagation();

            // 다른 드롭다운 닫기
            menus.forEach((menu) => menu.classList.remove("active"));

            const target = trigger.getAttribute("data-target");
            const menu = document.querySelector(target);

            if (menu) {
                menu.classList.add("active");
            }
        });
    });

    // 옵션 클릭 시 input에 값 반영 + 닫기
    document.querySelectorAll(".option-item").forEach((item) => {
        item.addEventListener("click", () => {
            const dropdown = item.closest(".form-field-dropdown");
            const input = dropdown.querySelector(".dropdown-trigger");
            const target = input.getAttribute("data-target");
            const menu = document.querySelector(target);

            // 직접 입력
            if (item.classList.contains("option-input")) {
                dropdown.classList.add("is-manual");
                if (input) {
                    input.readOnly = false;
                    input.value = "";
                    input.focus();
                }
                menu.classList.remove("active");
                return;
            }

            // 일반 옵션
            const text = item.innerText.trim();
            if (input && input.tagName === "INPUT") {
                input.value = text;
                input.readOnly = true;
                dropdown.classList.remove("is-manual");
            }
            menu.classList.remove("active");
        });
    });

    // 바깥 클릭 시 닫기
    // document.addEventListener("click", () => {
    //     menus.forEach((menu) => menu.classList.remove("active"));
    // });
}
dropdownFn();
// 페이징
const pageNum = document.getElementsByClassName("pageNum");
pageNum.forEach((page) => {
    page.addEventListener("click", (e) => {
        pageNum.forEach((p) => {
            p.classList.remove("active");
        });
        e.target.classList.add("active");
    });
});

const showPosts=async ()=>{
    const request=await myPageService.getPostsList();
    myPageLayout.showPosts(request);

    const remove = document.getElementsByClassName("remove");

    // 게시물 삭제 버튼
    remove.forEach((remove) => {
        remove.addEventListener("click", (e) => {
            del.style.display = "flex";
        });
    });
    cancle.addEventListener("click", (e) => {
        del.style.display = "none";
    });

    // 댓글창 열기
    const replys = document.getElementsByClassName("reply-btn");
    const modal = document.querySelector(".comment-modal");
    let postId;
    Array.from(replys).forEach((reply) => {
        reply.addEventListener("click", async (e) => {
            postId = reply.dataset.postId;
            modal.style.display = "flex";
            await showPostDetail(postId);
            await showCommentsList(postId);
            // 댓글 샌드위치 버튼 활성화
            const delet = document.getElementsByClassName("report-1");
            const dele = document.getElementsByClassName("del-post");
            const delbtn = document.getElementsByClassName("delbtn");
            const delreport = document.getElementsByClassName("delbtn-1");
            Array.from(delbtn).forEach((bt, i) => {
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
            Array.from(delreport).forEach((but) => {
                but.addEventListener("click", (e) => {
                    delreport.forEach((rep) => {
                        rep.style.display = "none";
                    });

                    dele.style.display = "flex";
                });
            });
            // 경고창 끄기
            const dlecancle = document.getElementsByClassName("del-12")[1];
            dlecancle.addEventListener("click", (e) => {
                dele.style.display = "none";
            });
            // 댓글창 닫기
            const replytext = document.querySelectorAll(".replytext");
            const down = document.getElementById("down");
            const change = document.querySelector(".change");
            down.addEventListener("click", (e) => {
                Array.from(replytext).forEach((text) => {
                    if (text.value !== "") {
                        change.style.display = "flex";
                    }
                });
                if (change.style.display !== "flex") {
                    modal.style.display = "none";
                }
            });
        });
    });


    const changeCancle = document.querySelector(".keep-write");
    const changeExit = document.querySelector(".exit-wrtie");
    changeCancle.addEventListener("click", (e) => {
        change.style.display = "none";
    });
    changeExit.addEventListener("click", (e) => {
        Array.from(replytext).forEach((text) => {
            text.value = "";
        });
        change.style.display = "none";
        modal.style.display = "none";
    });
    // 좋아요
    const hearts = document.getElementsByClassName("heart");
    Array.from(hearts).forEach((heart, i) => {
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
    // 답글
    const comments = document.getElementsByClassName("comments");
    const commentbtn = document.getElementsByClassName("comment");
    Array.from(commentbtn).forEach((comment, i) => {
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
    const delComment = document.querySelector(".del-comment");
    Array.from(delbtn1).forEach((bt, i) => {
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
    // 답글 삭제 버튼 클릭시 경고창
    Array.from(delreport1).forEach((but) => {
        but.addEventListener("click", (e) => {
            delreport1.forEach((rep) => {
                rep.style.display = "none";
            });

            delComment.style.display = "flex";
        });
    });

    const commentCancle = document.querySelector(".del-comment-btn");
    const commentExit = document.querySelector(".del-comment-cancle-btn");
    commentExit.addEventListener("click", (e) => {
        delComment.style.display = "none";
    });
    commentCancle.addEventListener("click", (e) => {
        delComment.style.display = "none";
    });

    return request;
}

const showPostDetail=async (id)=>{
    const request=await myPageService.getPostDetail(id);
    myPageLayout.showPostDetail(request);

    //게시글 수정 팝업
    function popupFn() {
        const triggers = document.querySelectorAll(".popup-trigger");
        const popups = document.querySelectorAll(".popup-container");
        const dropdowns = document.querySelectorAll(".option-menu");

        if (!triggers) return;

        // 팝업 - from 안의 버튼 submit 막기
        document.addEventListener(
            "submit",
            (e) => {
                if (e.target.closest(".popup-container")) {
                    e.preventDefault();
                }
            },
            true
        );

        // 팝업 열기
        triggers.forEach((trigger) => {
            trigger.addEventListener("click", () => {
                const target = trigger.getAttribute("data-target");
                const popup = document.querySelector(target);

                // 드롭다운도 전부 닫기
                dropdowns.forEach((menu) => menu.classList.remove("active"));

                // 다른 팝업 닫기 (data-sticky가 붙어있는 팝업 제외)
                popups.forEach((pop) => {
                    if (!pop.hasAttribute("data-sticky")) {
                        pop.classList.remove("active");
                    }
                });

                // 현재(부모) 팝업 닫기 - 스티키면 유지
                const parentPopup = trigger.closest(".popup-container");
                if (parentPopup && !parentPopup.hasAttribute("data-sticky")) {
                    parentPopup.classList.remove("active");
                }
                console.log(popup);
                // 해당 팝업 열기
                if (popup) {
                    const text =
                        document.getElementsByClassName("popup-textarea")[0];
                    const image = document.getElementsByClassName(
                        "popup-preview-inner"
                    )[0];
                    popup.classList.add("active");
                    text.value = "테스트용 글 확인";
                    image.innerHTML = `<div class="file-9">
                                                <!-- 이미지들 -->
                                                <div class="file-10">
                                                    <img class="img" alt="첨부 이미지 1" draggable="false" loading="eager" width="0" height="0" decoding="async" data-nimg="1" class="bd_1px_solid bd-c_strokes.surface.standard bdr_8px" sizes="(max-width: 768px) 100vw, (max-width: 1200px) 50vw, 33vw" src="../../static/images/mypage/IMG_4127_2025_09_11T05_25_47_988Z.webp" style="color: transparent; height: 320px; object-fit: cover; width: auto;">
                                                </div>
                                                <div class="file-10">
                                                    <img class="img" alt="첨부 이미지 1" draggable="false" loading="eager" width="0" height="0" decoding="async" data-nimg="1" class="bd_1px_solid bd-c_strokes.surface.standard bdr_8px" sizes="(max-width: 768px) 100vw, (max-width: 1200px) 50vw, 33vw" src="../../static/images/mypage/2.webp" style="color: transparent; height: 320px; object-fit: cover; width: auto;">
                                                </div>
                                                <div class="file-10">
                                                    <img class="img" alt="첨부 이미지 1" draggable="false" loading="eager" width="0" height="0" decoding="async" data-nimg="1" class="bd_1px_solid bd-c_strokes.surface.standard bdr_8px" sizes="(max-width: 768px) 100vw, (max-width: 1200px) 50vw, 33vw" src="../../static/images/mypage/3.webp" style="color: transparent; height: 320px; object-fit: cover; width: auto;">
                                                </div>
                                            </div>`;
                }
                console.log(popup);
            });
        });

        // 팝업 닫기 버튼
        const popupRemoves = document.querySelectorAll(".popup-close");
        popupRemoves.forEach((closeBtn) => {
            closeBtn.addEventListener("click", () => {
                const popup = closeBtn.closest(".popup-container");
                if (popup) {
                    popup.classList.remove("active");
                }
            });
        });

        // 바깥 클릭 시 닫기
        // document.addEventListener("click", (e) => {
        //     document
        //         .querySelectorAll(".popup-container.active")
        //         .forEach((popup) => {
        //             if (
        //                 !e.target.closest(".popup-inner") &&
        //                 !e.target.closest(".popup-trigger")
        //             ) {
        //                 popup.classList.remove("active");
        //             }
        //         });
        // });
    }
    popupFn();
}

const showCommentsList=async (postId)=>{
    const request=await myPageService.getComments(postId);
    myPageLayout.showCommentsList(request);

    return request;
}

const showInternRequest=async ()=>{
    const request=await myPageService.getInternList();
    myPageLayout.showInternRequest(request);

    // 지원 취소
    const btn = document.querySelectorAll(".retract-triger");
    const retract = document.querySelector(".intern-cancle");
    const cancleReq=document.querySelector(".intern-cancle-10");
    const retractClose = document.querySelector(".intern-cancle-12");

    let reqId;

    if(btn){
        btn.forEach((bt)=>{
            bt.addEventListener("click", (e) => {
                retract.style.display = "flex";
                reqId=bt.dataset.reqid;
                // console.log(reqId);
                cancleReq.addEventListener("click", ()=>{
                // 지원 취소 fetch
                    retract.style.display = "none";
                    myPageService.deleteRequestIntern(reqId);
                    showInternRequest();
                })
                retractClose.addEventListener("click", (e) => {
                    retract.style.display = "none";
                });
            });
        });
    }
    return request;
}

const showPaymentList=async ()=>{
    const request=await myPageService.getPaymentList();
    myPageLayout.showPaymentList(request);

    return request;
}

const showSavedIntList=async ()=>{
    const request=await myPageService.getSavedInternList();
    await myPageLayout.showSavedIntList(request);

    const expBtn=document.querySelector(".exp-btn");
    const intBtn=document.querySelector(".intern-btn");
    const experience = document.getElementById("experience-list");
    const employ = document.getElementById("intern-list");

    expBtn.addEventListener("click", ()=>{
        intBtn.classList.remove("active");
        expBtn.classList.add("active");
        employ.style.display = "none";
        experience.style.display = "contents";
    });

    return request;
}

const showSavedExpList=async ()=>{
    const request=await myPageService.getSavedExperienceList();
    await myPageLayout.showSavedExpList(request);

    const expBtn=document.querySelector(".exp-btn");
    const intBtn=document.querySelector(".intern-btn");
    const experience = document.getElementById("experience-list");
    const employ = document.getElementById("intern-list");

    intBtn.addEventListener("click", ()=>{
        expBtn.classList.remove("active");
        intBtn.classList.add("active");
        experience.style.display = "none";
        employ.style.display = "contents";
    });

    return request;
}

const showExperienceRequest=async ()=>{
    const request=await myPageService.getExperienceList();
    myPageLayout.showExperienceRequest(request);

    // 지원 취소
    const btn = document.querySelectorAll(".retract-triger");
    const retract = document.querySelector(".retract");
    const cancleReq=document.querySelector(".retract-10");
    const retractClose = document.querySelector(".retract-12");

    let reqId;

    if(btn){
        btn.forEach((bt)=>{
            bt.addEventListener("click", (e) => {
                retract.style.display = "flex";
                reqId=bt.dataset.reqid;
                console.log(reqId);
                cancleReq.addEventListener("click", ()=>{
                // 지원 취소 fetch
                    retract.style.display = "none";
                    myPageService.deleteRequestExperience(reqId);
                    showExperienceRequest();
                })
                retractClose.addEventListener("click", (e) => {
                    retract.style.display = "none";
                });
            });
        });
    }
    console.log(reqId);

    return request;
}

document.addEventListener("DOMContentLoaded", showPosts);
document.addEventListener("DOMContentLoaded", showExperienceRequest);
document.addEventListener("DOMContentLoaded", showInternRequest);
document.addEventListener("DOMContentLoaded", showPaymentList);
document.addEventListener("DOMContentLoaded", showSavedIntList);
document.addEventListener("DOMContentLoaded", showSavedExpList);