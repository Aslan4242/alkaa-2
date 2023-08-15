package com.escodro.domain.repository

import com.escodro.domain.model.Task
import kotlinx.coroutines.flow.Flow

/**
 * Interface to represent the implementation of Task repository.
 */
interface TaskRepository {

    /**
     * Inserts a new task.
     *
     * @param task task to be added
     */
    suspend fun insertTask(task: Task)

    /**
     * Updates a task.
     *
     * @param task task to be updated
     */
    suspend fun updateTask(task: Task)

    /**
     * Deletes a task.
     *
     * @param task task to be deleted
     */
    suspend fun deleteTask(task: Task)

    /**
     * Cleans the entire table.
     */
    suspend fun cleanTable()

    /**
     * Get all inserted tasks with due date.
     *
     * @return all inserted tasks with due date
     */
    suspend fun findAllTasksWithDueDate(): List<Task>

    /**
     * Get flow task by id.
     *
     * @param taskId task id
     *
     * @return flow of selected task
     */
    fun findTaskFlowById(taskId: Long): Flow<Task>

    /**
     * Get task by id.
     *
     * @param taskId task id
     *
     * @return selected task
     */
    suspend fun findTaskById(taskId: Long): Task
}
