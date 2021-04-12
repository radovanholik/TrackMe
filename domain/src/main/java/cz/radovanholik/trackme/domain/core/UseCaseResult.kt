package cz.radovanholik.trackme.domain.core

/**
 * An abstract class for a UseCase (Interactor in terms of Clean Architecture).
 * This class represents an execution unit for different use cases (this means that any use case
 * within the application should extend this class).
 *
 * This [UseCaseResult] returns [T] wrapped by [Result]
 */
abstract class UseCaseResult<out T : Any, in Params> : UseCase<Result<T>, Params>()