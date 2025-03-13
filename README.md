# Documentation
## Project Report: Console-Based Calculator

### 1. Introduction
   The purpose of this project was to develop a console-based calculator in Java that can perform basic arithmetic operations and additional functions such as power, square root, absolute value, and rounding. The calculator also maintains a history of calculations and provides a user-friendly interface.

### 2. Design Choices

**Programming Language:** Java was chosen due to its robustness and ease of handling user input/output.

**Expression Evaluation:** A custom parser was implemented to handle mathematical expressions instead of using external libraries.

**Function Handling:** Regular expressions were used to identify and process functions such as abs(), sqrt(), round(), and pow().

**Error Handling:** Try-catch blocks were used to gracefully handle invalid inputs and division by zero.

**History Feature:** A list was used to store previous calculations, allowing users to review their past entries.

### 3. Challenges Encountered

**Parsing Expressions:** Implementing a correct parser to evaluate mathematical expressions while supporting function calls was challenging.

**Handling Custom Functions:** Using regular expressions to identify functions and replace them with computed values required careful regex pattern matching.

**Operator Precedence:** Ensuring correct precedence of operators like multiplication and division over addition and subtraction needed a stack-based approach.

**Invalid Input Handling:** Ensuring the program provides clear error messages for malformed expressions and preventing crashes.

### 4. Conclusion
   The project successfully implemented a functional console-based calculator with support for basic and advanced operations. Future improvements could include supporting variables, additional mathematical functions, and improving expression parsing efficiency.

# Sample output:

## Welcome to the Calculator!
 ### Please enter your arithmetic expression: 34+(76-45)*2 - abs(-5)

**Result:** 91.0

**Do you want to continue? (y/n):** y

### Please enter your arithmetic expression: 12+32-(12*3)
**Result:** 8.0

**Do you want to continue? (y/n):** y

### Please enter your arithmetic expression: 34+(76-45)*2 - sqrt(-5)
**Error: Invalid expression**

**Do you want to continue? (y/n):** y
 
### Please enter your arithmetic expression: 10*10/5
**Result:** 20.0

**Do you want to continue? (y/n):** n

### Calculation History:
34+(76-45)*2 - abs(-5) = 91.0

12+32-(12*3) = 8.0

10*10/5 = 20.0

#### Thank you for using the Calculator!

Process finished with exit code 0