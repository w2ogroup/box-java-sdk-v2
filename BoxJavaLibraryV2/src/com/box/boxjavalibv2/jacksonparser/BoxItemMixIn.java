package com.box.boxjavalibv2.jacksonparser;

import com.box.boxjavalibv2.dao.BoxEvent;
import com.box.boxjavalibv2.dao.BoxFile;
import com.box.boxjavalibv2.dao.BoxFolder;
import com.box.boxjavalibv2.dao.BoxRealTimeServer;
import com.box.boxjavalibv2.dao.BoxWebLink;
import com.box.boxjavalibv2.interfaces.IJacksonMixIn;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * A jackson <a href="http://wiki.fasterxml.com/JacksonMixInAnnotations">MixIn</a> class to direct the parsing of BoxItem types into proper sub type.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({@Type(value = BoxFile.class, name = "file"), @Type(value = BoxFolder.class, name = "folder"),
               @Type(value = BoxWebLink.class, name = "web_link"), @Type(value = BoxEvent.class, name = "event"),
               @Type(value = BoxRealTimeServer.class, name = "realtime_server")})
public class BoxItemMixIn implements IJacksonMixIn {

}
