package comt.cmindproject.infrastructure

import android.widget.ImageView
import com.squareup.picasso.Picasso


object ImageHelper {
    fun loadImage(imageUrl: String, imageView: ImageView) {
        Picasso.get()
            .load(imageUrl)
            .into(imageView)
    }

}