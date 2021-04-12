package cz.radovanholik.model

sealed class Result<out T: Any> {

    data class Running<out T: Any>(val data: T? = null) : Result<T>()

    data class Success<out T: Any>(val data: T) : Result<T>()

    data class Error<out T: Any>(val error: ErrorResult, val data: T? = null) : Result<T>()

    override fun toString(): String {
        return when (this) {
            is Success -> "Success[data=$data]"
            is Error -> "Error[exception=${error.throwable}"
            is Running -> "Running[cachedData=$data]"
        }
    }

    fun isFinished() = this is Success || this is Error

    fun isRunning() = this is Running

    fun isSuccess() = this is Success

    fun isError() = this is Error

    /**
     * Returns the encapsulated value if this instance represents [Success] or cached data when available in [Running] state
     */
    fun getOrNull() = when {
        this is Success -> data
        this is Running -> data
        this is Error -> data
        else -> null
    }

    /**
     * Returns the encapsulated error if this instance represents [Error]
     */
    fun errorOrNull() = when {
        this is Error -> error
        else -> null
    }

    /**
     * Returns [Result] of same type ([Result.Success], [Result.Error] or [Result.Running]) but with different "data" type.
     * Original data are transformed by passed [dataTransform] function.
     *
     * **Example**
     * ```
     * suspend fun getLocation():Result<Location> =
     *     getLocationUseCase().map { latLng ->
     *         Location(
     *              latitude = latLng.latitude
     *              longitude = latLng.longitude
     *         )
     *     }
     * ```
     * @param dataTransform Function that transforms data from one type to another
     * @param R Target "data" type
     *
     * @return Result of same type but with different data type.
     */
    inline fun <R : Any> map(dataTransform: (T) -> R): Result<R> = when (this) {
        is Success -> Success(dataTransform(data))
        is Error -> Error(this.error, data?.let { dataTransform(it) })
        is Running -> Running(data?.let { dataTransform(it) })
    }
}

open class ErrorResult(open var message: String? = null, open var throwable: Throwable? = null)
