# FirstProject

This is a simple project for practicing modern Android development and is currently a work in progress.

The goal of the project is to create a TODO list interface where the user can add, edit or delete items. The data is managed using the Room database and the ViewModel architecture. The items can be daily or weekly tasks and can be swithed into a 'done' state once completed. At the start of a  new day or week, the state resets to 'not done'. If we are not done with the task yet, a circular progress bar indicates how much time we have left to finish the task. The idea is to provide user enabled notifications as the due date approaches. We could have any type of tasks: recurrent and one time only tasks, and their recurring period could be of any length. Howeverm this feature is not yet implemented. The user interface needs revision, as well.
