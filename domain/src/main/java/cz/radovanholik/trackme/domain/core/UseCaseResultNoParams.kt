package cz.radovanholik.trackme.domain.core

import cz.radovanholik.model.Result

/**
 * An UseCase class with not parameters.
 *
 * This [UseCaseResult] returns [T] wrapped by [Result]
 */
abstract class UseCaseResultNoParams<out T : Any> : UseCase<Result<T>, Unit>() {

    suspend operator fun invoke() = super.invoke(Unit)

}