package com.moaimar.ricknmortyapp.app.domain

sealed class ErrorApp {
    object DataLayerError : ErrorApp()
    object NoInternetError : ErrorApp()
    object TimeOutError : ErrorApp()
    object UnKnowError : ErrorApp()

    object NotFoundError : ErrorApp()
}