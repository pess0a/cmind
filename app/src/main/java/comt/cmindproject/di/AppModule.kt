package comt.cmindproject.di

import comt.cmindproject.infrastructure.BackendIntegrator
import comt.cmindproject.presentation.source.SourcePresenter
import comt.cmindproject.repository.NewsRepository
import comt.cmindproject.repository.api.NewsAPI
import org.koin.dsl.module.module

private val newsAPI: NewsAPI = BackendIntegrator.getNewsAPI()

val newsModule = module {
    factory { NewsRepository(get()) }
    factory { SourcePresenter(get()) }
}

val networkModule = module {
    single { newsAPI }
}