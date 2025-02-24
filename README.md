# javadoro

A simple command-line application that implements the Pomodoro Technique for time management. This project was created as my first Java app to help me learn the language by building a useful tool that alternates between work sessions and breaks.

## Overview

The Pomodoro Timer in Java is a console-based application that helps you manage your time using the Pomodoro Technique. The technique involves breaking work into intervals (typically 25 minutes) separated by short breaks (typically 5 minutes). This application automatically counts down each session and transitions between work and break periods.

## Features

- **Work and Break Sessions:**  
  Automatically alternates between a work session and a break session.
  
- **Scheduled Countdown:**  
  Uses Java's concurrency utilities to schedule tasks that update the timer every second.
  
- **Console Output:**  
  Displays real-time countdown information in the command line.

## How It Works

- **Scheduling Tasks:**  
  The app leverages the `ScheduledExecutorService` from the `java.util.concurrent` package to run tasks periodically. A single-threaded scheduler ensures that tasks run sequentially, which avoids common concurrency issues.

- **Countdown Implementation:**  
  A lambda expression is used inside `scheduleAtFixedRate` to update the remaining time every second. A one-element array is used as a mutable container for the countdown value, working around the requirement that variables referenced inside a lambda must be effectively final.

- **Session Transition:**  
  Once a session (work or break) ends, a scheduled task cancels the countdown and transitions to the next session based on a simple conditional check.

## Java Functionality Demonstrated

1. **Concurrency and Scheduling:**  
   - Uses `ScheduledExecutorService` to schedule periodic and delayed tasks.
   - Demonstrates safe task cancellation with `ScheduledFuture`.

2. **Lambda Expressions:**  
   - Utilizes concise lambda syntax (introduced in Java 8) to define inline tasks.

3. **Object-Oriented Programming:**  
   - Encapsulates timer functionality in the `PomodoroTimer` class.
   - Uses private fields, a constructor for initialization, and methods to structure the code.

4. **Primitive Types and Immutability:**  
   - Uses primitive data types (e.g., `int`) for time calculations.
   - Employs the `final` keyword to enforce immutability where applicable.

5. **String Handling and Conditional Logic:**  
   - Uses the `equals` method for proper string comparison (instead of `==`).

6. **Console I/O:**  
   - Uses `System.out.println` to interact with the user by printing messages to the console.

## Installation & Running the Application

### Prerequisites

- **Java Development Kit (JDK) 8 or later:**  
  Make sure you have the JDK installed on your system. You can download it from Oracle’s website or use OpenJDK.

### Steps to Compile and Run

1. **Download the Code:**  
   Save the code into a file named `PomodoroTimer.java`.

2. **Compile the Code:**  
   Open a terminal (or Command Prompt on Windows) and navigate to the directory containing the file. Run the following command to compile:
   ```bash
   javac PomodoroTimer.java
   ```
   This command compiles the Java source file and creates a `PomodoroTimer.class` file.

3. **Run the Application:**  
   In the same terminal, execute:
   ```bash
   java PomodoroTimer
   ```
   The application will start, and you will see the countdown and session transition messages printed in the console.

## Usage

- The application starts with a work session (default 25 minutes) and prints the remaining time to the console.
- When the work session ends, it automatically switches to a break session (default 5 minutes).
- You can modify the work and break durations by changing the parameters in the `main` method.

## License

This project is open source. Feel free to modify and use it for your own learning purposes.