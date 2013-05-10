Box Java SDK
=============

API Calls Quickstart
--------------------

### <a href="https://github.com/box/box-java-sdk-private/wiki/HelloWorld">Hello World example</a> 

### Authenticate

Authenticate the client with OAuth. This java sdk does not provide a authentication UI. 
You can use your own UI to get the authentication code and use the sdk OAuthManager to get a BoxOAuthToken.
Then you can authenticate the client using:
```java
boxClient.authenticate(boxOAuthToken);
```
For more details plese see: <a href="https://github.com/box/box-java-sdk-private/wiki/HelloWorld">Hello World example</a> 

### Get Default File Info

```java
BoxFile boxFile = boxClient.getFilesManager().getFile(fileId, null);
```

### Get Additional File Info

Get default file info plus its description and SHA1.

```java
BoxDefaultRequestObject requestObj =
  (new BoxDefaultRequestObject()).addField(BoxFile.FIELD_SHA1);
		.addField(BoxFile.FIELD_DESCRIPTION);
BoxFile boxFile = boxClient.getFilesManager().getFile(fileId, requestObj);
```

### Get Folder Children

Get 30 child items, starting from the 20th one, requiring etag, description, and
name to be included.

```java
BoxFolderRequestObject requestObj = 
	BoxFolderRequestObject.getFolderItemsRequestObject(30, 20)
		.addField(BoxFolder.FIELD_NAME)
		.addField(BoxFolder.FIELD_DESCRIPTION)
		.addField(BoxFolder.FIELD_ETAG);
BoxCollection collection = 
	boxClient.getFoldersManager().getFolderItems(folderId, requestObj);
```

### Upload a New File

```java
BoxFileUploadRequestObject requestObj = 
	BoxFileUploadRequestObject.uploadFileRequestObject(parent, "name", file);
List<BoxFile> bFiles = boxClient.getFilesManager().uploadFiles(requestObj);
```

### Upload a File with a Progress Listener

```java
BoxFileUploadRequestObject requestObj = 
	BoxFileUploadRequestObject.uploadFileRequestObject(parent, "name", file)
		.setListener(listener));
List<BoxFile> bFiles = boxClient.getFilesManager().uploadFiles(requestObj);
```

### Download a File

```java
boxClient.getFilesManager().downloadFile(fileId, null);
```

### Delete a File

Delete a file, but only if the etag matches.

```java
BoxFileRequestObject requestObj =
	BoxFileRequestObject.deleteFileRequestObject().setIfMatch(etag);
boxClient.deleteFile(fileId, requestObj);
```


Known Issues
------------
Java sdk does not provide an OAuth UI.

