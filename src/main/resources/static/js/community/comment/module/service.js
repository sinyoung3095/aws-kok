const commentService = (() => {

    // 댓글 목록
    const getComments = async (postId) => {
        const response = await fetch(`/api/comments/${postId}`);
        if (!response.ok) throw new Error("댓글을 불러올 수 없습니다.");
        return await response.json();
    };

    // 댓글 작성
    const writeComment = async (commentDTO) => {
        const response = await fetch(`/api/comments`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(commentDTO),
        });
        if (!response.ok) throw new Error("댓글 작성 실패");
        return await response.json();
    };

    // 댓글 좋아요
    const commentLike = async (commentId) => {
        const response = await fetch(`/api/comment-likes/${commentId}`, {
            method: "POST"
        });

        if(response.ok) {
            console.log("좋아요 성공");
            return true;
        } else{
            const errorMessage = await response.text();
            console.log(errorMessage)
            alert("이미 좋아요를 누른 댓글 입니다.");
            return false;
        }
    };

    // 댓글 좋아요 취소
    const commentUnlike = async (commentId) => {
        const response = await fetch(`/api/comment-likes/${commentId}`, {
            method: "DELETE"
        });

        return response.ok;
    };

    // 대댓글 목록
    const getReplies = async (commentId) => {
        const response = await fetch(`/api/replies/${commentId}`);
        if (!response.ok) throw new Error("대댓글을 불러올 수 없습니다.");
        return await response.json();
    };

    // 대댓글 작성
    const writeReply = async (replyDTO) => {
        const response = await fetch(`/api/replies`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(replyDTO),
        });
        if (!response.ok) throw new Error("대댓글 작성 실패");
        return await response.json();
    };

    return { getComments : getComments, writeComment : writeComment, commentLike : commentLike,
        commentUnlike : commentUnlike, getReplies : getReplies, writeReply : writeReply};

})();