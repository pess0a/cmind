package comt.cmindproject.presentation.source

import comt.cmindproject.model.Source
import comt.cmindproject.presentation.base.BaseView

interface SourceView : BaseView {
    fun loadSourceList(listSource : List<Source>)
    fun errorOnLoadSource()
}