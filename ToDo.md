My Security
1 - Authorization Server
  - Have an authorization server that maintains a client ID and 
       a client secret (for resource server access)
  - This will call on a database and validate user credentials
  - If the credentials are correct it will provide a token in cookies to the caller
  - The token will be stored and utilized when the resource server is called (using the 
       client ID and client secret) to confirm there is a valid token associated with XYZ User

2 - Application
  - This is the login page and associated UI
  - It will send credentials to the auth server 
  - If credentials are approved the browser will receive and store a token
  - When a call is made to the Resource Server the token will be passed for validation/authorization

3 - Resource Server
  - The resource server will verify the token against the authorization server using its ID & Secret
  - If the token is valid it will permit access to various protected REST resourecs

https://localhost:8081/
https://localhost:8081/login/ckulig/123
https://localhost:8081/validate


JWT tutorial:
https://metamug.com/article/security/jwt-java-tutorial-create-verify.html

Steps
- XAuth Server returns some information to caller
- XServer calls information from the database and returns something, a string, to the caller
   Not sure weather to use JPA or somethign else, see if JPA will work to call the database
   make a repository manager that he contorller calls
- XServer validates user credentials 
- XServer returns a token that is saved into cookies for further use
- XPage maintains cookies that gets passed back to the server - 
  https://metamug.com/article/security/jwt-java-tutorial-create-verify.html
- XDocument JWT
- Adjust call method to send credentials in the header or body not in plain text
- Create JWT function in the utility class not in the controller
- Create a filter method
- Call event data
- Test from an HTML page

