package cz.radovanholik.trackme.domain.core

/**
 * An abstract class for a UseCase (Interactor in terms of Clean Architecture).
 * This class represents an execution unit for different use cases (this means that any use case
 * within the application should extend this class).
 */
abstract class UseCase<out T, in Params> {

    /**
     * Executes appropriate implementation of [UseCase]
     * @param params Set of input parameters.
     * @return type [T] of parameter. In the most common way the [T] is wrapped to a special
     * use case implementation
     */
    suspend operator fun invoke(params: Params): T = doWork(params)

    /**
     * Inner business logic of [UseCase]
     *
     * @param Set of input parameters
     * @return type [T] of parameter. In the most common way the [T] is wrapped to a special
     * use-case implementation.
     */
    protected abstract suspend fun doWork(params: Params): T
}