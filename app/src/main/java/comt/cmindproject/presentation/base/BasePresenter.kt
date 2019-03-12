package comt.cmindproject.presentation.base

interface BasePresenter<T : BaseView> {
    fun subscribe(view: T)
    fun unSubscribe()
}