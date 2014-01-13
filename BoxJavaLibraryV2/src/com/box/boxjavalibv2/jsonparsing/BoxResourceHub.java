package com.box.boxjavalibv2.jsonparsing;

import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.box.boxjavalibv2.dao.BoxCollaboration;
import com.box.boxjavalibv2.dao.BoxCollection;
import com.box.boxjavalibv2.dao.BoxComment;
import com.box.boxjavalibv2.dao.BoxEmailAlias;
import com.box.boxjavalibv2.dao.BoxEvent;
import com.box.boxjavalibv2.dao.BoxEventCollection;
import com.box.boxjavalibv2.dao.BoxFile;
import com.box.boxjavalibv2.dao.BoxFileVersion;
import com.box.boxjavalibv2.dao.BoxFolder;
import com.box.boxjavalibv2.dao.BoxGroup;
import com.box.boxjavalibv2.dao.BoxItem;
import com.box.boxjavalibv2.dao.BoxLock;
import com.box.boxjavalibv2.dao.BoxOAuthToken;
import com.box.boxjavalibv2.dao.BoxPreview;
import com.box.boxjavalibv2.dao.BoxRealTimeServer;
import com.box.boxjavalibv2.dao.BoxResourceType;
import com.box.boxjavalibv2.dao.BoxServerError;
import com.box.boxjavalibv2.dao.BoxTypedObject;
import com.box.boxjavalibv2.dao.BoxUser;
import com.box.boxjavalibv2.dao.BoxWebLink;
import com.box.boxjavalibv2.interfaces.IBoxType;

public class BoxResourceHub extends BaseBoxResourceHub {

    // As a performance optimization, set up string values for all types.
    private static final Map<String, IBoxType> lowercaseStringToType = new HashMap<String, IBoxType>();

    public BoxResourceHub() {
        super();
    }

    @Override
    @SuppressWarnings("rawtypes")
    public Class getClass(IBoxType type) {
        if (getConcreteClassForIBoxType().equals(type.getClass())) {
            return getObjectClassGivenConcreteIBoxType(type);
        }
        else {
            return super.getClass(type);
        }
    }

    @Override
    public Collection<IBoxType> getAllTypes() {
        return lowercaseStringToType.values();
    }

    @Override
    protected Class getObjectClassGivenConcreteIBoxType(IBoxType type) {
        switch ((BoxResourceType) type) {
            case FILE:
                return BoxFile.class;
            case PREVIEW:
                return BoxPreview.class;
            case FOLDER:
                return BoxFolder.class;
            case WEB_LINK:
                return BoxWebLink.class;
            case USER:
                return BoxUser.class;
            case GROUP:
                return BoxGroup.class;
            case FILE_VERSION:
                return BoxFileVersion.class;
            case ITEM:
                return BoxItem.class;
            case COMMENT:
                return BoxComment.class;
            case COLLABORATION:
                return BoxCollaboration.class;
            case EMAIL_ALIAS:
                return BoxEmailAlias.class;
            case OAUTH_DATA:
                return BoxOAuthToken.class;
            case EVENT:
                return BoxEvent.class;
            case EVENTS:
                return BoxEventCollection.class;
            case REALTIME_SERVER:
                return BoxRealTimeServer.class;
            case LOCK:
                return BoxLock.class;
            case ERROR:
                return BoxServerError.class;
            case ITEMS:
            case FILES:
            case USERS:
            case COMMENTS:
            case FILE_VERSIONS:
            case COLLABORATIONS:
            case EMAIL_ALIASES:
                return BoxCollection.class;
            default:
                return BoxTypedObject.class;
        }
    }

    @SuppressWarnings("rawtypes")
    @Override
    protected Class getConcreteClassForIBoxType() {
        return BoxResourceType.class;
    }

    @Override
    public IBoxType getTypeFromLowercaseString(String type) {
        return lowercaseStringToType.get(type);
    }

    @Override
    protected void initializeTypes() {
        for (BoxResourceType type : BoxResourceType.values()) {
            String str = type.name().toLowerCase(Locale.ENGLISH);
            lowercaseStringToType.put(str, type);
        }
    }
}
