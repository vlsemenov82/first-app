fun main() {

    val likes = Likes(0, true, true, true);
    val repost = Reposts(0, true);
    val geo = Geo("Страна", "0.0.0.0", "Россия")

    val post1 = Post(
        0, 0, 0, 0, 1685464427, "Hello, Netology", 0, 0,
        "Photo", true, true, true, true, "BBC news", 0, "post",
        2, original = null, true, true, true, 1, comments = null, likes, repost, geo
    )

    val post2 = Post(
        1000, 0, 0, 0, 1685464427, "Hello, Netology", 0, 0,
        "Photo", true, true, true, true, "BBC news", 0, "post",
        2, original = null, true, true, true, 1, comments = null, likes, repost, geo
    )

    println(WallService.addPost(post1))
    println(WallService.addPost(post2))

    val post3 = Post(
        2, 0, 0, 0, 1685464427, "Test", 0, 0,
        "Video", true, true, true, true, "BBC news", 0, "post",
        2, original = null, true, true, true, 1, comments = null, likes, repost, geo
    )

    println(WallService.update(post3))

    val commentOne = Comment(1, 1, 168546441, "Good Post")

    println(WallService.creatComment(1, commentOne))
    //println(WallService.creatComment(3, commentOne))

    val reportComment = ReportComments(1,1, 9)
    println(WallService.addReportComment(1, reportComment))
}