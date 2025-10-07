// const advertisementService = (() => {
//     const updateInternStatus = async (noticeId, statusValue) => {
//         const data = { id: noticeId, internNoticeStatus: statusValue };
//
//         const response = await fetch(`/api/enterprise-console/intern/${noticeId}/status`, {
//             method:"PUT",
//             headers: {
//                 "Content-Type": "application/json"
//             },
//             body: JSON.stringify(data)
//         });
//
//         console.log("보내는 데이터",data)
//
//         if(response.ok) {
//             console.log("상태변경 성공")
//         }else if(response.status === 404){
//             console.log("상태변경 실패")
//         }else {
//             const error = await response.text()
//             console.log(error);
//         }
//
//         return data;
//     }
//
//     return {updateInternStatus:updateInternStatus}
// })();
//
//
