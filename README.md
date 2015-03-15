Fresh Hot Falafel!
==

https://www.youtube.com/watch?v=1s0ksX4qfJg

Objective
--

In this project, we'll program the animated LED sign board for a New York falafel cart.  While doing this, we'll also practice using an API (_application programming interface_) that provides capabilities for our program to use.  In this case, we'll use a pre-built API for the sign board itself; our job is to invent interesting and attractive animations for the board. 


Setup
--

Clone this repository as follows.

1. Open a terminal window.
2. Go to your Access Code directory.

   ```
   $ cd 
   $ cd Desktop/accesscode
   ```
   
3. Clone this repository.  Use the URL of your group's forked repo.

   ```
   $ git clone <URL>
   $ cd signboard
   ```
   
4. Open the project in IntelliJ and compile it.
   
4. Invoke the provided run script to run the sample code.

   ```
   $ ./run
   ```
   
Press Control-C when you are bored of watching it.


API
--

The sign board has these features:

* runs in terminal
* black background
* 8 lines high
* variable-width, depends on terminal size
* can display any character at each position.
* four colors: white, yellow, red, green

The API provides a Java _class_ called `SignBoard`. You'll use one _instance_ of this class in your program.  (This is similar to how you have used one instance of `Scanner` in programs that read from the terminal.)

```java
SignBoard board = new SignBoard(8);
```

The argument specifies how many rows high you want the sign board to be.  For this project, use 8.

Once you have this object, you can access the width and height of the sign.

```java
int height = SignBoard.getHeight();
int width = SignBoard.getWidth();
```

The first of these calls will always return the height you specified when you created the `SingBoard`, but it's better to use this function to retrieve it than to code the value many times in your program.  The second call will determine the width of your actual terminal.

You'll create an animation by drawing a sequence of frames (still pictures), each for a fraction of a second.  Draw one frame by following these steps:

1. Clear the sign board.

  ```java
  board.clear();
  ```

2. Draw some letters on it.

  ```java
  board.write(x, y, string);
  ```
  
  `x` and `y` are integers that specify, respectively, the horizontal and vertical position to write.  For `x`, zero is the leftmost column and increasing numbers move right.  For `y`, zero is top topmost line, and increasing numbers move down.  `string` contains the text you wish to write.  You can use letters, numbers, or any sybmols you like, such as `*` or `-` or `|` to draw words, pictures, and designs.
  
3. Pause for a moment.

  ```java
  board.pause(0.05);
  ```
   
   Specify the length of time to pause in seconds.  For a smooth animation, you should avoid pausing more than 1/10 of a second between frames.

Because computers are so fast, steps 1 and 2 happen nearly instantaneously.  If you change the frame only slightly between frames, _e.g._ by moving contents over one position, the display will appear to move.

You may call `write()` as many times as you need to draw your frame.  Use your creativity!  

**Important:** The text must fall fully within the board, or your program will fail.  More specifically, when you call `write()`, these conditions must hold:

| condition | description |
|-----------|-------------|
| `x >= 0` | text cannot start before the left edge |
| `x + string.length() <= getWidth()` | text cannot extend beyond the right edge |
| `y >= 0` | text cannot appear above the top edge |
| `y < getHeight()` | text cannot appear below the bottom edge |

You may also use colors.  Whenever you clear the frame, the text color is set to white.  You may call any of these methods to switch colors; all subsequent calls to `write()` will use that color, until you change it again.

```java
board.setGreen();
board.setRed();
board.setWhite();
board.setYellow();
```

Running your program
--

**Important:** Your sign board will work only if you run it in the OSX terminal (i.e. shell window).  **It will not work correctly in IntelliJ's embedded terminal.**  That's because IntelliJ's terminal lacks some of the features necessary to draw artistic text such as the signboard.  For that reason, we'll have to run the program manually using the `java` command.

You may, however, continue to write, edit, and compile your code in IntelliJ.  Remember that IntelliJ runs the Java compiler `javac` on your Java source files for you.  We'll just have to do a bit more work to make sure that `java` can find the class files that IntelliJ compiled.

Follow these steps to do so:

1. Open a terminal window. 
2. In the terminal window, navigate to the directory where you cloned this repository.

  ```
  $ cd
  $ cd Desktop/accesscode/signboard
  ```
3. Invoke the run script.

  ```
  $ ./run
  ```
  
Keep the terminal window open.  As you edit your program and wish to test it out, switch back to the terminal window and run `./run` to start it.

Some extra hints:

* If Java complains "cannot find or load main class", check the following:
  * Does your program contain errors?  If IntelliJ highlights any of your code with red, or shows compile errors at the bottom, the compiler has failed to compile your code.  Fix the errors before trying again.
  * Have you compiled your code in IntelliJ?  Select `Build > Make Project` from the menu, or press Command-F9.

    
* If your program is stuck or running in an infinite loop, press Control-C to kill it.

* If your terminal gets messed up, for instance the cursor is gone or the colors are wrong, run the `reset` command to reset it.

* You can configure IntelliJ to compile automatically and continuously as you edit your code.  Open the Preferences window by pressing Command-, and find `Build, Execution, Deployment > Compiler` on the left side.  Find the `Make project automatically` checkbox and check it.


