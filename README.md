Box Java SDK
=============

API Calls Quickstart
--------------------

```java
// Authenticate the client with OAuth. See the authentication section below for
// more information.
boxClient.authenticate(oAuthView, autoRefreshToken, listener);

// Get default file info.
BoxAndroidFile boxFile = boxClient.getFilesManager().getFile(fileId, null);

// Get default file info plus its description and SHA1.
BoxDefaultRequestObject requestObj =
  (new BoxDefaultRequestObject()).addField(BoxFile.FIELD_SHA1);
		.addField(BoxFile.FIELD_DESCRIPTION);
BoxAndroidFile boxFile = boxClient.getFilesManager().getFile(fileId, requestObj);

// Get the 20th through 50th children items (inclusive, for a total 30 items) of a folder, requiring 
// etag, description, and name to be included. 
BoxFolderRequestObject requestObj = 
	BoxFolderRequestObject.getFolderItemsRequestObject(30, 20)
		.addField(BoxFolder.FIELD_NAME)
		.addField(BoxFolder.FIELD_DESCRIPTION)
		.addField(BoxFolder.FIELD_ETAG);
BoxAndroidCollection collection = 
	boxClient.getFoldersManager().getFolderItems(folderId, requestObj);

// Upload a new file.
BoxFileUploadRequestObject requestObj = 
	BoxFileUploadRequestObject.uploadFileRequestObject(parent, "name", file);
List<BoxAndroidFile> bFiles =
	boxClient.getFilesManager().uploadFiles(requestObj);

// Upload a new file with an upload progress listener.
BoxFileUploadRequestObject requestObj = 
	BoxFileUploadRequestObject.uploadFileRequestObject(parent, "name", file)
		.setListener(listener));
List<BoxAndroidFile> bFiles = 
	boxClient.getFilesManager().uploadFiles(requestObj);

// Download a file.
boxClient.getFilesManager().downloadFile(fileId, null);

// Delete a file, but only if the etag matches.
BoxFileRequestObject requestObj =
	BoxFileRequestObject.deleteFileRequestObject().setIfMatch(etag);
boxClient.deleteFile(fileId, requestObj);
```

Authentication
--------------

**Note:** You can find a full example of how to perform authentication in the sample
app.

```java
// The easiest way to authenticate is to use the OAuthActivity, which is
// included in the SDK. Add it to your manifest to use it.
Intent intent = OAuthActivity.createOAuthActivityIntent(this, clientId, 
	clientSecret);
startActivityForResult(intent);

@Override
public void onActivityResult(int requestCode, int resultCode, Intent data) {
	if (resultCode == Activity.RESULT_CANCELED) {
		// Get the error message for why authentication failed.
		String failMessage = data.getStringExtra(OAuthActivity.ERROR_MESSAGE);
		// Implement your own logic to handle the error.
	   handleFail(failMessage);
	} else {
		// You will get an authenticated BoxClient object back upon success.
		BoxAndroidClient client =
			data.getParcelableExtra(OAuthActivity.BOX_CLIENT);
		youOwnMethod(client);
	}
}
```

```java
// Alternatively, you can use your own custom login activity with a WebView for
// authentication.
oauthView = (OAuthWebView) findViewById(R.id.oauthview);
oauthView.initializeAuthFlow(boxClient, this);
boxClient.authenticate(oauthView, autoRefreshOAuth, getOAuthFlowListener());

// Create a listener listening to OAuth flow. The most important part you need
// to implement is onAuthFlowEvent and catch the OAUTH_CREATED event. This event
// indicates that the OAuth flow is done, the BoxClient is authenticated and that
// you can start making API calls. 
private OAuthWebViewListener getOAuthFlowListener() {
	return new OAuthWebViewListener() {
		@Override
		public onAuthFlowEvent(final IAuthEvent event,
			final IAuthFlowMessage message) {

			// Authentication is done, you can start using your BoxClient
			// instance.
		}
	}
}

// You can get a BoxOAuthToken and use it to authenticate the client at a later
// time or in a different activity.
BoxOAuthToken oauthObject = boxClient.getAuthData();

// Re-authenticate using the previously obtained OAuth object.
boxClient.authenticate(oauthObject);
```

Known Issues
------------

Not all features, such as trash and search, are supported yet.
