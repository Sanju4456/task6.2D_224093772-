package sit707_week2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThrows;

import java.util.List;
import java.util.Map;

import org.junit.Test;

public class OnTrackTest {

    @Test
    public void testGetTaskInbox() {
        OnTrack onTrack = new OnTrack();
        System.out.println("Running testGetTaskInbox...");
        
        // Right: Correct retrieval of task list
        List<OnTrack.Task> tasks = onTrack.getTaskInbox(1);
        System.out.println("Tasks retrieved: " + tasks.size());
        assertEquals(2, tasks.size());

        // Boundary: Invalid student ID
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            onTrack.getTaskInbox(-1);
        });
        System.out.println("Exception message: " + exception.getMessage());
        assertEquals("Invalid student ID.", exception.getMessage());
    }

    @Test
    public void testViewInboxTask() {
        OnTrack onTrack = new OnTrack();
        System.out.println("Running testViewInboxTask...");
        
        // Right: Correct retrieval of task details
        Map<String, Object> taskDetails = onTrack.viewInboxTask(1, 1);
        System.out.println("Task feedback: " + taskDetails.get("feedback"));
        assertEquals("Good work, but improve calculations.", taskDetails.get("feedback"));
        
        // Boundary: Invalid task ID
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            onTrack.viewInboxTask(1, 999);
        });
        System.out.println("Exception message: " + exception.getMessage());
        assertEquals("Invalid task ID.", exception.getMessage());

        // Boundary: Invalid student ID
        Exception studentException = assertThrows(IllegalArgumentException.class, () -> {
            onTrack.viewInboxTask(-1, 1);
        });
        System.out.println("Exception message: " + studentException.getMessage());
        assertEquals("Invalid student ID.", studentException.getMessage());
    }

    @Test
    public void testSubmitTask() {
        OnTrack onTrack = new OnTrack();
        System.out.println("Running testSubmitTask...");
        
        // Right: Successful task submission
        Map<String, Object> result = onTrack.submitTask(1, "History Essay", "Content of essay");
        System.out.println("Submission status: " + result.get("status"));
        assertEquals("Submitted", result.get("status"));
        System.out.println("Task ID: " + result.get("taskId"));
        assertNotNull(result.get("taskId"));
        
        // Boundary: Missing task title
        Exception titleException = assertThrows(IllegalArgumentException.class, () -> {
            onTrack.submitTask(1, "", "Content of essay");
        });
        System.out.println("Exception message: " + titleException.getMessage());
        assertEquals("Task title is required.", titleException.getMessage());

        // Boundary: Missing task content
        Exception contentException = assertThrows(IllegalArgumentException.class, () -> {
            onTrack.submitTask(1, "History Essay", "");
        });
        System.out.println("Exception message: " + contentException.getMessage());
        assertEquals("Task content is required.", contentException.getMessage());

        // Boundary: Invalid student ID
        Exception studentException = assertThrows(IllegalArgumentException.class, () -> {
            onTrack.submitTask(-1, "History Essay", "Content of essay");
        });
        System.out.println("Exception message: " + studentException.getMessage());
        assertEquals("Invalid student ID.", studentException.getMessage());
    }

    @Test
    public void testChatWithTutor() {
        OnTrack onTrack = new OnTrack();
        System.out.println("Running testChatWithTutor...");
        
        // Right: Message added to chat history
        List<String> chatHistory = onTrack.chatWithTutor(1, 1, "Can you help me with this?");
        System.out.println("Chat history: " + chatHistory);
        assertTrue(chatHistory.contains("Student: Can you help me with this?"));

        // Boundary: Missing message content
        Exception messageException = assertThrows(IllegalArgumentException.class, () -> {
            onTrack.chatWithTutor(1, 1, "");
        });
        System.out.println("Exception message: " + messageException.getMessage());
        assertEquals("Message content is required.", messageException.getMessage());

        // Boundary: Invalid student ID
        Exception studentException = assertThrows(IllegalArgumentException.class, () -> {
            onTrack.chatWithTutor(-1, 1, "Can you help me with this?");
        });
        System.out.println("Exception message: " + studentException.getMessage());
        assertEquals("Invalid student or tutor ID.", studentException.getMessage());

        // Boundary: Invalid tutor ID
        Exception tutorException = assertThrows(IllegalArgumentException.class, () -> {
            onTrack.chatWithTutor(1, -1, "Can you help me with this?");
        });
        System.out.println("Exception message: " + tutorException.getMessage());
        assertEquals("Invalid student or tutor ID.", tutorException.getMessage());
    }
}
