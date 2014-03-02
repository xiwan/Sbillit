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
	
###### get history order  detail info by user id
	GET		/order/:order/detail
	_RESPONSE_		{order, orderShare, orderComment, orderItem, orderThumbup}
	
###### post comment
	POST    /order/:orderid/comment
	_REQUEST_  		postData={message, atUserId}
	_RESPONSE_		{orderID}
	
###### order create
	POST    		/order/quick
	_REQUEST_		postData={orderShareArray, orderItemArray, orderCreator, orderImagePath, orderComments, orderTitle, totalNumber}
	_RESPONSE_		{orderID}
	
###### quick order create
	POST    		/order/quick
	_REQUEST_		postData={orderTitle, totalNumber, friendsArray, contactsArray}
	_RESPONSE_		{orderID}
	
##### order image upload
	POST 			/order/upload
	_REQUEST_		postData={orderId, image}
	_RESPONSE_		{status}
	
##### ads pull
	GET				/ads/pull
	_RESPONSE_		{imageUrl, comboId}
	
##### friend get
	GET 			/sns/get/:friendid
	_RESPONSE_		{friendList}

##### friend add
	POST 			/sns/add
	_REQUEST_  		postData={friendArray=[{phone, name}]}

	
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
	
	
	

		
	