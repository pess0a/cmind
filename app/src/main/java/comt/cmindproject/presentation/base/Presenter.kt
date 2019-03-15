package comt.cmindproject.presentation.base

interface Presenter<V : View> {
    fun bind(view: V)
    fun unBind()
}