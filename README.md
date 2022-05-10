# Description
This program is a simple demonstration and benchmark of the AES-CBC encryption algorithm implemented in Java 11 JDK (11.0.15).

# Expected Behavior
Upon running the program, be patient and allow it a few seconds to complete its first encryption and decryption pass. The time this takes will vary from system to system. The program will run 20 passes of encryption and decryption on a target text (`.txt`) file and print the times each time it completes a pass. It will then print an average encryption and decryption times and the best encryption and decryption times upon completing all 20 runs.

# How to Run
## Installation
In order to run this program, you will need to have Java 11 JDK (version 11.0.15 or later) installed. You can follow the installation instructions [here](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html). The project was built and ran on Linux, so that is what the rest of the guide will reference for execution.

## Compilation and Execution
In order to run the program, you first must navigate to the project folder and edit the `JEncryption.java` file to use the correct file path to run the encryption and decryption processes on. Replace the file path with the **full path** to your file in the `fileName` variable on line 30. Be sure to save the file upon making your changes.
<br><br>
Finally, execute the program by running `java -Xmx8G JEncryption.java` in the terminal.