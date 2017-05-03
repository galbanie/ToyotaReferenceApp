package com.github.galbanie.utils

import javafx.scene.image.Image

/**
 * Created by Galbanie on 2017-04-16.
 */

class ImageByChassis(){
    fun getImage(code : String) : Image {
        var res = ImageByChassis::class.java.getResourceAsStream("/image/$code.gif")
        if (res == null) {
            res = ImageByChassis::class.java.getResourceAsStream("/image/_comingsoon.gif")
        }
        return Image(res)
    }
}