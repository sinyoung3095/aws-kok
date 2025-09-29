const postService = (() => {
    // 게시글 전체 조회
    const getList = async (page = 1, callback) => {
        const response = await fetch(`/api/community/${page}`);
        const postsCriteria = await response.json();
        if (callback) callback(postsCriteria);
        return postsCriteria;
    };

    // 게시글 조회
    const getOne = async (id) => {
        const response = await fetch(`/api/community/post/${id}`);
        if (!response.ok) throw new Error("게시글을 불러올 수 없습니다.");
        return await response.json();
    };

    // 글쓰기
    const write = async (postContent, files = []) => {
        const formData = new FormData();
        formData.append("postContent", postContent);
        for (let i = 0; i < files.length; i++) {
            formData.append("files", files[i]);
        }

        const response = await fetch("/api/community", {
            method: "POST",
            body: formData
        });

        if (!response.ok) throw new Error(await response.text());
        return await response.json();
    };

    // 수정
    const update = async (postId, postContent, deleteFiles = [], files = []) => {
        const formData = new FormData();
        formData.append("postContent", postContent);
        deleteFiles.forEach(id => formData.append("deleteFiles", id));
        for (let i = 0; i < files.length; i++) {
            formData.append("files", files[i]);
        }

        const response = await fetch(`/api/community/${postId}`, {
            method: "PUT",
            body: formData
        });

        return response.ok;
    };

    // 삭제
    const remove = async (id) => {
        const response = await fetch(`/api/community/${id}`, { method: "DELETE" });
        return response.ok;
    };

    // 게시글 좋아요
    const postLike = async (postId) => {
        const postLikeDTO = {
            postId: postId
        };

        const response = await fetch("/api/likes", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(postLikeDTO)
        });

        if(response.ok) {
            console.log("좋아요 성공");
            return true;
        } else{
            const errorMessage = await response.text();
            console.log(errorMessage)
            alert("이미 좋아요를 누른 게시글 입니다.");
            return false;
        }
    };

    // 게시글 좋아요 취소
    const removeLike = async (postId) => {
        const response = await fetch(`/api/likes/${postId}`, {
            method: "DELETE"
        });
        return response.ok;
    };

    // 게시글 신고
    const reportPost = async (postId) => {
        const response = await fetch(`/api/report/${postId}`, {
            method: "POST"
        });

        if (!response.ok) {
            const errorMessage = await response.text();
            throw new Error(errorMessage);
        }
        return true;
    };

    return { getList : getList, getOne : getOne, write : write, update : update, remove : remove,
        postLike : postLike, removeLike : removeLike, reportPost : reportPost};

})();
