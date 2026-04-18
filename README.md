# TechPrep  — Modular Java Assessment Tool

A console-based interview readiness quiz for CS students and job seekers.
Covers **Java**, **Python**, and **C++** with 70+ curated questions.


Preparing for technical interviews can be overwhelming. I wanted to build something that wasn't just a random trivia game, but a targeted self-assessment tool. TechPrep is a console-based Java application designed to help CS students and job seekers test their knowledge in specific domains like Java, Python, and C++.

The Concept
The core idea was to move away from "one-size-fits-all" quizzes. Instead, the user chooses their battleground—whether that's Java's memory management, Python's data structures, or C++ pointers. It’s designed to mimic the pressure of a viva exam or a preliminary placement screening.

How I Built It (The Architecture)
I leaned heavily on Object-Oriented Programming (OOP) to ensure the app could grow. If I want to add a "Javascript" or "DBMS" module tomorrow, the architecture allows for that without rewriting the engine.

Smart Categorization: I used Java Enums to define categories. This makes the code type-safe and allows the quiz engine to filter questions dynamically based on what the user wants to study.

The Logic Engine: The Quiz class acts as the orchestrator. It doesn't just display questions; it handles the flow, tracks performance across different topics, and calculates the final "readiness" score.

Data Structure: I used the Collections Framework (specifically ArrayLists) to manage the question bank, allowing for easy shuffling and expansion.

User Experience
The interface is kept clean and distraction-free in the console. Here’s a quick look at how a session typically goes:

Select Domain: The user picks their language (e.g., Java).

Focus Session: The app pulls only Java-specific questions covering JVM, Collections, and Exception Handling.

Real-time Feedback: Immediate "Correct/Incorrect" responses to reinforce learning.

Performance Summary: A final score that helps the user identify if they are "Interview Ready" or need more practice.


Example Console Output:

```
Starting Java Quiz...

Q1: Which component is responsible for converting bytecode to machine code?
1. JDK
2. JRE
3. JVM
4. JIT Compiler

Answer: 3
✅ Correct!
```

What’s Next? (The Roadmap)
Building the core was just Phase 1. I’m looking at several ways to make this more "pro":

Weak Area Analytics: Instead of just a score, the app could tell you: "You got 100% on Syntax, but only 40% on Memory Management."

External Data: Moving the question bank to a JSON file or a MySQL database so the app stays lightweight and dynamic.

The Web Leap: My ultimate goal is to wrap this logic in a Spring Boot backend and build a modern frontend with React, turning it into a full-scale placement platform.

Reflections
Building this project was a deep dive into modular design. It taught me that writing code that "works" is easy, but writing code that is "extensible"—meaning you can add features without breaking the old ones—is where the real challenge lies.

This tool is open-source and built for anyone looking to sharpen their technical edge.

## How to Run

### Option 1 — Run the pre-built JAR (easiest)
```bash
java -jar TechPrep.jar
```

### Option 2 — Compile from source
```bash
javac -d out src/*.java
java -cp out Main
```

> Requires Java 17+ (uses switch expressions and text blocks).

---

## Features

| Feature | Description |
|---|---|
| 70 Questions | Java, Python, C++ with sub-topics |
| Topic Analytics | Per-topic score with visual progress bars |
| Shuffled Options | Prevents memorising answer positions |
| Timed Mode | 20-second countdown per question |
| Retry Mode | Re-attempt only the questions you missed |
| Mixed Quiz | All 3 languages in one session |
| Readiness Verdict | Interview Ready / Almost There / Needs Work |

---

## Project Structure

```
src/
├── Main.java          — Entry point, menus, session configuration
├── Quiz.java          — Quiz engine, timer, analytics, retry flow
├── QuestionBank.java  — All 70 questions (Java / Python / C++)
├── Question.java      — Question model with shuffle support
├── TopicResult.java   — Per-topic score tracker
├── Category.java      — Enum: JAVA, PYTHON, CPP
├── Color.java         — ANSI console colours
└── UI.java            — Banner, separators, utilities
```

---

## Roadmap

- [ ] Load questions from JSON file (external question bank)
- [ ] Save session history to a local file
- [ ] Difficulty levels (Easy / Medium / Hard per question)
- [ ] Spring Boot REST API backend
- [ ] React frontend (full placement platform)

---

## Author

Built by [aabhi-999](https://github.com/aabhi-999) · Open source · MIT License
