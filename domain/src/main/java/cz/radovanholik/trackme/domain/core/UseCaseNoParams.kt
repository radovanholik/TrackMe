package cz.radovanholik.trackme.domain.core

/**
 * An UseCase class with not parameters.
 */
abstract class UseCaseNoParams<out T> : UseCase<T, Unit>() {

    /**
     * Executes appropriate implementation of [UseCaseNoParams]
     */
    suspend operator fun invoke(): T = super.invoke(Unit)
}