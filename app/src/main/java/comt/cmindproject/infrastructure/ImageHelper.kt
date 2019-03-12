package comt.cmindproject.infrastructure

import android.widget.ImageView
import com.squareup.picasso.Picasso

class ImageHelper {
    companion object {
        fun loadImage(imageUrl: String, placeHolderResourceId: Int, imageView: ImageView) = Picasso.get()
            .load(imageUrl)
            .placeholder(placeHolderResourceId)
            .into(imageView)
    }
}



