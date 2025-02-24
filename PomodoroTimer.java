import java.util.concurrent.*;

// The PomodoroTimer class defines our timer functionality.
// In Java, all code must reside inside a class.
public class PomodoroTimer {

    // The durations are stored in seconds for simplicity.
    // 'private' means these variables are only accessible within this class.
    // 'final' indicates that these values, once set, cannot change (similar to C#'s readonly).
    private final int workDuration;
    private final int breakDuration;
    private final ScheduledExecutorService scheduler;

    // Constructor: This special method is called when a new instance of PomodoroTimer is created.
    // The parameters workMinutes and breakMinutes are provided in minutes.
    // The 'this' keyword distinguishes the class fields from the constructor parameters.
    public PomodoroTimer(int workMinutes, int breakMinutes) {
        // Convert minutes to seconds for easier timing management.
        this.workDuration = workMinutes * 60;
        this.breakDuration = breakMinutes * 60;
        // Initialize the scheduler with a single thread.
        // This scheduler manages the execution of our timer tasks.
        this.scheduler = Executors.newSingleThreadScheduledExecutor();
    }

    // startPomodoro() is a public method that begins the timer cycle.
    // It prints a message to the console and starts a work session.
    public void startPomodoro() {
        System.out.println("Starting Pomodoro session...");
        runSession(workDuration, "Work");
    }

    // runSession() handles both work and break sessions.
    // It takes two parameters:
    //    duration - the length of the session in seconds.
    //    sessionType - a String indicating whether this is a "Work" or "Break" session.
    private void runSession(int duration, String sessionType) {
        // Java lambda expressions require variables used inside to be final or effectively final.
        // We use a one-element array to allow mutability of the countdown value.
        final int[] remainingSeconds = {duration};

        // Schedule a task to run at a fixed rate (every second) to update the countdown.
        ScheduledFuture<?> countdown = scheduler.scheduleAtFixedRate(() -> {
            // Check if there is time remaining in the session.
            if (remainingSeconds[0] > 0) {
                System.out.println(sessionType + " Time Left: " + remainingSeconds[0] + " seconds");
                remainingSeconds[0]--; // Decrement the countdown.
            } else {
                // When the countdown reaches zero, print that the session is complete.
                System.out.println(sessionType + " session complete.");
            }
        }, 0, 1, TimeUnit.SECONDS);  // No initial delay, run every 1 second.

        // Schedule a one-time task to execute after the full duration of the session.
        scheduler.schedule(() -> {
            // Cancel the countdown task. The 'false' argument indicates that
            // if the task is currently running, it will not be interrupted abruptly.
            countdown.cancel(false);

            // Depending on the current session type, either start a break or end the cycle.
            if (sessionType.equals("Work")) {
                System.out.println("Time for a break!");
                // Start a break session by calling runSession with breakDuration.
                runSession(breakDuration, "Break");
            } else {
                System.out.println("Break finished! Ready for a new Pomodoro session.");
                // Optionally, restart the Pomodoro session.
                // startPomodoro();
            }
        }, duration, TimeUnit.SECONDS);  // This task executes after the full session duration.
    }

    // The main method is the entry point of the program.
    // This is where the Java runtime starts executing your code.
    public static void main(String[] args) {
        // Create a new instance of PomodoroTimer with 25 minutes of work and 5 minutes of break.
        PomodoroTimer timer = new PomodoroTimer(25, 5);
        // Start the timer.
        timer.startPomodoro();
    }
}
