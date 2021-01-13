<h1>Oauth2</h1>

authorization_code
http://localhost:9999/oauth/authorize?client_id=clientapp&response_type=code&scope=read_profile_info
1. Tekan approval or not


implicit
  curl http://localhost:9999/oauth/authorize?client_id=jsclient&response_type=token&scope=write&state=blablabla&redirect_uri=
1. Ngak perlu tekan perlu approval

Credential
 ngak perlu username dan password credential aja
 
 
 password
 menggunakan passwoerd / tapi ngak di gunakan   

