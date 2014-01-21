package com.box.boxjavalibv2.requests.requestobjects;

import com.box.boxjavalibv2.dao.BoxGroup;
import com.box.boxjavalibv2.dao.BoxGroupMembership;
import com.box.boxjavalibv2.dao.BoxUser;
import com.box.boxjavalibv2.jsonentities.MapJSONStringEntity;

public class BoxGroupRequestObject extends BoxDefaultRequestObject {

    public static BoxGroupRequestObject createGroupRequestObject(final String name) {
        return updateGroupRequestObject(name);
    }

    public static BoxGroupRequestObject updateGroupRequestObject(final String name) {
        BoxGroupRequestObject obj = new BoxGroupRequestObject();
        obj.put(BoxGroup.FIELD_NAME, name);
        return obj;
    }

    /**
     * 
     * @param groupId
     *            id of the group
     * @param userId
     *            id of the user to be added.
     * @param role
     *            role of the user.
     * @return
     */
    public static BoxGroupRequestObject addMembershipRequest(final String groupId, final String userId, final String role) {
        return (new BoxGroupRequestObject()).setGroup(groupId).setUser(userId).setRole(role);
    }

    public static BoxGroupRequestObject updateMembershipRequest(final String role) {
        return (new BoxGroupRequestObject()).setRole(role);
    }

    public BoxGroupRequestObject setUser(final String userId) {
        MapJSONStringEntity userEntity = new MapJSONStringEntity();
        userEntity.put(BoxUser.FIELD_ID, userId);
        put(BoxGroupMembership.FIELD_USER, userEntity);
        return this;
    }

    public BoxGroupRequestObject setGroup(final String groupId) {
        MapJSONStringEntity groupEntity = new MapJSONStringEntity();
        groupEntity.put(BoxGroup.FIELD_ID, groupId);
        put(BoxGroupMembership.FIELD_GROUP, groupEntity);
        return this;
    }

    /**
     * @param role
     *            role of the user.
     */
    public BoxGroupRequestObject setRole(final String role) {
        put(BoxGroupMembership.FIELD_ROLE, role);
        return this;
    }
}
