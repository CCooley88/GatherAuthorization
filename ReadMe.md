Goal:
Create a small RestApi that uses authentication to permit caller access. The current authorization
passes a JSON Web Token to the calling and verifies credentials in a SQL database. 

SpringBoot supports the Rest functionality however as OAuth is no longer supported and
the authorization functionality of OAuth2 is no longer supported/has not been updated
the project relies strictly on the JWT for authorization. Additionally, the filtering capabilities
of SpringSecurity could be leveraged but for experience purposes I am relying on my own filtering 
functionality. 

While the project goal is simple the changes around authorization services between Spring and OAuth have
created some challenges which encouraged reliance on a more foundational code base.

Finally, due to the rapip changes occuring in web specifications the project relies
on a self signed SSL key. FireFox was throwing a "same-site" error and cookies were not persisting
through browser sessions or api calls.

Resources:
- JWT web token
- MySql (not included in repo)
- Javax cookies
- Self signed SSL cert available through the JDK

Classes:
- AuthUser - The database maintains credentials for authorization
- AuthUserController - The controller user for authentication
- JWTUtil - supports creation and validation of JSON Web Tokens