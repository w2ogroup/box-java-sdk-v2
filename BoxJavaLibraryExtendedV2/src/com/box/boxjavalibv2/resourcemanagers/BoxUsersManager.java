package com.box.boxjavalibv2.resourcemanagers;

import java.util.ArrayList;
import java.util.List;

import com.box.boxjavalibv2.dao.BoxCollection;
import com.box.boxjavalibv2.dao.BoxEmailAlias;
import com.box.boxjavalibv2.dao.BoxFolder;
import com.box.boxjavalibv2.dao.BoxResourceType;
import com.box.boxjavalibv2.dao.BoxTypedObject;
import com.box.boxjavalibv2.dao.BoxUser;
import com.box.boxjavalibv2.exceptions.AuthFatalFailureException;
import com.box.boxjavalibv2.exceptions.BoxServerException;
import com.box.boxjavalibv2.interfaces.IBoxResourceHub;
import com.box.boxjavalibv2.requests.CreateEmailAliasRequest;
import com.box.boxjavalibv2.requests.CreateEnterpriseUserRequest;
import com.box.boxjavalibv2.requests.DeleteEmailAliasRequest;
import com.box.boxjavalibv2.requests.GetAllUsersInEnterpriseRequest;
import com.box.boxjavalibv2.requests.GetCurrentUserRequest;
import com.box.boxjavalibv2.requests.GetEmailAliasesRequest;
import com.box.boxjavalibv2.requests.MoveFolderToAnotherUserRequest;
import com.box.boxjavalibv2.requests.UpdateUserLoginRequest;
import com.box.boxjavalibv2.requests.UpdateUserRequest;
import com.box.boxjavalibv2.requests.requestobjects.BoxDefaultRequestObject;
import com.box.boxjavalibv2.requests.requestobjects.BoxUserRequestObject;
import com.box.restclientv2.exceptions.BoxRestException;
import com.box.restclientv2.interfaces.IBoxConfig;
import com.box.restclientv2.interfaces.IBoxRESTClient;
import com.box.restclientv2.interfaces.IBoxRequestAuth;

/**
 * Use this class to execute requests <b>synchronously</b> against the Box REST API(V2), users endpints. Full details about the Box API can be found at <a
 * href="http://developers.box.com/docs">http://developers.box.com/docs</a> . You must have an OpenBox application with a valid API key to use the Box API. All
 * methods in this class are executed in the invoking thread, and therefore are NOT safe to execute in the UI thread of your application. You should only use
 * this class if you already have worker threads or AsyncTasks that you want to incorporate the Box API into.
 */
public final class BoxUsersManager extends BoxResourceManager {

    /**
     * Constructor.
     * 
     * @param config
     *            Config
     * @param resourceHub
     *            IResourceHub
     * @param auth
     *            auth for api calls
     * @param restClient
     *            REST client to make api calls.
     */
    public BoxUsersManager(IBoxConfig config, final IBoxResourceHub resourceHub, final IBoxRequestAuth auth, final IBoxRESTClient restClient) {
        super(config, resourceHub, auth, restClient);
    }

    /**
     * Get the current user's information.
     * 
     * @param requestObject
     *            request object
     * @return current user
     * @throws BoxRestException
     *             exception
     * @throws BoxServerException
     *             exception
     * @throws AuthFatalFailureException
     *             exception
     */
    public BoxUser getCurrentUser(BoxDefaultRequestObject requestObject) throws BoxRestException, BoxServerException, AuthFatalFailureException {
        GetCurrentUserRequest request = new GetCurrentUserRequest(getConfig(), getObjectMapper(), requestObject);
        return (BoxUser) getResponseAndParseAndTryCast(request, BoxResourceType.USER, getObjectMapper());
    }

    /**
     * Get the list of all users for the Enterprise with their user_id, public_name, and login if the user is an enterprise admin. If the user is not an admin,
     * this request returns the current user's user_id, public_name, and login.
     * 
     * @param requestObject
     *            request object
     * @param filterTerm
     *            A string used to filter the results to only users starting with the filter_term in either the name or the login. Use null if don't want
     *            filter.
     * @return collection of users
     * @throws BoxRestException
     *             exception
     * @throws BoxServerException
     *             exception
     * @throws AuthFatalFailureException
     *             exception
     */
    public List<BoxUser> getAllEnterpriseUser(final BoxDefaultRequestObject requestObject, final String filterTerm) throws BoxRestException,
        BoxServerException, AuthFatalFailureException {
        GetAllUsersInEnterpriseRequest request = new GetAllUsersInEnterpriseRequest(getConfig(), getObjectMapper(), requestObject, filterTerm);
        BoxCollection collection = (BoxCollection) getResponseAndParseAndTryCast(request, BoxResourceType.USERS, getObjectMapper());
        return getUsers(collection);
    }

    /**
     * Moves all of the content from within one user's folder into a new folder in another user's account. You can move folders across users as long as the you
     * have administrative permissions. To move everything from the root folder, use 0 which always represents the root folder of a Box account
     * 
     * @param userId
     *            id of the user
     * @param folderId
     *            id of the folder to be removed
     * @param requestObject
     *            request object
     * @return the newly created destination folder
     * @throws BoxRestException
     *             exception
     * @throws BoxServerException
     *             exception
     * @throws AuthFatalFailureException
     *             exception
     */
    public BoxFolder moveFolderToAnotherUser(final String userId, final String folderId, final BoxUserRequestObject requestObject) throws BoxRestException,
        BoxServerException, AuthFatalFailureException {
        MoveFolderToAnotherUserRequest request = new MoveFolderToAnotherUserRequest(getConfig(), getObjectMapper(), userId, folderId, requestObject);
        return (BoxFolder) getResponseAndParseAndTryCast(request, BoxResourceType.FOLDER, getObjectMapper());
    }

    /**
     * Used to provision a new user in an enterprise. This method only works for enterprise admins.
     * 
     * 
     * @param requestObject
     *            request object
     * @return newly created user
     * @throws BoxRestException
     *             exception
     * @throws BoxServerException
     *             exception
     * @throws AuthFatalFailureException
     *             exception
     */
    public BoxUser createEnterpriseUser(BoxUserRequestObject requestObject) throws BoxRestException, BoxServerException, AuthFatalFailureException {
        CreateEnterpriseUserRequest request = new CreateEnterpriseUserRequest(getConfig(), getObjectMapper(), requestObject);
        return (BoxUser) getResponseAndParseAndTryCast(request, BoxResourceType.USER, getObjectMapper());
    }

    /**
     * Used to edit the settings and information about a user. This method only works for enterprise admins.
     * 
     * @param userId
     *            id of the user.
     * @param requestObject
     *            request object
     * @return the updated user
     * @throws BoxRestException
     *             exception
     * @throws BoxServerException
     *             exception
     * @throws AuthFatalFailureException
     *             exception
     */
    public BoxUser updateUserInformaiton(final String userId, BoxUserRequestObject requestObject) throws BoxRestException, BoxServerException,
        AuthFatalFailureException {
        UpdateUserRequest request = new UpdateUserRequest(getConfig(), getObjectMapper(), userId, requestObject);
        return (BoxUser) getResponseAndParseAndTryCast(request, BoxResourceType.USER, getObjectMapper());
    }

    /**
     * Retrieves all email aliases for this user. The collection of email aliases does not include the primary login for the user
     * 
     * @param userId
     *            id of user
     * @param requestObject
     *            request object
     * @return collection of email aliases
     * @throws BoxServerException
     *             exception
     * @throws BoxRestException
     *             exception
     * @throws AuthFatalFailureException
     *             exception
     */
    public List<BoxEmailAlias> getEmailAliases(final String userId, final BoxDefaultRequestObject requestObject) throws BoxServerException, BoxRestException,
        AuthFatalFailureException {
        GetEmailAliasesRequest request = new GetEmailAliasesRequest(getConfig(), getObjectMapper(), userId, requestObject);
        BoxCollection collection = (BoxCollection) getResponseAndParseAndTryCast(request, BoxResourceType.EMAIL_ALIASES, getObjectMapper());
        return getEmailAliases(collection);
    }

    /**
     * Adds a new email alias to the given user's account. This feature is currently only available to enterprise admins and the new email must be in a domain
     * associated with the enterprise and can not be a publicly atainable domain (e.g. gmail.com).
     * 
     * @param userId
     *            id of user
     * @param requestObject
     *            request object
     * @return the newly added email alias
     * @throws BoxServerException
     *             exception
     * @throws BoxRestException
     *             exception
     * @throws AuthFatalFailureException
     *             exception
     */
    public BoxEmailAlias addEmailAlias(final String userId, BoxUserRequestObject requestObject) throws BoxServerException, BoxRestException,
        AuthFatalFailureException {
        CreateEmailAliasRequest request = new CreateEmailAliasRequest(getConfig(), getObjectMapper(), userId, requestObject);
        return (BoxEmailAlias) getResponseAndParseAndTryCast(request, BoxResourceType.EMAIL_ALIAS, getObjectMapper());
    }

    /**
     * Removes an email alias from a user.
     * 
     * @param userId
     *            id of the user
     * @param emailId
     *            id of the email alias to be removed
     * @param requestObject
     *            request object
     * @throws BoxRestException
     *             exception
     * @throws BoxServerException
     *             exception
     * @throws AuthFatalFailureException
     *             exception
     */
    public void deleteEmailAlias(final String userId, final String emailId, BoxDefaultRequestObject requestObject) throws BoxRestException, BoxServerException,
        AuthFatalFailureException {
        DeleteEmailAliasRequest request = new DeleteEmailAliasRequest(getConfig(), getObjectMapper(), userId, emailId, requestObject);
        executeRequestWithNoResponseBody(request);
    }

    /**
     * Used to convert one of the user's confirmed email aliases into the user's primary login.
     * 
     * @param userId
     *            id of the user
     * @param requestObject
     *            request object
     * @return the updated user object
     * @throws BoxRestException
     *             exception
     * @throws BoxServerException
     *             exception
     * @throws AuthFatalFailureException
     *             exception
     */
    public BoxUser updateUserPrimaryLogin(final String userId, final BoxUserRequestObject requestObject) throws BoxRestException, BoxServerException,
        AuthFatalFailureException {
        UpdateUserLoginRequest request = new UpdateUserLoginRequest(getConfig(), getObjectMapper(), userId, requestObject);
        return (BoxUser) getResponseAndParseAndTryCast(request, BoxResourceType.USER, getObjectMapper());
    }

    /**
     * Get users from a collection.
     * 
     * @param collection
     *            collection
     * @return users
     */
    public static List<BoxUser> getUsers(BoxCollection collection) {
        List<BoxUser> users = new ArrayList<BoxUser>();
        List<BoxTypedObject> list = collection.getEntries();
        for (BoxTypedObject object : list) {
            if (object instanceof BoxUser) {
                users.add((BoxUser) object);
            }
        }
        return users;
    }

    /**
     * Get email aliases from a collection.
     * 
     * @param collection
     *            collection
     * @return email aliases
     */
    public static List<BoxEmailAlias> getEmailAliases(BoxCollection collection) {
        List<BoxEmailAlias> aliases = new ArrayList<BoxEmailAlias>();
        List<BoxTypedObject> list = collection.getEntries();
        for (BoxTypedObject object : list) {
            if (object instanceof BoxEmailAlias) {
                aliases.add((BoxEmailAlias) object);
            }
        }
        return aliases;
    }
}
