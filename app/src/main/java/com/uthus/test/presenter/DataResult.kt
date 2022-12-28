package com.uthus.test.presenter

data class DataResult<out T>(val status: Status, val data: T?, val error: Throwable?) {
    enum class Status {
        LOADING,
        SUCCESS,
        ERROR
    }

    companion object {

        fun <T> loading(data: T?): DataResult<T> {
            return DataResult(status = Status.LOADING, data = data, error = null)
        }

        fun <T> success(data: T): DataResult<T> {
            return DataResult(status = Status.SUCCESS, data = data, error = null)
        }

        fun <T> error(error: Throwable): DataResult<T> {
            return DataResult(status = Status.ERROR, data = null, error = error)
        }
    }
}