import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class QuestionBank {

    private final List<Question> allQuestions = new ArrayList<>();

    public QuestionBank() {
        loadJavaQuestions();
        loadPythonQuestions();
        loadCppQuestions();
    }

    // ─── helpers ─────────────────────────────────────────────────────────────

    private void add(String q, String[] opts, int ans, Category cat, String topic) {
        allQuestions.add(new Question(q, Arrays.asList(opts), ans, cat, topic));
    }

    public List<Question> getByCategory(Category cat) {
        return allQuestions.stream()
                .filter(q -> q.getCategory() == cat)
                .collect(Collectors.toList());
    }

    public int totalCount() { return allQuestions.size(); }

    // ─── JAVA ────────────────────────────────────────────────────────────────

    private void loadJavaQuestions() {

        // JVM Internals
        add("Which component converts Java bytecode to machine code at runtime?",
            new String[]{"JDK", "JRE", "JVM", "JIT Compiler"}, 4, Category.JAVA, "JVM Internals");

        add("Where are class files stored during execution inside the JVM?",
            new String[]{"Stack", "Heap", "Method Area", "Program Counter"}, 3, Category.JAVA, "JVM Internals");

        add("Which JVM area stores local variables and method call frames?",
            new String[]{"Heap", "Stack", "Metaspace", "Code Cache"}, 2, Category.JAVA, "JVM Internals");

        add("What is Metaspace in Java 8+?",
            new String[]{"Replacement for PermGen that stores class metadata",
                         "A new heap region for large objects",
                         "A thread-local memory pool",
                         "The JIT compiled code cache"}, 1, Category.JAVA, "JVM Internals");

        // Memory Management
        add("Which garbage collector is the default in Java 9+?",
            new String[]{"Serial GC", "Parallel GC", "G1 GC", "ZGC"}, 3, Category.JAVA, "Memory Management");

        add("What does 'System.gc()' do?",
            new String[]{"Immediately runs GC", "Suggests JVM to run GC — not guaranteed",
                         "Clears the entire heap", "Compacts memory and pauses threads"}, 2, Category.JAVA, "Memory Management");

        add("Which reference type in Java allows the GC to reclaim an object even if the reference exists?",
            new String[]{"Strong reference", "Soft reference", "Weak reference", "Phantom reference"}, 3, Category.JAVA, "Memory Management");

        add("What is a memory leak in Java?",
            new String[]{"Using too many threads", "Objects that are no longer needed but still referenced",
                         "Running out of stack space", "Exceeding the PermGen limit"}, 2, Category.JAVA, "Memory Management");

        // Collections
        add("Which Map implementation maintains insertion order?",
            new String[]{"HashMap", "TreeMap", "LinkedHashMap", "Hashtable"}, 3, Category.JAVA, "Collections");

        add("What is the initial capacity of an ArrayList?",
            new String[]{"0", "5", "10", "16"}, 3, Category.JAVA, "Collections");

        add("Which collection does NOT allow duplicate elements?",
            new String[]{"ArrayList", "LinkedList", "HashSet", "ArrayDeque"}, 3, Category.JAVA, "Collections");

        add("What is the time complexity of get() in a HashMap (best case)?",
            new String[]{"O(n)", "O(log n)", "O(1)", "O(n log n)"}, 3, Category.JAVA, "Collections");

        add("Which interface does TreeMap implement that enforces sorted order?",
            new String[]{"Comparable", "Comparator", "SortedMap", "NavigableMap"}, 3, Category.JAVA, "Collections");

        add("What happens when you call iterator.remove() vs list.remove() inside a for-each loop?",
            new String[]{"Both throw ConcurrentModificationException",
                         "iterator.remove() is safe; list.remove() throws ConcurrentModificationException",
                         "list.remove() is safe; iterator.remove() throws an exception",
                         "Both are safe"}, 2, Category.JAVA, "Collections");

        // Exception Handling
        add("Which exception is thrown when an array index is out of bounds?",
            new String[]{"NullPointerException", "ArrayIndexOutOfBoundsException",
                         "IllegalArgumentException", "ClassCastException"}, 2, Category.JAVA, "Exception Handling");

        add("What is the difference between checked and unchecked exceptions?",
            new String[]{"Checked are caught at compile time; unchecked at runtime",
                         "Checked must be declared or handled; unchecked need not be",
                         "Unchecked extend Error; checked extend Exception",
                         "They are the same thing"}, 2, Category.JAVA, "Exception Handling");

        add("Which block always executes regardless of whether an exception occurs?",
            new String[]{"catch", "throw", "finally", "throws"}, 3, Category.JAVA, "Exception Handling");

        add("What does 'throw new RuntimeException()' do?",
            new String[]{"Declares an exception", "Catches an exception",
                         "Manually raises an unchecked exception", "Logs an error"}, 3, Category.JAVA, "Exception Handling");

        // OOP
        add("Which keyword prevents a class from being subclassed?",
            new String[]{"static", "private", "final", "abstract"}, 3, Category.JAVA, "OOP");

        add("What is method overriding?",
            new String[]{"Same method name, different parameters in same class",
                         "Subclass provides its own implementation of a parent method",
                         "Hiding a static method from parent",
                         "Calling a constructor from another constructor"}, 2, Category.JAVA, "OOP");

        add("What does the 'super' keyword refer to?",
            new String[]{"The current object", "The parent class or its constructor/methods",
                         "A static reference", "The outermost class"}, 2, Category.JAVA, "OOP");

        add("Which OOP principle is achieved by declaring fields private and using getters/setters?",
            new String[]{"Inheritance", "Polymorphism", "Abstraction", "Encapsulation"}, 4, Category.JAVA, "OOP");

        // Concurrency
        add("Which keyword ensures a variable is always read from main memory?",
            new String[]{"synchronized", "volatile", "transient", "atomic"}, 2, Category.JAVA, "Concurrency");

        add("What does 'synchronized' on a method do?",
            new String[]{"Makes the method run faster", "Locks the method so only one thread executes it at a time",
                         "Prevents the method from being overridden", "Runs the method in a new thread"}, 2, Category.JAVA, "Concurrency");

        add("Which class is used to run code in a separate thread without returning a value?",
            new String[]{"Callable", "Future", "Runnable", "CompletableFuture"}, 3, Category.JAVA, "Concurrency");

        add("What is a deadlock?",
            new String[]{"Two threads competing for CPU time",
                         "Two or more threads waiting on each other's locks indefinitely",
                         "A thread that runs forever",
                         "A method that blocks I/O"}, 2, Category.JAVA, "Concurrency");

        // Streams & Lambdas
        add("Which terminal operation collects stream results into a List?",
            new String[]{"map()", "filter()", "collect()", "peek()"}, 3, Category.JAVA, "Streams & Lambdas");

        add("What does 'flatMap()' do?",
            new String[]{"Filters null values", "Maps and flattens nested streams into one stream",
                         "Sorts stream elements", "Reduces to a single value"}, 2, Category.JAVA, "Streams & Lambdas");

        add("Which functional interface represents a function that takes one argument and returns a result?",
            new String[]{"Consumer", "Supplier", "Predicate", "Function"}, 4, Category.JAVA, "Streams & Lambdas");

        add("What is the difference between 'map()' and 'flatMap()'?",
            new String[]{"map() is for Integers only", "'map()' produces a stream of streams; 'flatMap()' flattens it to one stream",
                         "They are identical", "flatMap() filters before mapping"}, 2, Category.JAVA, "Streams & Lambdas");
    }

    // ─── PYTHON ──────────────────────────────────────────────────────────────

    private void loadPythonQuestions() {

        // Data Structures
        add("Which Python data structure maintains insertion order AND allows duplicates?",
            new String[]{"set", "dict (Python 3.7+)", "list", "frozenset"}, 3, Category.PYTHON, "Data Structures");

        add("What is the time complexity of a list lookup by value (worst case)?",
            new String[]{"O(1)", "O(log n)", "O(n)", "O(n²)"}, 3, Category.PYTHON, "Data Structures");

        add("Which Python type is immutable and can be used as a dict key?",
            new String[]{"list", "dict", "tuple", "set"}, 3, Category.PYTHON, "Data Structures");

        add("What does 'collections.defaultdict' do?",
            new String[]{"Sorts a dictionary", "Returns a default value for missing keys automatically",
                         "Merges two dicts", "Reverses key-value pairs"}, 2, Category.PYTHON, "Data Structures");

        // OOP
        add("What is '__init__' in Python?",
            new String[]{"A destructor", "A class method called before object creation",
                         "An initialiser (constructor) called when an object is created",
                         "A static method"}, 3, Category.PYTHON, "OOP");

        add("Which decorator makes a method callable on the class itself, not an instance?",
            new String[]{"@property", "@staticmethod", "@classmethod", "@abstractmethod"}, 3, Category.PYTHON, "OOP");

        add("What does 'super()' return in Python?",
            new String[]{"The current class", "A proxy object representing the parent class",
                         "The metaclass", "None"}, 2, Category.PYTHON, "OOP");

        add("What is multiple inheritance in Python?",
            new String[]{"A class with multiple methods", "A class inheriting from more than one base class",
                         "Calling super() multiple times", "Using mixins only"}, 2, Category.PYTHON, "OOP");

        // Memory & Internals
        add("What is Python's GIL?",
            new String[]{"A graphics interface library", "A mutex that allows only one thread to execute Python bytecode at a time",
                         "A garbage collection algorithm", "A global import lock"}, 2, Category.PYTHON, "Memory & Internals");

        add("How does Python manage memory for small integers (e.g. -5 to 256)?",
            new String[]{"Creates a new object each time", "Caches and reuses them (interning)",
                         "Stores them on the stack", "Uses a linked list"}, 2, Category.PYTHON, "Memory & Internals");

        add("Which memory management technique does Python use?",
            new String[]{"Manual malloc/free", "Reference counting + cyclic garbage collector",
                         "Generational GC only", "Tracing GC only"}, 2, Category.PYTHON, "Memory & Internals");

        // Functions & Scoping
        add("What does a generator function return?",
            new String[]{"A list", "A tuple", "A generator object (lazy iterator)",
                         "None"}, 3, Category.PYTHON, "Functions & Scoping");

        add("What is a closure in Python?",
            new String[]{"A class with no public methods",
                         "An inner function that captures variables from its enclosing scope",
                         "A decorator pattern", "A lambda with no arguments"}, 2, Category.PYTHON, "Functions & Scoping");

        add("What does LEGB stand for in Python scoping?",
            new String[]{"Local, Enclosing, Global, Built-in",
                         "Lambda, Eval, Generator, Block",
                         "List, Enum, Generator, Built-in",
                         "Local, External, Global, Base"}, 1, Category.PYTHON, "Functions & Scoping");

        // Error Handling
        add("Which keyword re-raises the current exception in Python?",
            new String[]{"throw", "raise", "throw()", "re-raise"}, 2, Category.PYTHON, "Error Handling");

        add("What block always runs in a try/except/finally construct?",
            new String[]{"except", "else", "finally", "try"}, 3, Category.PYTHON, "Error Handling");

        // Concurrency
        add("Which Python module provides true parallelism by bypassing the GIL?",
            new String[]{"threading", "asyncio", "multiprocessing", "concurrent.futures only"}, 3, Category.PYTHON, "Concurrency");

        add("What is 'async def' used for?",
            new String[]{"Defining a method that runs in a new thread",
                         "Defining a coroutine for use with asyncio",
                         "Making a function run faster",
                         "Defining a generator"}, 2, Category.PYTHON, "Concurrency");

        // Python Specifics
        add("What is the output of: bool([]) in Python?",
            new String[]{"True", "False", "None", "Error"}, 2, Category.PYTHON, "Python Specifics");

        add("What does the 'walrus operator' (:=) do?",
            new String[]{"Compares two values", "Assigns a value within an expression",
                         "Creates a dictionary pair", "Defines a lambda"}, 2, Category.PYTHON, "Python Specifics");
    }

    // ─── C++ ─────────────────────────────────────────────────────────────────

    private void loadCppQuestions() {

        // Pointers & References
        add("What does the '*' operator do when applied to a pointer?",
            new String[]{"Multiplies the pointer", "Dereferences (accesses the value at) the pointer",
                         "Creates a new pointer", "Returns the address of a variable"}, 2, Category.CPP, "Pointers & References");

        add("What is the difference between a pointer and a reference in C++?",
            new String[]{"There is none", "A reference cannot be null and must be initialised; a pointer can be null",
                         "A pointer cannot be reassigned; a reference can",
                         "References use * syntax; pointers use & syntax"}, 2, Category.CPP, "Pointers & References");

        add("What does 'nullptr' represent in C++11+?",
            new String[]{"An integer 0", "A type-safe null pointer constant",
                         "An uninitialized pointer", "A void pointer"}, 2, Category.CPP, "Pointers & References");

        add("What is a dangling pointer?",
            new String[]{"A pointer to a null value", "A pointer that points to already-freed memory",
                         "A pointer with two levels of indirection",
                         "An uninitialized pointer"}, 2, Category.CPP, "Pointers & References");

        // Memory Management
        add("Which operator allocates memory on the heap in C++?",
            new String[]{"malloc", "alloc", "new", "create"}, 3, Category.CPP, "Memory Management");

        add("What must always pair with 'new[]' to avoid memory leaks?",
            new String[]{"free()", "delete", "delete[]", "dealloc()"}, 3, Category.CPP, "Memory Management");

        add("What is RAII in C++?",
            new String[]{"A design pattern where resource lifetimes are tied to object lifetimes",
                         "A memory allocator",
                         "A type of smart pointer",
                         "A runtime error handling technique"}, 1, Category.CPP, "Memory Management");

        add("Which smart pointer shares ownership and reference-counts the managed object?",
            new String[]{"unique_ptr", "shared_ptr", "weak_ptr", "auto_ptr"}, 2, Category.CPP, "Memory Management");

        // OOP
        add("What is a virtual function in C++?",
            new String[]{"A function declared but never defined",
                         "A function that allows runtime polymorphism via vtable lookup",
                         "A function only accessible in the same class",
                         "A function that is inlined by the compiler"}, 2, Category.CPP, "OOP");

        add("What does 'pure virtual function' mean?",
            new String[]{"A virtual function with an empty body",
                         "A virtual function set to 0 (= 0), making the class abstract",
                         "A private virtual function",
                         "A function that cannot be overridden"}, 2, Category.CPP, "OOP");

        add("What is the order of constructor calls in inheritance?",
            new String[]{"Derived first, then Base", "Base first, then Derived",
                         "Both simultaneously", "Random order"}, 2, Category.CPP, "OOP");

        add("What is operator overloading?",
            new String[]{"Replacing a built-in operator", "Defining custom behaviour for operators on user-defined types",
                         "Overriding a function template", "Creating a new operator symbol"}, 2, Category.CPP, "OOP");

        // STL
        add("Which STL container provides O(1) average insertion/lookup by key?",
            new String[]{"std::map", "std::set", "std::unordered_map", "std::vector"}, 3, Category.CPP, "STL");

        add("What does 'std::vector::push_back()' do when capacity is exceeded?",
            new String[]{"Throws an exception", "Doubles capacity, copies elements to new storage, then inserts",
                         "Refuses to insert", "Wraps around to index 0"}, 2, Category.CPP, "STL");

        add("Which algorithm header function sorts a range in ascending order by default?",
            new String[]{"std::qsort", "std::sort", "std::heap_sort", "std::order"}, 2, Category.CPP, "STL");

        // Templates & Modern C++
        add("What is a template in C++?",
            new String[]{"A design pattern", "A mechanism for generic programming — code works for any type",
                         "A preprocessor macro", "An abstract class"}, 2, Category.CPP, "Templates & Modern C++");

        add("What does 'auto' keyword do in C++11+?",
            new String[]{"Makes a variable constant", "Instructs the compiler to deduce the variable's type automatically",
                         "Allocates memory on the heap", "Marks a function as inline"}, 2, Category.CPP, "Templates & Modern C++");

        add("What is a lambda expression in C++11?",
            new String[]{"A named function template", "An anonymous inline function object",
                         "A type alias", "A compile-time constant"}, 2, Category.CPP, "Templates & Modern C++");

        // Concurrency
        add("Which C++11 header provides std::thread?",
            new String[]{"<process>", "<thread>", "<concurrent>", "<parallel>"}, 2, Category.CPP, "Concurrency");

        add("What does 'std::mutex::lock()' do?",
            new String[]{"Prevents the variable from changing", "Blocks the calling thread until it acquires exclusive ownership",
                         "Creates a new thread", "Signals another thread"}, 2, Category.CPP, "Concurrency");
    }
}
