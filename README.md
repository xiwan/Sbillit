Sbillit --  great tool to split bill
========

This is [play!](http://www.playframework.com/) based project. 

### API specification (ver 1):
		
###### user login, create session and identify login source, finally return session id and user info
	POST 	/user/login	
	_REQUEST_		postData={jsonObject}
	_RESPONSE_		{sessionId:xxx, [profile:xxx]}
	
###### get user info
	GET		/user/:id/info
		
###### get order info (session check)
	GET		/order/:id/info	
		
###### get history order info by user id
	GET		/order/:userid/history
	_RESPONSE_		{orderList}
	
###### quick order create
	POST    		/order/quick
	_REQUEST_		postData={totalNumber, memberArray, orderCurrency}
	_RESPONSE_		{totalNumber, memberArray, orderCurrency, orderImagePath, orderID}
	
##### order image upload
	POST 			/image/upload
	_REQUEST_		postData={orderId, image}
	_RESPONSE_		{status}
	
### NOTE:
* To finish a successful request and response cycle, **sessionId=xxx** is required.
* A decent response formatted as {code: xxx, body:{jsonObj}}
* There are two different environments: local and production. In order to switch from one to one, you should comment out the correct location tag in spring xml.
* Error code tables:	
	| ERROR 	       		| Code          | 
	| ---------------------	|:-------------:| 
	| ERROR_INTERNAL     	| 500 | 
 	| ERROR_BAD_REQUEST     | 400 | 
 	| ERROR_NOT_ALLOWED     | 300 | 
 	| ERROR_FREE     		| 200 | 
 	| ERROR_AUTH_EXPIRED    | 100 | 
 	| ERROR_AUTH_NO     	| 101 | 
 	| ERROR_AUTH_NORMAL     | 102 | 
	
	
	

		
	