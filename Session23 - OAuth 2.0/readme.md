1. From our hyper link -> http://localhost:8080/oauth2/authorization/github
2. Step 1. redirected to ->
   https://github.com/login/oauth/authorize?response_type=code&client_id=Ov23liz7oPQjBt0DiQHY&scope=read:user&state=aSqSM2fdRsR4qaUZI85R8lvr23WduGlKRKURM4tKjUg%3D&redirect_uri=http://localhost:8080/login/oauth2/code/github
3. Once the end user authorizes oauth2.0 client to use their resources on github, the following call is made by github's FE: https://github.com/login/oauth/authorize
4. Github redirected to: http://localhost:8080/login/oauth2/code/github?code=5b2b9dd23572e5810949&state=U9QqdMRS4Rfy19uJNZqP7IlJEpOJ_eRFaiUeCv3Fnfk%3D which is nothing but our authorization callback url
5. Authorization call back url redirects to our homepage url: http://localhost:8080/

OAuth 2.0 access can be revoked in 2 ways:
1. End user revoking the access from provider(github) - user may not want to share data with OAuth 2.0 client(geeksforgeeks)
2. OAuth 2.0 application admin can also revoke the access - May be client has made some changes in policy/scope and want clients to reauthorize