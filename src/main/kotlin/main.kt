//    Задача 1
fun main() {
    val commentOne = Post.Comments()
    println(commentOne.count)

    val postOne = Post(0, 26, 15, 8, 1685464424, "Hello, World", "Post", true, true, true, commentOne)
    println(postOne.text)

    val postTwo = Post(0, 26, 15, 8, 1685464425, "Hello, Kotlin", "Post", true, true, true, commentOne)
    println(postTwo.text)

    val postThree = Post(2, 26, 15, 8, 1685464426, "Hello, Earth", "Post", true, true, true, commentOne)

    WallService.add(postOne)
    println(postOne.id)

    WallService.add(postTwo)
    println(postTwo.id)
    println(postTwo.text)

    println(WallService.update(postThree))






}