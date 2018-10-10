# NameBook2
TARge+v17 : 03.10.18 App that was created in lesson. DID NOT FINISH WILL CONTINUE NEXT LESSON.

Exception Handling Keywords: Java provides specific keywords for exception handling purposes.

throw – if any exception occurs, an exception object is getting created and then Java runtime starts processing to handle them. 
Sometime you might want to generate exception explicitly in your code, for example in a user authentication program you should throw exception to client if the password is null. 
Throw keyword is used to throw exception to the runtime to handle it.

throws – when throwing any exception in a method and not handling it, then you need to use throws keyword in method signature to let caller program know the exceptions that might be thrown by the method. 
The caller method might handle these exceptions or propagate it to it’s caller method using throws keyword. 
You can provide multiple exceptions in the throws clause and it can be used with main() method also.

try-catch – try-catch block is used for exception handling in your code. Try is the start of the block and catch is at the end of try block to handle the exceptions. 
You can have multiple catch blocks with a try and try-catch block can be nested also. Catch block requires a parameter that should be of type Exception.

finally – finally block is optional and can be used only with try-catch block. Since exception halts the process of execution, 
you might have some resources open that will not get closed, so you can use finally block. Finally block gets executed always, whether exception occurred or not.


The idea of the app is to have user input data that will be saved to internal storage and then read from there when displaying it.

The MainActivity layout:

![screenshot_1538592437](https://user-images.githubusercontent.com/31770163/46433123-b99e2a00-c758-11e8-82ce-f7eb608aabb9.png)


When user tries to save input without the name field:

![save](https://user-images.githubusercontent.com/31770163/46433204-e9e5c880-c758-11e8-939c-2e6bb48cc207.png)



Read about internal/external storage:
[Internal external storage.pdf](https://github.com/hnagel413/NameBook2/files/2443345/Internal.external.storage.pdf)
