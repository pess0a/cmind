package comt.cmindproject.presentation.source

import comt.cmindproject.model.Source
import comt.cmindproject.presentation.base.View

interface SourceView : View {
    fun loadSourceList(listSource: List<Source>)
    fun errorOnLoadSource()
}