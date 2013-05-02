package com.box.boxjavalibv2.jacksonparser;

import com.box.boxjavalibv2.dao.BoxCollaboration;
import com.box.boxjavalibv2.dao.BoxComment;
import com.box.boxjavalibv2.dao.BoxEmailAlias;
import com.box.boxjavalibv2.dao.BoxEvent;
import com.box.boxjavalibv2.dao.BoxFile;
import com.box.boxjavalibv2.dao.BoxFileVersion;
import com.box.boxjavalibv2.dao.BoxFolder;
import com.box.boxjavalibv2.dao.BoxRealTimeServer;
import com.box.boxjavalibv2.dao.BoxServerError;
import com.box.boxjavalibv2.dao.BoxUser;
import com.box.boxjavalibv2.dao.BoxWebLink;
import com.box.boxjavalibv2.interfaces.IJacksonMixIn;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * A jackson <a href="http://wiki.fasterxml.com/JacksonMixInAnnotations">MixIn</a> class to direct the parsing of BoxTypedObject types into proper sub type.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({@Type(value = BoxFile.class, name = "file"), @Type(value = BoxFolder.class, name = "folder"),
               @Type(value = BoxWebLink.class, name = "web_link"), @Type(value = BoxCollaboration.class, name = "collaboration"),
               @Type(value = BoxComment.class, name = "comment"), @Type(value = BoxEmailAlias.class, name = "email_alias"),
               @Type(value = BoxFileVersion.class, name = "file_version"), @Type(value = BoxUser.class, name = "user"),
               @Type(value = BoxServerError.class, name = "error"), @Type(value = BoxEvent.class, name = "event"),
               @Type(value = BoxRealTimeServer.class, name = "realtime_server")})
public class BoxTypedObjectMixIn implements IJacksonMixIn {

}
