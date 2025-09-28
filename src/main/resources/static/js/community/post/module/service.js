const postService = (() => {
    const getPost = async (callback, page=1) => {
        const response = await fetch(`/api/community/${page}`);
        const postsCriteria = await response.json();
        if(callback){
            setTimeout(() => {
                callback(postsCriteria);
            }, 500)
        }

        if(response.ok) {
            console.log("게시글 존재")
        }else if(response.status === 404){
            console.log("게시글 없음")
        }else {
            const error = await response.text()
            console.log(error);
        }

        return postsCriteria;
    }

    return {getPost: getPost}
})();