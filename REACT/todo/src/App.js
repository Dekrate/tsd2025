import React, { useState } from 'react';

function TaskForm({ newTask, editingTask, onNewTaskChange, onAddTask, onCancelEdit }) {
  return (
    <div className="card p-4 mb-4 rounded-md shadow-sm">
      <h5 className="card-title mb-3">{editingTask ? 'Edit' : 'Add new'}</h5>
      <div className="mb-3">
        <label htmlFor="taskTitle" className="form-label">Title</label>
        <input
          type="text"
          className="form-control rounded-md"
          id="taskTitle"
          name="title"
          placeholder="Title"
          value={newTask.title}
          onChange={onNewTaskChange}
        />
      </div>
      <div className="mb-3">
        <label htmlFor="taskDescription" className="form-label">Description</label>
        <textarea
          className="form-control rounded-md"
          id="taskDescription"
          name="description"
          rows="3"
          placeholder="Description"
          value={newTask.description}
          onChange={onNewTaskChange}
        ></textarea>
      </div>
      <div className="d-flex justify-content-end">
        {editingTask ? (
          <>
            <button className="btn btn-primary rounded-md me-2" onClick={onAddTask}>Aktualizuj</button>
            <button className="btn btn-secondary rounded-md" onClick={onCancelEdit}>Anuluj Edycję</button>
          </>
        ) : (
          <button className="btn btn-success rounded-md w-fit" onClick={onAddTask}>Dodaj</button>
        )}
      </div>
    </div>
  );
}


function TaskList({ tasks, expandedTaskId, onToggleComplete, onToggleDescription, onEditClick, onDeleteTask }) {
  return (
    <div className="card p-4 rounded-md shadow-sm">
      <h5 className="card-title mb-3">Zadania:</h5>
      {tasks.length === 0 ? (
        <p className="text-center text-muted">Brak zadań. Dodaj jedno powyżej!</p>
      ) : (
        <ul className="list-group">
          {tasks.map(task => (
            <li
              key={task.id}
              className={`list-group-item rounded-md mb-2 shadow-sm ${task.completed ? 'list-group-item-success' : ''}`}
            >
              <div className="d-flex justify-content-between align-items-center">
                <div
                  className={`flex-grow-1 ${task.completed ? 'text-decoration-line-through text-muted' : ''}`}
                  onClick={() => onToggleComplete(task.id)}
                  style={{ cursor: 'pointer' }}
                >
                  {task.title}
                </div>
                {(task.description && task.description.trim() !== '') && (
                  <button
                    className="btn btn-light btn-sm rounded-circle shadow-sm"
                    style={{ width: '30px', height: '30px', display: 'flex', justifyContent: 'center', alignItems: 'center' }}
                    onClick={() => onToggleDescription(task.id)}
                  >
                    <i className={`bi bi-chevron-${expandedTaskId === task.id ? 'up' : 'down'}`}></i>
                  </button>
                )}
              </div>

              {expandedTaskId === task.id && (
                <div className="mt-2">
                  {task.description && task.description.trim() !== '' && (
                    <p className="text-muted mb-2">{task.description}</p>
                  )}
                  <button
                    className="btn btn-primary btn-sm rounded-md me-2"
                    onClick={() => onEditClick(task)}
                  >
                    Edytuj
                  </button>
                  <button
                    className="btn btn-danger btn-sm rounded-md"
                    onClick={() => onDeleteTask(task.id)}
                  >
                    Usuń
                  </button>
                </div>
              )}
            </li>
          ))}
        </ul>
      )}
    </div>
  );
}


function App() {
  const [newTask, setNewTask] = useState({
    title: "",
    description: "",
  });

  const [tasks, setTasks] = useState([]);
  const [expandedTaskId, setExpandedTaskId] = useState(null);
  const [editingTask, setEditingTask] = useState(null);

  const handleNewTaskChange = (e) => {
    const { name, value } = e.target;
    setNewTask(prevNewTask => ({
      ...prevNewTask,
      [name]: value,
    }));
  };

  const handleAddTask = () => {
    if (newTask.title.trim() === "") {
      console.log("Title can't be null!");
      return;
    }

    if (editingTask) {
      setTasks(tasks.map(task =>
        task.id === editingTask.id
          ? { ...task, title: newTask.title, description: newTask.description }
          : task
      ));
      setEditingTask(null);
    } else {
      const taskToAdd = {
        id: Date.now(),
        title: newTask.title,
        description: newTask.description,
        completed: false,
      };
      setTasks([...tasks, taskToAdd]);
    }
    setNewTask({ title: "", description: "" });
  };

  const handleDeleteTask = (id) => {
    setTasks(tasks.filter(task => task.id !== id));
    if (expandedTaskId === id) {
      setExpandedTaskId(null);
    }
    if (editingTask && editingTask.id === id) {
      setEditingTask(null);
      setNewTask({ title: "", description: "" });
    }
  };

  const handleToggleComplete = (id) => {
    setTasks(tasks.map(task =>
      task.id === id ? { ...task, completed: !task.completed } : task
    ));
  };

  const handleToggleDescription = (id) => {
    setExpandedTaskId(expandedTaskId === id ? null : id);
  };

  const handleEditClick = (task) => {
    setEditingTask(task);
    setNewTask({ title: task.title, description: task.description });
  };

  const handleCancelEdit = () => {
    setEditingTask(null);
    setNewTask({ title: "", description: "" });
  };

  return (
    <div className="App container py-5" style={{ fontFamily: 'Inter, sans-serif' }}>
      <h1 className="text-center mb-4 rounded-md">ToDo App</h1>

      <TaskForm
        newTask={newTask}
        editingTask={editingTask}
        onNewTaskChange={handleNewTaskChange}
        onAddTask={handleAddTask}
        onCancelEdit={handleCancelEdit}
      />

      <TaskList
        tasks={tasks}
        expandedTaskId={expandedTaskId}
        onToggleComplete={handleToggleComplete}
        onToggleDescription={handleToggleDescription}
        onEditClick={handleEditClick}
        onDeleteTask={handleDeleteTask}
      />
    </div>
  );
}

export default App;
