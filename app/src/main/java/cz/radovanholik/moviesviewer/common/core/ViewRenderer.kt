package cz.radovanholik.moviesviewer.common.core

interface ViewRenderer<STATE, COMMAND> {

    /**
     * Reflects the state changes.
     */
    fun renderUI(state: STATE)

    /**
     * Reflects one command events.
     */
    fun onCommand(command: COMMAND)
}