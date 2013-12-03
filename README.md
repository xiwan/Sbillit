Sbillit --  great tool to split bill
========

This is [play!](http://www.playframework.com/) based project. 

There are two different environments: local and production. In order to switch from one to one, you should comment out the correct location tag in spring xml.

## API specification (ver 1):
	# get user info (session check)
	GET		/1/user/:id/info		
		
	# user login, create session and identify login source, finally session id and user info
	POST 	/1/user/login
	
	# get order info (session check)
	GET		/1/order/:id/info	
	
	# get history order info by user id
	GET		/1/order/:userid/history
	
	# order create
	POST    /1/order/quick
	
	REQUEST
	{totalNumber, memberArray, orderCurrency}
	RESPONSE
	{totalNumber, memberArray, orderCurrency, orderImagePath, orderID}
	
	# order image upload
	POST 	/1/image/upload
	
	REQUEST		
	{orderId, image}
	RESPONSE
	{status}
	
	
	
	
	
	

		
	