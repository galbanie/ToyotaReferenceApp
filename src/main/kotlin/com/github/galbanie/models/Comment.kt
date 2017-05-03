package com.github.galbanie.models

import tornadofx.*

/**
 * Created by Galbanie on 2017-04-26.
 */

class Comment(id : Int, comment : String){
    var id by property(id)
    fun idProperty() = getProperty(Comment::id)

    var comment by property(comment)
    fun commentProperty() = getProperty(Comment::comment)
}

class CommentModel : ItemViewModel<Comment>() {
    val id = bind { item?.idProperty() }
    val comment = bind { item?.commentProperty() }
}
