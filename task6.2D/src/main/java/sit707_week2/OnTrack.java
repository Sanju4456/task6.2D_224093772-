package sit707_week2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OnTrack {

    public static class Task {
        int taskId;
        String taskTitle;
        String submissionDate;
        String status;

        public Task(int taskId, String taskTitle, String submissionDate, String status) {
            this.taskId = taskId;
            this.taskTitle = taskTitle;
            this.submissionDate = submissionDate;
            this.status = status;
        }

        // Getters and setters (if necessary)
    }

    public List<Task> getTaskInbox(int studentId) {
        if (studentId <= 0) {
            throw new IllegalArgumentException("Invalid student ID.");
        }
        // Simulating database retrieval with a static list for demonstration
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1, "Math Assignment", "2024-05-20", "Submitted"));
        tasks.add(new Task(2, "Science Project", "2024-05-22", "Reviewed"));
        return tasks;
    }

    public Map<String, Object> viewInboxTask(int studentId, int taskId) {
        if (studentId <= 0) {
            throw new IllegalArgumentException("Invalid student ID.");
        }
        // Simulating database retrieval
        Map<Integer, Map<String, Object>> taskDetails = new HashMap<>();
        Map<String, Object> task1Details = new HashMap<>();
        task1Details.put("feedback", "Good work, but improve calculations.");
        task1Details.put("chat", List.of("Tutor: Check your calculations.", "Student: Sure, I will redo."));
        taskDetails.put(1, task1Details);

        Map<String, Object> task2Details = new HashMap<>();
        task2Details.put("feedback", "Excellent project.");
        task2Details.put("chat", List.of("Tutor: Well done!", "Student: Thank you!"));
        taskDetails.put(2, task2Details);

        if (!taskDetails.containsKey(taskId)) {
            throw new IllegalArgumentException("Invalid task ID.");
        }

        return taskDetails.get(taskId);
    }

    public Map<String, Object> submitTask(int studentId, String taskTitle, String taskContent) {
        if (studentId <= 0) {
            throw new IllegalArgumentException("Invalid student ID.");
        }
        if (taskTitle == null || taskTitle.isEmpty()) {
            throw new IllegalArgumentException("Task title is required.");
        }
        if (taskContent == null || taskContent.isEmpty()) {
            throw new IllegalArgumentException("Task content is required.");
        }

        // Simulate task submission
        int newTaskId = 3;  // Assume taskId is auto-incremented in the database
        Map<String, Object> result = new HashMap<>();
        result.put("taskId", newTaskId);
        result.put("status", "Submitted");
        return result;
    }

    public List<String> chatWithTutor(int studentId, int tutorId, String message) {
        if (studentId <= 0 || tutorId <= 0) {
            throw new IllegalArgumentException("Invalid student or tutor ID.");
        }
        if (message == null || message.isEmpty()) {
            throw new IllegalArgumentException("Message content is required.");
        }

        // Simulating chat storage
        Map<Integer, List<String>> chatHistory = new HashMap<>();
        chatHistory.putIfAbsent(studentId, new ArrayList<>());
        chatHistory.get(studentId).add("Student: " + message);
        return chatHistory.get(studentId);
    }
}
