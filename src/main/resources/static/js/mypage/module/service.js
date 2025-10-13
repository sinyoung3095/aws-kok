const myPageService = (() => {
    // 게시글 목록
    const getPostsList=async (page=1, keyword="")=>{
        const list=await fetch(`/api/mypage/post-list`);
        const response=await list.json();
        // console.log(response);

        return response;
    }
    // 게시글 상세 불러오기
    const getPostDetail=async (id)=>{
        const postDetail=await fetch(`/api/community/post/${id}`);
        const response=await postDetail.json();
        
        return response;
    }
    // 댓글 목록
    const getComments = async (postId) => {
        const response = await fetch(`/api/comments/${postId}`);
        return response.json();
    };

    // 댓글 작성
    const writeComment = async (commentDTO) => {
        const response = await fetch(`/api/comments`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(commentDTO),
        });
        return response.json();
    };

    // 댓글 수정
    const updateComment = async (commentId, content) => {
        const response = await fetch(`/api/comments/${commentId}`, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ commentContent: content }),
        });
        return response.ok;
    };

    // 댓글 삭제
    const deleteComment = async (commentId) => {
        const response = await fetch(`/api/comments/${commentId}`, {
            method: "DELETE",
        });
        return response.ok;
    };

    // 대댓글 목록
    const getReplies = async (commentId) => {
        const response = await fetch(`/api/replies/${commentId}`);
        return response.json();
    };

    // 대댓글 작성
    const writeReply = async (replyDTO) => {
        const response = await fetch(`/api/replies`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(replyDTO),
        });
        return response.json();
    };

    // 대댓글 수정
    const updateReply = async (replyId, content) => {
        const response = await fetch(`/api/replies/${replyId}`, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ replyContent: content }),
        });
        return response.ok;
    };

    // 대댓글 삭제
    const deleteReply = async (replyId) => {
        const response = await fetch(`/api/replies/${replyId}`, {
            method: "DELETE",
        });
        return response.ok;
    };
    
    // 체험 목록 불러오기
    const getExperienceList = async (page = 1, keyword = "") => {
        const request = await fetch (`/api/mypage/request-list`);
        const response = await request.json();

        return response;
    };
    return { getPostsList:getPostsList, getPostDetail:getPostDetail, getExperienceList:getExperienceList, getComments:getComments, writeComment:writeComment, updateComment:updateComment, deleteComment:deleteComment,
    getReplies:getReplies, writeReply:writeReply, updateReply:updateReply, deleteReply:deleteReply };
})();
